package com.dis.practica2;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
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

    @Override
    protected void init(VaadinRequest vaadinRequest) {
    	
    	//añado la lista
    	ListaUsuarios lista = new ListaUsuarios();
    	 
    	//layout vertical de recogida de datos
    	final VerticalLayout vlayout = new VerticalLayout();
    	
    	//layout vertical de mostrar datos
        final VerticalLayout vlayout2 = new VerticalLayout();
        
        //layout horizontal que engloba a los dos verticales
        final HorizontalLayout hlayout = new HorizontalLayout();
        
        //Textfield del formulario
        final TextField nombre = new TextField();
        nombre.setCaption("Nombre: *");
        nombre.setPlaceholder("Ej: Jorge");
        
        final TextField apellidos = new TextField();
        apellidos.setCaption("Apellidos: ");
        nombre.setPlaceholder("Ej: Peréz");
        
        final TextField empresa = new TextField();
        empresa.setCaption("Empresa: ");
        nombre.setPlaceholder("Ej: BABEL");
        
        final TextField telefono = new TextField();
        telefono.setCaption("Telefono: *");
        nombre.setPlaceholder("Ej: +34 685928591");
        
        final TextField email = new TextField();
        email.setCaption("Email: ");
        nombre.setPlaceholder("Ej: jorgeperez@gmail.com");
        
        final TextField direccion = new TextField();
        direccion.setCaption("Direccion: ");
        nombre.setPlaceholder("Ej: Calle Gran vía, 13, Madrid");
        
        //array para numero de usuario
        ArrayList<Integer> numerousuario= new ArrayList<>();
        
        
        //creamos una tabla con sus items y columnas
        Grid<Usuario> grid = new Grid<>();
        grid.setItems(lista.getUsuariosLista());
        grid.addColumn(Usuario::getNombre).setCaption("Nombre");
        grid.addColumn(Usuario::getApellidos).setCaption("Apellidos");
        grid.addColumn(Usuario::getEmpresa).setCaption("Empresa");
        grid.addColumn(Usuario::getTelefono).setCaption("Teléfono");
        grid.addColumn(Usuario::getEmail).setCaption("Email");
        grid.addColumn(Usuario::getDireccion).setCaption("Direccion");
        grid.setCaption("Contantos de tu agenda: \n");
        grid.setWidth("760px");
    	grid.setHeight("325px");


        Button button = new Button("Añadir contacto");
        button.addClickListener(e -> {
        	// Comprobamos si los textfield que son necesarios segun el enunciado, estan vacios (Nombre y telefono)
			if (nombre.getValue().isEmpty() || telefono.getValue().isEmpty()){
	        	Notification.show("No se puede añadir el contacto, ya que faltan por completar datos obligatorios", Notification.Type.HUMANIZED_MESSAGE).setDelayMsec(3000);        	
			}
			else  {
				numerousuario.add(1);
				Usuario usuario = new Usuario(nombre.getValue(), apellidos.getValue(), empresa.getValue(), telefono.getValue(), email.getValue(), direccion.getValue());
	        	lista.addUsuario(usuario);
	        	grid.setItems(lista.getUsuariosLista()); //para que cuando se añada un nuevo objeto se vuelva a crear/actualizar la tabla
 
    
	        	//Limpiamos los datos de los campos para cuando se quiera crear otro contacto
				nombre.clear();
				apellidos.clear();
				empresa.clear();
				telefono.clear();
				email.clear();
				direccion.clear();
			}
        });
              
        vlayout.addComponents(nombre, apellidos, empresa, telefono, email, direccion, button);
        hlayout.addComponents(vlayout, grid);
        
        setContent(hlayout);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
