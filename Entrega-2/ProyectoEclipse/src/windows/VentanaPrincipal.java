package windows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.BaseDatos;

import model.Usuario;
import view.Aplicacion;


public class VentanaPrincipal implements ActionListener{
public static Usuario getUsuario() {
		return usuario;
	}

	public static void setUsuario(Usuario usuario) {
		VentanaPrincipal.usuario = usuario;
	}

	public static BaseDatos getBaseDatos() {
		return baseDatos;
	}

	public static void setBaseDatos(BaseDatos baseDatos) {
		VentanaPrincipal.baseDatos = baseDatos;
	}

	public static JFrame getFrame() {
		return frame;
	}

	public static void setFrame(JFrame frame) {
		VentanaPrincipal.frame = frame;
	}

	public static JTextField getNombreUsuario() {
		return nombreUsuario;
	}

	public static void setNombreUsuario(JTextField nombreUsuario) {
		VentanaPrincipal.nombreUsuario = nombreUsuario;
	}

	public static JTextField getContrasena() {
		return contrasena;
	}

	public static void setContrasena(JTextField contrasena) {
		VentanaPrincipal.contrasena = contrasena;
	}

	public static JTextField getTipoUsuario() {
		return tipoUsuario;
	}

	public static void setTipoUsuario(JTextField tipoUsuario) {
		VentanaPrincipal.tipoUsuario = tipoUsuario;
	}

	// Atributos
	private static Usuario usuario;
	private static BaseDatos baseDatos;
	private static JFrame frame;
	private static JTextField nombreUsuario;
	private static JTextField contrasena;
	private static JTextField tipoUsuario;
	
// Métodos
	public static void main(String[] args) {
		baseDatos=Aplicacion.cargarDatos();
		cargarVentanaPrincipal();
    }

	public static void cargarVentanaPrincipal() {
		// TODO Auto-generated method stub
	    frame = new JFrame("RamenAutos");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    //Config panel
	    JPanel panel =new JPanel();
	    frame.setSize(500, 500);
	    frame.add(panel);
	    panel.setLayout(null);
	    
	    // Usuario
	    JLabel labelUsuario = new JLabel("Usuario");
	    labelUsuario.setBounds(10,20,80,25);
	    nombreUsuario = new JTextField(20);
	    nombreUsuario.setBounds(100,20,165,25);
	    panel.add(labelUsuario);
	    panel.add(nombreUsuario);
	    
	    // Contraseña
	    JLabel labelContra = new JLabel("Contrasena");
	    labelContra.setBounds(10,50,80,25);
	    contrasena = new JTextField(20);
	    contrasena.setBounds(100,50,165,25);
	    panel.add(labelContra);
	    panel.add(contrasena);
	    
	    // Tipo de usuario
	    JLabel labelInstrucciones = new JLabel("Ingrese 1 para admin, 2 para cliente o 3 para empleado");
	    labelInstrucciones.setBounds(10,80,350,25);
	    panel.add(labelInstrucciones);
	    
	    JLabel labelTipoUsuario = new JLabel("Tipo de Usuario");
	    labelTipoUsuario.setBounds(10,110,110,25);
	    tipoUsuario = new JTextField(20);
	    tipoUsuario.setBounds(120,110,165,25);
	    panel.add(labelTipoUsuario);
	    panel.add(tipoUsuario);
	    
	    // botón login
	    JButton inicio = new JButton("LogIn");
	    inicio.setBounds(10, 140, 80, 25);
	    inicio.addActionListener(new VentanaPrincipal());
	    panel.add(inicio);
	    frame.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String tipo =tipoUsuario.getText();
		if (tipo.equals("1")){
			//// Llamen aca lo del admin
		}
		else if (tipo.equals("2")) {
		System.out.println("Hi");
		VentanaCliente.login(baseDatos);
	}	
		else  if (tipo.equals("3")) {
			// LLamen lo del empleado
		}
}}
