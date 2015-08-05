/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EDD;

/**
 *
 * @author jenny
 */
public class NodoMatriz {
    NodoMatriz anterior,siguiente,arriba,abajo;
    Object dato;
    
    public NodoMatriz(Object dato){
        this.anterior=null;
        this.siguiente=null;
        this.arriba=null;
        this.abajo=null;
        this.dato=dato;
    }
    
    public NodoMatriz(){
        this.anterior=null;
        this.siguiente=null;
        this.arriba=null;
        this.abajo=null;
        this.dato=null;
    }


    

    
}
