package NVDConn;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;

import filesManagement.FileDownloader;
import interfaces.Connector;
import logger.Logger;
import timerTasks.DownloadTimerTask;

/**
 * Clase que hace de conector a la NVD (National Vulnerability Database)
 * Clase Padre: interfaces.Connector
 * @author mikel.hernandez
 * @version 22/11/2018
 */
public class NVDConnector implements Connector {
	static final String ZIP_FILENAME = "nvdcve-1.0-modified.json.zip"; 
	Configuration conf; //Objeto con los parametros de configuración
	FileDownloader fileDownloader; //Objeto para descargar y descomprimir el archivo
	Path outputFilePath; //Ruta donde se va a guardar el archivo
	String outputFile; //Nombre del archivo que se va a descargar
	Timer downloadTimer; //Timer para programar la tarea de descargar
	boolean downloadFile; //Booleano que  define el momento de descargar el archivo
	long downloadNextTime; //Tiempo en el que se tiene que realizar la proxima descarga del fichero
	
	/**
	 * Constructor de la clase
	 */
	public NVDConnector() {
		fileDownloader = new FileDownloader();
		downloadTimer = new Timer();
		downloadNextTime = new Date().getTime();
	}
	
	/**
	 * Método que se encarga de obtener el archivo de la NVD si es el momento de hacerlo
	 */
	public void run() {
		// TODO Auto-generated method stub
		if ((conf.getStatus().equals("run")) && downloadFile) {
			getFile();
			decompress();
			saveFile();
			Logger.notifyTask("File downloaded in: " + outputFilePath.toString());
			downloadFile = false;
		}
	}
	
	/**
	 * Método que se encarga de cargar los parametros de configuración desde el archivo de configuración
	 */
	public void loadConfiguration() {
		ConfigurationFileReader confFileReader = new ConfigurationFileReader();
		ArrayList<String> parameters = confFileReader.getConfigurationParameters();
		conf = new Configuration(parameters);
		createOutputFilePath();
		scheduleDownloadTask();
		Logger.logConfigurationFile(conf);
	}
	
	/**
	 * Método que define la ruta completa del archivo que se va a descargar, en caso de que la carpeta no exista, la crea
	 */
	public void createOutputFilePath() {
		Path folder = Paths.get(conf.getDestFolder()).toAbsolutePath();
		if (!Files.exists(folder)) {
			new File(folder.toString()).mkdirs();
		}
		outputFilePath = Paths.get(conf.getDestFolder(), conf.getOutputFile()).toAbsolutePath();
		outputFile = outputFilePath.toString();
	}
	
	/**
	 * Método que programa la tarea de descargar el archivo en el tiempo que le corresponda
	 */
	public void scheduleDownloadTask() {
		DownloadTimerTask task = new DownloadTimerTask(this);
		downloadTimer.schedule(task, new Date(downloadNextTime));
		downloadNextTime = downloadNextTime + conf.getVulnerabilitySearchFrequency()*60*1000;
	}
	
	/**
     * Método que llama al método para obtener el fichero mediante una URL
     */
	public void getFile() {
		try {
			URL url = new URL(conf.getResource());
			fileDownloader.getFileFromURL(url,ZIP_FILENAME);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("No se ha podido descargar el archivo .json --> Excepcion " + e.getMessage());
		}
	}
	
	/**
     * Método que llama al método para descomprimir el fichero y borrar el fichero que esta comprimido
     */
	public void decompress() {
		String path = conf.getOutputFile();
		fileDownloader.unzipDownloadedFile(ZIP_FILENAME,path);
		File file = new File(ZIP_FILENAME);
		if (file.exists()) file.delete();
	}
	
	/**
     * Método que se encarga de mover el fichero descomprimido a la carpeta de destino que se se muestra en el fichero de configuración
     */
	public void saveFile() {
		File file = new File(outputFile);
		if (file.exists()) file.delete();
		try {
			Files.move(Paths.get(conf.getOutputFile()), outputFilePath);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("No se ha podido guardar el archivo --> Excepcion " + e.getMessage());
		}
	}
	
	/**
	 * Método que devuelve el periodo con el que se tiene que leer el archivo de configuración
	 * @return Periodo con el que se tiene que leer el archivo de configuración
	 */
	public int getRescanPeriod() {
		return this.conf.getReScanTime();
	}

	/**
	 * Método que cambia el valor de la variable downloadFile
	 * @param downloadFile Booleano (true o false) con el valor que se le quiere dar a la variable downloadFile
	 */
	public void setDownloadFile(boolean downloadFile) {
		this.downloadFile = downloadFile;
	}
}
