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
	 * @param t1Period Periodo de ejecución inicial de la tarea 1
	 */
	public TaskManager(long t1Period) {
		this.nvdTimer = new Timer();
		this.scheduler = new ScheduleUpdater(t1Period);
		this.nvdConn = new NVDConnector();
	}
	
	/**
	 * Método que se encarga de gestionar las tareas.
	 * Primero programa la siguiente ejecución de cada tarea y después actualiza los siguientes tiempos de ejecución de cada tarea.
	 */
	public void manage() {
		scheduleNVDTask();
		scheduler.update();
	}
	
	/**
	 * Método que se encarga de programar la siguiente tarea correspondiente a la conexión con la NVD.
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
