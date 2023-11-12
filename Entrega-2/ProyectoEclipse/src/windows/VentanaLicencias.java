package windows;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class VentanaLicencias extends JFrame implements ActionListener {

	/**
	 * 
	 */
	
	private CrearAlquiler ventana;
	private JTextField licencia1;
	private JTextField licencia2;
	private JTextField licencia3;
	private JTextField licencia4;
	
	public VentanaLicencias(CrearAlquiler ventana)
	{
		this.ventana = ventana;
		
		setTitle("Licencias");
		setSize(500, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
		
		JLabel label1 = new JLabel("Licencias");
		 label1.setAlignmentX(Component.CENTER_ALIGNMENT);
	     gbc.gridx = 0;
	     gbc.gridy = 0;
	     gbc.gridwidth = 2;
		panel.add(label1,gbc);
		
		this.licencia1 = new JTextField(30);
		this.licencia2 = new JTextField(30);
		this.licencia3 = new JTextField(30);
		this.licencia4 = new JTextField(30);
        
		gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(licencia1,gbc);
        gbc.gridy = 2;
        panel.add(licencia2,gbc);
        gbc.gridy = 3;
        panel.add(licencia3,gbc);
        gbc.gridy = 4;
        panel.add(licencia4,gbc);
        
        JButton boton = new JButton("Ok");
        boton.addActionListener(this);
        
        add(panel);
        gbc.gridy = 5;
        panel.add(boton,gbc);
        setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		 String seleccion1 = licencia1.getText();
		 String seleccion2 = licencia2.getText();
		 String seleccion3 = licencia3.getText();
		 String seleccion4 = licencia4.getText();
		 
		 if (!seleccion1.isEmpty())
		  {ventana.setLicencia(seleccion1);}
		 if (!seleccion2.isEmpty())
		  {ventana.setLicencia(seleccion2);}
		 if (!seleccion3.isEmpty())
		  {ventana.setLicencia(seleccion3);}
		 if (!seleccion4.isEmpty())
		  {ventana.setLicencia(seleccion4);}
		 
		 dispose();

	}

}
