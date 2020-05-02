package com.dis.practica2;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.Page;
import com.vaadin.server.Sizeable;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.dis.practica2.*;

/**
 * This UI is the application entry point. A UI may either represent a browser
 * window (or tab) or some part of an HTML page where a Vaadin application is
 * embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is
 * intended to be overridden to add component to the user interface and
 * initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {

	@Override
	protected void init(VaadinRequest vaadinRequest) {

		// Cargamos los usuarios que se encuentran de forma predeterminada
		Predeterminado usuariosPre = new Predeterminado();
		usuariosPre.Predeterminado();


		// LAYOUT NECESARIAS
		Label labelTituloPrincipal = new Label("AGENDA DE CONTACTOS PERSONAL");
		
		
		Label labelTitulo = new Label("DATOS DE LA AGENDA: ");

		Label labelTitulo1 = new Label("OPCIONES: ");
		
		Label labelexpdelete = new Label("* Si quieres eliminar un contacto, tendrás que introducir el número de teléfono del contacto que quieres eliminar.");


		Label labelexpupdate = new Label("* Si quieres modificar un contacto, tendrás que introducir el número de teléfono del contacto que quieres modificar, además de los nuevos datos del contacto a modificar.");

		
		// layout vertical de recogida de datos
		final VerticalLayout vlayout = new VerticalLayout();
		
		// layout vertical de recogida de botones
		final VerticalLayout vlayout1 = new VerticalLayout();

		// layout vertical de mostrar datos
		final VerticalLayout vlayout2 = new VerticalLayout();

		// layout horizontal que engloba a los dos verticales
		final HorizontalLayout hlayout = new HorizontalLayout();
		
		// layout vertical para la ventana window
		final VerticalLayout wlayout = new VerticalLayout();
		
		// layout veritcal que engloba a los dos horizontales
		final VerticalLayout vlayout3 = new VerticalLayout();
		
		final VerticalLayout vlayout4 = new VerticalLayout();
		
		final VerticalLayout vlayout5 = new VerticalLayout();
		


		// Textfield del formulario
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
		direccion.setPlaceholder("Ej: Calle Gran vía, 13");
		
		//Pestaña de detalle del contacto
		Window pestanadetalle = new Window("Detalle del contacto: ");
		pestanadetalle.center();
		pestanadetalle.setWidth("500px");
		pestanadetalle.setHeight("325px");
		
		Label labelverNombre = new Label("Nombre: ");
		Label labelverApellidos = new Label("Apellidos: ");
		Label labelverEmpresa = new Label("Empresa: ");
		Label labelverTelefono = new Label("Telefono: ");
		Label labelverEmail = new Label("Email: ");
		Label labelverDireccion = new Label("Direccion: ");
		

		// creamos una tabla con sus items y columnas
		Label labelTituloTabla = new Label("CONTACTOS DE TU AGENDA: ");
		Grid<Usuario> grid = new Grid<>();
		grid.setItems(ListaUsuarios.getInstance().getUsuariosLista());
		// grid.setItems(lista.getUsuariosLista());
		grid.addColumn(Usuario::getNombre).setCaption("Nombre");
		grid.addColumn(Usuario::getApellidos).setCaption("Apellidos");
		grid.addColumn(Usuario::getEmpresa).setCaption("Empresa");
		grid.addColumn(Usuario::getTelefono).setCaption("Teléfono");
		grid.addColumn(Usuario::getEmail).setCaption("Email");
		grid.addColumn(Usuario::getDireccion).setCaption("Direccion");
		grid.setWidth("900px");
		grid.setHeight("500px");

		// Boton de añadir contacto
		Button buttonCreate = new Button("Añadir contacto");
		buttonCreate.setWidth("250px");
		buttonCreate.addClickListener(e -> {
			// Comprobamos si los textfield que son necesarios segun el enunciado, estan
			// vacios (Nombre y telefono)
			if (nombre.getValue().isEmpty() || telefono.getValue().isEmpty()) {
				Notification.show("No se puede añadir el contacto, ya que faltan por completar datos obligatorios",
						Notification.Type.HUMANIZED_MESSAGE).setDelayMsec(3000);
			}
			Notification.show("El contacto ha sido añadido a la agenda correctamente", Notification.Type.HUMANIZED_MESSAGE).setDelayMsec(3000);
			//numerousuario.add(1);
			Usuario usuario = new Usuario(nombre.getValue(), apellidos.getValue(), empresa.getValue(),
					telefono.getValue(), email.getValue(), direccion.getValue());
			//lista.addUsuario(usuario);
			ListaUsuarios.getInstance().getUsuariosLista().add(usuario);
			grid.setItems(ListaUsuarios.getInstance().getUsuariosLista());
				// para que cuando se añada un nuevo objeto se vuelva a
														// crear/actualizar la tabla
			nombre.clear();
			apellidos.clear();
			empresa.clear();
			telefono.clear();
			email.clear();
			direccion.clear();
		});
		
		// Boton de eliminar contacto	
		Button buttonDelete = new Button("Eliminar contacto");
		buttonDelete.setWidth("250px");
		buttonDelete.addClickListener(e2 -> {
	
		int i = 0;
		for(Usuario datos : ListaUsuarios.getInstance().getUsuariosLista()) {
			
			if((datos.getTelefono().equals(telefono.getValue()))) {
				
				ListaUsuarios.getInstance().getUsuariosLista().remove(i);
				Notification.show("Usuario eliminado de la agenda correctamente.", Notification.Type.HUMANIZED_MESSAGE).setDelayMsec(3000);
				
				// Recargamos la página
				//Page.getCurrent().reload();
				grid.setItems(ListaUsuarios.getInstance().getUsuariosLista());
				
				break;
			}					
			else {
				Notification.show("Introduce un telefono válido.", Notification.Type.HUMANIZED_MESSAGE).setDelayMsec(3000);	
			}
			i = i+1;
		}
		
		nombre.clear();
		apellidos.clear();
		empresa.clear();
		telefono.clear();
		email.clear();
		direccion.clear();
		
		});
		
		
		// Boton de modificar contacto	
				Button buttonUpdate = new Button("Modificar contacto");
				buttonUpdate.setWidth("250px");
				buttonUpdate.addClickListener(e2 -> {
			
				int i = 0;
				for(Usuario datos : ListaUsuarios.getInstance().getUsuariosLista()) {
					
					if((datos.getTelefono().equals(telefono.getValue()))) {
						
						ListaUsuarios.getInstance().getUsuariosLista().remove(i);
						
						// Recargamos la página
						//Page.getCurrent().reload();
						Usuario usuario = new Usuario(nombre.getValue(), apellidos.getValue(), empresa.getValue(),
								telefono.getValue(), email.getValue(), direccion.getValue());
						ListaUsuarios.getInstance().getUsuariosLista().add(usuario);
						grid.setItems(ListaUsuarios.getInstance().getUsuariosLista());
						
						break;
					}					
					else {
						Notification.show("Introduce un telefono válido.", Notification.Type.HUMANIZED_MESSAGE).setDelayMsec(3000);		
					}
				
					i = i+1;
					Notification.show("Usuario modificado de la agenda correctamente.", Notification.Type.HUMANIZED_MESSAGE).setDelayMsec(3000);
				}
				
				nombre.clear();
				apellidos.clear();
				empresa.clear();
				telefono.clear();
				email.clear();
				direccion.clear();
				
				});

				
		//Boton de detalle del contacto
				Button buttonDetalle = new Button("Detalle del contacto");
				buttonDetalle.setWidth("250px");
				buttonDetalle.addClickListener(e -> {
					
					int i = 0;
					for(Usuario datos : ListaUsuarios.getInstance().getUsuariosLista()) {
						
						if((datos.getTelefono().equals(telefono.getValue()))) {
							
							// Hacemos que aparezca la nueva pestaña
			        		addWindow(pestanadetalle);
							
			        		// Indicamos los datos que se verán en los labels
			        		labelverNombre.setValue("Nombre: " + datos.getNombre());
			        		labelverApellidos.setValue("Apellidos: " + datos.getApellidos());
			        		labelverEmpresa.setValue("Empresa: " + datos.getEmpresa());
			        		labelverTelefono.setValue("Telefono: " + datos.getTelefono());
			        		labelverEmail.setValue("Email: " + datos.getEmail());
			        		labelverDireccion.setValue("Direccion: " + datos.getDireccion());

							break;
						}					
						else {
							Notification.show("Introduce un telefono válido.", Notification.Type.HUMANIZED_MESSAGE).setDelayMsec(3000);		
						}
					
						i = i+1;
					}
					
					nombre.clear();
					apellidos.clear();
					empresa.clear();
					telefono.clear();
					email.clear();
					direccion.clear();

		    	});
				
		// Limpiamos los datos de los campos para cuando se quiera crear otro contacto
		nombre.clear();
		apellidos.clear();
		empresa.clear();
		telefono.clear();
		email.clear();
		direccion.clear();

		vlayout.addComponents(labelTitulo, nombre, apellidos, empresa, telefono, email, direccion);
		vlayout1.addComponents(labelTitulo1, buttonCreate, buttonDelete, buttonUpdate, buttonDetalle);
		vlayout2.addComponents(labelTituloTabla, grid);
		hlayout.addComponents(vlayout, vlayout1, vlayout2);
		vlayout4.addComponents(labelexpdelete, labelexpupdate);
		vlayout3.addComponents(hlayout, vlayout4);
		vlayout5.addComponents(labelTituloPrincipal, vlayout3);
		wlayout.addComponents(labelverNombre, labelverApellidos, labelverEmpresa, labelverTelefono, labelverEmail, labelverDireccion);

		setContent(vlayout5);
		pestanadetalle.setContent(wlayout);
	}

	@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
	public static class MyUIServlet extends VaadinServlet {
	}
}
