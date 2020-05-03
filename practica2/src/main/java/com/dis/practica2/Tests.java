package com.dis.practica2;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.dis.practica2.*;


public class Tests {

		Usuario usuario = null;
		ListaUsuarios lista = null;
	
		@Before
		public void initialize() {
			usuario = new Usuario("Pedro", "Díaz", "BABEL", "680465204", "pedrod@gmail.com", "Calle Pez Volador");
		}
	
	// PRUEBAS DE CRUD DE CONTACTOS
	// Prueba de la creacion de los contactos
		@Test
		public void crearContacto() {
			ListaUsuarios lista = new ListaUsuarios();
			lista.addUsuario(usuario);
			assertEquals(1, lista.totalUsuarios());
		}
		
		// Test de los getters
		@Test
		public void getUsuario() {
			assertEquals("Pedro", usuario.getNombre());
			assertEquals("Díaz", usuario.getApellidos());
			assertEquals("BABEL", usuario.getEmpresa());
			assertEquals("680465204", usuario.getTelefono());
			assertEquals("pedrod@gmail.com", usuario.getEmail());
			assertEquals("Calle Pez Volador", usuario.getDireccion());
		}
		
		
		//Test de los setters
		@Test
	    public void setUsuario() {
	        usuario.setNombre("Diego");
	        usuario.setApellidos("Ruiz");
	        usuario.setEmpresa("Santander");
	        usuario.setTelefono("620172004");
	        usuario.setEmail("diegor@gmail.com");
	        usuario.setDireccion("Calle de Alcalá");
	        
	        assertTrue(usuario.getNombre() == "Diego");
	        assertTrue(usuario.getApellidos() == "Ruiz");
	        assertTrue(usuario.getEmpresa() == "Santander");
	        assertTrue(usuario.getTelefono() == "620172004");
	        assertTrue(usuario.getEmail() == "diegor@gmail.com");
	        assertTrue(usuario.getDireccion() == "Calle de Alcalá");
	    }
		

		// Prueba de la eliminacion de los contactos
		@Test
		public void borrarContacto() {
			ListaUsuarios lista = new ListaUsuarios();
			lista.removeUsuario(usuario);
			assertEquals(0, lista.totalUsuarios());
		}		


}
