package model;

public class Seguro {
	
	// Atributos
	
	private String nombre;
	
	private String id;
	
	private static int num=0;
	
	private double precio;
	
	public Seguro( String nombre, double precio)
	{
		this.nombre= nombre;
		num++;
		this.id= String.valueOf(num);
		this.precio= precio;
	}
	
	//getters
	
	public String getNombre()
	{
		return this.nombre;
	}
	
	public String getId()
	{
		return this.id;
	}
	
	public double getPrecio()
	{
		return this.precio;
	}
	
	//setters
	
	public void setPrecio(double precio)
	{
		this.precio= precio;
	}
	
	
}
