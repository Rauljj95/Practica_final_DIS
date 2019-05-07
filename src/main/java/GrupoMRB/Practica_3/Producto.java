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

	private String Nombre;
	private String Marca;
	private String EAN13;
	//Con el objetivo de facilitar la implementación de CRUD, creamos un ID del producto. 
	@SuppressWarnings("unused")
	private int ID;
	

	public Producto(String nombre, String marca, String eAN13) {
		super();
		Nombre = nombre;
		Marca = marca;
		EAN13 = eAN13;
	}
	
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public String getMarca() {
		return Marca;
	}
	public void setMarca(String marca) {
		Marca = marca;
	}
	public String getEAN13() {
		return EAN13;
	}
	public void setEAN13(String eAN13) {
		EAN13 = eAN13;
	}
	
	public String MostrarDatos()
	{
		String datos = "Nombre: " + this.Nombre + " Marca: " + this.Marca + " EAN13: " + EAN13.toString();
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
			MatrixToImageWriter.writeToPath(ew1.encode(EAN13, BarcodeFormat.EAN_13, 1920, 1080), "png", ruta);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}
	
}
