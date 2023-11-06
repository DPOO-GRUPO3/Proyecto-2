package model;

import java.util.ArrayList;

public class Categoria {
// Atributos
	private String nombreCat;
	private double tarifaCat;
	private ArrayList<Temporada> tarifas;
	private ArrayList<Tarifa>  tarifasExcedente;
	private ArrayList<Carro> carros;
	
	public Categoria(String nombreCat, double tarifaAsociada) {
		this.nombreCat=nombreCat;
		this.tarifaCat=tarifaAsociada;
		this.carros= new ArrayList<Carro>();
		this.tarifasExcedente = new ArrayList<Tarifa>();
		this.tarifas = new ArrayList<Temporada>();
		
	}
	
	//getters
	public String getNombre() {
	return nombreCat;
	
}
	public double tarifaCat() {
		return tarifaCat;
	}
	
	public ArrayList<Tarifa> getTarifaExcedente()
	{
		return this.tarifasExcedente;
	}
	
	public ArrayList<Temporada> getTarifa()
	{
		return this.tarifas;
	}
	
	public ArrayList<Carro> getCarros()
	{
		return this.carros;
	}
	
	
	//setters
	
	public void setTarifa(Temporada tarifa)
	{
		this.tarifas.add(tarifa);
	}
	
	public void setTarifaExcedente(Tarifa tarifaExcedente)
	{
		this.tarifasExcedente.add(tarifaExcedente);
	}
	
	public void setCarro(Carro carro)
	{
		this.carros.add(carro);
	}
	
	
	}

