package windows;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class VentanaFactura extends JFrame {
	
	private ArrayList<String> datos;
	
	public VentanaFactura(ArrayList<String> datos)
	{
		this.datos= datos;
		
		setTitle("Factura");
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel(new GridBagLayout()); // GridLayout con 2 columnas
		GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); 
        gbc.fill = GridBagConstraints.HORIZONTAL;
		
		//Reserva
		
		JLabel factura = new JLabel("Factura");
		
		gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(factura);
		
		//id
		
		JLabel id1= new JLabel ("idFactura:");
		JLabel id2 = new JLabel (datos.get(0));
		
		gbc.gridx = 0;
        gbc.gridy = 1;
		panel.add(id1,gbc);
		gbc.gridx = 1;
		panel.add(id2, gbc);
		
		//PagoAnticipaado
		
		JLabel pagoAnticipado1= new JLabel ("Pago Anticipado:");
		JLabel pagoAnticipado2 = new JLabel (datos.get(1));
				
		gbc.gridx = 0;
		gbc.gridy = 2;
		panel.add(pagoAnticipado1,gbc);
		gbc.gridx = 1;
		panel.add(pagoAnticipado2, gbc);
		
		//precioLicencias
		
		JLabel precioLicencias1= new JLabel ("Precio Licencias:");
		JLabel precioLicencias2 = new JLabel (datos.get(2));
				
		gbc.gridx = 0;
		gbc.gridy = 3;
		panel.add(precioLicencias1,gbc);
		gbc.gridx = 1;
		panel.add(precioLicencias2, gbc);
		
		//total
		
		JLabel total1= new JLabel ("Total:");
		JLabel total2 = new JLabel (datos.get(3));
				
		gbc.gridx = 0;
		gbc.gridy = 4;
		panel.add(total1,gbc);
		gbc.gridx = 1;
		panel.add(total2, gbc);
		
		add(panel);
		setVisible(true);
		
	
	}

}
