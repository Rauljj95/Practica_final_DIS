package GrupoMRB.Practica_3;

import com.google.gson.Gson;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import com.google.gson.Gson;

public class Productos {

	private List <Producto> Productos; 
	
	//Constructor de la clase Productos
	public Productos() {
		super();
		Productos = new ArrayList<Producto>();
	}

	//Constructor getter y setter de la clase Productos
	public List<Producto> getProductos() {
		return Productos;
	}

	public void setProductos(List<Producto> productos) {
		Productos = productos;
	}
	
	//Con el objetivo de realizar el CRUD (Create, Read, Update y Delete)
	
	//Función create 
	public void CrearElemento(Producto productoNuevo) 
	{
		//Comprobamos si previamente existe el elemento en nuestro conjunto
		if(ExisteElemento(productoNuevo.getEAN13()) == true)
		{
			System.out.println("\nEl elemento ya existe en el conjunto\n");
		}
		else 
			Productos.add(productoNuevo); 
			System.out.println("\nProducto añadido satisfactoriamente\n"); 
		return; 
	}
	
	//Leemos el producto por el código EAN13.
	public Producto LeerProducto(String EAN13) {
		if (ExisteElemento(EAN13) == true)
		{
			for (Producto producto : Productos)
			{
				if (producto.getEAN13() == EAN13)
					return producto; 
			}
		}	
		else System.out.println("\nNo existe el elemento buscado en el conjunto\n");
			return null;
	}
	
	//Función para comprobar si el elemento buscado existe en nuestra lista
	public Boolean ExisteElemento (String EAN13)
	{
		for (Producto producto : Productos)
		{
			if (producto.getEAN13() == EAN13)
				return true; 
		}
		return false;
	}

	//Métodos para actualizar la información del producto
	
	public void ActualizarNombreElemento(String EAN13, String nombreNuevo) {
		//Para actualizar, es necesario primero buscar el elemento a modificar y después, cambiar la información deseada.
		Producto producto = LeerProducto(EAN13);
		if (producto == null)
		{
			System.out.println("\nActualización no realizada. El producto no existe\n"); 
		}
		else 
		{
			producto.setNombre(nombreNuevo);
			System.out.println("\nActualización realizada. El producto ha cambiado de nombre\n"); 
		}
	return;	
	}
	
	public void ActualizarEAN13Elemento(String EAN13Antiguo, String EAN13Nuevo) {
		//Para actualizar, es necesario primero buscar el elemento a modificar y después, cambiar la información deseada.
		Producto producto = LeerProducto(EAN13Antiguo);
		if (producto == null)
		{
			System.out.println("\nActualización no realizada. El producto no existe\n"); 
		}
		else 
		{
			producto.setEAN13(EAN13Nuevo);
			System.out.println("\nActualización realizada. El producto ha cambiado de código EAN13\n"); 
		}
		return;
	}
	
	public void ActualizarMarcaElemento(String EAN13, String marcaNueva) {
		//Para actualizar, es necesario primero buscar el elemento a modificar y después, cambiar la información deseada.
		Producto producto = LeerProducto(EAN13);
		if (producto == null)
		{
			System.out.println("\nActualización no realizada. El producto no existe\n"); 
		}
		else 
		{
			producto.setMarca(marcaNueva);
			System.out.println("\nActualización realizada. El producto ha cambiado de marca\n"); 
		}
		return ;
	}
	
	//Función para borrar un elemento de mi conjunto de productos
	public void BorrarElemento(String EAN13) {
		Producto producto = LeerProducto(EAN13);
		if (producto == null)
		{
			System.out.println("\nEl producto no existe\n"); 
		}
		else 
			Productos.remove(producto); 
		return;
	}
	
	//Función para convertir a Json gracias a Gson y guardarlos en la ruta deseada
	public String ToJson(String ruta) throws IOException {
	    Gson json = new Gson();
		String productoString = json.toJson(Productos).toString();
		System.out.println(productoString);
		
		//Ahora guardamos el archivo que acabamos de convertirlo a la ruta deseada.
		FileWriter file = new FileWriter(ruta);
		file.write(productoString); 
		file.close();
		
		return productoString; 
	}
	
	//Creamos la función para leer el texto json desde el fichero deseado. 
	public void LeerFichero(String ruta) throws IOException 
	{
		Files.readAllBytes(Paths.get(ruta));
		//String productoJson = String(encoded, Charset.defaultCharset());
		List<java.lang.String> productoJson = Files.readAllLines(Paths.get(ruta), Charset.defaultCharset());

		Gson gson = new Gson();

		System.out.println(productoJson);
		//this.Productos  = gson.fromJson(productoJson, this.Productos.getClass());
		
	}

	@SuppressWarnings("unused")
	private String String(byte[] encoded, Charset defaultCharset) {
		// TODO Auto-generated method stub
		return null;
	}
}
