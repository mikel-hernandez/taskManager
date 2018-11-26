package timerTasks;


import java.util.TimerTask;

import NVDConn.NVDConnector;

/**
 * Clase que se encarga de ejecutar la tarea de conexión a la NVD (National Vulnerability Database)
 * Clase Padre: java.util.TimerTask
 * @author mikel.hernandez
 * @version 22/11/2018
 */
public class NVDTask extends TimerTask {
	NVDConnector nvdConn;
	
	/**
	 * Constructor de la clase
	 * @param nvdConn Clase que hace de conector a la NVD
	 */
	public NVDTask(NVDConnector nvdConn) {
		this.nvdConn = nvdConn;
	}
	
	/**
	 * Método run de la tarea, se encarga de cargar los parametros de configuración para la conexión a la NVD
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
		nvdConn.loadConfiguration();
	}
}
