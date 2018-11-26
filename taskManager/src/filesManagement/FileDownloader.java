package filesManagement;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Esta clase se encarga de descargar un fichero de la web y descomprimirlo
 * @author: mikel.hernandez
 * @version: 09/11/2018
 */
public class FileDownloader {
	
	/**
     * Método que descarga un fichero desde la web
     * @param url URL desde la cual se va a descargar el fichero
     * @param outputFile nombre que se le va a dar el fichero descargado
     */
	public void getFileFromURL(URL url, String outputFile) {
		BufferedInputStream in;
		FileOutputStream fileOutputStream;
		byte dataBuffer[] = new byte[1024];
	    int bytesRead;
		try {
			in = new BufferedInputStream(url.openStream());
			fileOutputStream = new FileOutputStream(outputFile);
			while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
				fileOutputStream.write(dataBuffer, 0, bytesRead);
			}
			fileOutputStream.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("No se ha podido descargar el archivo .json --> Excepcion " + e.getMessage());
		}
	}
	
	/**
     * Método que descomprime el archivo descargado
     * @param inputFile directorio del fichero que se va a descomprimir
     * @param newFilePath directorio donde se va a descomprimir el fichero
     */
	public void unzipDownloadedFile(String inputFile, String newFilePath) {
		FileInputStream fis;
		ZipInputStream zip;
		ZipEntry zipEntry;
		FileOutputStream fos;
		int len;
		byte[] buffer = new byte[1024];
		try {
			fis = new FileInputStream(inputFile);
			zip = new ZipInputStream(fis);
			zipEntry = zip.getNextEntry();
			while (zipEntry != null) {
				File file = new File(newFilePath);
				fos = new FileOutputStream(file);
				len = zip.read(buffer);
				while (len > 0) {
					fos.write(buffer, 0, len);
					len = zip.read(buffer);
				}
				fos.close();
				zip.closeEntry();
				zipEntry = zip.getNextEntry();
			}
			zip.closeEntry();
			zip.close();
			fis.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("No se ha podido descomprimir el archivo --> Excepcion " + e.getMessage());
		}
	}
}
