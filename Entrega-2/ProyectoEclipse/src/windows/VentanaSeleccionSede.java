package windows;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import controller.BaseDatos;
import controller.ControllerAdministrador;

public class VentanaSeleccionSede implements ActionListener {
    private JFrame frame;
    private ButtonGroup grupoSedes = new ButtonGroup();

    public VentanaSeleccionSede() {
        frame = new JFrame("Selección de Sede");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 500);

        JPanel panel = new JPanel();
        frame.add(panel);
        panel.setLayout(null);

        JLabel labelMensaje = new JLabel("Seleccione una sede:");
        labelMensaje.setFont(new Font("Arial", Font.PLAIN, 24));
        labelMensaje.setBounds(10, 20, 300, 25);
        panel.add(labelMensaje);

        addSedeButton(panel, "Sede Norte", 50);
        addSedeButton(panel, "Sede Centro", 80);
        addSedeButton(panel, "Sede Sur", 110);

        JButton botonSeleccionar = new JButton("Seleccionar");
        botonSeleccionar.setFont(new Font("Arial", Font.PLAIN, 20));
        botonSeleccionar.setBounds(50, 200, 150, 25);
        panel.add(botonSeleccionar);

        botonSeleccionar.addActionListener(this);

        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    private void addSedeButton(JPanel panel, String nombreSede, int posY) {
        JRadioButton button = new JRadioButton(nombreSede);
        button.setFont(new Font("Arial", Font.PLAIN, 18));
        button.setActionCommand(nombreSede);
        grupoSedes.add(button);

        button.setBounds(10, posY, 200, 25);
        panel.add(button);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ButtonModel selectedSede = grupoSedes.getSelection();
        if (selectedSede != null) {
            String seleccion = selectedSede.getActionCommand();
            JOptionPane.showMessageDialog(null, "Ha seleccionado: " + seleccion);
            VentanaAdministrador.cargarVentanaAdministrador();
            frame.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Por favor, seleccione una sede.");
        }
    }

    public static void mostrarVentana() {
        SwingUtilities.invokeLater(() -> new VentanaSeleccionSede());
    }
}
