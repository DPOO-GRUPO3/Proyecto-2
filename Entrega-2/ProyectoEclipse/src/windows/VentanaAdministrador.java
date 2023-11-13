package windows;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JPanel;


import controller.BaseDatos;
import controller.ControllerCliente;
import controller.ControllerEmpleado;
import controller.ControllerAdministrador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaAdministrador implements ActionListener {
    private static JFrame frame;

    public static JFrame getFrame() {
        return frame;
    }

    public static void setFrame(JFrame frame) {
        VentanaAdministrador.frame = frame;
    }

    public static void cargarVentanaAdministrador() {
        frame = new JFrame("Menú de Administrador");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.setSize(500, 500);
        frame.add(panel);
        panel.setLayout(null);

        JLabel titulo = new JLabel("Bienvenido Admin!");
        titulo.setBounds(10, 10, 300, 35); // Alineación izquierda
        titulo.setFont(new Font("Arial", Font.BOLD, 20)); // Estilo del título
        panel.add(titulo);

        JLabel mensaje = new JLabel("Selecciona alguna de las siguientes opciones:");
        mensaje.setBounds(10, 50, 400, 25); // Alineación izquierda
        mensaje.setFont(new Font("Arial", Font.PLAIN, 16)); // Estilo del mensaje
        panel.add(mensaje);

        JButton adminLocalButton = new JButton("Agregar/Eliminar Administrador Local");
        adminLocalButton.setBounds(50, 90, 300, 25);
        adminLocalButton.addActionListener(new VentanaAdministrador());
        panel.add(adminLocalButton);

        JButton empleadoButton = new JButton("Agregar/Eliminar Empleado");
        empleadoButton.setBounds(50, 140, 300, 25);
        empleadoButton.addActionListener(new VentanaAdministrador());
        panel.add(empleadoButton);

        JButton autoButton = new JButton("Actualizar/Eliminar Auto");
        autoButton.setBounds(50, 190, 300, 25);
        autoButton.addActionListener(new VentanaAdministrador());
        panel.add(autoButton);

        JButton vehiculosMensualesButton = new JButton("Vehículos Disponibles al año(Gráfica)");
        vehiculosMensualesButton.setBounds(50, 240, 300, 25);
        vehiculosMensualesButton.addActionListener(new VentanaAdministrador());
        panel.add(vehiculosMensualesButton);

        JPanel logOutButton = BotonLogOut.crearBotonLogOut(panel, 50, 290, 300, 25);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Agregar/Eliminar Administrador Local")) {
        	JPanel panel = new JPanel(new GridLayout(0, 1));

            JTextField docIdentidadField = new JTextField(10);
            JTextField nombreField = new JTextField(15);
            JTextField usuarioField = new JTextField(15);
            JPasswordField passwordField = new JPasswordField(15);

            panel.add(new JLabel("Ingrese el documento de identidad del Administrador:"));
            panel.add(docIdentidadField);
            panel.add(new JLabel("Ingrese el nombre del Administrador:"));
            panel.add(nombreField);
            panel.add(new JLabel("Asigne un nombre de usuario para el ingreso del Administrador:"));
            panel.add(usuarioField);
            panel.add(new JLabel("Asigne un password para la contraseña del Adminstrador:"));
            panel.add(passwordField);

            int result = JOptionPane.showConfirmDialog(null, panel, "Agregar/Eliminar Administrador Local",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                String docIdentidad = docIdentidadField.getText();
                String nombre = nombreField.getText();
                String usuario = usuarioField.getText();
                String password = new String(passwordField.getPassword());

                
            }
        	
        	
        } else if (e.getActionCommand().equals("Agregar/Eliminar Empleado")) {
        	JPanel panel = new JPanel(new GridLayout(0, 1));

            JTextField docIdentidadField = new JTextField(10);
            JTextField nombreField = new JTextField(15);
            JTextField usuarioField = new JTextField(15);
            JPasswordField passwordField = new JPasswordField(15);

            panel.add(new JLabel("Ingrese el documento de identidad del empleado:"));
            panel.add(docIdentidadField);
            panel.add(new JLabel("Ingrese el nombre del empleado:"));
            panel.add(nombreField);
            panel.add(new JLabel("Asigne un nombre de usuario para el ingreso del empleado:"));
            panel.add(usuarioField);
            panel.add(new JLabel("Asigne un password para la contraseña del usuario:"));
            panel.add(passwordField);

            int result = JOptionPane.showConfirmDialog(null, panel, "Agregar/Eliminar Administrador Local",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                String docIdentidad = docIdentidadField.getText();
                String nombre = nombreField.getText();
                String usuario = usuarioField.getText();
                String password = new String(passwordField.getPassword());

                
            }
        	
        	
        } else if (e.getActionCommand().equals("Actualizar/Eliminar Auto")) {
        	JPanel panel = new JPanel(new GridLayout(0, 1));

            JTextField placaField = new JTextField(10);
            JTextField marcaField = new JTextField(15);
            JTextField modeloField = new JTextField(15);
            JTextField transmisionField = new JTextField(15);

            panel.add(new JLabel("Ingrese la placa del vehiculo:"));
            panel.add(placaField);
            panel.add(new JLabel("Ingrese la marca del vehiculo:"));
            panel.add(marcaField);
            panel.add(new JLabel("Ingrese el modelo del vehiculo:"));
            panel.add(modeloField);
            panel.add(new JLabel("Escriba el tipo de transmision del vehiculo:"));
            panel.add(transmisionField);

            int result = JOptionPane.showConfirmDialog(null, panel, "Actualizar/Eliminar Auto",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                String placa = placaField.getText();
                String marca = marcaField.getText();
                String modelo = modeloField.getText();
                String transmision = transmisionField.getText();

                // Aquí puedes manejar la lógica para agregar o eliminar el vehículo
            }
        	
        	
        } else if (e.getActionCommand().equals("Vehículos Disponibles al año(Gráfica)")) {
        	
                DatosDisponibilidad datos = new DatosDisponibilidad();
                datos.cargarDatosDesdeArchivo("./data/carro.txt");

                VentanaGraficoAutos ventana = new VentanaGraficoAutos(datos);
                ventana.mostrarVentana();
       
        }
        
    }
}