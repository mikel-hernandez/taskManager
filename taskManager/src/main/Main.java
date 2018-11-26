package main;

import taskManager.TaskManager;

/**
 * Clase principal del programa.
 * Clase Padre: java.lang.Thread
 * @author mikel.hernandez
 * @version 22/11/2018
 */
public class Main extends Thread {
	TaskManager tManager;

	/**
	 * Constructor de la clase
	 * @param t1Period Periodo de ejecuci�n inicial de la tarea 1
	 */
	public Main(long t1Period) {
		super();
		tManager = new TaskManager(t1Period);
	}
	
	/**
     * M�todo principal del programa
     * @param args
     */
	public static void main(String[] args) {
		Main m = new Main(0); 
		m.start(); 
	}
	
	/**
     * M�todo run del Thread de la clase, este m�todo se ejecuta cada vez que se llama al m�todo start de esta misma clase
     */
	@Override
	public synchronized void run() {
		while (true) {
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				System.out.println("Excepcion --> " + e.getMessage());
			}
			tManager.manage();
		}
	}
}
