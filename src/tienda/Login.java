/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienda;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Carlos
 */
public class Login extends JFrame{
    private Informacion miInfo; 
    JPasswordField jpf_Contraseña;
    JTextField jtf_Nombre; 

    public Login(Informacion miInfo) {
        this.miInfo = miInfo; 
        initComponent(); 
    }  
    
    private void initComponent(){
        //Titulo
        this.setTitle("Login"); 
        //Dimension y Posicion (x,y,x,y) 
        this.setBounds(500,100,500,500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel panelUno = (JPanel) this.getContentPane(); 
        panelUno.setLayout(null); 
        
        JLabel jl_Nombre = new JLabel("Ingrese su nombre: "); 
        jl_Nombre.setBounds(100,125,200,25);
        panelUno.add(jl_Nombre);
        
        jtf_Nombre = new JTextField();
        jtf_Nombre.setBounds(100,150,200,25);
        panelUno.add(jtf_Nombre); 
        
        JLabel jl_Contraseña = new JLabel("Ingrese su Contraseña: "); 
        jl_Contraseña.setBounds(100,200,200,25); 
        panelUno.add(jl_Contraseña); 
        
        jpf_Contraseña = new JPasswordField(); 
        jpf_Contraseña.setBounds(100,225,200,25);
        panelUno.add(jpf_Contraseña); 
        
        JButton jb_Entrar = new JButton("Entrar");
        jb_Entrar.setBounds(100,275,200,50); 
        jb_Entrar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                opcionEntrar(); 
            }   
        }); 
        panelUno.add(jb_Entrar); 
        
    }
    
    public void opcionEntrar(){
        String name = jtf_Nombre.getText();
        String pass = jpf_Contraseña.getText(); 
        int i = 0; 
        while(miInfo.getClientes()[i]!=null && i < miInfo.getClientes().length){
            if(miInfo.getClientes()[i].getNickname().equals(name) && miInfo.getClientes()[i].getPassword().equals(pass)){
                String nuevoNickname = JOptionPane.showInputDialog("Ingrese un nuevo Nickname");
                miInfo.getClientes()[i].setNickname(nuevoNickname);  
                return; 
            }
            i++; 
        }
        JOptionPane.showConfirmDialog(null, "No Exite coincidencias"); 
    }
    
}
