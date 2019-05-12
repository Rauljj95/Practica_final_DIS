package GrupoMRB.Practica_3;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.junit.jupiter.api.Test;

class TestProductos {

	@Test
	void testCrearElemento() {
		
		
		Producto nuevoProducto = new Producto("Nombre prueba 1", "Marca prueba 1", "123456789012");
		Productos Productos = new Productos();
		Productos.CrearElemento(nuevoProducto);
		Boolean creado = Productos.getProductos().contains(nuevoProducto);
		Boolean esperado = true;
		assertEquals(creado, esperado);
		
		nuevoProducto = new Producto("Nombre prueba 2", "Marca prueba 1", "123456789012");
		Productos.CrearElemento(nuevoProducto);
		creado = Productos.getProductos().contains(nuevoProducto);
		esperado = false;
		assertEquals(creado, esperado);
	}

	@Test
	void testLeerProducto() {
		
		
		Producto nuevoProducto = new Producto("Nombre prueba 1", "Marca prueba 1", "123456789012");
		Productos Productos = new Productos();
		Productos.CrearElemento(nuevoProducto);
		
		Producto productoPrueba = Productos.LeerProducto("123456789012");
		assertEquals(nuevoProducto, productoPrueba);
		
		productoPrueba = Productos.LeerProducto("123456789022");
		assertEquals(null, productoPrueba);
	}
	
	@Test
	void testExisteElemento() {
		
		Producto nuevoProducto = new Producto("Nombre prueba 1", "Marca prueba 1", "123456789012");
		Productos Productos = new Productos();
		Productos.CrearElemento(nuevoProducto);
		
		Boolean leido = Productos.ExisteElemento(nuevoProducto.getEAN13());
		Boolean esperado = true;
		assertEquals(leido, esperado);
		
		leido = Productos.ExisteElemento("123456789010");
		esperado = false;
		assertEquals(leido, esperado);
	}
	
	@Test
	void testActualizarNombreElemento() {
		
		Producto nuevoProducto = new Producto("Nombre prueba 1", "Marca prueba 1", "123456789012");
		Productos Productos = new Productos();
		Productos.CrearElemento(nuevoProducto);
		
		String nuevoNombre = "Prueba cambio";
		Productos.ActualizarNombreElemento(nuevoProducto.getEAN13(), nuevoNombre);
		assertEquals(nuevoNombre, nuevoProducto.getNombre());
		
		nuevoNombre = "Prueba cambio 2";
		Productos.ActualizarNombreElemento("1254521563252", nuevoNombre);
		assertNotEquals(nuevoNombre, nuevoProducto.getNombre());
	}

	@Test
	void testActualizarEAN13Elemento() {
		
		Producto nuevoProducto = new Producto("Nombre prueba 1", "Marca prueba 1", "123456789012");
		Productos Productos = new Productos();
		Productos.CrearElemento(nuevoProducto);
		
		String nuevoEAN13 = "987654321123";
		Productos.ActualizarEAN13Elemento(nuevoProducto.getEAN13(), nuevoEAN13);
		assertEquals(nuevoEAN13, nuevoProducto.getEAN13());
		
		nuevoEAN13 = "987654321124";
		Productos.ActualizarEAN13Elemento("987654321128", nuevoEAN13);
		assertNotEquals(nuevoEAN13, nuevoProducto.getEAN13());
	}

	
	@Test
	void testActualizarMarcaElemento() {
		Producto nuevoProducto = new Producto("Nombre prueba 1", "Marca prueba 1", "123456789012");
		Productos Productos = new Productos();
		Productos.CrearElemento(nuevoProducto);
		
		String nuevaMarca = "Prueba cambio";
		Productos.ActualizarMarcaElemento(nuevoProducto.getEAN13(), nuevaMarca);
		assertEquals(nuevaMarca, nuevoProducto.getMarca());
		
		nuevaMarca = "Prueba cambio 2";
		Productos.ActualizarMarcaElemento("1254521563252", nuevaMarca);
		assertNotEquals(nuevaMarca, nuevoProducto.getMarca());
	}
	
	@Test
	void testBorrarElemento() {
		Producto nuevoProducto = new Producto("Nombre prueba 1", "Marca prueba 1", "123456789012");
		Productos Productos = new Productos();
		Productos.CrearElemento(nuevoProducto);
		
		Productos.BorrarElemento("123456789812");
		Boolean obtenido = Productos.ExisteElemento(nuevoProducto.getEAN13());
		Boolean esperado = true;
		
		assertEquals(obtenido, esperado);
		
		Productos.BorrarElemento(nuevoProducto.getEAN13());
		
		obtenido = Productos.ExisteElemento(nuevoProducto.getEAN13());
		 esperado = false;
		
		assertEquals(obtenido, esperado);
		
	}

	@Test
	<File> void testToJson() throws IOException {
		
		
		Producto nuevoProducto = new Producto("Nombre prueba 1", "Marca prueba 1", "123456789012");
		Productos Productos = new Productos();
		Productos.CrearElemento(nuevoProducto);
        Productos.ToJson("prueba.json");
        BufferedReader br = new BufferedReader(new FileReader("prueba.json"));  
        assertNotEquals(br.readLine(), null);
           
	}

}
