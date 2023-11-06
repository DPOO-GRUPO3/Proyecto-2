package model;
//Llenar con atributos y getters unicamente
public class Licencia {
private String numero;
private String pais;
private String fechaVens;
private String rutaImagen;
private String alqu;

public Licencia(String numero,String pais,String
		fechaVens,String rutaImagen) {

	this.fechaVens=fechaVens;
	this.numero=numero;
	this.pais=pais;
	this.rutaImagen=rutaImagen;
	this.alqu="-";
	
}

public String getFechaVens() {
	return fechaVens;
}
public String getNumero() {
	return numero;
}
public String getPais() {
	return pais;
}
public String getRutaImagen() {
	return rutaImagen;
}
public String getAlq() {
	return alqu;
}
public void setAlq(String alq) {
	this.alqu=alq;
}
}
