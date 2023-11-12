package windows;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Set;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import controller.BaseDatos;
import controller.ControllerEmpleado;
import model.Carro;

public class VentanaEmpleado extends JFrame implements ActionListener{
	
	//Atributos
	private static ControllerEmpleado elEmpleado; 
	private static JPanel panel;
	private static BaseDatos datos;
	
	
	
	//Constructor 
	
	public VentanaEmpleado()
	{
		setTitle("Empleado");
		setSize(500, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JLabel label1 = new JLabel("¿Que desea realizar?");
		label1.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(label1);
		
		JButton alquiler = new JButton("Crear Alquiler");
		alquiler.setActionCommand("ALQUILER");
        JButton mantenimiento = new JButton("Definir Fecha Mantenimiento");
        mantenimiento.setActionCommand("MANTENIMIENTO");
        JButton disponibilidad = new JButton("Actualizar Fecha Disponibilidad");
        disponibilidad.setActionCommand("DISPONIBILIDAD");
        
        alquiler.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrar el JButton horizontalmente
        mantenimiento.setAlignmentX(Component.CENTER_ALIGNMENT);
        disponibilidad.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        alquiler.addActionListener(this);
        mantenimiento.addActionListener(this);
        disponibilidad.addActionListener(this);
        
        panel.add(Box.createVerticalStrut(20));
        
        panel.add(alquiler);
        panel.add(Box.createVerticalGlue());
        panel.add(mantenimiento);
        panel.add(Box.createVerticalGlue());
        panel.add(disponibilidad);
        
        // Botón LogOut
        panel=BotonLogOut.crearBotonLogOut(panel, 200, 240, 150, 25);
        
        add(panel);
        
        setLocationRelativeTo(null); 
        setVisible(true);
		
	}
	
	
	//Verificar si el usuario existe para poder crear la ventana
	public static void login(BaseDatos datos) {
		VentanaEmpleado.datos = datos;
		elEmpleado =new ControllerEmpleado(datos);
		
		String usuario = VentanaPrincipal.getNombreUsuario().getText();
		String contrasena =VentanaPrincipal.getContrasena().getText();
		
		elEmpleado.logIn(usuario, contrasena);
		if(elEmpleado.getEmpleado()==null){
			System.out.println("Error ingresando sesión");
			VentanaPrincipal.getErrorLogIn().setText("Error Ingresando sesión!");
		}
		else {
			JOptionPane.showMessageDialog(null, "Ingresado Correctamente");
			new VentanaEmpleado();
		}
	}
	
	
	
	//Acceder a otro botón dependiendo del botón accionado
	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		
		if (comando.equals("ALQUILER")){
			
			new CrearAlquiler(this);
			
			}
		else if (comando.equals("MANTENIMIENTO")){
			
			new VentanaMantenimiento(this);
			
			}
		
		else if (comando.equals("DISPONIBILIDAD")){
			
			elEmpleado.cumplimientoFechaCarro( LocalDateTime.now());
			JOptionPane.showMessageDialog(null, "Se han actualizado los carros que ya no estan en mantenimiento");
			
			
			}
		
	}
	
	//acceder a categorias en datos
	public Set<String> getKeysCategoria()
	{
		return datos.getMapaCateg().keySet();
	}
	
	//Crear un alquiler
	
	public void CrearAlquiler( String reserva,String usuario, String sedeDevolucion, String sedeRecoger,
			LocalDateTime fechaDeb, LocalDateTime fechaInicio, String categoria,
			ArrayList<String> seguros, ArrayList<String> licencias, ArrayList<String> tarifas) throws IOException
	{
		if (reserva.equals("no"))
		{
			elEmpleado.CrearAlquiler(usuario, sedeDevolucion, sedeRecoger, fechaDeb, fechaInicio, categoria);
		}
		else {
			elEmpleado.crearAlquilerReserva(categoria, usuario, fechaInicio, fechaInicio);
		}
		
		if (seguros != null) {
		for (String seguro : seguros)
		{
			elEmpleado.setSeguro(seguro);
		}}
		
		
		if (licencias != null) {
		for (String licencia : licencias)
		{
			elEmpleado.setLicencia(licencia);
		}}
		
		if (tarifas != null) {
		for (String tarifa : tarifas)
		{
			elEmpleado.setSeguro(tarifa);
		}}
		
		elEmpleado.actualizarDatos();
		MostrarFactura();
		
		
	}
	
	// Dar mantenimiento
	
	public void ActualizarCarro ( String placa, LocalDateTime fechaHoy, int dias)
	{
		elEmpleado.ActualizarCarro(placa, fechaHoy, dias);
	}
	
	//Crear factura como ventana
	
	private void MostrarFactura()
	{
		new VentanaFactura( elEmpleado.getFactura());
	}

}
