package model;

public class Factura {
	
	//Atributos
	
	private String id;
	
	private static int numF;
	
	private double pagoAnticipado = 0;
	
	private double precioLicencias;
	
	private double total;
	
	private Cliente cliente;
	
	private Alquiler alquiler;
	

	
	//Constructor
	
	public Factura ( Cliente cliente, Alquiler alquiler)
	{
		this.numF++;
		this.id=String.valueOf(numF);
		this.cliente= cliente;
		this.alquiler= alquiler;
		calcularTotal(alquiler);
		
			
	}
	
	//getters
	
	public String getId()
	{
		return this.id;
	}
	
	public double getPagoAnticipado()
	{
		return this.pagoAnticipado;
	}
	
	public double getTotal()
	{
		return this.total;
	}
	
	public Cliente getCliente()
	{
		return this.cliente;
	}
	
	public Alquiler getAlquiler()
	{
		return this.alquiler;
	}
	
	public double getPrecioLicencias()
	{
		return this.precioLicencias;
	}
	
	//setters
	
	public void setPagoAnticipado()
	{
		this.pagoAnticipado= this.total * 0.3;
	}
	
	public void setPrecioLicencias(int num)
	{
		this.precioLicencias= 500*num;
	}
	
	public void setTotal(double total)
	{
		this.total= total;
	}
	
	
	//Metódo para calcular total
	
	private void calcularTotal(Alquiler alquiler)
	{
		double precioTarifa= alquiler.getTarifa().getTarifaTemporada();
		
		double precioExcedente=0;
		Tarifa tarifa = alquiler.getTarifaExcedente();
		if ((tarifa!= null))
		{
			 precioExcedente= alquiler.getTarifaExcedente().getPrecioExcedente();
			
		} 
		
		double precioSeguro=0;
		
		
		if (!(alquiler.getSeguro().isEmpty()))
		{
			for (Seguro seguro: alquiler.getSeguro()) 
				{
					precioSeguro += seguro.getPrecio();
				
				}
			
		} 
		
		this.total= precioTarifa + precioExcedente + precioSeguro + this.precioLicencias;
		
		
	}

}
