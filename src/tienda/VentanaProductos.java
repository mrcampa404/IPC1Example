/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienda;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

/**
 *
 * @author Carlos
 */
public class VentanaProductos extends JFrame {

    private Informacion miInfo;
    JTextArea textArea;
    //Guarda la posicion a todos los que hay que descontarles 1 
    private int[] carrito;
    private int contProd;

    public VentanaProductos(Informacion miInfo) {
        this.miInfo = miInfo;
        //Limpiamos el carrito con un maximo de 10 productos; 
        carrito = new int[10];
        contProd = 0;
        onInit();
    }

    private void onInit() {
        //Agregando titulo, tamaño y posicion, operacion de cierre 
        this.setTitle("Productos");
        this.setBounds(100, 100, 600, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Evita que la ventana sea redimensionada
        this.setResizable(false);

        //Creamos el panel y le agregamos un Layout 
        JPanel panelUno = (JPanel) this.getContentPane();
        panelUno.setLayout(null);

        //Agrego una linea de separacion 
        JSeparator linea = new JSeparator(SwingConstants.VERTICAL);
        linea.setBounds(200, 30, 1, 510);
        panelUno.add(linea);

        //El titulo del carrito 
        JLabel carrito = new JLabel("Carrito: ");
        carrito.setBounds(25, 30, 100, 25);
        panelUno.add(carrito);
        //Agrego un area de texto
        textArea = new JTextArea();
        //no va a ser editable 
        textArea.setEditable(false);
        //Lo metemos en un scroll para poder desplazar el texto 
        JScrollPane scroll = new JScrollPane(textArea);
        scroll.setBounds(25, 60, 150, 420);
        panelUno.add(scroll);
        //Agregamos boton de compra 
        JButton comprar = new JButton("Confirmar Compra");
        comprar.setBounds(25, 500, 150, 25);
        comprar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                realizarCompra();
            }

        });
        panelUno.add(comprar);

        //Agrego un nuevo panel con un layout null 
        JPanel panelDos = new JPanel();
        panelDos.setLayout(null);
        //Agrego un redimensionar para luego meterle un scroll 
        int var = 0;
        while (miInfo.getProductos()[var] != null && var < miInfo.getProductos().length) {
            var++;
        }
        panelDos.setPreferredSize(new Dimension(300, (var / 3) * 125));
        JScrollPane scrollPanel = new JScrollPane(panelDos);
        scrollPanel.setBounds(225, 35, 350, 500);
        panelUno.add(scrollPanel);

        int columna = 0; //Lo uso para guardar la columna a poner el boton 
        int fila = 0; //Lo uso para saber en que fila voy 
        for (int i = 0; miInfo.getProductos()[i] != null && i < miInfo.getProductos().length; i++) {
            //Creamos un boton 
            JButton button_producto = new JButton();
            //Creamos una imagen y luego la ponemos a la imagen
            ImageIcon p1 = new ImageIcon(miInfo.getProductos()[i].getImagen());
            button_producto.setIcon(p1);
            //La union de setSize y setLocation hacen a setBounds 
            button_producto.setSize(90, 90);
            button_producto.setLocation((120 * columna), (125 * fila));
            //Esto nos permite agregar otra imagen cuando el mouse esta encima del boton
            button_producto.setRolloverIcon(new ImageIcon("productos/ADD.png"));
            //Agregamos accion al boton 
            final String temp = miInfo.getProductos()[i].getNombre();
            final int posicion = i;
            button_producto.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    agregarCarrito(temp, posicion);
                }
            });
            panelDos.add(button_producto);

            //Vamos moviendonos de posicion con esto, podriamos usar los layouts para evitar esto tambien 
            columna++;
            if (columna == 3) {
                columna = 0;
                fila++;
            }

        }

    }

    public void agregarCarrito(String nombre, int posicion) {
        if (this.contProd < 10) {
            this.textArea.setText(this.textArea.getText() + "\n" + nombre);
            this.carrito[this.contProd] = posicion;
            contProd++;
        } else {
            JOptionPane.showMessageDialog(null, "No puedes adquirir más productos");
        }
    }

    private void realizarCompra() {
        for(int i = 0; i<10; i++){
            this.miInfo.getProductos()[this.carrito[i]].setUnidades(this.miInfo.getProductos()[this.carrito[i]].getUnidades()-1); 
        }
        JOptionPane.showMessageDialog(null, "Compra realizada"); 
        this.dispose();     
    }
}
