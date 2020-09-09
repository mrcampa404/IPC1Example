/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienda;

/**
 *
 * @author Carlos
 */
public class Informacion {
    private Cliente clientes[]; 
    private Producto productos[]; 
    

    public Informacion() {
        this.clientes = new Cliente[0]; 
        this.productos = new Producto[0]; 
    }

    public Cliente[] getClientes() {
        return clientes;
    }

    public void setClientes(Cliente[] clientes) {
        this.clientes = clientes;
    }

    public Producto[] getProductos() {
        return productos;
    }

    public void setProductos(Producto[] productos) {
        this.productos = productos;
    }
    
    
    
    
}
