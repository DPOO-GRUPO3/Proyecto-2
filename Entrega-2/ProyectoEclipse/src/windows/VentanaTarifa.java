package windows;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.stream.IntStream;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VentanaTarifa extends JFrame implements ActionListener {

	private CrearAlquiler ventana;
	private JComboBox<Integer> tarifa2;
	private JComboBox<Integer> tarifa1;

	
	public VentanaTarifa(CrearAlquiler ventana)
	{
		this.ventana = ventana;
		
		setTitle("Taarifas Excedentes");
		setSize(500, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
		
        String categoria = ventana.Categoria();
        
		JLabel label1 = new JLabel("Tarifas Excedentes de " + categoria );
		label1.setAlignmentX(Component.CENTER_ALIGNMENT);
		gbc.gridx = 0;
	    gbc.gridy = 0;
	    gbc.gridwidth = 2;
		panel.add(label1);
		
		this.tarifa1 = new JComboBox<Integer>();
        this.tarifa2 = new JComboBox<Integer>();
		
		if (categoria.equals("todoterreno"))
		{
			IntStream.rangeClosed(1, 9).forEach(tarifa1::addItem);
			IntStream.rangeClosed(1, 9).forEach(tarifa2::addItem);
		}
		else if (categoria.equals("familiares"))
		{
			IntStream.rangeClosed(10, 18).forEach(tarifa1::addItem);
			IntStream.rangeClosed(10, 18).forEach(tarifa2::addItem);
		}
		else if (categoria.equals("lujoso"))
		{
			IntStream.rangeClosed(19, 27).forEach(tarifa1::addItem);
			IntStream.rangeClosed(19, 27).forEach(tarifa2::addItem);
		}
		
		Dimension comboBoxDimension = new Dimension(100, 30); // Ajusta el ancho según tus necesidades
		tarifa1.setPreferredSize(comboBoxDimension);
		tarifa2.setPreferredSize(comboBoxDimension);
        
        
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(tarifa1);
        gbc.gridy = 2;
        panel.add(tarifa2);
        
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
		
		 String seleccion1 = (String) tarifa1.getSelectedItem();
		 String seleccion2 = (String) tarifa2.getSelectedItem();
		 
		 if (seleccion1 != null)
		  {ventana.setTarifa(seleccion1);}
		 if (seleccion2 != null)
		  {ventana.setTarifa(seleccion2);}
		 
		 System.out.println("Cerrando la ventana");
		 dispose();

	}

}
