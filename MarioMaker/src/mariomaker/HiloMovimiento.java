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
import javax.annotation.Resource;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
    public static int punteo=0;
    public static int vida=1;
    
    JLabel lpunteo=new JLabel("Punteo: "+punteo);
    JLabel lvidas=new JLabel("Vidas: "+vida);

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
        //label punteo
        lpunteo.setBounds(50, 50, 125, 50);
        formjuego.add(lpunteo);
        //label vidas        
        lvidas.setBounds(50, 120, 125, 50);
        formjuego.add(lvidas);
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
                if(p.direccion.equals("derecha")){                    
                    direccion=getClass().getResource("/Imagenes/mariod.png");
                }else{
                    direccion=getClass().getResource("/Imagenes/marioi.png");
                }
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
                                                //se verifica si es personaje, se decrementa vida
                                                try{
                                                    Personaje p=(Personaje)tempcol.dato;
                                                    vida--;
                                                }catch(Exception er){
                                                    
                                                }
                                                o.direccion="izquierda";
                                                tempcol.dato=o;
                                            }else{//enemigo se mueve a la derecha
                                                tempcol.siguiente.dato=tempcol.dato;
                                                tempcol.dato=null;                                            
                                                tempcol=tempcol.siguiente;
                                            }
                                        }else{//objeto se mueve a la izquierda
                                            if(tempcol.anterior.dato!=null){//existe tope, se cambia direccion del enemigo
                                                try{
                                                    Personaje p=(Personaje)tempcol.dato;
                                                    vida--;
                                                }catch(Exception er){
                                                    
                                                }
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
                                vida=0;
                            }else{//se verifica que tiene abajo
                                if(tempcol.abajo.dato==null){                                    
                                    tempcol.abajo.dato=tempcol.dato;
                                    tempcol.dato=null;
                                }else{
                                    Objeto o=(Objeto)tempcol.abajo.dato;
                                    switch(o.getTipo()){                                                                                                                        
                                        case 3://encuentra goomba                                        
                                        tempcol.abajo.dato=tempcol.dato;
                                        tempcol.dato=null;
                                        break;
                                        case 4://encuentra koopa
                                            //personaje no se mueve, siguiente corrida koopa cambia de posicion
                                        break;
                                        case 5://moneda
                                        punteo++;
                                        tempcol.abajo.dato=tempcol.dato;
                                        tempcol.dato=null;
                                        break;
                                        case 6://hongo
                                        vida++;
                                        tempcol.abajo.dato=tempcol.dato;
                                        tempcol.dato=null;
                                        break;
                                        case 7://castillo gana el juego
                                        tempcol.abajo.dato=tempcol.dato;
                                        tempcol.dato=null;
                                        URL direccion=null;
                                        ImageIcon imagen=null; 
                                        direccion=getClass().getResource("/Imagenes/gano.png");
                                        imagen=new ImageIcon(direccion); 
                                        JOptionPane.showMessageDialog(null, imagen);
                                        System.exit(0);
                                        break;
                                        default:
                                    }
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
        if(vida<=0){
            URL direccion=null;
            ImageIcon imagen=null; 
            direccion=getClass().getResource("/Imagenes/perdio.png");
            imagen=new ImageIcon(direccion); 
            JOptionPane.showMessageDialog(null, imagen);
            System.exit(0);
        }
        lvidas.setText("Vidas: "+vida);
        lpunteo.setText("Punteo: "+punteo);
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
                    p.direccion="derecha";
                    if(p!=null){                        
                        if(temp.siguiente!=null){//verifica que no este en la ultima columna
                            if(temp.siguiente.dato==null){//si no hay elemento a la derecha se mueve
                                temp.siguiente.dato=p;
                                temp.dato=null;
                                System.out.println("derecho");
                            }else{//tiene que existir un objeto en esa posicion
                                Objeto o=(Objeto)temp.siguiente.dato;
                                switch(o.getTipo()){                                                                        
                                    case 3://encuentra goomba
                                    vida--;
                                    temp.siguiente.dato=p;
                                    temp.dato=null;
                                    break;
                                    case 4://encuentra koopa
                                    vida--;
                                    temp.siguiente.dato=p;
                                    temp.dato=null;
                                    break;
                                    case 5://moneda
                                    punteo++;
                                    temp.siguiente.dato=p;
                                    temp.dato=null;
                                    break;
                                    case 6://hongo
                                    vida++;
                                    temp.siguiente.dato=p;
                                    temp.dato=null;
                                    break;
                                    case 7://castillo gana el juego
                                    temp.siguiente.dato=p;
                                    temp.dato=null;
                                    URL direccion=null;
                                    ImageIcon imagen=null; 
                                    direccion=getClass().getResource("/Imagenes/gano.png");
                                    imagen=new ImageIcon(direccion); 
                                    JOptionPane.showMessageDialog(null, imagen);
                                    System.exit(0);
                                    break;
                                    default:
                                }
                            }
                            
                        }
                    }
                
                }else if(e.VK_LEFT==e.getKeyCode()){
                    
                    NodoMatriz temp=matriz.BuscarPersonaje();
                    Personaje p=(Personaje)temp.dato;
                    p.direccion="izquierda";
                    if(p!=null){
                        if(temp.anterior!=null){
                            if(temp.anterior.dato==null){
                                temp.anterior.dato=p;
                                temp.dato=null;
                                System.out.println("izquierda");
                            }else{
                                Objeto o=(Objeto)temp.anterior.dato;
                                switch(o.getTipo()){                                                                        
                                    case 3://encuentra goomba
                                    vida--;
                                    temp.anterior.dato=p;
                                    temp.dato=null;
                                    break;
                                    case 4://encuentra koopa
                                    vida--;
                                    temp.anterior.dato=p;
                                    temp.dato=null;
                                    break;
                                    case 5://moneda
                                    punteo++;
                                    temp.anterior.dato=p;
                                    temp.dato=null;
                                    break;
                                    case 6://hongo
                                    vida++;
                                    temp.anterior.dato=p;
                                    temp.dato=null;
                                    break;
                                    case 7://castillo gana el juego
                                    temp.anterior.dato=p;
                                    temp.dato=null;
                                    URL direccion=null;
                                    ImageIcon imagen=null; 
                                    direccion=getClass().getResource("/Imagenes/gano.png");
                                    imagen=new ImageIcon(direccion); 
                                    JOptionPane.showMessageDialog(null, imagen);
                                    System.exit(0);
                                    break;
                                    default:
                                }
                            }                        
                        }
                    }
                
                }else if(e.VK_UP==e.getKeyCode()){
                    NodoMatriz temp=matriz.BuscarPersonaje();
                    Personaje p=(Personaje)temp.dato;
                    if(p.direccion.equals("derecha")){//salta dos espacios hacia arriba, uno a la derecha
                        if(temp.arriba!=null){//existe nodo arriba del personaje
                            if(temp.arriba.dato==null){//hay espacio arriba se mueve un espacio
                                if(temp.arriba.arriba!=null){//existe nodo arriba
                                   if(temp.arriba.arriba.dato==null) {//hay espacio arriba
                                       if(temp.arriba.arriba.siguiente!=null){//existe nodo a la derecha
                                           if(temp.arriba.arriba.siguiente.dato==null){//hay espacio a la derecha
                                               temp.arriba.arriba.siguiente.dato=p;
                                               temp.dato=null;
                                           }else{//no hay espacio a la derecha, salta dos espacios
                                               temp.arriba.arriba.dato=p;
                                               temp.dato=null;
                                           }
                                       }else{//no existe nodo a la derecha, salta dos espacios
                                            temp.arriba.arriba.dato=p;
                                            temp.dato=null;
                                       }
                                   }else{//no hay espacio arriba, salta un espacio
                                       temp.arriba.dato=p;
                                       temp.dato=null;
                                   }
                                }else{//no existe nodo arriba, salta un espacio
                                    temp.arriba.dato=p;
                                    temp.dato=null;
                                }
                            }
                            //no hay espacio arriba, no salta
                        }//no existe nodo arriba, no salta
                        
                    }else{
                        if(temp.arriba!=null){//existe nodo arriba del personaje
                            if(temp.arriba.dato==null){//hay espacio arriba se mueve un espacio
                                if(temp.arriba.arriba!=null){//existe nodo arriba
                                   if(temp.arriba.arriba.dato==null) {//hay espacio arriba se mueve un espacio
                                       if(temp.arriba.arriba.anterior!=null){//existe nodo a la izquierda
                                           if(temp.arriba.arriba.anterior.dato==null){//hay espacio se mueve hacia esta posicion
                                               temp.arriba.arriba.anterior.dato=p;
                                               temp.dato=null;
                                           }else{//no hay espacio solo salta dos espacios, no se desplaza
                                               temp.arriba.arriba.dato=p;
                                               temp.dato=null;
                                           }
                                       }else{//no hay nodo a la izquierda, solo salta dos espacios
                                            temp.arriba.arriba.dato=p;
                                            temp.dato=null;
                                       }
                                   }else{//no hay espacio, solo salta un espacio
                                       temp.arriba.dato=p;
                                       temp.dato=null;
                                   }
                                }else{//no existe nodo arriba, solo salta un espacio
                                    temp.arriba.dato=p;
                                    temp.dato=null;
                                }
                            }
                            //no hay espacio no se desplaza
                        }//no existe nodo arriba, no salta
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
