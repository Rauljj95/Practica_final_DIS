/**
 * 
 */
package GrupoMRB.Practica_3;

/**
 * @author Grupo MBR
 *
 */
import java.io.IOException;

import java.nio.file.Path;

import com.google.zxing.*;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.oned.EAN13Writer;
import com.google.gson.Gson;

public class Producto {

	private String nombre;
	private String marca;
	private String ean13;
	//Con el objetivo de facilitar la implementación de CRUD, creamos un ID del producto. 
	@SuppressWarnings("unused")
	private int ID;
	

	public Producto(String Nombre, String Marca, String eAN13) {
		super();
		nombre = Nombre;
		marca = Marca;
		ean13 = eAN13;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String Nombre) {
		nombre = Nombre;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String Marca) {
		marca = Marca;
	}
	public String getEAN13() {
		return ean13;
	}
	public void setEAN13(String eAN13) {
		ean13 = eAN13;
	}
	
	public String MostrarDatos()
	{
		String datos = "Nombre: " + this.nombre + " Marca: " + this.marca + " EAN13: " + ean13.toString();
		return datos;
	}
	
	public String ToJson()
	{
		Gson json = new Gson();
		String productoString = json.toJson(this).toString();
		return productoString;
	}
	
	public void GuardarImagenCodigoBarras(Path ruta)
	{
		
		EAN13Writer ew1 = new EAN13Writer();
		try {
			MatrixToImageWriter.writeToPath(ew1.encode(ean13, BarcodeFormat.EAN_13, 1920, 1080), "png", ruta);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}
	
	
	
}
