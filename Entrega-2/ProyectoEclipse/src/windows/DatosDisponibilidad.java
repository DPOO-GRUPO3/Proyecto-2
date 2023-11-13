package windows;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.time.format.DateTimeFormatter;

public class DatosDisponibilidad {
    private Map<LocalDate, Boolean> disponibilidad;

    public DatosDisponibilidad() {
        this.disponibilidad = new HashMap<>();
    }

    public void cargarDatosDesdeArchivo(String filePath) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(";");
                
                // La fecha se encuentra en la penúltima posición
                String fechaString = data[data.length - 1];
                boolean disponible = data[data.length - 2].equals("Disponible") || data[data.length - 1].equals("alquilado");

                // Evitamos errores de formato en la cadena "Disponible"
                if (disponible) {
                    LocalDate fecha = LocalDate.parse(fechaString, formatter);
                    disponibilidad.put(fecha, disponible);
                }
            }
        } catch (IOException | ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }

    public Map<LocalDate, Boolean> getDisponibilidad() {
        return disponibilidad;
    }
}