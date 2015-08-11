/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EDD;
import EDD.NodoMatriz;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import mariomaker.Objeto;
import mariomaker.Personaje;

/**
 *
 * @author jenny
 */
public class MOrtogonal implements Cloneable{
    public NodoMatriz auxcol,auxfila,inicio;
    public int numfilas;
    public int numcolumnas;       
    
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
        for(int i =2;i<=numfilas;i++){                    
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
        for(int i =1;i<=filas;i++){//agregar filas indicadas
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
    public void EliminarColumna(int c){
        if(numcolumnas==1){//si solo existe una columna en la matriz no se puede eliminar, para no eliminar inicio
            System.out.println("No se puede eliminar la columna");
            JOptionPane.showMessageDialog(null, "No se puede eliminar la columna");
        }else{
            if(c==numcolumnas){//se elimina la columna mas hacia la derecha
                //buscar el elemento anterior al ultimo en la fila de inicio
                auxcol=inicio;
                for(int i=1;i<numcolumnas-1;i++){
                    auxcol=auxcol.siguiente;
                }
                //se eliminan punteros horizontales desde la fila superior hacia abajo
                for(int i=numfilas;i>=1;i--){
                    auxcol.siguiente.anterior=null;
                    auxcol.siguiente=null;
                    auxcol=auxcol.abajo;
                }
                numcolumnas--;
            }else if(c==1){//se eliminara la columna mas hacia la izquierda
                inicio=inicio.siguiente;
                auxfila=inicio;
                for(int i=numfilas;i>=1;i--){
                    auxfila.anterior.siguiente=null;
                    auxfila.anterior=null;                    
                    auxfila=auxfila.abajo;
                }
                numcolumnas--;                
            }else{//se elimina columna de en medio
                auxcol=inicio;
                //se busca columna a eliminar
                for(int i=1;i<c;i++){
                    auxcol=auxcol.siguiente;
                }
                for(int i=numfilas;i>=1;i--){                    
                //cambiar punteros horizontales entre columnas anterior y siguiente
                auxcol.anterior.siguiente=auxcol.siguiente;
                auxcol.siguiente.anterior=auxcol.anterior;
                //eliminar punteros
                auxcol.siguiente=null;
                auxcol.anterior=null;
                auxcol=auxcol.abajo;
                }
                numcolumnas--;
            }
            //mover auxiliares
            auxcol=inicio;
            auxfila=inicio;            
        }
    }
       
    public void EliminarFila(int f){
        if(numfilas==1){//si solo existe una fila en la matriz no se permite eliminar, para no eliminar el puntero inicio
            System.out.println("No se puede eliminar la fila");
            JOptionPane.showMessageDialog(null, "No se puede eliminar la fila");
        }else{
            if (f==numfilas){//fila a eliminar es la superior
                inicio=inicio.abajo;
                auxcol=inicio;
                auxfila=inicio;
                for(int i=1;i<=numcolumnas;i++){                    
                    auxcol.arriba.abajo=null;
                    auxcol.arriba=null;
                    auxcol=auxcol.siguiente;
                }                            
            }else if(f==1){//se elimina la fila inferior
                auxfila=inicio;
                //se busca la ultima fila
                for(int i=numfilas-1;i>f;i--){
                    auxfila=auxfila.abajo;
                }                
                auxcol=auxfila;
                for(int i=1;i<numcolumnas;i++){
                    auxcol.abajo.arriba=null;
                    auxcol.abajo=null;
                    auxcol=auxcol.siguiente;
                }                
            }else{//se elimina una fila de en medio
                auxfila=inicio;
                //se busca la fila a eliminar
                for(int i=numfilas;i>f;i--){                    
                    auxfila=auxfila.abajo;
                } 
                auxcol=auxfila;
                for(int i=1;i<=numcolumnas;i++){
                    //reapuntar los nodos superior e inferior
                    auxcol.arriba.abajo=auxcol.abajo;
                    auxcol.abajo.arriba=auxcol.arriba;
                    //eliminar punteros de la fila que se quiere eliminar
                    auxcol.arriba=null;
                    auxcol.abajo=null;
                    if(auxcol.anterior!=null){//es el primer nodo en la fila
                        auxcol.anterior.siguiente=null;
                        auxcol.anterior=null;
                    }
                    auxcol=auxcol.siguiente;
                }                
            }
            auxcol=inicio;
            auxfila=inicio;
            numfilas--;
        }
    }
    
    public Object clone(){
        Object obj=null;
        try{
            obj=super.clone();
        }catch(CloneNotSupportedException ex){
            System.out.println("No se puede clonar matriz");
        }
        return obj;
        
    }
            
    
    public String Graficar(){        
        NodoMatriz tempfila=inicio;
        NodoMatriz tempcol;
        int i=1;
        int j=1;
        String s="";
        //encabezado de .dot
        s+="digraph G{\n";
        s+="rankdir=RL;\n";
        s+="orientation=portrait;\n";
        s+="subgraph cluster1{\n";
        s+="node[shape=box,style=filled,color=lightskyblue];\n";
        s+="style=filled;\n";
        s+="color=skyblue;\n";
        s+="edge[arrowhead=inv,arrowtail=inv,dir=both];\n";
        s+="label=\"MATRIZ ORTOGONAL\";\n";
        while(tempfila!=null){
            tempcol=tempfila;
            while(tempcol!=null){
                try{//personaje principal
                    Personaje p=(Personaje)tempcol.dato;
                    s+="nodo"+i+j+"[label=\""+p.nombre+"\n"+p.tipo+"\n "+i+","+j+"\"];\n";
                }catch(Exception er){
                    try{//objetos
                        Objeto o=(Objeto)tempcol.dato;
                        s+="nodo"+i+j+"[label=\""+o.nombre+"\n"+o.tipo+"\n "+i+","+j+"\"];\n";
                    }catch(Exception ex){//objeto nulo
                        s+="nodo"+i+j+"[label=\"Objeto\nVacio"+"\n "+i+","+j+"\"];\n";
                    }
                }
                //enlaces
                if(tempcol.arriba!=null){
                    s+="nodo"+i+j+" -> nodo"+(i-1)+j+";\n";
                }
                if(tempcol.siguiente!=null){
                    s+="nodo"+i+j+" -> nodo"+i+(j+1)+";\n";
                }
                j++;
                tempcol=tempcol.siguiente;
            }
            i++;
            j=1;
            tempfila=tempfila.abajo;
        }       
        s+="}\n}";
        return s;
    }    
    public NodoMatriz BuscarPersonaje(){
        NodoMatriz tempfila=inicio;
        NodoMatriz tempcol=inicio;
        while(tempfila!=null){
            while(tempcol!=null){
                try{
                    Personaje p=(Personaje)tempcol.dato;
                    if(p!=null){                        
                        return tempcol;
                    }
                }catch(Exception er){
                }
                tempcol=tempcol.siguiente;
            }
            tempfila=tempfila.abajo;
            tempcol=tempfila;
        }
        return null;
    }
}
