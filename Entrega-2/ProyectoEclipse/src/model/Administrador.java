package model;

public class Administrador implements Usuario {
	
	private String id;
	
	private String usuario;
	
	private String contrasena;
	
	private String nombre;
	
	private Sede sede;

    public Administrador(String id, String usuario, String contrasena, String nombre) {
    	this.id = id;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.nombre = nombre;
        
    }
    
    
	public String getId()
	{
		return id;
	}
	

	public String getUsuario()
	{
		return usuario;
		
	}
	
	public String getContrasena()
	{
		return contrasena;
		
	}
	
	public String getNombre()
	{
		return nombre;
	}
	
	public Sede getSede() {
		return sede;
	}
	
	public Sede setSede(Sede sede) {
		return this.sede = sede;
	}
	
	
	


    /*
     * Inicion de sesion
     * */

    
}







