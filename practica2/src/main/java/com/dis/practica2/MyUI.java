package com.dis.practica2;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import com.dis.practica2.*;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of an HTML page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {

	private Usuario usuarioSeleccionado;
	
    @Override
    protected void init(VaadinRequest vaadinRequest) {
    	
		// Cargamos los usuarios que se encuentran de forma predeterminada
		Predeterminado usuariosPre = new Predeterminado();
		usuariosPre.Predeterminado();
    	
    	//añado la lista
    	ListaUsuarios lista = new ListaUsuarios();
    	
    	// LAYOUT NECESARIAS
    	Label labelTitulo = new Label("Datos de la agenda: ");
    	labelTitulo.setWidth("600px");
    	
    	//layout vertical de recogida de datos
    	final VerticalLayout vlayout = new VerticalLayout();
    	
    	//layout vertical de mostrar datos
        final VerticalLayout vlayout2 = new VerticalLayout();
        
        //layout horizontal que engloba a los dos verticales
        final HorizontalLayout hlayout = new HorizontalLayout();
        
        //Textfield del formulario
        final TextField nombre = new TextField();
        nombre.setCaption("Nombre: *");
        nombre.setPlaceholder("Ej: Luis");
        
        final TextField apellidos = new TextField();
        apellidos.setCaption("Apellidos: ");
        apellidos.setPlaceholder("Ej: Peréz");
        
        final TextField empresa = new TextField();
        empresa.setCaption("Empresa: ");
        empresa.setPlaceholder("Ej: BABEL");
        
        final TextField telefono = new TextField();
        telefono.setCaption("Telefono: *");
        telefono.setPlaceholder("Ej: +34 685928591");
        
        final TextField email = new TextField();
        email.setCaption("Email: ");
        email.setPlaceholder("Ej: luisperez@gmail.com");
        
        final TextField direccion = new TextField();
        direccion.setCaption("Direccion: ");
        direccion.setPlaceholder("Ej: Calle Gran vía, 13, Madrid");
        
        
        //array para numero de usuario
        ArrayList<Integer> numerousuario= new ArrayList<>();
        
        //creamos una tabla con sus items y columnas
        Label labelTituloTabla = new Label("CONTACTOS DE TU AGENDA: ");
        Grid<Usuario> grid = new Grid<>();
        grid.setItems(lista.getUsuariosLista());
        grid.addColumn(Usuario::getNombre).setCaption("Nombre");
        grid.addColumn(Usuario::getApellidos).setCaption("Apellidos");
        grid.addColumn(Usuario::getEmpresa).setCaption("Empresa");
        grid.addColumn(Usuario::getTelefono).setCaption("Teléfono");
        grid.addColumn(Usuario::getEmail).setCaption("Email");
        grid.addColumn(Usuario::getDireccion).setCaption("Direccion");
        grid.setWidth("760px");
    	grid.setHeight("325px");
    	

    	// Boton de añadir contacto
        Button buttonCreate = new Button("Añadir contacto");
        buttonCreate.addClickListener(e -> {
        	// Comprobamos si los textfield que son necesarios segun el enunciado, estan vacios (Nombre y telefono)
			if (nombre.getValue().isEmpty() || telefono.getValue().isEmpty()){
	        	Notification.show("No se puede añadir el contacto, ya que faltan por completar datos obligatorios", Notification.Type.HUMANIZED_MESSAGE).setDelayMsec(3000);        	
			}
			else  {
	        	Notification.show("El contacto ha sido añadido a la agenda correctamente", Notification.Type.HUMANIZED_MESSAGE).setDelayMsec(3000);        	
				numerousuario.add(1);
				Usuario usuario = new Usuario(nombre.getValue(), apellidos.getValue(), empresa.getValue(), telefono.getValue(), email.getValue(), direccion.getValue());
	        	lista.addUsuario(usuario);
	        	grid.setItems(lista.getUsuariosLista()); //para que cuando se añada un nuevo objeto se vuelva a crear/actualizar la tabla
 /*
	     // Boton de borrar contacto
	     Button buttonDelete = new Button("Eliminar contacto");  
	    buttonDelete.addClickListener(e2 ->  {
	    	
	    	pm.removeProduct(Integer.parseInt(id.getValue()));
        	grid.setItems(pm.getProducts());
            grid.getDataProvider().refreshAll();
            Notification.show("Producto eliminado de la lista!");
	    	
	    	});
	    */	
	        	//Limpiamos los datos de los campos para cuando se quiera crear otro contacto
				nombre.clear();
				apellidos.clear();
				empresa.clear();
				telefono.clear();
				email.clear();
				direccion.clear();
			}
        });
              
        vlayout.addComponents(labelTitulo, nombre, apellidos, empresa, telefono, email, direccion, button);
        vlayout2.addComponents(labelTituloTabla, grid);
        hlayout.addComponents(vlayout, vlayout2);
        
        setContent(hlayout);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
