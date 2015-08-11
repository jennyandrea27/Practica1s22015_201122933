/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mariomaker;

/**
 *
 * @author jenny
 */
public class Personaje {
    public String nombre,tipo;
    public int vida,punteo;
    public String direccion;
    
    public Personaje(String nombre){
        this.nombre=nombre;
        this.vida=0;
        this.punteo=0;
        this.tipo="pprincipal";
        this.direccion="derecha";
    }
    void Imprimir(){
        System.out.println("Personaje: "+this.nombre);
    }
}
