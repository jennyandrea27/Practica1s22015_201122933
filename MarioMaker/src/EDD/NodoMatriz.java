/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EDD;

import javax.swing.JLabel;

/**
 *
 * @author jenny
 */
public class NodoMatriz {
    NodoMatriz anterior,siguiente,arriba,abajo;
    Object dato;
    JLabel label;
    
    public NodoMatriz(Object dato){
        this.anterior=null;
        this.siguiente=null;
        this.arriba=null;
        this.abajo=null;
        this.dato=dato;
        label=new JLabel();
    }
      

    
}
