/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mariomaker;

import EDD.ListaD;
import EDD.MOrtogonal;
import EDD.Nodo;
import EDD.NodoMatriz;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import static mariomaker.Principal.listadoble;

/**
 *
 * @author jenny
 */
public class Comportamiento extends javax.swing.JFrame {
    String comportamiento="";
    //generar nueva form
    //frame en donde se visualiza la matriz    
    JFrame juego=new JFrame();
    JPanel panel;
    MOrtogonal matriz=new MOrtogonal();
    //panel para lista
    JPanel panellista;  
    ListaD listadoble;
    //eliminar fila matriz   
    
    //direccion para reportes
    public static String direccion="/home/jenny/Documents/EDD2/MarioMaker/src/Imagenes/";

    /**
     * Creates new form Comportamiento
     */
    public Comportamiento(ListaD listadoble) {
        initComponents();
        this.listadoble=listadoble;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        bPila = new javax.swing.JButton();
        BCola = new javax.swing.JButton();
        bAnterior = new javax.swing.JButton();
        bSiguiente = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("URW Bookman L", 0, 15)); // NOI18N
        jLabel1.setText("Seleccione el comportamiento ");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(50, 30, 270, 30);

        bPila.setFont(new java.awt.Font("URW Bookman L", 0, 15)); // NOI18N
        bPila.setText("PILA");
        bPila.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bPilaActionPerformed(evt);
            }
        });
        getContentPane().add(bPila);
        bPila.setBounds(100, 120, 70, 30);

        BCola.setFont(new java.awt.Font("URW Bookman L", 0, 15)); // NOI18N
        BCola.setText("COLA");
        BCola.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BColaActionPerformed(evt);
            }
        });
        getContentPane().add(BCola);
        BCola.setBounds(240, 120, 80, 30);

        bAnterior.setFont(new java.awt.Font("URW Bookman L", 0, 15)); // NOI18N
        bAnterior.setText("VOLVER");
        bAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAnteriorActionPerformed(evt);
            }
        });
        getContentPane().add(bAnterior);
        bAnterior.setBounds(30, 190, 90, 28);

        bSiguiente.setFont(new java.awt.Font("URW Bookman L", 0, 15)); // NOI18N
        bSiguiente.setText("SIGUIENTE");
        bSiguiente.setEnabled(false);
        bSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSiguienteActionPerformed(evt);
            }
        });
        getContentPane().add(bSiguiente);
        bSiguiente.setBounds(280, 190, 110, 28);

        jLabel2.setFont(new java.awt.Font("URW Bookman L", 0, 15)); // NOI18N
        jLabel2.setText("para remover los objetos...");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(50, 70, 210, 16);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/fondo_1.jpg"))); // NOI18N
        getContentPane().add(jLabel3);
        jLabel3.setBounds(-240, -80, 720, 410);

        setSize(new java.awt.Dimension(409, 276));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void bPilaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPilaActionPerformed
        // TODO add your handling code here:
        comportamiento="pila";
        bSiguiente.setEnabled(true);
    }//GEN-LAST:event_bPilaActionPerformed

    private void BColaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BColaActionPerformed
        // TODO add your handling code here:
        comportamiento="cola";
        bSiguiente.setEnabled(true);
    }//GEN-LAST:event_BColaActionPerformed

    private void GenerarMatriz(){
        final int f=(matriz.numfilas+1)*36;
        final int c=(matriz.numcolumnas+1)*36;
        panel=new JPanel(){
            
            public Dimension getPreferedSize(){
                return new Dimension(c,f);
            }
        };
        JScrollPane jsp=new JScrollPane(panel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        jsp.setBounds(175,150,600,400);        
        juego.add(jsp);        
        panel.setBackground(new Color(199,215,250));        
        DibujarMatriz();                
        panel.setLayout(null);
    }
    private void DibujarMatriz(){        
        NodoMatriz filatemp=matriz.inicio;
        NodoMatriz coltemp;
        int posfila=0;
        int poscol=0;
        int contfila=1;
        int contcol=1;
        URL direccion=null;
        ImageIcon imagen=null;          
        while(filatemp!=null){
            coltemp=filatemp;
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
                //agregar metodos al label, dependiendo del comportamiento
                switch (comportamiento) {
                    case "cola":
                        MetodoCola(coltemp.label,contfila,contcol);
                        break;
                    case "pila":
                        MetodoPila(coltemp.label,contfila,contcol);
                        break;
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
        }
        
    }
    //ImagenLabel() retorna una label que contiene la imagen requerida, dependiendo del tipo del objeto
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
                    }
                }catch(Exception er){
                    
                }                
            }
        return etiqueta;
    }
    
    //metodo para sacar elementos como cola
    private void MetodoCola(JLabel label,int contfila, int contcol){
        
        label.addMouseListener(new MouseAdapter(){

            @Override
            public void mouseClicked(MouseEvent e) {                              
                //se recorre la matriz para objeter el nodo a modificar, en la columna y fila indicadas
                NodoMatriz nodo=matriz.inicio;
                //buscar fila seleccionada
                for(int i=1;i<contfila;i++){
                    nodo=nodo.abajo;
                }
                //buscar columna seleccionala
                for(int i=1;i<contcol;i++){
                    nodo=nodo.siguiente;
                }
                
                if(nodo.dato==null){//nodo se encuentra vacio, se inserta elemento
                    //extraer nodo de la lista
                    Object temp=listadoble.EliminarInicio();
                    //se implementa cola
                    //se verifica si es de tipo personaje u objeto                
                    try{
                        Personaje nuevo=(Personaje)temp;
                        nodo.dato=nuevo;
                    }catch(Exception er){
                        try{
                            Objeto nuevo=(Objeto)temp;
                            nodo.dato=nuevo;
                        }catch(Exception er2){
                        }
                    }                    
                }else{//nodo contiene un dato, se elimina de la matriz y se inserta de nuevo a la listadoble
                    try{
                        Personaje nuevo=(Personaje)nodo.dato;
                        listadoble.InsertarFinal(nuevo);
                        nodo.dato=null;
                    }catch(Exception er){
                        try{
                            Objeto nuevo=(Objeto)nodo.dato;
                            listadoble.InsertarFinal(nuevo);
                            nodo.dato=null;
                        }catch(Exception er2){
                        }
                    }
                }
                
                //refrescar panel para visualizar matriz                
                juego.getContentPane().removeAll();
                GenerarForm();
            }

        });
    }
    //metodo para sacar elementos como pila
    private void MetodoPila(JLabel label,int contfila,int contcol){
        label.addMouseListener(new MouseAdapter(){

            @Override
            public void mouseClicked(MouseEvent e) {
                //se recorre la matriz para objeter el nodo a modificar, en la columna y fila indicadas
                NodoMatriz nodo=matriz.inicio;
                //buscar fila seleccionada
                for(int i=1;i<contfila;i++){
                    nodo=nodo.abajo;
                }
                //buscar columna seleccionala
                for(int i=1;i<contcol;i++){
                    nodo=nodo.siguiente;
                }
                
                if(nodo.dato==null){//nodo se encuentra vacio, se inserta elemento
                    //extraer nodo de la lista
                    Object temp=listadoble.EliminarFinal();
                    //se implementa cola
                    //se verifica si es de tipo personaje u objeto                
                    try{
                        Personaje nuevo=(Personaje)temp;
                        nodo.dato=nuevo;
                    }catch(Exception er){
                        try{
                            Objeto nuevo=(Objeto)temp;
                            nodo.dato=nuevo;
                        }catch(Exception er2){
                        }
                    }                    
                }else{//nodo contiene un dato, se elimina de la matriz y se inserta de nuevo a la listadoble
                    try{
                        Personaje nuevo=(Personaje)nodo.dato;
                        listadoble.InsertarFinal(nuevo);
                        nodo.dato=null;
                    }catch(Exception er){
                        try{
                            Objeto nuevo=(Objeto)nodo.dato;
                            listadoble.InsertarFinal(nuevo);
                            nodo.dato=null;
                        }catch(Exception er2){
                        }
                    }
                }
                
                //refrescar panel para visualizar matriz                
                juego.getContentPane().removeAll();
                GenerarForm();
            }

        });
    }
    
    //genera el panel para visualizar la lista de elementos agregados
    //incluye el comportamiento de salida de los mismos
    private void GenerarLista(){
        int cantidad=listadoble.getCantidad();//cantidad de nodos en la lista
        panellista=new JPanel(){            
            public Dimension getPreferedSize(){
                return new Dimension(600, 50);
            }
        };
        JScrollPane jsp2=new JScrollPane(panellista,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        jsp2.setBounds(175, 10, 600,80);
        juego.add(jsp2); 
        panellista.setBackground(new Color(199,215,250));
        //panellista.setLayout(null);
        listadoble.GenerarLista(panellista);                        
              
    }
    //genera nuevo form junto con el panel q contiene la matriz
    public void GenerarForm(){       
        GenerarMatriz();
        GenerarLista();                
        //agregar boton que permiten agregar filas
        JButton bFila=new JButton();
        bFila.setText("+ Fila");
        bFila.setBounds(800, 150, 150, 50);
        AgregarFila(bFila);
        juego.add(bFila);
        //agregar boton que permiten agregar columnas
        JButton bCol=new JButton("+ Columna");        
        bCol.setBounds(800, 250, 150, 50);
        AgregarColumna(bCol);
        juego.add(bCol);
        //agregar boton y textfield para eliminar fila
        JSpinner js_numfila=new JSpinner();
        js_numfila.setValue(1);
        js_numfila.setName("js_numfila");
        js_numfila.setBounds(800, 350, 50, 50);
        juego.add(js_numfila);        
        JButton bEliminarFila=new JButton("- Fila");
        bEliminarFila.setBounds(860, 350, 150, 50);
        MetodoEliminarFila(bEliminarFila, js_numfila);
        juego.add(bEliminarFila);
        //agregar boton y textfield para eliminar columna
        JSpinner js_numcol=new JSpinner();
        js_numcol.setValue(1);
        js_numcol.setName("js_numcol");
        js_numcol.setBounds(800, 450, 50, 50);
        juego.add(js_numcol);        
        JButton bEliminarCol=new JButton("- Columna");
        bEliminarCol.setBounds(860, 450, 150, 50);
        MetodoEliminarColumna(bEliminarCol, js_numcol);
        juego.add(bEliminarCol);
        //agregar boton para visualizar resumen de elementos en la lista
        JButton bresumen=new JButton();
        bresumen.setText("RESUMEN");
        bresumen.setBounds(50,150,120,50);
        BotonResumen(bresumen);
        juego.add(bresumen);
        //agregar boton para visualizar grafo de lista doble
        JButton blistadoble=new JButton();
        blistadoble.setText("Lista Doble");
        blistadoble.setBounds(50,350,120,50);
        bReporteListaDoble(blistadoble);
        juego.add(blistadoble);
        //agregar boton para visualizar grafo de matriz
        JButton bmatriz=new JButton();
        bmatriz.setText("Matriz");
        bmatriz.setBounds(50,450,120,50);
        bReporteMatriz(bmatriz);
        juego.add(bmatriz);
        //agregar boton Comenzar
        JButton bComenzar=new JButton("COMENZAR");
        bComenzar.setBounds(825, 530, 150, 50);
        MetodoBComenzar(bComenzar);
        juego.add(bComenzar);
        //fondo frameVista
        juego.setLayout(null);
        URL direccion=null;
        ImageIcon imagen=null; 
        direccion=getClass().getResource("/Imagenes/fondo_1.jpg");
        imagen=new ImageIcon(direccion);
        JLabel fondo=new JLabel(imagen);
        fondo.setBounds(0, -100, 1000, 700);
        juego.add(fondo);
        juego.setLayout(null);
        juego.setSize(1000, 600);
        juego.setDefaultCloseOperation(HIDE_ON_CLOSE);
        juego.setLocationRelativeTo(null);
        juego.setVisible(true);                
    }
    private void MetodoEliminarFila(JButton boton,JSpinner js){        
        
                
        boton.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e)
            {
                int f=(int)js.getValue();
                //metodo para agregar fila a la matriz                    
                matriz.EliminarFila(f);
                //refrescar panel para visualizar matriz  
                juego.getContentPane().removeAll();
                GenerarForm();
                
            }
        }); 
    }
    
    private void MetodoEliminarColumna(JButton boton,JSpinner js){        
        
                
        boton.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e)
            {
                int c=(int)js.getValue();
                //metodo para agregar fila a la matriz                    
                matriz.EliminarColumna(c);
                //refrescar panel para visualizar matriz                
                juego.getContentPane().removeAll();
                GenerarForm();
                
            }
        }); 
    }
    
    private void AgregarFila(JButton boton){
        boton.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e)
            {
                //metodo para agregar fila a la matriz                    
                matriz.AgregarFila(1);
                //refrescar panel para visualizar matriz                
                juego.getContentPane().removeAll();
                GenerarForm();
                
            }
        }); 
    }
    private void AgregarColumna(JButton boton){
        boton.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e)
            {
                //metodo para agregar fila a la matriz                    
                matriz.AgregarColumna(1);
                //refrescar panel para visualizar matriz                
                juego.getContentPane().removeAll();
                GenerarForm();
                
            }
        }); 
    }
    private void MetodoBComenzar(JButton boton){                                
        boton.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e)
            {                
                HiloMovimiento hilo=new HiloMovimiento(matriz);
                hilo.Iniciar();
            }
        }); 
    }
    private void BotonResumen(JButton boton){
        boton.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e)
            {
                //agregar metodo al boton para visualizar resumen de elementos dentro de la lista
                String s=listadoble.ResumenObjetos();                
                String html=HTML(s,"RESUMEN ELEMENTOS, LISTA DOBLE");
                //generar html del resumen
                escribirArch(html,direccion+"HTMLResumen.html");
                //abrir archivo html
                try {
                    //abrir html de resumen
                    Runtime.getRuntime().exec("firefox %u"+" "+direccion+"HTMLResumen.html");
                } catch (IOException ex) {
                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        }); 
    }
    
    public String HTML(String s,String nombre){
        int cont=1;
        String html="<html><head><Title><"+nombre+"></title></head><body><center>\n";
        html+=s;
        html+="</center></body></html>";
        return html;                      
    }
    private void bReporteListaDoble(JButton boton){
        boton.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e)
            {
                //agregar metodo al boton para visualizar resumen de elementos dentro de la lista
                String dot=listadoble.Graficar();                
                //String html=HTML(s,"LISTA DOBLE");
                //generar html del resumen
                escribirArch(dot,direccion+"ListaDoble.dot");
                //abrir archivo html
                try {
                    //ejecutar comando .dot en consola
                    ProcessBuilder pbuilder;                
                    pbuilder = new ProcessBuilder( "dot", "-Tpng", "-o", direccion+"ListaDoble.png", direccion+"ListaDoble.dot" );
                    pbuilder.redirectErrorStream( true );                
                    pbuilder.start();
                    //abrir imagen de lista                    
                    Runtime.getRuntime().exec("run-mailcap"+" "+direccion+"ListaDoble.png");
                } catch (IOException ex) {
                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        }); 
    }
    private void bReporteMatriz(JButton boton){
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
    public static void escribirArch(String cadena, String archivo){
            FileWriter fichero = null;
            PrintWriter pw = null;
                try {
                    fichero = new FileWriter(archivo);
                    pw = new PrintWriter(fichero);                    
                    pw.println(cadena);
                    pw.close();
                    fichero.close();                    
                } catch (IOException ex) {

                }                               
    }
    private void bAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAnteriorActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);        
    }//GEN-LAST:event_bAnteriorActionPerformed

    private void bSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSiguienteActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        juego.getContentPane().removeAll();
        GenerarForm();
    }//GEN-LAST:event_bSiguienteActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BCola;
    private javax.swing.JButton bAnterior;
    private javax.swing.JButton bPila;
    private javax.swing.JButton bSiguiente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables
}
