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
public class Objeto {
    public String nombre,tipo;
    
    public Objeto(String nombre,String tipo){
        this.nombre=nombre;
        this.tipo=tipo;        
    }
    void Imprimir(){
        System.out.println("Objeto "+this.tipo+" "+this.nombre);
    }
    public int getTipo(){
        if(tipo.equals("suelo")){
            return 1;
        }else if(tipo.equals("pared")){
            return 2;
        }else if(tipo.equals("goomba")){
            return 3;
        }else if(tipo.equals("koopa")){
            return 4;
        }else if(tipo.equals("moneda")){
            return 5;
        }else if(tipo.equals("hongo")){
            return 6;
        }else if(tipo.equals("castillo")){
            return 7;
        }
    return -1;
    }
}
