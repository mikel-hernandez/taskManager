package NVDConn;

import java.util.ArrayList;

/**
 * Esta clase contiene los parametros de la fuente que se va a consultar y los parametros operacionales de configuración
 * @author: mikel.hernandez
 * @version: 09/11/2018
 */
public class Configuration {
	String name; //Nombre de la fuente
	String resource; //URL desde la que se va a descargar el archivo
	String outputFile; //Nombre del archivo que se va a guardar
	String destFolder; //Ruta en la que se va a guardar el archivo
	String logFile; //Nombre del archivo log
	String logFolder; //Ruta en la que esta el archivo log
	int vulnerabilitySearchFrequency; //Frecuencia con la que se quiere descargar el archivo
	int reScanTime; //Frecuencia con la que se quiere leer el fichero de configuración
	String status; //Estado en el que se encuentra la busqueda
	
	/**
     * Constructor de la clase
     * @param parameters Una lista con los parametros leidos del archivo de configuración
     */
	public Configuration(ArrayList<String> parameters) {
		this.name = parameters.get(0);
		this.resource = parameters.get(1);
		this.outputFile = parameters.get(2);
		this.destFolder = parameters.get(3);
		this.logFile = parameters.get(4);
		this.logFolder = parameters.get(5);
		this.vulnerabilitySearchFrequency = Integer.parseInt(parameters.get(6));
		this.reScanTime = Integer.parseInt(parameters.get(7));
		this.status = parameters.get(8);
	}

	/**
     * Método que devuelve el nombre de la fuente
     * @return nombre de la fuente
     */
	public String getName() {
		return name;
	}
	
	/**
     * Método que devuelve la URL desde la que se va a descargar el archivo
     * @return URL desde la que se va a descargar el archivo
     */
	public String getResource() {
		return resource;
	}

	/**
     * Método que devuelve el nombre del archivo que se ha guardado
     * @return nombre del archivo que se ha guardado
     */
	public String getOutputFile() {
		return outputFile;
	}

	/**
     * Método que devuelve la ruta en la que se ha guardado el archivo
     * @return ruta en la que se ha guardado el archivo
     */
	public String getDestFolder() {
		return destFolder;
	}

	/**
     * Método que devuelve el nombre del archivo log
     * @return nombre del archivo log
     */
	public String getLogFile() {
		return logFile;
	}

	/**
     * Método que devuelve la ruta en la que esta el archivo log
     * @return ruta en la que esta el archivo log
     */
	public String getLogFolder() {
		return logFolder;
	}

	/**
     * Método que devuelve la frecuencia con la que se quiere descargar el archivo
     * @return frecuencia con la que se quiere descargar el archivo
     */
	public int getVulnerabilitySearchFrequency() {
		return vulnerabilitySearchFrequency;
	}
	
	/**
     * Método que devuelve la frecuencia con la que se quiere leer el fichero de configuración
     * @return frecuencia con la que se quiere leer el fichero de configuración
     */
	public int getReScanTime() {
		return reScanTime;
	}

	/**
     * Método que devuelve el estado en el que se encuentra la busqueda
     * @return estado en el que se encuentra la busqueda
     */
	public String getStatus() {
		return status;
	}
}
