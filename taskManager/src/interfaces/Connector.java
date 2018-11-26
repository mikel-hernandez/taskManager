package interfaces;

/**
 * Interfaz que define los m�todos de los conectores a las fuentes de seguridad
 * @author mikel.hernandez
 * @version 22/11/2018
 */
public interface Connector {
	
	/**
	 * M�todo que hace la conexi�n con la fuente de seguridad
	 */
	public abstract void run();
	
	/**
	 * M�todo que carga los parametros de configuraci�n de la fuente de seguridad
	 */
	public abstract void loadConfiguration();
	
	/**
	 * M�todo que consigue el archivo de la fuente de seguridad
	 */
	public abstract void getFile();
	
	/**
	 * M�todo que guarda localmente el archivo de la fuente de seguridad
	 */
	public abstract void saveFile();

}
