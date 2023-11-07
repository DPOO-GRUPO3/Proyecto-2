package controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import model.Administrador;
import model.Alquiler;
import model.Carro;
import model.Categoria;
import model.Cliente;
import model.Empleado;
import model.Factura;
import model.Licencia;
import model.Reserva;
import model.Sede;
import model.Seguro;
import model.Tarifa;
import model.Tarjeta;
import model.Temporada;
import model.Usuario;

public class Reader {
//Convierte String estandar en objeto. Va construyento el objeto.
//IMPORTANTE EL ORDEN EN QUE SE CARGAN LOS DATOS.

//PRIMER OBJETO: TEMPORADA
public Temporada descomprimirTemporada(String linea, HashMap<String,Categoria> mapaCategorias) {
	String[] partes = linea.split(";");
	String id = partes[0];
	LocalDateTime fecha1 = LocalDateTime.parse(partes[1]);
	LocalDateTime fecha2 = LocalDateTime.parse(partes[2]);
	double tarifa=Double.parseDouble(partes[3]);
	String categoria = partes[4];
	Categoria objCategoria = mapaCategorias.get(categoria);
	Temporada temp=new Temporada(fecha1, fecha2, tarifa);
	temp.setID(id);
	temp.setCategoria(objCategoria);
	objCategoria.setTarifa(temp);
	
	return temp;
}
//SEGUNDO OBJETO: TARJETA
public Tarjeta descomprimirTarjeta(String linea) {
	String[] partes = linea.split(";");
	String numero = partes[0];
	String codigo = partes[1];
	String bloqueo = partes[2];
	
	Tarjeta tar=new Tarjeta(numero, codigo);
	if(bloqueo.equals("1")==true) {
		tar.bloquear();
	}
	return tar;
}
//TERCER OBJETO: LICENCIA
public Licencia descomprimirLicencia(String linea) {
	String[] partes = linea.split(";");
	String fechaVens = partes[0];
	String numero = partes[1];
	String pais = partes[2];
	String rutaImagen = partes[3];
	String alq= partes[4];
	Licencia lic=new Licencia(numero,pais,fechaVens,rutaImagen);
	lic.setAlq(alq);
	return lic;
}
// CUARTO OBJETO: CATEGORIA
public Categoria descomprimirCategoria(String linea) {
	String[] partes = linea.split(";");
	String nombre = partes[0];
	double tarifa = Double.parseDouble(partes[1]);
	
	Categoria cat=new Categoria(nombre,tarifa);
	return cat;
}
// QUINTA CATEGORÃ�A: CLIENTE 
//REtornamos una lista de objetos porque necesitamos aÃ±adirle a cliente
// una tarjeta y una licencia asociadas
public ArrayList<Object> descomprimirCliente(String linea) {
	String[] partes = linea.split(";");
	String usuario = partes[0];
	String contrasena = partes[1];
	String email = partes[2];
	String pais=partes[3];
	String nombre=partes[4];
	String rutaImagen=partes[5];
	String numLic=partes[6];
	String numTar=partes[7];
	Cliente cli=new Cliente(usuario, contrasena, nombre,
			 email,  pais,  rutaImagen);
	ArrayList<Object>  lista=new ArrayList<Object>();
	lista.add(cli);
	lista.add(numLic);
	lista.add(numTar);
	return lista;
}
// SEXTO OBJETO: SEDE
public Sede descomprimirSede(String linea) {
	String[] partes = linea.split(";");
	String nombre = partes[0];
	String dir= partes[1];
	String inicio = partes[2];
	String fin=partes[3];
	LocalTime in=LocalTime.parse(inicio);
	LocalTime f=LocalTime.parse(fin);
	Sede s=new Sede(nombre, dir, in,  f, null);
	return s;
}
// SEPTIMO OBJETO: CARRO
public Carro descomprimirCarro(String linea,HashMap<String, Sede> mapaSedes,
		HashMap<String, Categoria> mapaCategorias) {
	String[] partes = linea.split(";");
	String placa = partes[0];
	String marca = partes[1];
	String modelo = partes[2];
	String color=partes[3];
	String trans=partes[4];
	String nombrCateg=partes[5];
	String nombreCede=partes[6];
	String estado=partes[7];
	String dispon=partes[8];
	Carro car =new Carro(placa,marca, modelo,  color,trans);
	if(dispon.equals("null")==false) {
		car.setFechaDisponibleCons(LocalDateTime.parse(dispon));
	}
	

	Categoria categoria = mapaCategorias.get(nombrCateg);
	car.setEstado(estado);
	car.setCategoria(mapaCategorias.get(nombrCateg));
	car.setCede(mapaSedes.get(nombreCede));
	Sede sede= mapaSedes.get(nombreCede);
	sede.setCarro(car);
	categoria.setCarro(car);
	return car;
}
// OCTAVO OBJETO: RESERVA
public Reserva descomprimirReserva(String linea,HashMap<String, Sede> mapaSedes,
		HashMap<String, Categoria> mapaCategorias,
		HashMap<String, Carro> mapaCar,HashMap<String, Cliente> mapaClientes) {
	String[] partes = linea.split(";");
	String id = partes[0];
	String sedeFin= partes[1];
	String sedeInicio = partes[2];
	String placa=partes[3];
	String categoria=partes[4];
	String fechaF=partes[5];
	String fechaIn=partes[6];
	String usCliente=partes[7];
	
	Sede sFin=mapaSedes.get(sedeFin);
	Sede sIn=mapaSedes.get(sedeInicio);
	Categoria cat=mapaCategorias.get(categoria);
	Carro carro=mapaCar.get(placa);
	Cliente cliente=mapaClientes.get(usCliente);
	LocalDateTime in=LocalDateTime.parse(fechaIn);
	LocalDateTime fin=LocalDateTime.parse(fechaF);
	
	Reserva r =new Reserva(cliente, in, fin, cat, carro, sIn, sFin);
	r.setNumReserva(Integer.parseInt(id));
	return r;
} 
// NOVENO OBJETO: ALQUILER
public Alquiler descomprimirAlquiler(String linea,HashMap<String, Sede> mapaSedes,
		HashMap<String, Categoria> mapaCategorias,HashMap<String, Licencia> mapaLic,
		HashMap<String, Carro> mapaCar,HashMap<String, Cliente> mapaClientes, 
		HashMap<String, Seguro> mapaSeguros,HashMap<String, Tarifa> mapaTarifaExcedente,
		HashMap<String, Temporada> mapaTarifa,HashMap<String, Reserva> mapaReservas ) {
	String[] partes = linea.split(";");
	String id = partes[0];
	String sedeFin= partes[1];
	String sedeInicio = partes[2];
	String placa=partes[3];
	String fechaF=partes[4];
	String fechaIn=partes[5];
	String usCliente=partes[6];
	String tarifaExcedente = partes[7];
	String temporada = partes[8];
	
	//si no hay reserva hay espacio para null
	String reserva= partes[9];
	
	
	Alquiler alq=new Alquiler(mapaClientes.get(usCliente), 
			LocalDateTime.parse(fechaF), LocalDateTime.parse(fechaIn), mapaSedes.get(sedeInicio),
			mapaSedes.get(sedeFin), mapaCar.get(placa));
	alq.setId(id);
	for(Licencia licencia:mapaLic.values()) {
	if (licencia.getAlq().equals(alq.getAlquileresId())==true) {
		alq.setLicencia(licencia);
	}
	}
	
	alq.setTarifaExcedente(mapaTarifaExcedente.get(tarifaExcedente));
	alq.setTarifa(mapaTarifa.get(temporada));
	alq.setReserva(mapaReservas.get(reserva));
	return alq;}

//DECIMO OBJETO: EMPLEADO
public Empleado descomprimirEmpleado(String linea, HashMap<String, Sede> mapaSedes, HashMap<String, Empleado> mapaEmpleados)
{
	String[] partes = linea.split(";");
	String id = partes[0];
	String nombre = partes[1];
	String usuario = partes[2];
	String contrasena = partes[3];
	String email = partes[4];
	String nombreSede = partes[5];
	
	
	Sede sede = mapaSedes.get(nombreSede);
	
	Empleado empleado= new Empleado(id, nombre, usuario, contrasena, email, sede);
	
	return empleado;
}

// ADMINISTRADOR



public Administrador descomprimirAdministrador(String linea, HashMap<String, Sede> mapaSedes) {
	String[] partes = linea.split(";");
	String usuario = partes[0];
	String contrasena = partes[1];
	String nombre=partes[2];
	String id = partes[3];
	String nombreSede = partes[4];
	


	Administrador admin= new Administrador(id,usuario, contrasena, nombre);
	
	admin.setSede(mapaSedes.get(nombreSede));
	Sede sede= mapaSedes.get(nombreSede);
	sede.setAdministrador(admin);
	
	return admin;
}
////////////////////////////////////////////////

//UNDECIMO OBJETO: SEGURO
public Seguro descomprimirSeguro(String linea)
{
	String[] partes = linea.split(";");
	String nombre = partes[0];
	Double precio= Double.parseDouble(partes[1]);
	
	return new Seguro(nombre,precio);
}

//DOCEAVO OBJETO: TARIFA
public Tarifa descomptimirTarifaExcedente(String linea,HashMap<String,Categoria> mapaCategorias) {
	

	String[] partes = linea.split(";");
	String id = partes[0];
	Double precio= Double.parseDouble(partes[1]);
	LocalDateTime fechaInicio= LocalDateTime.parse(partes[2]);
	LocalDateTime fechaFin = LocalDateTime.parse(partes[3]);
	String categoria = partes[4];
	Categoria objCategoria = mapaCategorias.get(categoria);
	Tarifa tarifa = new Tarifa (precio,fechaInicio,fechaFin);
	objCategoria.setTarifaExcedente(tarifa);
	tarifa.setCategoria(objCategoria);

	
	return tarifa;
}	

//TRECEAVO OBJETO: Factura
public Factura descomptimirFactura(String linea,HashMap<String,Cliente> mapaClientes,HashMap<String, Alquiler> mapaAlquileres) {
	

	String[] partes = linea.split(";");
	String id = partes[0];
	Double pagoAnticipado= Double.parseDouble(partes[1]);
	int licencias = Integer.parseInt(partes[2]);
	Double total= Double.parseDouble(partes[3]);
	String usuario = partes[4];
	String idAlquiler = partes[5];
	
	Factura factura = new Factura(mapaClientes.get(usuario), mapaAlquileres.get(idAlquiler));
	
	factura.setPagoAnticipado();
	factura.setTotal(total);
	factura.setPrecioLicencias(licencias);
	
	
	return factura;
}

}


