package windows;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VentanaSeguro extends JFrame implements ActionListener {

	private CrearAlquiler ventana;
	private JComboBox<String> seguro1;
	private JComboBox<String> seguro2;
	
	public VentanaSeguro(CrearAlquiler ventana)
	{
		this.ventana = ventana;
		
		setTitle("Seguros");
		setSize(500, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
		
		JLabel label1 = new JLabel("Seguros");
		label1.setAlignmentX(Component.CENTER_ALIGNMENT);
		gbc.gridx = 0;
	    gbc.gridy = 0;
	    gbc.gridwidth = 2;
		panel.add(label1);
		
		String[] opciones = {null,"Perdida", "Accidente"};
        this.seguro1 = new JComboBox<>(opciones);
        this.seguro2 = new JComboBox<>(opciones);
        
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(seguro1);
        gbc.gridy = 2;
        panel.add(seguro2);
        
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
		
		 String seleccion1 = (String) seguro1.getSelectedItem();
		 String seleccion2 = (String) seguro2.getSelectedItem();
		 
		 if (seleccion1 != null)
		  {ventana.setSeguro(seleccion1);}
		 if (seleccion2 != null)
		  {ventana.setSeguro(seleccion2);}
		 
		 System.out.println("Cerrando la ventana");
		 dispose();

	}

}
