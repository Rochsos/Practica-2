package com.dis.practica2;

import java.util.ArrayList;

import com.dis.practica2.*;

public class Predeterminado {

	public void Predeterminado() {

		if (ListaUsuarios.getInstance().getUsuariosLista().isEmpty()) {
			// Creamos algunos datos para añadir
			String nombre1 = "Luis";
			String nombre2 = "Antonio";
			
			String apellidos1 = "Perez";
			String apellidos2 = "Rios";
			
			String empresa1 = "BABEL";
			String empresa2 = "Santander";
			
			String telefono1 = "681629037";
			String telefono2 = "601883672";
			
			String email1 = "luisperez@gmail.com";
			String email2 = "antoniorios@gmail.com";
			
			String direccion1 = "Calle Gran Vía, 13";
			String direccion2 = "Calle Goya, 34";

			// Un ArrayList para añadir los contactos predeterminados
			ArrayList<Usuario> predeterminado = new ArrayList<Usuario>();

			// Añadimos el primer contacto predeterminado
			Usuario predeterminado1 = new Usuario(nombre1, apellidos1, empresa1, telefono1, email1, direccion1);
			ListaUsuarios.getInstance().getUsuariosLista().add(predeterminado1);

			// Añadimos el segundo contacto
			Usuario predeterminado2 = new Usuario(nombre2, apellidos2, empresa2, telefono2, email2, direccion2);
			ListaUsuarios.getInstance().getUsuariosLista().add(predeterminado1);

		}
	}
}
