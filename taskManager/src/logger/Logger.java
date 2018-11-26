package logger;

import java.sql.Timestamp;
import java.util.Date;

import NVDConn.Configuration;

/**
 * Clase que se encarga de mostrar en pantalla el estado de las tareas que se han realizado
 * @author mikel.hernandez
 * @version 21/11/2018
 */
public class Logger {
	static Timestamp timestamp;
	
	/**
	 * Método que muestra en pantalla a que hora se ha leido el archivo de configuración y los datos que se han leido
	 * @param conf Objeto de la clase que contiene los parametros de configuración
	 */
	public static void logConfigurationFile(Configuration conf) {
		timestamp = new Timestamp(new Date().getTime());
		System.out.println(timestamp + " --> Configuration file read");
		System.out.println("\t client: {");
		System.out.println("\t \t source: {");
		System.out.println("\t \t \t name : " + conf.getName());
		System.out.println("\t \t \t resource : " + conf.getResource());
		System.out.println("\t \t \t outputFile : " + conf.getOutputFile());
		System.out.println("\t \t \t destinationFolder : " + conf.getDestFolder());
		System.out.println("\t \t \t logFile : " + conf.getLogFile());
		System.out.println("\t \t \t logFolder : " + conf.getLogFolder());
		System.out.println("\t \t \t vulnerabilitySearchFrequency : " + conf.getVulnerabilitySearchFrequency());
		System.out.println("\t \t }");
		System.out.println("\t }");
		System.out.println("\t operationalParameters : {");
		System.out.println("\t \t reScanTime : " + conf.getReScanTime());
		System.out.println("\t \t status : " + conf.getStatus());
		System.out.println("\t }");
		System.out.println();
	}
	
	/**
	 * Método que notifica la tarea que se ha realizado y a que hora se ha realizado
	 * @param task Definición de la tarea que se ha realizado
	 */
	public static void notifyTask(String task) {
		timestamp = new Timestamp(new Date().getTime());
		System.out.println(timestamp + " --> " + task);
	}
}
