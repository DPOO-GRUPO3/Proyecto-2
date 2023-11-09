package windows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JPanel;

import controller.ControllerCliente;



public class BotonLogOut implements ActionListener{

	private static JButton LogOut;
	public static JPanel crearBotonLogOut(JPanel panel,int x, int y, int width, int height) {
		 LogOut = new JButton("LogOut");
		    LogOut.setBounds(x, y, width, height);
		    
		    LogOut.addActionListener(new BotonLogOut());
		    
		    panel.add(LogOut);
			return panel;
	}
	
	
	
@Override
public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	ControllerCliente elCliente =VentanaCliente.getElCliente();
	try {
		elCliente.actualizarDatos();
		VentanaPrincipal.cargarVentanaPrincipal();
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
}

}
