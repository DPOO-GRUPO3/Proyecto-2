package windows;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDateTime;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class VentanaMantenimiento extends JFrame implements ActionListener{

	private VentanaEmpleado ventana;
	private JTextField textoPlaca;
	private JComboBox<String> textoDias;
	
	public VentanaMantenimiento(VentanaEmpleado ventana) {
		
		this.ventana = ventana;
		
		setTitle("Mantenimiento Carro");
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel(new GridBagLayout()); // GridLayout con 2 columnas
		GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); 
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
      //Placa
		
      	JLabel placa= new JLabel ("Placa:");
      	this.textoPlaca = new JTextField(30);
      		
      	gbc.gridx = 0;
        gbc.gridy = 0;
      	panel.add(placa,gbc);
      	gbc.gridx = 1;
      	panel.add(textoPlaca, gbc);
      	
      	//Dias
		
      	JLabel dias= new JLabel ("Dias:");
      	String[] opciones = {null,"0","1", "2"};
      	this.textoDias = new JComboBox<String>(opciones);
      		
      	gbc.gridx = 0;
        gbc.gridy = 1;
      	panel.add(dias,gbc);
      	gbc.gridx = 1;
      	panel.add(textoDias, gbc);
      	
      	JButton boton = new JButton("Ok");
        boton.addActionListener(this);
         
        gbc.gridwidth = 2; // Ajuste el ancho para cubrir dos columnas
        gbc.gridx = 0;
        gbc.gridy = 3;
         panel.add(boton);
         
         add(panel);
         
         setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
	 
		String placa = textoPlaca.getText();
		int dias = Integer.parseInt((String) textoDias.getSelectedItem());
		LocalDateTime fecha = LocalDateTime.now();
		
		try {
			ventana.ActualizarCarro(placa, fecha, dias);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		dispose();
		
	}

}
