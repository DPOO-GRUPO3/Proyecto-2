package controller;


import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

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

public class ControllerEmpleado {
	
	//Atributos
	
	private Empleado empleado;
	
	private BaseDatos datos;
	
	private HashMap<String,Carro> mapaCarros; //mapa carros por placa
	private HashMap<String,Reserva> mapaReservas; //mapa reservas por id
	private HashMap<String,Categoria> mapaCategorias;//mapa categorias por nombre
	private HashMap<String,Temporada> mapaTemporadas;
	private HashMap<String,Sede> mapaSedes;//mapa sedes por nombre
	private HashMap<String,Seguro> mapaSeguros;//mapa seguros por id
	private HashMap<String,Tarifa> mapaTarifasExcedente;//mapa tarivas por id
	private HashMap<String, Factura> mapaFacturas; //mapa factura por id
	//USUARIOS
	private HashMap<String, Alquiler> mapaAlquileres;// mapa alquileres por id
	private HashMap<String,Cliente> mapaClientes; //mapa clientes por login
	private HashMap<String,Empleado> mapaEmpleados; //mapa empleados por login
	private HashMap<String, Licencia> mapaLicencias; //mapa licencias por numero de licencia
	private HashMap<String, Tarjeta> mapaTarjetas; //mapa tarjetas por número
	
	
	//Constructor
	
	public ControllerEmpleado(BaseDatos datos)
	{
		this.empleado=null;
		this.datos=datos;
		this.mapaCarros= datos.getMapaCarros();
		this.mapaAlquileres= datos.getMapaAlquileres();
		this.mapaEmpleados=datos.getMapaEmpleados();
		this.mapaLicencias= datos.getMapaLicencias();
		this.mapaSeguros= datos.getMapaSeguros();
		this.mapaFacturas = datos.getMapaFacturas();
		this.mapaClientes = datos.getMapaClientes();
		this.mapaCategorias= datos.getMapaCateg();
		this.mapaSedes=datos.getMapaSedes();
		this.mapaSeguros = datos.getMapaSeguros();
		this.mapaTarifasExcedente= datos.getMapaTarifas();
		this.mapaTemporadas=datos.getMapaTemporadas();
		this.mapaTarjetas = datos.getMapaTarjetas();
	}
	
	
	public BaseDatos getDatos()
	{
		return this.datos;
	}
	
	
	
	public void getEmpleado(String usuario)
	{
		if (mapaEmpleados.containsKey(usuario))
		{
			this.empleado= mapaEmpleados.get(usuario);
		}
		else {System.out.println("No se encuentra este usuario");}
	}
	
	public boolean verificarContrasena(String contrasena)
	{
		boolean r= false;
		if(empleado.getContrasena().equals(contrasena))
		{
			r= true;
		}
		
		return r;
	}
	
	private Carro Disponibilidad(Sede sede,Categoria categoria, LocalDateTime fechaInicio, LocalDateTime fechaFin)
	{
		boolean disponible= false;
		
		for (Carro carro:categoria.getCarros())
		{
			ArrayList<Reserva> reservas = carro.getReservas();
			
			if (reservas.isEmpty())
			{disponible = true;}
			else {
			for (Reserva reserva:reservas)
			{
				disponible = hayInterseccionIntervaloReservaConFechas( reserva, fechaInicio,fechaFin);
				
			}}
			
			if (disponible && sede.equals(carro.getSede()))
			{
				return carro;
			}	
		}
		
		return null;	
	}
	
