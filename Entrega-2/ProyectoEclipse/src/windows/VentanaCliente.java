package windows;

import controller.BaseDatos;
import controller.ControllerCliente;

public class VentanaCliente {

private static ControllerCliente elCliente;

public static void login(BaseDatos datos) {
	elCliente =new ControllerCliente();
	elCliente.setDatos(datos);
	
	String usuario = VentanaPrincipal.getNombreUsuario().getText();
	String contrasena =VentanaPrincipal.getContrasena().getText();
	
	elCliente.logIn(usuario, contrasena);
	if(elCliente.getCliente().equals(null)) {
		System.out.println("Error ingresando sesión");
		
	}
	else {
		System.out.println("Ingresado correctamente");
		cargarVentanaCliente();
	}
}

private static void cargarVentanaCliente() {
	// TODO Auto-generated method stub
	
}
}
