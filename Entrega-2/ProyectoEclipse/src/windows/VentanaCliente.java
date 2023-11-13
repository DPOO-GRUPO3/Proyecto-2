package windows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.stream.IntStream;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.BaseDatos;
import controller.ControllerCliente;

public class VentanaCliente implements ActionListener{

private static ControllerCliente elCliente;
private static JComboBox<Integer> diaInicio;
private static JComboBox<Integer> mesInicio;
private static JComboBox<Integer> anioInicio;
private static JComboBox<Integer> diaFin;
private static JComboBox<Integer> mesFin;
private static JComboBox<Integer> anioFin;
private static JComboBox<Integer> horaFin;
private static JComboBox<Integer> horaInicio;
private static JComboBox<String> categoriaComboBox;
public static ControllerCliente getElCliente() {
	return elCliente;
}

public static void setElCliente(ControllerCliente elCliente) {
	VentanaCliente.elCliente = elCliente;
}

public static JFrame getFrame() {
	return frame;
}

public static void setFrame(JFrame frame) {
	VentanaCliente.frame = frame;
}

public static JTextField getCategoria() {
	return categoria;
}

public static void setCategoria(JTextField categoria) {
	VentanaCliente.categoria = categoria;
}

public static JTextField getFechaInicial() {
	return fechaInicial;
}

public static void setFechaInicial(JTextField fechaInicial) {
	VentanaCliente.fechaInicial = fechaInicial;
}

public static JTextField getFechaFin() {
	return fechaFin;
}

public static void setFechaFin(JTextField fechaFin) {
	VentanaCliente.fechaFin = fechaFin;
}

public static JTextField getSedeInicio() {
	return sedeInicio;
}

public static void setSedeInicio(JTextField sedeInicio) {
	VentanaCliente.sedeInicio = sedeInicio;
}

public static JTextField getSedeFin() {
	return sedeFin;
}

public static void setSedeFin(JTextField sedeFin) {
	VentanaCliente.sedeFin = sedeFin;
}

private static JFrame frame;
private static JTextField categoria;
private static JTextField fechaInicial;
private static JTextField fechaFin;
private static JTextField sedeInicio;
private static JTextField sedeFin;
private static JLabel error;

public static void login(BaseDatos datos) {
	elCliente =new ControllerCliente();
	elCliente.setDatos(datos);
	
	String usuario = VentanaPrincipal.getNombreUsuario().getText();
	String contrasena =VentanaPrincipal.getContrasena().getText();
	
	elCliente.logIn(usuario, contrasena);
	if(elCliente.getCliente()==null){
		System.out.println("Error ingresando sesión");
		VentanaPrincipal.getErrorLogIn().setText("Error Ingresando sesión!");
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
    labelCategoria.setBounds(10,10,80,25);
    categoria = new JTextField(20);
    categoria.setBounds(100,10,165,25);
    panel.add(labelCategoria);
    panel.add(categoria);
    
	//FechaInicio
	JLabel FechaInicio= new JLabel ("FechaInicio:");
	FechaInicio.setBounds(10,50,80,25);
	
	diaInicio = new JComboBox<>();
	diaInicio.setBounds(100, 50, 50, 25);
    IntStream.rangeClosed(1, 31).forEach(diaInicio::addItem);
    
    mesInicio = new JComboBox<>();
    mesInicio.setBounds(160, 50, 50, 25);
    IntStream.rangeClosed(1, 12).forEach(mesInicio::addItem);
	
    anioInicio = new JComboBox<>();
    anioInicio.setBounds(220, 50, 70, 25);
    IntStream.rangeClosed(2023, 2033).forEach(anioInicio::addItem);
    
    horaInicio = new JComboBox<>();
    horaInicio.setBounds(300, 50, 60, 25);
    IntStream.rangeClosed(0, 23).forEach(horaInicio::addItem);
	
	panel.add(FechaInicio);
	panel.add(diaInicio);
	panel.add(mesInicio);
	panel.add(anioInicio);
	panel.add(horaInicio);
	//FechaFin
	JLabel FechaFin= new JLabel ("FechaFin:");
	FechaFin.setBounds(10,80,80,25);
			
	diaFin = new JComboBox<>();
	diaFin.setBounds(100, 80, 50, 25);
	IntStream.rangeClosed(1, 31).forEach(diaFin::addItem);
	        
	mesFin = new JComboBox<>();
	mesFin.setBounds(160, 80, 50, 25);
	 IntStream.rangeClosed(1, 12).forEach(mesFin::addItem);
			
	anioFin = new JComboBox<>();
	anioFin.setBounds(220, 80, 70, 25);
	IntStream.rangeClosed(2023, 2033).forEach(anioFin::addItem);
	
    horaFin = new JComboBox<>();
    horaFin.setBounds(300, 80, 60, 25);
    IntStream.rangeClosed(0, 23).forEach(horaFin::addItem);
	
	FechaFin.setBounds(10,80,80,25);
	

     panel.add(FechaFin);
	 panel.add(diaFin);
	 panel.add(mesFin);
	 panel.add(anioFin);
	 panel.add(horaFin);
	 // ETIQUETAS
		JLabel elDia= new JLabel ("Día");
		elDia.setBounds(100,40,80,10);
		
		JLabel elMes= new JLabel ("Mes");
		elMes.setBounds(160,40,80,10);
		
		JLabel elAnio = new JLabel ("Año");
		elAnio.setBounds(220,40,80,10);
		
		JLabel laHora = new JLabel ("Hora"); 
		laHora.setBounds(300,40,80,10);
		 panel.add(elDia);
		 panel.add(elMes);
		 panel.add(elAnio);
		 panel.add(laHora);
	
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
    
    // botón Crear Reservas
    JButton inicio = new JButton("Crear Reserva");
    inicio.setBounds(50, 240, 150, 25);
    inicio.addActionListener(new VentanaCliente());
    panel.add(inicio);
   
    
    // Botón LogOut
    panel=BotonLogOut.crearBotonLogOut(panel, 200, 240, 150, 25);
    
    // error
    error= new JLabel("");
    error.setBounds(10, 300, 400, 25);
    panel.add(error);
    
    
    frame.setVisible(true);
    
}

@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	String nombreCat=categoria.getText();
	String sedeRec=sedeInicio.getText();
	
	String sedeFin2=sedeFin.getText();
	
	
	int dInicio = (int) diaInicio.getSelectedItem();
	int mInicio = (int) mesInicio.getSelectedItem();
	int aInicio = (int) anioInicio.getSelectedItem();
	int hInicio = (int) horaInicio.getSelectedItem();
	LocalDateTime fechaInicio = LocalDateTime.of(aInicio, mInicio, dInicio, hInicio,0,0);
	
	int dFin = (int) diaFin.getSelectedItem();
	int mFin = (int) mesFin.getSelectedItem();
	int aFin = (int) anioFin.getSelectedItem();
	int hFin = (int) horaFin.getSelectedItem();
	LocalDateTime fechaFin = LocalDateTime.of(aFin, mFin, dFin, hFin,0,0);
	String timeFin=fechaFin.toString();
	String timeRecoger= fechaInicio.toString();
	double cobro=elCliente.crearReserva(nombreCat, sedeRec, timeRecoger, sedeFin2, timeFin);
	if(cobro!=0) {
	System.out.println("Su reserva está lista, se le cobró el 30% correspondiente a"
			+cobro );
	}
	else{
		System.out.println("No hay carros disponibles, intente cambiar la categoría o las fechas");
		error.setText("No hay carros disponibles, intente cambiar la categoría o las fechas");
	}

	
}
}

