package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import controller.BaseDatos;
import controller.ControllerCliente;
import controller.ControllerEmpleado;
import model.Alquiler;
import model.Empleado;
import model.Reserva;
import model.Seguro;

public class InterfazEmpleado {
	public static ControllerEmpleado elEmpleado;
	private static BaseDatos datos;
	
	
	public static void correrEmpleado(BaseDatos datos) throws IOException
	{
		System.out.println("Bienvenido cliente");
		elEmpleado= new ControllerEmpleado(datos);
		InterfazEmpleado.datos = elEmpleado.getDatos();
		boolean inicializacion = false;
		
		boolean continuar = true;
		while (continuar)
		{
			try
			{
				mostrarMenu();
				int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opci�n"));
				
				if (opcion_seleccionada == 1)
					inicializacion = login();
				else if (opcion_seleccionada == 2 && inicializacion == true)
					crearAlquiler();
				
				else if (opcion_seleccionada == 3 && inicializacion == true)
				{
					actualizarCarro();
				}
				
				else if (opcion_seleccionada == 4 && inicializacion == true)
				{
					cumplimientoFechaCarro();
				}

				else if (opcion_seleccionada == 5)
				{
					cargarDatos();
					System.out.println("Saliendo de la aplicaci�n ...");
					continuar = false;
				}
				else if (inicializacion == false)
				{
					System.out.println("Para poder ejecutar esta opci�n primero debe iniciar sesi�n");
				}
				else
				{
					System.out.println("Por favor seleccione una opci�n v�lida.");
				}
			}
			catch (NumberFormatException e)
			{
				System.out.println("Debe seleccionar uno de los n�meros de las opciones.");
			}
		}
	}


//Actualizar la fecha de un carro que este lista
	
public static void actualizarCarro()
{
	String placa = input("Digite la placa del carro:");
	LocalDateTime fecha = LocalDateTime.now();
	int dias = Integer.parseInt(input("Inserte el n�mero de d�as que necesuta el carro en limpieza o manentenimiento (max 2)"));
	
	elEmpleado.ActualizarCarro(placa, fecha, dias);
	
}

//Cambiar las fechas de los carros que ya no estan en mantenimiento

private static void cumplimientoFechaCarro()
{
	LocalDateTime fecha = LocalDateTime.now();
	elEmpleado.cumplimientoFechaCarro(fecha);
	
}

//login del usuario y contrase�a
	
public static boolean login()	
{

	String usuario = input("Inserte su usuario: ");
	String contrasena = input ("Ingrese contrase�a: ");
	
	
	if ((InterfazEmpleado.datos.getMapaEmpleados().get(usuario)!= null))
	{
		Empleado empleado= InterfazEmpleado.datos.getMapaEmpleados().get(usuario);
		
		if (empleado.getUsuario().equals(usuario)&& empleado.getContrasena().equals(contrasena))
		{
			return true;
		}
		else { System.out.println("Usuario o contrase�a incorrectos");}
	}
	
	else {System.out.println("Usuario inexistente, por favor vuelva a intentarlo");}
	
	return false;
}

// Crear Alquiler

private static void crearAlquiler() 
{
	Alquiler alquiler;
	String reserva = input("�El cliente tiene una reserva? (SI/NO)");
	String cliente = input("Ingrese el usuario del cliente");
	String sedeDevolucion = input("Ingrese la sede en donde se desea devolver el veh�culo");
	String sedeRecoger = input("Ingrese la sede en donde se recoge el veh�culo"); 
	LocalDateTime fechaDeb = LocalDateTime.parse(input("Ingrese fecha de devoluci�n del veh�culo (yyyy-MM-dd'T'HH:mm:ss)"));
	LocalDateTime fechaInicio = LocalDateTime.parse(input("Ingrese fecha para recoger veh�culo (yyyy-MM-dd'T'HH:mm:ss)"));
	String categoria = input("Digite la categor�a del veh�culo deseada: ");
	
	//Verificar si ya existe reserva
	if (reserva.equals("SI"))
	{
		alquiler = elEmpleado.crearAlquilerReserva(categoria, cliente, fechaInicio, fechaDeb);	
	}
	
	else 
	{
		alquiler = elEmpleado.CrearAlquiler(cliente, sedeDevolucion, sedeRecoger, fechaDeb, fechaInicio, categoria);
	}
	
	// Agregar seguros si es posible crear el alquiler
	if (alquiler.equals(null)) {
		System.out.println("No hay un veh�culo disponible en este momento");
	} else {
		// Preguntar si desea adquirir un nuevo seguro

		String seguro = input("Desea adquirir alg�n tipo de seguro (SI/NO)");

		if (seguro.equalsIgnoreCase("SI")) {
			boolean condicion = true;

			while (condicion) {
				Seguro seg = menuSeguros();
				alquiler.setSeguro(seg);

				String continuar = input("Desea a�adir m�s seguros (SI/NO)");

				if (!(continuar.equalsIgnoreCase("SI"))) {
					condicion = false;
				}
			}
		}
		
		alquiler.setLicencia( InterfazEmpleado.datos.getMapaClientes().get(cliente).getLicencia());
		
		//Crear las licencias que necesite el cliente
		String licencia = input("Desea a�adir m�s licencias");

		if (licencia.equalsIgnoreCase("SI")) {
			boolean condicion = true;
			int contador = 0;

			while (condicion) {
				contador++;
				String lic = input("Ingrese n�mero de licencia");
				alquiler.setLicencia(InterfazEmpleado.datos.getMapaLicencias().get(lic));

				String continuar = input("Desea a�adir m�s licencias (SI/NO)");

				if (!(continuar.equalsIgnoreCase("SI"))) {
					condicion = false;
				}
			}
			
			alquiler.getFactura().setPrecioLicencias(contador-1);
		}
	}
	
	//imprimir factura
	
	elEmpleado.generarFactura(alquiler.getFactura());
		
}

private static Seguro menuSeguros()
{
	for (Seguro seg:InterfazEmpleado.datos.getMapaSeguros().values())
	{
		String id = seg.getId();
		String nombre = seg.getNombre();
		String precio = String.valueOf(seg.getPrecio());
		System.out.println(id + ".  "+nombre + "  precio: $ " + precio);
	}
	
	String idSeguro = input("Digite el id del seguro que desea");
	return InterfazEmpleado.datos.getMapaSeguros().get(idSeguro);
}
	
public static String input(String mensaje)
{
	try
	{
		System.out.print(mensaje + ": ");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		return reader.readLine();
	}
	catch (IOException e)
	{
		System.out.println("Error leyendo de la consola");
		e.printStackTrace();
	}
	return null;}

// Actualizar datos
public static void cargarDatos() throws IOException {
	elEmpleado.actualizarDatos();
}

//Mostrar men�
public static void mostrarMenu()
{
		System.out.println("\nOpciones de la aplicaci�n\n");
		System.out.println("1. LogIn");
		System.out.println("2. Crear un Alquiler");
		System.out.println("3. Modificar fecha de finalizaci�n de limpieza o mantenimiento");
		System.out.println("4. Cambiar fecha de veh�culos que ya completaron limpieza");
		System.out.println("5. LogOut");

}




}