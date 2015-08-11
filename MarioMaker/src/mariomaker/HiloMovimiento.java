/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mariomaker;

import EDD.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import static javax.swing.WindowConstants.HIDE_ON_CLOSE;
import static mariomaker.Comportamiento.*;


/**
 *
 * @author jenny
 */
public class HiloMovimiento implements Runnable{
    Object m=null;
    MOrtogonal moriginal=new MOrtogonal();
    MOrtogonal matriz=new MOrtogonal();
    boolean estado=false;//apagado, pausa
    Thread hilo=new Thread();
    JFrame formjuego=new JFrame();
    JPanel panel;//panel para graficar matriz
    boolean cambio=true;
    int contteclas=0;
    int punteo=0;
    int vida=1;

    public HiloMovimiento(MOrtogonal matriz) {
        this.moriginal=(MOrtogonal)matriz.clone();
        this.m = matriz.clone();
        this.matriz=(MOrtogonal)m;
    }
    
    @Override
    public void run() {
        while(estado){
            if(cambio){                
                GenerarForm(); 
                DibujarMatriz();
                MovimientoEnemigos();                
                
//                cambio=false;
            }
            try {
                hilo.sleep(800);
            } catch (InterruptedException ex) {
                Logger.getLogger(HiloMovimiento.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Detener();
    }
    
    private void GenerarForm(){        
        int f=(matriz.numfilas+1)*36;
        int c=(matriz.numcolumnas+1)*36;
        formjuego.getContentPane().removeAll();
        panel=new JPanel(){
            
            public Dimension getPreferedSize(){
                return new Dimension(c,f);
            }
        };        
        
        //fondo framejuego
        formjuego.setLayout(null);
        URL direccion=null;
        ImageIcon imagen=null; 
        direccion=getClass().getResource("/Imagenes/fondo_1.jpg");
        imagen=new ImageIcon(direccion);
        JLabel fondo=new JLabel(imagen);
        fondo.setBounds(0, -100, 1200, 750);
        //boton para pausar el juego
        JButton bpausa=new JButton("PAUSA");
        bpausa.setBounds(50,200,100,50);
        MetodoBPausa(bpausa);
        formjuego.add(bpausa);
        //boton para reanudar el juego
        JButton breanudar=new JButton("REANUDAR");
        breanudar.setBounds(50,300,100,50);
        MetodoBReanudar(breanudar);
        formjuego.add(breanudar);
        //boton para reanudar el juego
        JButton breiniciar=new JButton("REINICIAR");
        breiniciar.setBounds(50,400,100,50);
        MetodoBReiniciar(breiniciar);
        formjuego.add(breiniciar);
        //agregar boton para visualizar grafo de matriz
        JButton bmatriz=new JButton();
        bmatriz.setText("Matriz");
        bmatriz.setBounds(50,500,120,50);
        MetodoBReporteMatriz(bmatriz);
        formjuego.add(bmatriz);
        //hilo para reconocer teclas que se presionan
        formjuego.addKeyListener(new HiloTeclado());
        formjuego.setFocusable(true);
        formjuego.add(fondo);
        formjuego.setSize(1200, 650);
        formjuego.setDefaultCloseOperation(HIDE_ON_CLOSE);
        formjuego.setLocationRelativeTo(null);        
        formjuego.setLayout(null);
             
    }
    
    private void DibujarMatriz(){
        JScrollPane jsp=new JScrollPane(panel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        jsp.setBounds(200,50,800,600);        
        NodoMatriz filatemp=matriz.inicio;
        NodoMatriz coltemp=matriz.inicio;
        int posfila=0;
        int poscol=0;
        int contfila=1;
        int contcol=1;
        URL direccion=null;
        ImageIcon imagen=null;          
        while(filatemp!=null){
            while(coltemp!=null){
                if(coltemp.dato!=null){
                    JLabel label=ImagenLabel(coltemp);
                    if(label!=null){
                        coltemp.label=label;
                        coltemp.label.setBounds(poscol, posfila, 35, 35);
                        
                    }
                }else{
                    direccion=getClass().getResource("/Imagenes/null.png");
                    imagen=new ImageIcon(direccion); 
                    coltemp.label=new JLabel(imagen);
                    coltemp.label.setBounds(poscol, posfila, 35, 35);
                }                   
                //agregar label actual al panel
                panel.add(coltemp.label);
                //aumenta la posicion en el eje x para dibujar siguiente label
                poscol+=36;
                //aumenta el contador de columna
                contcol++;
                //mover coltemp a su siguiente posicion
                coltemp=coltemp.siguiente;
            }
            //aumenta la posicion de fila para dibujar abajo la siguiente fila
            posfila+=36;
            //devuelve la posicion de columna a 0 para dibujar siguiente fila
            poscol=0;
            //devuelve contador de columna a 1
            contcol=1;
            //aumenta el contador de fila
            contfila++;
            filatemp=filatemp.abajo;
            coltemp=filatemp;
        }
        panel.setLayout(null);
        formjuego.add(jsp);        
        panel.setBackground(new Color(199,215,250));
        formjuego.setVisible(true);           
    }
    public JLabel ImagenLabel(NodoMatriz temp){
        URL direccion=null;
        ImageIcon imagen=null; 
        JLabel etiqueta=null;
        try{
                Personaje p=(Personaje)temp.dato;
                direccion=getClass().getResource("/Imagenes/mariov.png");
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
                        direccion=getClass().getResource("/Imagenes/goombav.png");
                        imagen=new ImageIcon(direccion); 
                        etiqueta=new JLabel(imagen);
                        break;
                        case 4:
                        direccion=getClass().getResource("/Imagenes/koopav.png");
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
                        etiqueta=null;
                    }
                }catch(Exception er){
                    etiqueta=null;
                }                
            }
        return etiqueta;
    }
    private void MetodoBPausa(JButton boton){                                
        boton.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e)
            {                
                cambio=false;
            }
        }); 
    }
    private void MetodoBReanudar(JButton boton){                                
        boton.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e)
            {                
                cambio=true;
            }
        }); 
    }
    private void MetodoBReiniciar(JButton boton){                                
        boton.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e)
            {                
                m = moriginal.clone();
                matriz=(MOrtogonal)m;
            }
        }); 
    }
    private void MetodoBReporteMatriz(JButton boton){
        boton.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e)
            {
                //agregar metodo al boton para visualizar resumen de elementos dentro de la lista
                String dot=matriz.Graficar();
                //String html=HTML(s,"LISTA DOBLE");
                //generar html del resumen
                escribirArch(dot,direccion+"Matriz.dot");
                //abrir archivo html
                try {
                    //ejecutar comando .dot en consola
                    ProcessBuilder pbuilder;                
                    pbuilder = new ProcessBuilder( "dot", "-Tpng", "-o", direccion+"Matriz.png", direccion+"Matriz.dot" );
                    pbuilder.redirectErrorStream( true );                
                    pbuilder.start();
                    //abrir imagen de lista                    
                    Runtime.getRuntime().exec("run-mailcap"+" "+direccion+"Matriz.png");
                } catch (IOException ex) {
                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        }); 
    }
    //metodo que recorre la matriz para verificar el movimieno
    private void MovimientoEnemigos(){
        NodoMatriz tempfila=matriz.inicio;
        NodoMatriz tempcol;
        while(tempfila!=null){
            tempcol=tempfila;
            while(tempcol!=null){
                if(tempcol.dato!=null){                
                    //mover enemigo
                    try{
                        Objeto o=(Objeto)tempcol.dato;
                        switch(o.getTipo()){
                            case 3://goomba                    
                            case 4://koopa
                                if(tempcol.abajo!=null){
                                    if(tempcol.abajo.dato!=null){//existe elemento en la posicion inferior, se mueve hacia el lado correspondiente
                                        if(o.direccion.equals("derecha")){
                                            if(tempcol.siguiente.dato!=null){//existe tope, se cambia direccion del enemigo
                                                o.direccion="izquierda";
                                                tempcol.dato=o;
                                            }else{//enemigo se mueve a la derecha
                                                tempcol.siguiente.dato=tempcol.dato;
                                                tempcol.dato=null;                                            
                                                tempcol=tempcol.siguiente;
                                            }
                                        }else{//objeto se mueve a la izquierda
                                            if(tempcol.anterior.dato!=null){//existe tope, se cambia direccion del enemigo
                                                o.direccion="derecha";
                                                tempcol.dato=o;
                                            }else{//enemigo se mueve a la derecha                                
                                                tempcol.anterior.dato=tempcol.dato;
                                                tempcol.dato=null;                                
                                            }    

                                        }
                                    }else{//no existe objeto abajo, se baja enemigo
                                        if(!o.bajo){
                                            o.bajo=true;
                                            if(tempcol.abajo!=null){
                                            tempcol.abajo.dato=o;
                                            tempcol.dato=null;
                                            }else{
                                                tempcol.dato=null;
                                            }
                                        }else{
                                            o.bajo=false;
                                            tempcol.dato=o;
                                        }
                                    }
                                }else{
                                    tempcol.dato=null;
                                }
                                break;                        
                        }
                    }catch(Exception er){
                        try{
                            Personaje p=(Personaje)tempcol.dato;
                            if(tempcol.abajo==null){//personaje muere
                                tempcol.dato=null;
                            }else{//se verifica que tiene abajo
                                if(tempcol.abajo.dato==null){
                                    Objeto o=(Objeto)tempcol.abajo.dato;
                                    switch(o.getTipo()){                                                                                                                        
                                        case 5:
                                        punteo++;
                                        break;
                                        case 6:
                                        vida++;
                                        break;
                                        case 7:
                                            System.out.println("Juego Ganado");
                                        break;
                                        default:
                                    }
                                    tempcol.abajo.dato=tempcol.dato;
                                    tempcol.dato=null;
                                }
                            }
                        }catch(Exception ex){
                            
                        }
                    }        
                }
                tempcol=tempcol.siguiente;
            }
            tempfila=tempfila.abajo;
        }        
    }        
    
    public synchronized void Iniciar(){
        if(estado){
            return;
        }
        estado=true;//se empieza el juego
        hilo=new Thread(this);
        hilo.start();
    }
    public synchronized void Detener(){
        if(!estado){
            return;
        }
        estado=false;//se empieza el juego        
        try {
            hilo.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(HiloMovimiento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public class HiloTeclado implements KeyListener{                
        @Override
        public void keyTyped(KeyEvent e) {        
        }

        @Override
        //metodo para reconocer cuando una tecla se presiona
        public void keyPressed(KeyEvent e) {
            contteclas++;
            if(contteclas%9999==1){
                if(e.VK_RIGHT==e.getKeyCode()){
                    
                    NodoMatriz temp=matriz.BuscarPersonaje();
                    Personaje p=(Personaje)temp.dato;
                    if(p!=null){                        
                        if(temp.siguiente!=null){
                            if(temp.siguiente.dato==null){
                                temp.siguiente.dato=p;
                                temp.dato=null;
                                System.out.println("derecho");
                            }                          
                        }
                    }
                
                }else if(e.VK_LEFT==e.getKeyCode()){
                    
                    NodoMatriz temp=matriz.BuscarPersonaje();
                    Personaje p=(Personaje)temp.dato;
                    if(p!=null){
                        if(temp.anterior!=null){
                            if(temp.anterior.dato==null){
                                temp.anterior.dato=p;
                                temp.dato=null;
                                System.out.println("izquierda");
                            }                            
                        }
                    }
                
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {        
            contteclas=0;
        }
    }
    
}
