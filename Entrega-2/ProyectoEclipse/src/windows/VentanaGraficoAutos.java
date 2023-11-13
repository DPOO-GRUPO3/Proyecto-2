package windows;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.JFrame;

public class VentanaGraficoAutos extends JPanel implements ActionListener {
    private DatosDisponibilidad datos;
    private static final String[] DIAS = {"L", "M", "M", "J", "V", "S", "D"};

    public VentanaGraficoAutos(DatosDisponibilidad datos) {
        this.datos = datos;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int cuadroWidth = (getWidth() - 40) / 52;
        int cuadroHeight = (getHeight() - 50) / 7;

        // Centrar el gráfico
        int xStart = (getWidth() - cuadroWidth * 52) / 2;
        int yStart = (getHeight() - cuadroHeight * 7) / 2;

        Map<LocalDate, Boolean> disponibilidad = datos.getDisponibilidad();

        // Etiqueta en el eje Y (Días)
        g.setFont(new Font("Arial", Font.PLAIN, 12));
        for (int y = 0; y < 7; y++) {
            g.drawString(DIAS[y], 5, yStart + (y * cuadroHeight) + (cuadroHeight / 2) + 5);
        }

        // Etiqueta de "Semanas" abajo del gráfico
        g.drawString("Semanas", getWidth() / 2, getHeight() - 5);

        // Título del gráfico
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.drawString("Gráfico Disponibilidad de Autos en 2023", getWidth() / 4, 20);

        for (int x = 0; x < 52; x++) {
            for (int y = 0; y < 7; y++) {
                LocalDate fecha = LocalDate.ofYearDay(2023, (x * 7) + y + 1);
                Boolean disponible = disponibilidad.getOrDefault(fecha, false);

                Color color = disponible ? Color.GREEN : Color.RED;
                g.setColor(color);
                g.fillRect(xStart + (x * cuadroWidth), yStart + (y * cuadroHeight), cuadroWidth - 1, cuadroHeight - 1);
            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        repaint(); // Si hay algún cambio en los datos, actualiza el gráfico
    }

    public void mostrarVentana() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Gráfico Disponibilidad de Autos en 2023");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            JButton boton = new JButton("Actualizar"); // Botón u otro componente que desencadene un evento
            boton.addActionListener((ActionListener) this);

            frame.add(boton, BorderLayout.SOUTH);
            frame.add(this, BorderLayout.CENTER);

            frame.setSize(800, 250);
            frame.setVisible(true);
        });
    }
}