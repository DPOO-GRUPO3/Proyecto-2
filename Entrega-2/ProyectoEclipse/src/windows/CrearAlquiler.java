package windows;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.IntStream;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import controller.BaseDatos;
import controller.ControllerEmpleado;
import model.Categoria;
import model.Licencia;
import model.Seguro;

public class CrearAlquiler extends JFrame implements ActionListener {

	private VentanaEmpleado ventana;
	private JRadioButton si;
	private JRadioButton no;
	private JTextField textoUsuario;
	private JTextField textoSedeRecoger;
	private JTextField textoSedeDevolucion;
	private JComboBox<Integer> diaInicio;
	private JComboBox<Integer> mesInicio;
	private JComboBox<Integer> anioInicio;
	private JComboBox<Integer> diaFin;
	private JComboBox<Integer> mesFin;
	private JComboBox<Integer> anioFin;
	private JComboBox<String> categoriaComboBox;
	private BaseDatos datos;
	private ArrayList<String> seguros;
	private ArrayList<String> licencias;
	private ArrayList<String> tarifas;
	
	public CrearAlquiler(VentanaEmpleado ventana) {
		
		this.ventana = ventana;
		this.seguros = new ArrayList<String> ();
		this.licencias = new ArrayList<String> ();
		
		setTitle("Alquiler");
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel(new GridBagLayout()); // GridLayout con 2 columnas
		GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); 
        gbc.fill = GridBagConstraints.HORIZONTAL;
		
		//Reserva
		
		JLabel reserva = new JLabel("Reserva");
		
		ButtonGroup buttonGroup = new ButtonGroup();
		this.si = new JRadioButton("Si");
		this.no = new JRadioButton("No");
		
		buttonGroup.add(si);
		buttonGroup.add(no);
		
		si.setOpaque(false);
		no.setOpaque(false);
		
		gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(reserva, gbc);
        gbc.gridx = 1;
		panel.add(si,gbc);
		gbc.gridx = 2;
		panel.add(no);
		
		//Cliente
		
		JLabel usuario= new JLabel ("Cliente (usuario):");
		this.textoUsuario = new JTextField(30);
		
		gbc.gridx = 0;
        gbc.gridy = 1;
		panel.add(usuario,gbc);
		gbc.gridx = 1;
		panel.add(textoUsuario, gbc);
		
		//SedeRecoger
		
		JLabel sedeRecoger= new JLabel ("Sede Recoger:");
		this.textoSedeRecoger = new JTextField(30);
				
		gbc.gridx = 0;
		gbc.gridy = 2;
		panel.add(sedeRecoger,gbc);
		gbc.gridx = 1;
		panel.add(textoSedeRecoger,gbc);
		
		//SedeDevolucion
		
		JLabel sedeDevolucion= new JLabel ("Sede Devolucion:");
		this.textoSedeDevolucion = new JTextField(30);
		
		gbc.gridx = 0;
        gbc.gridy = 3;
		panel.add(sedeDevolucion, gbc);
		gbc.gridx = 1;
		panel.add(textoSedeDevolucion, gbc);
		
		//FechaInicio
		JLabel FechaInicio= new JLabel ("FechaInicio:");
		
		this.diaInicio = new JComboBox<>();
        IntStream.rangeClosed(1, 31).forEach(diaInicio::addItem);
        
        this.mesInicio = new JComboBox<>();
        IntStream.rangeClosed(1, 12).forEach(mesInicio::addItem);
		
        this.anioInicio = new JComboBox<>();
        IntStream.rangeClosed(2023, 2033).forEach(anioInicio::addItem);
		
        gbc.gridx = 0;
        gbc.gridy = 4;
		panel.add(FechaInicio, gbc);
		gbc.gridx = 1;
		panel.add(diaInicio, gbc);
		gbc.gridx = 2;
		panel.add(mesInicio, gbc);
		gbc.gridx = 3;
		panel.add(anioInicio, gbc);
		
		//FechaFin
		JLabel FechaFin= new JLabel ("FechaFin:");
				
		this.diaFin = new JComboBox<>();
		IntStream.rangeClosed(1, 31).forEach(diaFin::addItem);
		        
		this.mesFin = new JComboBox<>();
		 IntStream.rangeClosed(1, 12).forEach(mesFin::addItem);
				
		this.anioFin = new JComboBox<>();
		IntStream.rangeClosed(2023, 2033).forEach(anioFin::addItem);
				
