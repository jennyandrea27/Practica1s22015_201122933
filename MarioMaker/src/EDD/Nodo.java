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
public class Nodo {
    public Nodo siguiente,anterior;
    public Object dato;
    
    public Nodo(Object dato){
        this.anterior=null;
        this.siguiente=null;
        this.dato=dato;
        
    }
}
