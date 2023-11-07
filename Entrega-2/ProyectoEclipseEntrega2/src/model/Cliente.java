package model;
import java.io.*;
import java.util.ArrayList;

public class Cliente implements Usuario {

	
	//Atributos
	//login
	private  String usuario;
	private  String contrasena;
	//datos generales:
	private String nombre;
	private String email;
	private String nacionalidad;
	private String rutaImagenID;
	//licencia
	private Licencia licencia;
	//tarjeta
	private Tarjeta tarjeta;
	
	
	public Cliente(String usuario, String contrasena, String nombre,
			String email, String nacionalidad, String rutaImagenID) {
		this.contrasena=contrasena;
		this.email=email;
		this.nacionalidad=nacionalidad;
		this.nombre=nombre;
		this.rutaImagenID=rutaImagenID;
		this.usuario=usuario;
		this.licencia=null;
		this.tarjeta=null;
	}
	
	
	public void setLicencia(Licencia licencia) {
		this.licencia=licencia;
	}
	public void setTarjeta(Tarjeta tarjeta) {
		this.tarjeta=tarjeta;
	}
	public String getUsuario() {
		return usuario;
	}

	public String getContrasena() {

		return contrasena;
	}

	public String getNombre() {

		return nombre;
	}

	public String getEmail() {
		return email;
	}
	public String getNacionalidad() {
		return nacionalidad;
		
	}
	public String getRutaImagenID() {
		return rutaImagenID;
}
	public Licencia getLicencia() {
		return licencia;
	}
	public Tarjeta getTarjeta() {
		return tarjeta;
	}
	
	/*
	 * METODOS LOGIN CLIENTE 
	*/
	
	//private boolean sesionIniciada;
	
	//public void iniciarSesion(String nombreUsuario, String contrasena) {
        //if (usuario.equals(this.usuario) && contrasena.equals(this.contrasena)) {
           // sesionIniciada = true;
          //  System.out.println("Sesi�n iniciada para el Cliente: " + usuario);
        //} else {
          //  System.out.println("Error: Nombre de usuario o contrase�a incorrectos.");
       // }
   // }

   // @Override
    //public void cerrarSesion() {
     //   sesionIniciada = false;
       // System.out.println("Sesi�n cerrada para el Cliente.");
   // }

   // @Override
   // public boolean estaSesionIniciada() {
    //    return sesionIniciada;
  //  }
    
    //public String getNombreUsuario() {
     //   return usuario;
   // }




    //public static Cliente registrar(String nombreUsuario, String contrasena) {
       // Cliente nuevoCliente = new Cliente(usuario, contrasena, "", "", "", "");
        //guardarRegistro(nuevoCliente);
      //  return nuevoCliente;
   // }

  //  private static void guardarRegistro(Usuario usuario) {
        ////try {
         //   FileWriter writer = new FileWriter("clientes.txt", true);
         //   writer.write(usuario.getUsuario() + "," + usuario.getContrasena() + "\n");
        //    writer.close();
        //} catch (IOException e) {
            //e.printStackTrace();
        //}
   // }

   // public static ArrayList<Usuario> cargarClientes() {
     //   ArrayList<Usuario> clientes = new ArrayList<>();
      //  try {
        //    FileReader reader = new FileReader(".//clientes.txt");
         //   BufferedReader br = new BufferedReader(reader);
          //  String line;
           // while ((line = br.readLine()) != null) {
           //     String[] data = line.split(",");
             //   if (data.length == 2) {
              //      Cliente cliente = new Cliente(data[0], data[1], "", "", "", "");
              //      clientes.add(cliente);
               // }
            //}
          //  br.close();
       // } catch (IOException e) {
        //    e.printStackTrace();
        //}
      //  return clientes;
    
}
	
	
	
	
	

