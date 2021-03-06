package views;

import java.awt.Color;

import javax.swing.JTable;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
//import org.jfree.chart.axis.CategoryAxis;
//import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;

public class RafagaTimeGraph 
{
	public void	show (JTable table)
	{
		XYSeries series = new XYSeries ("TR/Proceso");  // series guarda las coordenadas: proceso y turnaround
		// Introducción de datos: series.add(x,y) = (proceso,TR);
		// insercion por ciclo para manejo din+ámico

		int size = table.getRowCount ();

		for (int i = 0; i < size; i ++)
		{
			series.add (i + 1, (float) table.getValueAt (i, 2));
		}

		// creando una coleccion con los datos de la serie
		XYSeriesCollection dataset = new XYSeriesCollection (); // dataset tiene
		dataset.addSeries (series);
		// Gráfica: "chart"
		JFreeChart chart = ChartFactory.createXYLineChart
		(
			"Procesos VS T. ráfaga", // Título
			"Procesos", // Etiqueta Coordenada X
			"T. ráfaga", // Etiqueta Coordenada Y
			dataset, // Datos
			PlotOrientation.VERTICAL,
			true, // Muestra la leyenda ingresada en el constructor
			false,
			false
		);

		// obj para modificar el las caracteristicas del trazado de la gráfica "chart"
		XYPlot plot = (XYPlot) chart.getPlot ();
		//plot.getRangeAxis().setRange(0,10);   //para establecer el rango(eje y)
		//plot.getDomainAxis().setRange(0, 7);  //para establecer el rango(eje x)
		plot.setBackgroundPaint (Color.WHITE);         //fondo color blanco
		plot.setRangeGridlinesVisible (true);
		plot.setRangeGridlinePaint (Color.BLACK);	            	           
		// obj para modificar la distancia entre las abcisas (eje x) en representacion de los procesos
		NumberAxis xAxis = (NumberAxis) plot.getDomainAxis ();  
		xAxis.setTickUnit (new NumberTickUnit (1)); //distancia entre coordenas del eje x igual 1
		// obj para agregar los puntos de cortes entre ejes y color dek trazo de la linea
		XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) plot.getRenderer ();
		renderer.setSeriesShapesVisible (0, true); // serie = 0 , mostrarPuntos = true;
		renderer.setSeriesPaint (0, Color.BLACK);
		// Mostramos la grafica en pantalla
		ChartFrame frame = new ChartFrame ("Gráfica lineal", chart);
		frame.pack ();
		frame.setVisible (true);
	}
}



