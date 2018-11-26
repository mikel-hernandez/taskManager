package taskManager;

import java.util.Date;

/**
 * Clase que se encarga de actualizar los tiempos en los que se van a ejecutar las siguientes tareas.
 * @author mikel.hernandez
 * @version 22/11/2018
 */
public class ScheduleUpdater {
	Date currentTime, t1NextTime;
	long t1Period;
	
	/**
	 * Constructor de la clase
	 * @param t1Period Periodo de ejecución inicial de la tarea 1
	 */
	public ScheduleUpdater(long t1Period) {
		this.currentTime = new Date();
		this.t1Period = t1Period;
		this.t1NextTime = new Date(currentTime.getTime() + t1Period);
	}
	
	/**
	 * Método que actualiza los proximos tiempos de ejecución de las tareas
	 */
	public void update() {
		this.currentTime = new Date();
		updateTask1NextTime();
	}

	/**
	 * Método que actualiza el proximo tiempo de ejecución de la tarea 1
	 */
	public void updateTask1NextTime() {
		long previousTime = t1NextTime.getTime();
		this.t1NextTime = new Date(previousTime + t1Period);
	}
	
	/**
	 * Método que devuelve el próximo tiempo de ejecución de la tarea 1
	 * @return Próximo tiempo de ejecución de la tarea 1
	 */
	public Date getT1NextTime() {
		return t1NextTime;
	}

	/**
	 * Método que cambia el valor del periodo de la tarea 1
	 * @param t1Period Nuevo periodo de la tarea 1
	 */
	public void setT1Period(long t1Period) {
		this.t1Period = t1Period;
	}
}
