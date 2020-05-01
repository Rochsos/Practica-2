package com.dis.practica2;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.json.JSONArray;
import org.json.JSONObject;

public class JsonUsuario {
	
	private ResourceBundle bundle = ResourceBundle.getBundle(this.getClass().getName());
	private String url;
	private JSONObject json;
	
	public ArrayList<Usuario> LeerJson() 
	{
		ArrayList<Usuario> Usuarios = new ArrayList<Usuario>();
		url = bundle.getString("json");
		try {
			
			String contenido = new String(Files.readAllBytes(Paths.get(url)));
			
			json = new JSONObject(contenido.trim());
			
			JSONArray UsuariosJson = json.getJSONObject("Usuarios").getJSONArray("Usuario");
			
			for (int i = 0; i < UsuariosJson.length(); i++)
			{
				Usuario UsuarioTemp =  new Usuario(contenido, contenido, contenido, contenido, contenido, contenido);
				
				JSONObject UsuarioJson = (JSONObject) UsuariosJson.get(i);
				
				UsuarioTemp.setNombre(UsuarioJson.getString("nombre"));
				UsuarioTemp.setApellidos(UsuarioJson.getString("apellidos"));
				UsuarioTemp.setEmpresa(UsuarioJson.getString("empresa"));
				UsuarioTemp.setTelefono(UsuarioJson.getString("telefono"));
				UsuarioTemp.setDireccion(UsuarioJson.getString("direccion"));
								
				Usuarios.add(UsuarioTemp);
			}
			

		} catch (Exception e) {
			System.out.println("excepcion e" + e);
		}
		
		return Usuarios;
	}
	
	public static void main(String[] args) {
      JsonUsuario a =  new JsonUsuario();
      a.LeerJson();
    }

}
