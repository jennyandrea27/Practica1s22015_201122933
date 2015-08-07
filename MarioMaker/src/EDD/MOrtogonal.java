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
import javax.swing.JPanel;
import mariomaker.Objeto;
import mariomaker.Personaje;

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
    
    public void GenerarMatriz(JPanel panel,String comportamiento){
        NodoMatriz temp=inicio;
        NodoMatriz filatemp=inicio;
        NodoMatriz coltemp;
        int posfila=0;
        int poscol=0;
        URL direccion=null;
        ImageIcon imagen=null;          
        while(filatemp!=null){
            coltemp=filatemp;
            while(coltemp!=null){
                if(coltemp.dato!=null){
                    JLabel label=ImagenLabel(temp);
                    if(label!=null){
                        coltemp.label.setBounds(poscol, posfila, 35, 35);
                        panel.add(coltemp.label);
                        poscol+=36;
                    }
                }else{
                    direccion=getClass().getResource("/Imagenes/null.png");
                    imagen=new ImageIcon(direccion); 
                    coltemp.label=new JLabel(imagen);
                    coltemp.label.setBounds(poscol, posfila, 35, 35);                    
                }   
                //agregar metodos al label, dependiendo del comportamiento
                switch (comportamiento) {
                    case "cola":
                        MetodoCola(coltemp.label);
                        break;
                    case "pila":
                        MetodoPila(coltemp.label);
                        break;
                }
                //agregar label actual al panel
                panel.add(coltemp.label);
                //aumenta la posicion en el eje x para dibujar siguiente label
                poscol+=36;
                //mover coltemp a su siguiente posicion
                coltemp=coltemp.siguiente;
            }
            posfila+=36;
            poscol=0;
            filatemp=filatemp.abajo;
        }
        
    }
    
    //metodo para sacar elementos como cola
    private void MetodoCola(JLabel label){
        label.addMouseListener(new MouseAdapter(){

            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Label click cola");
            }

        });
    }
    //metodo para sacar elementos como pila
    private void MetodoPila(JLabel label){
        label.addMouseListener(new MouseAdapter(){

            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Label click pila");
            }

        });
    }
    
    //Metodo que decide que imagen colocar al label para generar matriz
    public JLabel ImagenLabel(NodoMatriz temp){
        URL direccion=null;
        ImageIcon imagen=null; 
        JLabel etiqueta=null;
        try{
                Personaje p=(Personaje)temp.dato;
                direccion=getClass().getResource("/Imagenes/mario.png");
                imagen=new ImageIcon(direccion);
                etiqueta=new JLabel(imagen);
            }catch(Exception e){
                try{
                    Objeto o=(Objeto)temp.dato;
                    switch(o.getTipo()){
                        case 1:
                        direccion=getClass().getResource("/Imagenes/suelo.png");
                        imagen=new ImageIcon(direccion);    
                        etiqueta=new JLabel(imagen);
                        break;
                        case 2:
                        direccion=getClass().getResource("/Imagenes/pared.png");
                        imagen=new ImageIcon(direccion);   
                        etiqueta=new JLabel(imagen);
                        break;
                        case 3:
                        direccion=getClass().getResource("/Imagenes/goomba.png");
                        imagen=new ImageIcon(direccion); 
                        etiqueta=new JLabel(imagen);
                        break;
                        case 4:
                        direccion=getClass().getResource("/Imagenes/koopa.png");
                        imagen=new ImageIcon(direccion);    
                        etiqueta=new JLabel(imagen);
                        break;
                        case 5:
                        direccion=getClass().getResource("/Imagenes/moneda.png");
                        imagen=new ImageIcon(direccion);   
                        etiqueta=new JLabel(imagen);
                        break;
                        case 6:
                        direccion=getClass().getResource("/Imagenes/hongo.png");
                        imagen=new ImageIcon(direccion);   
                        etiqueta=new JLabel(imagen);
                        break;
                        case 7:
                        direccion=getClass().getResource("/Imagenes/castillo1.png");
                        imagen=new ImageIcon(direccion);   
                        etiqueta=new JLabel(imagen);
                        break;
                        default:
                    }
                }catch(Exception er){
                    
                }                
            }
        return etiqueta;
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
