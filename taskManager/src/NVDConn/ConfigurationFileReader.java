package NVDConn;

import java.io.FileReader;
import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * Esta clase se encarga de leer el archivo con los parametros de configuracion
 * @author: mikel.hernandez
 * @version: 09/11/2018
 */
public class ConfigurationFileReader {
	JSONParser jsonParser;
	JSONObject jsonObject;
	FileReader fileReader;
	JSONObject source;
	JSONObject operParams;

	/**
     * Constructor de la clase
     */
	public ConfigurationFileReader() {
		jsonParser = new JSONParser();
		try {
			fileReader = new FileReader("configurationFile.json");
			jsonObject = (JSONObject) jsonParser.parse(fileReader);
			source = (JSONObject) jsonObject.get("client");
			source = (JSONObject) source.get("source");
			operParams = (JSONObject) jsonObject.get("operationalParameters");
			fileReader.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("No se ha podido leer el fichero de configuración --> Excepcion " + e.getMessage());
		}
	}
	
	/**
     * Método que devuelve los parametros de configuración desde el fichero de configuración
     * @return parametros de configuración
     */
	public ArrayList<String> getConfigurationParameters() {
		ArrayList<String> parameters = new ArrayList<>();
		parameters.add((String) source.get("name"));
		parameters.add((String) source.get("resource"));
		parameters.add((String) source.get("outputFile"));
		parameters.add((String) source.get("destinationFolder"));
		parameters.add((String) source.get("logFile"));
		parameters.add((String) source.get("logFolder"));
		parameters.add((String) source.get("vulnerabilitySearchFrequency"));
		parameters.add((String) operParams.get("reScanTime"));
		parameters.add((String) operParams.get("Status"));
		return parameters;
	}
}
