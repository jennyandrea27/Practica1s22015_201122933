/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EDD;
import EDD.NodoMatriz;

/**
 *
 * @author jenny
 */
public class MOrtogonal {
    NodoMatriz auxcol,auxfila,inicio;
    int numfilas;
    int numcolumnas;       
    
    public MOrtogonal(){        
        //formar matriz de 2x4
        //las filas se insertan hacia arriba
        //las columnas se insertan hacia la derecha
        //el auxiliar inicio se encuentra en la fila superior
        numfilas=2;
        numcolumnas=4;             
        inicio=new NodoMatriz(null);
        auxfila=inicio;
        auxcol=inicio;
        //crear primera fila
        for(int i =2;i<=numcolumnas;i++){            
            auxcol.siguiente=new NodoMatriz(null);
            auxcol.siguiente.anterior=auxcol;                        
            auxcol=auxcol.siguiente;
        }
        //regresar auxcol
        auxcol=inicio;
        //insertar filas
        int cont=0;
        for(int i =2;i<=numfilas;i++){
            cont++;            
            auxfila.arriba=new NodoMatriz(null);
            auxfila.arriba.abajo=auxfila;            
            auxcol=auxfila.arriba;
            //insertar nodos columna en la fila
            for(int j =2;j<=numcolumnas;j++){            
            auxcol.siguiente=new NodoMatriz(null);
            auxcol.siguiente.anterior=auxcol;
            auxcol=auxcol.siguiente;
            //enlaces verticales
            auxfila=auxfila.siguiente;
            auxcol.abajo=auxfila;            
            auxfila.arriba=auxcol;            
            }
            //inicio se mueve a la parte superior, en la fila nueva
            inicio=inicio.arriba;
            auxcol=inicio;
            auxfila=inicio;            
        }                                                
    }    
    public void AgregarFila(int filas){        
        //insertar filas
        int cont=0;
        for(int i =1;i<=filas;i++){//agreegar filas indicadas
            cont++;//contador de columna            
            auxfila.arriba=new NodoMatriz(null);
            auxfila.arriba.abajo=auxfila;            
            auxcol=auxfila.arriba;
            //insertar nodos columna en la fila
            for(int j =2;j<=numcolumnas;j++){
                cont++;                
                auxcol.siguiente=new NodoMatriz(null);
                auxcol.siguiente.anterior=auxcol;
                auxcol=auxcol.siguiente;
                //enlaces verticales
                auxfila=auxfila.siguiente;
                auxcol.abajo=auxfila;            
                auxfila.arriba=auxcol;            
            }
            cont=0;
            //inicio se mueve a la parte superior, en la fila nueva
            inicio=inicio.arriba;
            auxcol=inicio;
            auxfila=inicio;   
            numfilas++;
        } 
                                                       
    }
    public void AgregarColumna(int columnas){
        //insertar nodos columna en la fila
        int contcol=1;
        int contfila=numfilas;
        NodoMatriz temp=inicio;
        NodoMatriz temp2=inicio;
        
        //recorrer fila superior hasta encontrar el ultimo nodo
        while(temp.siguiente!=null){
            temp=temp.siguiente;
            contcol++;
        }
        //temp ultimo nodo de la fila superior
        //temp2 nodo inmediato inferior a temp
        temp2=temp.abajo;
        //se crea primer nodo en la fila superior, de la nueva columna        
        //se agrega nuevo nodo en la fila superior, en la nueva columna
        temp.siguiente=new NodoMatriz(null);
        temp.siguiente.anterior=temp;
        temp=temp.siguiente;
        //insertar el resto de nodos en cada fila de la columna nueva
        for(int i=2;i<=numfilas;i++){
            //se comienza
            contfila--;            
            temp.abajo=new NodoMatriz(null);
            temp.abajo.arriba=temp;
            //mover temporal al inmediato inferior
            temp=temp.abajo;
            temp2.siguiente=temp;
            temp.anterior=temp2;
            //se mueve temp2
            temp2=temp2.abajo;                        
        }     
        numcolumnas++;
    }
            
    
    public void Recorrer(){        
        NodoMatriz temp=inicio;
        int j=1;                
        while(temp!=null){
            int i=1;                
            NodoMatriz temp2=temp;                
            while(temp2!=null){                
                System.out.println(j+","+i);
                i++;
                temp2=temp2.siguiente; 
            }
            j++;
            temp=temp.abajo;

        }        
    }
    
    
    
    
}