	private boolean hayInterseccionIntervaloReservaConFechas(Reserva reserva,
			LocalDateTime fecha1,LocalDateTime fecha2) {
		LocalDateTime in=reserva.getFechaYHoraInicio();
		// anadimos 2 días a fin
		LocalDateTime fin=reserva.getFechaYHoraFin().plusDays(2);
		//deben pasar 4 cosas y cumplirse siempre
		//1 el inicio del intervalo no debe estar en el intervaloReserva
		if(hayFechaEnIntervalo(fecha1,in,fin)==true) {
			return true;
		}
		//2 el fin del intervalo no debe estar en el intervaloReserva
		if(hayFechaEnIntervalo(fecha2,in,fin)==true) {
			return true;}

	// 3 el inicio del intervaloReserva no debe estar en el intervalo
		if(hayFechaEnIntervalo(in,fecha1,fecha2)==true) {
			return true;
		}
		// 4 el fin del intervaloReserva no debe estar en el intervalo
		if(hayFechaEnIntervalo(fin,fecha1,fecha2)==true) {
			return true;
			
		}
		else {
			return false;
		}
	}
	
	private boolean hayFechaEnIntervalo(LocalDateTime fecha, LocalDateTime fecha1,
			LocalDateTime fecha2) {
		if(fecha.isAfter(fecha1) && fecha.isBefore(fecha2)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	//Para crear un alquiler donde no exista reserva
	
	public Alquiler CrearAlquiler( String usuario, String sedeDevolucion, String sedeRecoger,
			LocalDateTime fechaDeb, LocalDateTime fechaInicio, String categoria)
	{
		Cliente objCliente= mapaClientes.get(usuario);
		Sede objSedeDevolucion=mapaSedes.get(sedeDevolucion);
		Sede objSedeRecoger=mapaSedes.get(sedeRecoger);
		Categoria objCategoria = mapaCategorias.get(categoria);
		Temporada tarifa = tarifa(objCategoria, fechaInicio);
		Tarifa tarifaExcedente= null;
		
		Carro carro = Disponibilidad(objSedeRecoger, objCategoria,fechaInicio, fechaDeb);
		
		Alquiler alquiler= new Alquiler(objCliente, fechaDeb, fechaInicio, objSedeRecoger, objSedeDevolucion, carro);
		alquiler.setTarifa(tarifa);
		
		if (!(sedeDevolucion.equals(sedeRecoger)))
		{
			tarifaExcedente= tarifaExcedente(objCategoria, fechaInicio);
			alquiler.setTarifaExcedente(tarifaExcedente);
		}
		Factura factura= new Factura(objCliente,alquiler);
		mapaFacturas.put(factura.getId(), factura);
		alquiler.setFactura(factura);
		mapaAlquileres.put(alquiler.getAlquileresId(), alquiler);
		carro.setEstado("alquilado");
		return alquiler;
		
	}
	
// Crear alquiler para una reserva existente
	
	public Alquiler crearAlquilerReserva(String categoriaId, String usuario, LocalDateTime fechaInicio, LocalDateTime fechaFin)
	{
		Cliente cliente= mapaClientes.get(usuario);
		Categoria categoria = mapaCategorias.get(categoriaId);
		Reserva reserva = verificarReserva(categoria, cliente, fechaInicio, fechaFin);
		Sede sedeRecoger= reserva.getSedeInicio();
		Sede sedeDevolucion= reserva.getSedeFin();
		Temporada tarifa = tarifa(categoria, fechaInicio);
		Tarifa tarifaExcedente= null;
		Carro carro = reserva.getCarroReservado();
		
		Alquiler alquiler= new Alquiler(cliente, fechaFin, fechaInicio, sedeRecoger, sedeDevolucion, carro);
		alquiler.setTarifa(tarifa);
		
		if (!(sedeDevolucion.equals(sedeRecoger)))
		{
			tarifaExcedente = tarifaExcedente(categoria, fechaInicio);
			alquiler.setTarifaExcedente(tarifaExcedente);
		}
		
		mapaAlquileres.put(alquiler.getAlquileresId(), alquiler);
		Factura factura = new Factura(cliente, alquiler);
		factura.setPagoAnticipado();
		alquiler.setFactura(factura);
		mapaFacturas.put(factura.getId(), factura);
		carro.setEstado("alquilado");
		
		
		return alquiler;
		
	}
		
		
		
// Verificar si existe una reserva 
		private Reserva verificarReserva(Categoria categoria, Cliente cliente, LocalDateTime fechaInicio, LocalDateTime fechaFin)
		{
			
			for (Carro carro: categoria.getCarros())
			{
				for (Reserva reserva: carro.getReservas())
				{
					LocalDateTime inicio= reserva.getFechaYHoraInicio();
					LocalDateTime fin= reserva.getFechaYHoraFin();
					
					if (fechaInicio.isEqual(inicio) && fechaFin.isEqual(fin) && cliente.equals(reserva.getCliente()))
					{
						return reserva;
					}	
				}
			}
			
			return null;
			
		}
		
//Saber tarifa correspondiente según fecha y categoria
		
		private Temporada tarifa(Categoria categoria, LocalDateTime fechaInicio)
		{
			ArrayList <Temporada> hola = categoria.getTarifa();
			for (Temporada tarifa:categoria.getTarifa())
			{
				LocalDateTime inicio = tarifa.getInicioTemporada();
				LocalDateTime fin = tarifa.getFinTemporada();
				
				if (inicio.isEqual(fechaInicio) || fin.isAfter(fechaInicio))
				{
					return tarifa;
				}
			}
			
			return null;
		}
		
		private Tarifa tarifaExcedente(Categoria categoria, LocalDateTime fechaInicio)
		{
			for (Tarifa tarifa: categoria.getTarifaExcedente())
			{
				LocalDateTime inicio = tarifa.getFechaInicio();
				LocalDateTime fin = tarifa.getFechaFin();
				
				if (inicio.isEqual(fechaInicio) || fin.isAfter(fechaInicio))
				{
					return tarifa;
				}
			}
			
			return null;
		}
		
// Agregar las licencias al alquiler
		
		public void agregarLicencias(Alquiler alquiler, String idLicencia)
		{
			
				Licencia licencia = mapaLicencias.get(idLicencia);
				alquiler.setLicencia(licencia);
		}
		
		


		
// Poner fecha en que un carro saldra de mantenimiento o de limpieza
		
		public void ActualizarCarro ( String placa, LocalDateTime fechaHoy, int dias)
		{
			Carro carro = mapaCarros.get(placa);
			carro.setFechaDisponibleCons(fechaHoy.plusDays(dias));
			carro.setEstado("Limpieza o Mantenimiento");
		}
		
// Revisar que carros ya cumplieron con la fecha de mantenimientos o limpieza
		
		public void cumplimientoFechaCarro( LocalDateTime fechaHoy)
		{
			for (Carro carro: mapaCarros.values())
				if ((carro.getFechaDispCons()!= null))
				{if (fechaHoy.isAfter(carro.getFechaDispCons()))
				{
					carro.setFechaDisponibleCons(null);
					carro.setEstado("Disponible");
				}}
			
			
		}
		

//generar texto factura 
		
		public void generarFactura(Factura factura)
		{
			String id = "Id: "+ factura.getId() + "\n";
			String pagoAnticipado = "Pago Anticipado: "+ String.valueOf(factura.getPagoAnticipado())+ "\n";
			String total = "Total: " + String.valueOf(factura.getTotal())+ "\n";
			String cliente = "Cliente: " +  factura.getCliente().getNombre() + "\n";
			String alquiler = "Id alquiler: " + factura.getAlquiler().getAlquileresId() + "\n";
			String licencias = "Licencias ";
			for (Licencia licencia: factura.getAlquiler().getLicencias())
			{
				licencias += licencia.getNumero() + "  ";
			}
			
			System.out.println(id+pagoAnticipado+total+cliente+alquiler+ licencias + "\n");
		}

		
//Actualizar Dtaos
		
		public void actualizarDatos() throws IOException {
			datos.cargarTodosLosDatos();
		}
		
		
		

		
		
}
