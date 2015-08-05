/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EDD;

import mariomaker.Objeto;
import mariomaker.Personaje;

/**
 *
 * @author jenny
 */
public class ListaD {
    public Nodo pinicio,pfinal;
    int cantidad=0;
    
    public ListaD(){
        pinicio=null;
        pfinal=null;
    }
    public boolean Vacia(){
        if(pinicio==null && pfinal==null){
            return true;
        }
        return false;
    }
    
    public void InsertarFinal(Object nuevo){
        Nodo nodo=new Nodo(nuevo);
        if(!Vacia()){
            pfinal.siguiente=nodo;
            nodo.anterior=pfinal;
            pfinal=nodo;
            cantidad++;
        }else{
            pinicio=pfinal=nodo;
            cantidad++;
        }        
    }
    public Object EliminarFinal(){
        if(!Vacia()){
            Object temp=pfinal.dato;            
            if(pinicio==pfinal){
                pinicio=null;
                pfinal=null;
                cantidad--;
                System.out.println("Elemento eliminado");
                return temp;
            }
            
            pfinal=pfinal.anterior;
            pfinal.siguiente.anterior=null;
            pfinal.siguiente=null;
            cantidad--;
            System.out.println("Elemento eliminado");
            return temp;
        }else{
            return null;
        }
    }
    
    public void InsertarInicio(Object nuevo){
        Nodo nodo=new Nodo(nuevo);
        if(!Vacia()){
            pinicio.anterior=nodo;
            nodo.siguiente=pinicio;
            pinicio=nodo;
            cantidad++;
        }else{
            pinicio=pfinal=nodo;
            cantidad++;
        }
    }
    
    public Object EliminarInicio(){
        if(!Vacia()){
            Object temp=pinicio.dato;
            if(pinicio==pfinal){
                pinicio=null;
                pfinal=null;
                cantidad--;
                System.out.println("Elemento eliminado");
                return temp;
            }
            pinicio=pinicio.siguiente;
            pinicio.siguiente.anterior=null;
            pinicio.anterior=null;
            cantidad--;
            System.out.println("Elemento eliminado");
            return temp;
        }else{
            return null;
        }
    }
    public void Imprimir(){
        if(!Vacia()){
            Nodo temp=pinicio;
            while(temp!=null){
                System.out.println("Nodo:"+temp.dato);
                temp=temp.siguiente;
        }            
        }else{
            System.out.println("Lista Vacia");
        }
    }
    public int getCantidad(){
        return cantidad;
    }
    public Nodo BuscarElemento(int pos){
        Nodo temp=pinicio;
        int num=0;
        while(temp!=null){
            if (num==pos){
                return temp;
            }else{
                num++;
            }
            temp=temp.siguiente;
        }
        return null;
    }
    public Nodo Eliminar(int pos){
        Nodo temp=BuscarElemento(pos);
        if(temp!=null){
            if(temp==pinicio ){//primer elemento
                if(temp==pfinal){//unico elemento en la lista
                    pinicio=null;
                    pfinal=null;
                    cantidad--;
                    System.out.println("Elemento eliminado");
                    return temp;
                }else{
                    Nodo siguiente=temp.siguiente;
                    siguiente.anterior=null;
                    temp.siguiente=null;
                    pinicio=siguiente;
                    cantidad--;
                    System.out.println("Elemento eliminado");
                    return temp;
                }
                
            }else if(temp==pfinal){//ultimo elemento
                Nodo anterior=temp.anterior;
                anterior.siguiente=null;
                temp.anterior=null;
                pfinal=anterior;
                cantidad--;
                System.out.println("Elemento eliminado");
                return temp;
            }else{//en medio
                Nodo anterior=temp.anterior;
                anterior.siguiente=temp.siguiente;
                temp.siguiente.anterior=anterior;
                temp.anterior=null;            
                temp.siguiente=null;
                cantidad--;
                System.out.println("Elemento eliminado");                
                return temp;
            }                        
        }else{
            System.out.println("Elemento no encontrado");
        }        
        return temp;
    }
    public void Reemplazar(int pos,Object dato){
        Nodo temp=BuscarElemento(pos);
        if(temp!=null){
            //reemplaza el dato del nodo encontrado
           temp.dato=dato;
            System.out.println("Nodo reemplazado");
        }else{
            System.out.println("no se reemplazo objeto");
        }
        
    }
}