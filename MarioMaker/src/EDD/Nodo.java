/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EDD;

import javax.swing.JButton;

/**
 *
 * @author jenny
 */
public class Nodo {
    public Nodo siguiente,anterior;
    public Object dato;
    public JButton boton;
    
    public Nodo(Object dato){
        this.anterior=null;
        this.siguiente=null;
        this.dato=dato;
        boton=new JButton();
        
    }
}
