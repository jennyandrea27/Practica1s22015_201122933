/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EDD;
import EDD.NodoMatriz;
import mariomaker.Objeto;

/**
 *
 * @author jenny
 */
public class MOrtogonal {
    NodoMatriz auxcol,auxfila,inicio;
    int numfilas;
    int numcolumnas;
    
    public boolean Vacia(){
        if(inicio==null){
            return true;
        }
        return false;
    }
    
    public MOrtogonal(){        
        //formar matriz de 2x4
        numfilas=2;
        numcolumnas=4;     
        Objeto in=new Objeto("1,1","nodo");
        inicio=new NodoMatriz(in);
        auxfila=inicio;
        auxcol=inicio;
        //crear primera fila
        for(int i =2;i<=numcolumnas;i++){
            Objeto nuevo=new Objeto(i+",1", "nodo");            
            auxcol.siguiente=new NodoMatriz(nuevo);
            auxcol.siguiente.anterior=auxcol;                        
            auxcol=auxcol.siguiente;
        }
        //regresar auxcol
        auxcol=inicio;
        //insertar filas
        int cont=0;
        for(int i =2;i<=numfilas;i++){
            cont++;
            Objeto nuevo=new Objeto(i+","+cont,"nodo");
            auxfila.arriba=new NodoMatriz(nuevo);
            auxfila.arriba.abajo=auxfila;            
            auxcol=auxfila.arriba;
            //insertar nodos columna en la fila
            for(int j =2;j<=numcolumnas;j++){
            Objeto nuevo2=new Objeto(j+","+i,"nodo");
            auxcol.siguiente=new NodoMatriz(nuevo2);
            auxcol.siguiente.anterior=auxcol;
            auxcol=auxcol.siguiente;
            //enlaces verticales
            auxfila=auxfila.siguiente;
            auxcol.abajo=auxfila;            
            auxfila.arriba=auxcol;            
        }
            inicio=inicio.arriba;
            auxcol=inicio;
            auxfila=inicio;            
        }                                                
    }    
            
    public void InsertarFila(){
        
    }
    
    public void Recorrer(){
        if(!Vacia()){
            NodoMatriz temp=inicio;
            int j=1;                
            while(temp!=null){
                int i=1;                
                NodoMatriz temp2=temp;                
                while(temp2!=null){
                    Objeto imp=(Objeto)temp2.dato;  
                    System.out.println("NodoM "+imp.nombre);
                    System.out.println(i+","+j);
                    i++;
                    temp2=temp2.siguiente; 
                }
                j++;
                temp=temp.abajo;
                
            }
        }
    }
    
    
    
    
}
