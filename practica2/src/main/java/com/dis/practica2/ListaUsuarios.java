package com.dis.practica2;

import java.util.ArrayList;

public class ListaUsuarios {
	


	// Creamos algunas variables
	private static ListaUsuarios lista;
	
	// Creamos un ArrayList con los productos del inventario
	private ArrayList <Usuario> listausuarios;
	
	// Creamos el constructor
	ListaUsuarios() {
		listausuarios = new ArrayList<Usuario>();
	}
	
	// Creamos la instancia
	public static ListaUsuarios getInstance() {
		if (lista == null) {
			lista = new ListaUsuarios();
		}
		return lista;
	}
	
	// Creamos los getters y setters necesarios
	// Getter de los productos que se encuentran en el almacen
	public ArrayList <Usuario> getUsuariosLista() {
		return listausuarios;
	}

	// Setter de los productos que se encuentran en el almacen
	public void setUsuariosLista(ArrayList<Usuario> listausuarios) {
		this.listausuarios = listausuarios;
	}	

}
