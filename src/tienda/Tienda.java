/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienda;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 *
 * @author Carlos
 */
public class Tienda {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Informacion informacion = cargarInformacion();
        Graficas miVentana = new Graficas(informacion);
        miVentana.setVisible(true);
        

    }

    public static Informacion cargarInformacion() {
        Informacion enviar = new Informacion();
        File nombre = new File("ArchivoDeEntrada.txt");

        try {
            FileReader archivo = new FileReader(nombre);
            BufferedReader miBuffer = new BufferedReader(archivo);
            String linea = miBuffer.readLine();
            Producto[] productos = new Producto[1000];
            Cliente[] clientes = new Cliente[1000];
            int clientePos = 0;
            int productoPos = 0;
            for (int i = 0; linea != null; i++) {
                String[] contenidoLinea = linea.split(",");
                if (contenidoLinea[0].equals("producto")) {
                    productos[productoPos] = new Producto(contenidoLinea[1], Double.valueOf(contenidoLinea[2]), Integer.valueOf(contenidoLinea[3]));
                    productoPos++;
                } else if (contenidoLinea[0].equals("cliente")) {
                    clientes[clientePos] = new Cliente(contenidoLinea[1], contenidoLinea[2]);
                    clientePos++;
                } else {
                    System.out.println("Linea Erronea" + contenidoLinea);
                }
                linea = miBuffer.readLine();
            }
            enviar.setClientes(clientes);
            enviar.setProductos(productos);
        } catch (Exception e) {
            System.out.println(e);
        }
        return enviar;
    }

}
