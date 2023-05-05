import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class LeerArchivo {
    public static void main(String[] args) {
        String archivo = "nacimientos.csv";
        String linea;
        Map<String, Integer> nacimientosPorDepartamento = new HashMap<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            while ((linea = br.readLine()) != null) {
                String[] elementos = linea.split(",");
                String departamento = elementos[0].trim();
                if (nacimientosPorDepartamento.containsKey(departamento)) {
                    nacimientosPorDepartamento.put(departamento, nacimientosPorDepartamento.get(departamento) + 1);
                } else {
                    nacimientosPorDepartamento.put(departamento, 1);
                }
            }
            
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            for (String departamento : nacimientosPorDepartamento.keySet()) {
                dataset.addValue(nacimientosPorDepartamento.get(departamento), "Nacimientos", departamento);
            }
            
            JFreeChart chart = ChartFactory.createBarChart(
                    "Nacimientos por Departamento", 
                    "Departamento", 
                    "Cantidad", 
                    dataset, 
                    PlotOrientation.VERTICAL, 
                    false, 
                    true, 
                    false);
            
            chart.setBackgroundPaint(Color.white);
            chart.getTitle().setPaint(Color.black);
            
            ChartFrame frame = new ChartFrame("Gráfico", chart);
            frame.setVisible(true);
            frame.setSize(800, 600);
        } catch (IOException e) {
            System.out.println("Error al leer el archivo");
            e.printStackTrace();
        }
    }
}
