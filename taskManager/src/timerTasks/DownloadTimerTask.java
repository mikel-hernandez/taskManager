package timerTasks;

import java.util.TimerTask;

import NVDConn.NVDConnector;

/**
 * Clase que se encarga de ejecutar la tarea de descargar el fichero de la NVD
 * Clase Padre: java.util.TimerTask
 * @author mikel.hernandez
 * @version 22/11/2018
 */
public class DownloadTimerTask extends TimerTask {
	NVDConnector nvd;
	
	/**
	 * Constructor de la clase
	 * @param nvdConn Clase que hace de conector a la NVD
	 */
	public DownloadTimerTask(NVDConnector nvdConn) {
		this.nvd = nvdConn;
	}
	
	/**
	 * Método run de la tarea, se encarga de descargar el fichero de la NVD
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
		nvd.setDownloadFile(true);
		nvd.run();
	}

}
