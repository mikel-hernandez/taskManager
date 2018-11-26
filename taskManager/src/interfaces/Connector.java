package interfaces;

/**
 * Interfaz que define los métodos de los conectores a las fuentes de seguridad
 * @author mikel.hernandez
 * @version 22/11/2018
 */
public interface Connector {
	
	/**
	 * Método que hace la conexión con la fuente de seguridad
	 */
	public abstract void run();
	
	/**
	 * Método que carga los parametros de configuración de la fuente de seguridad
	 */
	public abstract void loadConfiguration();
	
	/**
	 * Método que consigue el archivo de la fuente de seguridad
	 */
	public abstract void getFile();
	
	/**
	 * Método que guarda localmente el archivo de la fuente de seguridad
	 */
	public abstract void saveFile();

}
