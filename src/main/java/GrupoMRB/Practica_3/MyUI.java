package GrupoMRB.Practica_3;

import java.nio.file.FileSystems;
import java.nio.file.Path;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.Column;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.renderers.ImageRenderer;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of an HTML page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        
    	Productos productos = new Productos();
    	
    	
    	Grid<Producto> grid_productos = new Grid<>(Producto.class);
        grid_productos.setItems(productos.getProductos());
        grid_productos.addColumn(
                p -> new ThemeResource("ean/" + p.getNombre() + ".png"),
                        new ImageRenderer<Producto>()).setCaption("Image");        grid_productos.setColumns("nombre", "marca", "ean13", "Imagen");
    	
    	VerticalLayout layout = new VerticalLayout();
	    	Panel panel = new Panel("Añadir producto");
	    		
	    		FormLayout formulario_producto = new FormLayout();
		    	
			        final TextField nombre = new TextField();
			        nombre.setCaption("Nombre del producto:");
			
			        final TextField marca = new TextField();
			        marca.setCaption("Marca:");
			        
			        final TextField ean = new TextField();
			        ean.setCaption("EAN13:");
			        Button button = new Button("Click Me!!");
			        button.addClickListener(e -> {
			            Producto producto = new Producto(nombre.getValue(), marca.getValue(), ean.getValue());
			            Path path = FileSystems.getDefault().getPath("src/main/webapp/VAADIN/themes/mytheme/ean" + producto.getNombre() + ".png");
			            producto.GuardarImagenCodigoBarras(path);
			            productos.CrearElemento(producto);
			            grid_productos.setItems(productos.getProductos());
			        });
		        
;			        formulario_producto.addComponents(nombre, marca, ean, button);
			        formulario_producto.setSizeUndefined(); // Shrink to fit
			        formulario_producto.setMargin(true);
			        panel.setContent(formulario_producto);
			        
			        
			  Panel panel2 = new Panel("mostrar productos");
			  
			  VerticalLayout layout_productos = new VerticalLayout();
			  
			  layout_productos.addComponents(grid_productos);
		        layout_productos.setSizeUndefined(); // Shrink to fit
		        layout_productos.setMargin(true);
		        
		        panel.setContent(layout_productos);
    
    	layout.addComponents(panel, panel2);
    	setContent(layout);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
