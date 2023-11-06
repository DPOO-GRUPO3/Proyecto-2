package model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Carro {
// Atributos
	
	// Los primeros son atributos solo de datos generales del vehículo
	private String placa;
	private String marca;
	private String modelo;
	private String color;
	private String tipoTransmision;
	// La factura tiene información sobre el cliente y cuando y donde devolverá el carro
	// si usoActual es null, el carro está en alguna cede
	private Alquiler usoActual;
	private ArrayList<Reserva> reservas;
	private Categoria categoria;
	// Si cede es null, el carro está prestado en el momento. 
	private Sede sede;
	private String estado;
	// Fecha en que el carro dejara de estar en limpieza o mantenimiento, si su estado
	// disponible, su valor es null
	private LocalDateTime fechaDispCons;
	
	//Método constructor
	public Carro(String placa,String marca,String modelo, String color,String tipoTransmision) {
		this.placa = placa;
		this.marca=marca;
		this.modelo=modelo;
		this.color=color;
		this.tipoTransmision=tipoTransmision;
		
		this.categoria =null;
		this.sede=null;
		this.reservas= new ArrayList<Reserva>();
		this.fechaDispCons=null;
		this.usoActual=null;
		this.estado=null;
	}
	// Métodos getters
	public String getPlaca() {
		return placa;
	}
	public String getmarca() {
		return marca;
	}
	public String getModelo() {
		return modelo;
	}
	public String getColor() {
		return color;
	}
	public String getTipoTransmision() {
		return tipoTransmision;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public Sede getSede() {
		return sede;
	}
	public LocalDateTime getFechaDispCons() {
		return fechaDispCons;
	}
	public ArrayList<Reserva> getReservas() {
		return reservas;
	}
	public Alquiler getUsoActual() {
		return usoActual;
	}
	public String getEstado() {
		return estado;
	}
	//Métodos setters
	public void setCategoria(Categoria categoria){
		this.categoria = categoria;
	}
	public void setCede(Sede sede){
		this.sede = sede;
	}
	public void agregarReserva(Reserva reserva){
		this.reservas.add(reserva);
	}
	public void setFechaDisponibleCons(LocalDateTime fechaDispCons){
		this.fechaDispCons = fechaDispCons;
	}
	public void setUsoActual(Alquiler usoActual) {
		this.usoActual=usoActual;
	}
	public void setEstado(String estado) {
		this.estado=estado;
	}
	
	
}
