import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LeerArchivo {
    public static void main(String[] args) {
        String archivo = "nacimientos.csv";
        String linea;
        
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            while ((linea = br.readLine()) != null) {
                String[] elementos = linea.split(",");
                String[] elementosSinSaltos = new String[elementos.length];
                for (int i = 0; i < elementos.length; i++) {
                    try {
                        elementosSinSaltos[i] = String.valueOf(Float.parseFloat(elementos[i].trim()));
                    } catch (NumberFormatException e) {
                        elementosSinSaltos[i] = elementos[i].trim();
                    }
                }
                String lineaSinSaltos = String.join(",", elementosSinSaltos);
                System.out.println(lineaSinSaltos);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo");
            e.printStackTrace();
        }
    }
}