		 gbc.gridx = 0;
		 gbc.gridy = 5;
	     panel.add(FechaFin, gbc);
		 gbc.gridx = 1;
		 panel.add(diaFin, gbc);
		 gbc.gridx = 2;
		 panel.add(mesFin, gbc);
		 gbc.gridx = 3;
		 panel.add(anioFin, gbc);
		 
		 //Categoría
		 
		 JLabel categoria= new JLabel ("Categoria:");
		 this.categoriaComboBox = new JComboBox<>();
		 
		 categoriaComboBox.addItem("");
		 for (String clave : ventana.getKeysCategoria()) {
             categoriaComboBox.addItem(clave);
         }
		 
		 gbc.gridx = 0;
		 gbc.gridy = 6;
	     panel.add(categoria, gbc);
		 gbc.gridx = 1;
		 panel.add(categoriaComboBox, gbc);
		 
		 //Seguros
		 
		 JButton seguro = new JButton("Seguros");
		 seguro.setActionCommand("SEGURO");
		 seguro.addActionListener(this);

		 gbc.gridx = 0;
		 gbc.gridy = 7;
	     panel.add(seguro, gbc);
	     
	     //Licencias
		 
		 JButton licencia = new JButton("Licencias");
		 licencia.setActionCommand("LICENCIA");
		 licencia.addActionListener(this);

		 gbc.gridy = 7;
		 gbc.gridx = 1;
	     panel.add(licencia, gbc);
	     
	     //Tarifa
	     JButton tarifa = new JButton("Tarifas Excedentes");
		 tarifa.setActionCommand("TARIFA");
		 tarifa.addActionListener(this);

		 gbc.gridy = 7;
		 gbc.gridx = 2;
	     panel.add(tarifa, gbc);
	     
	     //Continuar
		 
		 JButton crear = new JButton("Crear");
		 crear.setActionCommand("CREAR");
		 crear.addActionListener(this);

		 gbc.gridx = 1;
		 gbc.gridy = 9;
	     panel.add(crear, gbc);
	     
	     add(panel);
	     setVisible(true);
		 
		 
	}
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
    
		String comando = e.getActionCommand();
		
		if (comando.equals("SEGURO")){
			
			new VentanaSeguro(this);
			
			
			}
		else if (comando.equals("LICENCIA")){
			
			new VentanaLicencias(this);
			
		}
		
		else if (comando.equals("TARIFA")){
			
			new VentanaTarifa(this);
			
		}
		
		else if (comando.equals("CREAR")){
			
			 String reserva = null;
			 
			if (si.isSelected()) {
		          reserva = "si";
		          System.out.println("si");
		      } else if (no.isSelected()) {
		    	  reserva = "no";
		    	  System.out.println("no");
		      }
			String usuario = textoUsuario.getText();
			
			String sedeDevolucion = textoSedeDevolucion.getText();
			
			String sedeRecoger = textoSedeRecoger.getText();
			
			int dInicio = (int) diaInicio.getSelectedItem();
			int mInicio = (int) mesInicio.getSelectedItem();
			int aInicio = (int) anioInicio.getSelectedItem();
			LocalDateTime fechaInicio = LocalDateTime.of(aInicio, mInicio, dInicio, 0,0,0);
			
			int dFin = (int) diaFin.getSelectedItem();
			int mFin = (int) mesFin.getSelectedItem();
			int aFin = (int) anioFin.getSelectedItem();
			LocalDateTime fechaFin = LocalDateTime.of(aFin, mFin, dFin, 23,59,59);
			
			String categoria = (String) categoriaComboBox.getSelectedItem();
			
			try {
				ventana.CrearAlquiler(reserva, usuario, sedeDevolucion, sedeRecoger, 
						fechaFin, fechaInicio, categoria, seguros, licencias, tarifas);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
		
	}
	
	//AgregarLicencia
	
	public void setLicencia(String licencia)
	{
		this.licencias.add(licencia);
	}
	
	//AgregarSeguro
	
	public void setSeguro(String seguro)
	{
		this.seguros.add(seguro);
	}
	
	//Agregar Tarifa Excedente
	public void setTarifa(String tarifa)
	{
		this.tarifas.add(tarifa);
	}
	
	//Saber que hay en texto categoria
	
	public String Categoria() {
		
		return (String) categoriaComboBox.getSelectedItem();
		
	}
	
	

}
