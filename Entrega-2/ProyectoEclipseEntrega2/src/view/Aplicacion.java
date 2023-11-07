package view;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import controller.BaseDatos;

import view.InterfazCliente;
public class Aplicacion {
	private BaseDatos datos;

	public void ejecutarAplicacion() throws Exception
	{
		System.out.println("RamenAutos");

		boolean continuar = true;
		cargarDatos();
		while (continuar)
		{
			try
			{
				mostrarMenu();
				
				int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opción"));
				if (opcion_seleccionada == 1)
					InterfazCliente.correrCliente(datos);
				else if (opcion_seleccionada == 2 && datos != null)
					InterfazEmpleado.correrEmpleado(datos);
				else if (opcion_seleccionada == 3 && datos != null)
					InterfazAdministrador.correrAdministrador(datos);
					
				else if (opcion_seleccionada == 4)
				{
					System.out.println("Saliendo de la aplicación ...");
					continuar = false;
				}
				else
				{
					System.out.println("Por favor seleccione una opción válida.");
				}
			}
			catch (NumberFormatException e)
			{
				System.out.println("Debe seleccionar uno de los números de las opciones.");
			}
		}
	}

		public void mostrarMenu()
		{
			System.out.println("\nOpciones de la aplicación\n");

			System.out.println("1. Ingresar como Cliente");
			System.out.println("2. Ingresar como Empleado");
			System.out.println("3. Ingresar como Administrador");



	}
		public String input(String mensaje)
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
			return null;
		}
		private void cargarDatos() {
			System.out.println("Cargando datos: ");


			try
			{
				datos = new BaseDatos();
				datos.descargarTodoslosDatos();
				System.out.println("Se actualizaron los datos");
				
			}
			catch (FileNotFoundException e)
			{
				System.out.println("ERROR: el archivo indicado no se encontró.");
			}
			catch (IOException e)
			{
				System.out.println("ERROR: hubo un problema leyendo el archivo.");
				System.out.println(e.getMessage());
			}
		}
  public static void main(String[] args) throws Exception {
	
	  Aplicacion consola = new Aplicacion();
	  consola.ejecutarAplicacion();
  }
}

