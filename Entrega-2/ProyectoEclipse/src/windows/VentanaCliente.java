package windows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.BaseDatos;
import controller.ControllerCliente;

public class VentanaCliente implements ActionListener{

private static ControllerCliente elCliente;
private static JFrame frame;
private static JTextField categoria;
private static JTextField fechaInicial;
private static JTextField fechaFin;
private static JTextField sedeInicio;
private static JTextField sedeFin;

public static void login(BaseDatos datos) {
	elCliente =new ControllerCliente();
	elCliente.setDatos(datos);
	
	String usuario = VentanaPrincipal.getNombreUsuario().getText();
	String contrasena =VentanaPrincipal.getContrasena().getText();
	
	elCliente.logIn(usuario, contrasena);
	if(elCliente.getCliente()==null){
		System.out.println("Error ingresando sesión");
		
	}
	else {
		System.out.println("Ingresado correctamente");
		cargarVentanaCliente();
	}
}

private static void cargarVentanaCliente() {
	// TODO Auto-generated method stub
    frame = new JFrame("Crear Reserva");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    //Config panel
    JPanel panel =new JPanel();
    frame.setSize(500, 500);
    frame.add(panel);
    panel.setLayout(null);
    
    // Categoria
    JLabel labelCategoria = new JLabel("Categoria");
    labelCategoria.setBounds(10,20,80,25);
    categoria = new JTextField(20);
    categoria.setBounds(100,20,165,25);
    panel.add(labelCategoria);
    panel.add(categoria);
    
    // Fecha Inicial
    JLabel labelFechaIn = new JLabel("Fecha Inicio");
    labelFechaIn.setBounds(10,50,80,25);
    fechaInicial = new JTextField(20);
    fechaInicial.setBounds(100,50,165,25);
    panel.add(labelFechaIn);
    panel.add(fechaInicial);
    
    // Fecha Fin
    JLabel labelFechaFin = new JLabel("Fecha Fin");
    labelFechaFin.setBounds(10,80,80,25);
    fechaFin = new JTextField(20);
    fechaFin.setBounds(100,80,165,25);
    panel.add(labelFechaFin);
    panel.add(fechaFin);
    
    // Sede Inicio
    JLabel labelSedeInicio = new JLabel("Sede Inicial");
    labelSedeInicio.setBounds(10,110,80,25);
    sedeInicio = new JTextField(20);
    sedeInicio.setBounds(100,110,165,25);
    panel.add(labelSedeInicio);
    panel.add(sedeInicio);
    
    // SedeFin
    JLabel labelSedeFin = new JLabel("Sede Final");
    labelSedeFin.setBounds(10,130,80,25);
    sedeFin = new JTextField(20);
    sedeFin.setBounds(100,130,165,25);
    panel.add(labelSedeFin);
    panel.add(sedeFin);
    
    // botón login
    JButton inicio = new JButton("Crear Reserva");
    inicio.setBounds(50, 240, 150, 25);
    inicio.addActionListener(new VentanaCliente());
    panel.add(inicio);
    frame.setVisible(true);
}

@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	String nombreCat=categoria.getText();
	String sedeRec=sedeInicio.getText();
	String timeRecoger=fechaInicial.getText();
	String sedeFin2=sedeFin.getText();
	String timeFin=fechaFin.getText();
	double cobro=elCliente.crearReserva(nombreCat, sedeRec, timeRecoger, sedeFin2, timeFin);
	if(cobro!=0) {
	System.out.println("Su reserva está lista, se le cobró el 30% correspondiente a"
			+cobro );
	}
	else{
		System.out.println("No hay carros disponibles, intente cambiar la categoría o las fechas");
	}

	
}
}

