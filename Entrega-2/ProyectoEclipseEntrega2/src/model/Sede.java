package model;

import java.time.LocalTime;
import java.util.ArrayList;

public class Sede {
	
	//Atributos
	
	private String nombre;
	
	private String ubicacion;
	
	private LocalTime inicio;
	
	private LocalTime fin;
	
	private Administrador administrador;
	
	private ArrayList<Carro> carros;
	
	private ArrayList<Usuario> usuarios;
	
	//Constructor
	
	public Sede (String nombre, String ubicacion, LocalTime inicio, LocalTime fin, Administrador administrador)
	{
		this.nombre = nombre;
		this.ubicacion= ubicacion;
		this.inicio = inicio;
		this.fin = fin;
		this.administrador = administrador;
		this.carros = new ArrayList<Carro>();
		this.usuarios= new ArrayList<Usuario>();}
		
	
	
	//Métodos consultar información
	

	
	public String getNombre()
	{
		return this.nombre;
	}
	
	public String getUbicacion()
	{
		return this.ubicacion;
	}
	
	public LocalTime getInicio()
	{
		return this.inicio;
	}
	
	public LocalTime getFin()
	{
		return this.fin;
	}
	
	public ArrayList<Carro> getCarro()
	{ 
		return this.carros;
	}
	
	
	public Administrador setAdministrador(Administrador administrador)
	{
		return this.administrador;
	}
	
	//setters
	
	public void setCarro(Carro carro)
	{
		carros.add(carro);
	}
	

	
	public void setUsuarios(Usuario usuario)
	{
		usuarios.add(usuario);
	}
	
	
	
	
	
	

}
