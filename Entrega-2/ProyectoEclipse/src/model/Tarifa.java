package model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Tarifa {
	
	//Atributos
	
	private String id;
	
	private static int num=0;
	
	private double precioExcedente;

	private LocalDateTime fechaInicio;
	
	private LocalDateTime fechaFin;
	
	private Categoria categoria;
	
	//Constructor
	
	public Tarifa ( double precioExcedente, LocalDateTime fechaInicio,LocalDateTime fechaFin )
	{
		num++;
		this.id= String.valueOf(num);
		this.precioExcedente= precioExcedente;
		this.fechaInicio= fechaInicio;
		this.fechaFin= fechaFin;
	}
	
	//getters
	
	public Categoria getCategoria()
	{
		return this.categoria;
	}
	
	public String getId()
	{
		return this.id;
	}
	
	public double getPrecioExcedente()
	{
		return this.precioExcedente;
	}
	
	public LocalDateTime getFechaInicio()
	{
		return this.fechaInicio;
	}
	
	public LocalDateTime getFechaFin()
	{
		return this.fechaFin;
	}
	
	public void setCategoria(Categoria categoria)
	{
		this.categoria=categoria;
	}
	
	
	
}

