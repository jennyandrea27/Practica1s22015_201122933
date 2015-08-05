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
    int getTipo(){
        if(tipo=="suelo"){
            return 1;
        }else if(tipo=="pared"){
            return 2;
        }else if(tipo=="goomba"){
            return 3;
        }else if(tipo=="koopa"){
            return 4;
        }else if(tipo=="moneda"){
            return 5;
        }else if(tipo=="hongo"){
            return 6;
        }else if(tipo=="castillo"){
            return 7;
        }
    return -1;
    }
}
