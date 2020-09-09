/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienda;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author Carlos
 */
public class Graficas extends JFrame {

    private Informacion miInfo;

    public Graficas(Informacion miInfo) {
        this.miInfo = miInfo;
        initComponent();
    }

    private void initComponent() {
        //Titulo
        this.setTitle("Elegir");
        //Dimension y Posicion (x,y,x,y) 
        this.setBounds(500, 100, 200, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panelUno = (JPanel) this.getContentPane();
        panelUno.setLayout(null);

        JButton jb_graficas = new JButton("Inventario");
        jb_graficas.setBounds(50, 30, 100, 25);
        jb_graficas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                verGrafica();
            }
        });
        panelUno.add(jb_graficas);

        JButton jb_login = new JButton("Login");
        jb_login.setBounds(50, 110, 100, 25);
        jb_login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Login miVentana = new Login(miInfo);
                miVentana.setVisible(true);
            }
        });
        panelUno.add(jb_login);
    }

    public void verGrafica() {
        //Mas informacion en: http://www.jfree.org/jfreechart/
        //poseen una documentacion bien vasta y hay infinidad de ejemplos en internet 
        //Cramos una instancia del objeto que almanacena nuestros datos 
        DefaultPieDataset dataset = new DefaultPieDataset();
        for (int i = 0; miInfo.getProductos()[i] != null && i < miInfo.getProductos().length; i++) {
            dataset.setValue(miInfo.getProductos()[i].getNombre() + ": \n" 
                    + miInfo.getProductos()[i].getUnidades() + " unidades", miInfo.getProductos()[i].getUnidades());
        }
        //Creamos la grafica como tal y le pasamos los datos
        JFreeChart chart = ChartFactory.createPieChart("Inventario Total",
                dataset, true, true, true);
        ChartPanel CP = new ChartPanel(chart);
        chart.setBackgroundPaint(Color.white);
        chart.removeLegend();
        CP.setPreferredSize(new Dimension(500, 500));
        CP.setMouseWheelEnabled(true);

        JPanel jPanel1 = new JPanel();
        jPanel1.setLayout(new java.awt.BorderLayout());
        jPanel1.setVisible(true);
        jPanel1.setSize(500, 500);
        jPanel1.add(CP, BorderLayout.CENTER);
        jPanel1.validate();

        //Con el grafico creado lo pegamos en una ventana 
        JFrame frame = new JFrame("CrearGraficos - LineaDeCodigo");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(jPanel1);
        frame.pack();
        frame.setVisible(true);

    }

}
