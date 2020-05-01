package com.dis.practica2;

import java.util.ArrayList;

import com.dis.practica2.*;

public class ListaUsuarios {
	

	// Creamos la variable lista que será estatica para que no la tengamos que instanciar en otra clase
	private static ListaUsuarios lista;
	
	// Creamos un ArrayList lista con los datos del contacto
	private ArrayList <Usuario> listausuarios;
	
	// Creamos el constructor donde instancio el array
	public ListaUsuarios() {
		
		this.listausuarios = new ArrayList<Usuario>();
		
	}
	
	//voy a crear un metodo que va a ser para añadir objetos
	public void addUsuario(Usuario u) {
			
		this.listausuarios.add(u);
			
	}
		
	// Creamos la instancia
	public static ListaUsuarios getInstance() {
		if (lista == null) {
			lista = new ListaUsuarios();
		}
		return lista;
	}
	
	// Creamos los getters y setters necesarios
	// Getter de los contactos que se encuentran en la agenda
	public ArrayList <Usuario> getUsuariosLista() {
		
		return this.listausuarios;
		
	}

	// Setter de los contactos que se encuentran en la agenda
	public void setUsuariosLista(ArrayList<Usuario> listausuarios) {
		
		this.listausuarios = listausuarios;
		
	}	
	
	// otro metodo para que me devuelva el total de los contactos creados
	public int totalUsuarios() {
			
			return this.listausuarios.size();
			
		}

}
