package controller;

import java.io.IOException;

import model.Administrador;
import model.Carro;
//import java.util.HashMap;
//import controller.BaseDatos;
import model.Empleado;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class ControllerAdministrador {
	
	private Administrador administrador;
	private BaseDatos datos;
	

	public ControllerAdministrador() {
		this.administrador=null;
		this.datos=null;
		
	}
	
	public Administrador getAdministrador() {
		
		return this.administrador;
	}
	
	public void setDatos(BaseDatos datos) {
		this.datos=datos;
	}
	
	public void LogIn(String usuario,String contrasena) {

		Administrador administrador = datos.getMapaAdministradores().get(usuario);
		
		if(administrador.getUsuario().equals(usuario)&& administrador.getContrasena().equals(contrasena)) {
			this.administrador=administrador;
			
		}
		else {
			System.out.println("Error al ingresar");
		}
	}
		
		
	/*
	 * setters 
	 * 
	*/


	public static void agregarEmpleado(String id, String nombre, String login, String password, String email, String sede ) throws Exception {
		agregarEmpleado(id, nombre, login, password, email, sede);
	}
	
	public static void agregarVehiculo(String placa, String marca, String modelo,String color, String tipoTransmision) throws Exception {
		agregarVehiculo(placa, marca, modelo, color,tipoTransmision);
	}
	
	public void actualizarDatos() throws IOException {
		datos.cargarTodosLosDatos();
	}
	
	
	public static String crearLineaEmpleado(String id, String nombre, String usuario, String contrasena, String email, String sede) {

		return id + ";" + nombre + ";" + usuario + ";" + contrasena + ";" + email + ";"+ sede;
	}
	
	public String crearLineaVehiculo(Carro carro) {
		String placa = carro.getPlaca();
		String marca = carro.getmarca();
		String modelo = carro.getModelo();
		String color = carro.getColor();
		String tipoTransmision = carro.getTipoTransmision();

		String nombreCategoria = carro.getCategoria().getNombre();
		String nombreSede = carro.getSede().getNombre();
		String estado = carro.getEstado();
		String fechaDisp = String.valueOf(carro.getFechaDispCons());
		String str = placa + ";" + marca + ";" + modelo + ";" + color + ";" + tipoTransmision + ";" + ";"
				+ nombreCategoria + ";" + nombreSede + ";" + estado + ";" + fechaDisp;
		return str;
	}
	
	
    public void eliminarlineaEmpleados(String archivo, String contenidoAEliminar) {
        try {
            // Abre el archivo original y un archivo temporal para escritura
            BufferedReader reader = new BufferedReader(new FileReader(archivo));
            BufferedWriter writer = new BufferedWriter(new FileWriter("/data/tempEmpleados.txt"));

            String lineaActual;

            // Lee línea por línea y copia todas las líneas excepto la que deseas eliminar
            while ((lineaActual = reader.readLine()) != null) {
                if (!lineaActual.contains(contenidoAEliminar)) {
                    writer.write(lineaActual + System.getProperty("line.separator"));
                }
            }

            // Cierra los archivos
            reader.close();
            writer.close();

            // Borra el archivo original y renombra el archivo temporal
            if (new File(archivo).delete()) {
                new File("temp.txt").renameTo(new File(archivo));
                System.out.println("Fila eliminada exitosamente.");
            } else {
                System.out.println("No se pudo eliminar la fila.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void agregarLineaEmpleados(String archivo, String nuevaLinea) {
        try {
            // Abre el archivo en modo de adición
            BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, true));
            
            // Escribe la nueva línea en el archivo
            writer.write(nuevaLinea + System.getProperty("line.separator"));
            
            // Cierra el archivo
            writer.close();
            
            System.out.println("Nueva línea agregada exitosamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void eliminarLineaVehiculos(String archivo, String placa) {
        try {
            // Abre el archivo original y un archivo temporal para escritura
            BufferedReader reader = new BufferedReader(new FileReader(archivo));
            BufferedWriter writer = new BufferedWriter(new FileWriter("data/tempVehiculos.txt"));
            String lineaActual;

            // Lee línea por línea y copia todas las líneas excepto las que contienen el fragmento
            while ((lineaActual = reader.readLine()) != null) {
                if (!lineaActual.contains(placa)) {
                    writer.write(lineaActual + System.getProperty("line.separator"));
                }
            }

            // Cierra los archivos
            reader.close();
            writer.close();

            // Borra el archivo original y renombra el archivo temporal
            if (new File(archivo).delete()) {
                new File("data/tempVehiculos.txt").renameTo(new File(archivo));
                System.out.println("Carro eliminado exitosamente.");
            } else {
                System.out.println("No se pudo eliminar el Carro.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void eliminarLineaEmpleados(String archivo, String fragmento) {
        try {
            // Abre el archivo original y un archivo temporal para escritura
            BufferedReader reader = new BufferedReader(new FileReader(archivo));
            BufferedWriter writer = new BufferedWriter(new FileWriter("data/tempEmpleados.txt"));
            String lineaActual;

            // Lee línea por línea y copia todas las líneas excepto las que contienen el fragmento
            while ((lineaActual = reader.readLine()) != null) {
                if (!lineaActual.contains(fragmento)) {
                    writer.write(lineaActual + System.getProperty("line.separator"));
                }
            }

            // Cierra los archivos
            reader.close();
            writer.close();

            // Borra el archivo original y renombra el archivo temporal
            if (new File(archivo).delete()) {
                new File("data/tempEmpleados.txt").renameTo(new File(archivo));
                System.out.println("Empleado eliminado exitosamente.");
            } else {
                System.out.println("No se pudo eliminar el empleado.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void agregar(String archivo, String lineaEliminar) {
        //String archivo = "empleados.txt";
        //String contenidoAEliminar = "Juan Pérez,30"; // Debes proporcionar el contenido exacto de la fila que deseas eliminar

    	agregarLineaEmpleados(archivo, lineaEliminar);
    }

    public void eliminar(String archivo, String lineaEliminar) {
        //String archivo = "empleados.txt";
        //String contenidoAEliminar = "Juan Pérez,30"; // Debes proporcionar el contenido exacto de la fila que deseas eliminar

        eliminarlineaEmpleados(archivo, lineaEliminar);
    }

}
