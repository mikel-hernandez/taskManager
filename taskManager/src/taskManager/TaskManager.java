package taskManager;

import java.util.Timer;

import NVDConn.NVDConnector;
import timerTasks.NVDTask;

/**
 * Clase que se encarga de gestionar en que momento se va a realizar cada tarea
 * @author mikel.hernandez
 * @version 22/11/2018
 */
public class TaskManager {
	Timer nvdTimer;
	ScheduleUpdater scheduler;
	NVDConnector nvdConn;
	
	/**
	 * Constructor de la clase
	 * @param t1Period Periodo de ejecuci�n inicial de la tarea 1
	 */
	public TaskManager(long t1Period) {
		this.nvdTimer = new Timer();
		this.scheduler = new ScheduleUpdater(t1Period);
		this.nvdConn = new NVDConnector();
	}
	
	/**
	 * M�todo que se encarga de gestionar las tareas.
	 * Primero programa la siguiente ejecuci�n de cada tarea y despu�s actualiza los siguientes tiempos de ejecuci�n de cada tarea.
	 */
	public void manage() {
		scheduleNVDTask();
		scheduler.update();
	}
	
	/**
	 * M�todo que se encarga de programar la siguiente tarea correspondiente a la conexi�n con la NVD.
	 */
	public void scheduleNVDTask() {
		NVDTask nvdTask = new NVDTask(nvdConn);
		nvdTimer.schedule(nvdTask, scheduler.getT1NextTime());
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		scheduler.setT1Period(nvdConn.getRescanPeriod()*60*1000);
	}
}
