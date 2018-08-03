package frsf.isi.died.app.controller;

import frsf.isi.died.tp.estructuras.Arista;
import frsf.isi.died.tp.estructuras.Grafo;
import frsf.isi.died.tp.estructuras.Vertice;
import frsf.isi.died.tp.modelo.productos.MaterialCapacitacion;
import frsf.isi.died.tp.modelo.productos.Libro;
import frsf.isi.died.tp.modelo.BibliotecaList;
//import frsf.isi.died.tp.util.Ordenador;
import frsf.isi.died.tp.vista.interfacesByHauche.AristaView;
import frsf.isi.died.tp.vista.interfacesByHauche.ControlPanel;
import frsf.isi.died.tp.vista.interfacesByHauche.GrafoPanel;
import frsf.isi.died.tp.vista.interfacesByHauche.VerticeView;
import java.awt.Color;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javafx.util.Pair;


/**
 *
 * @author mdominguez
 */
public class GrafoController {
    
    private static Integer _GENERADOR_ID=1;
    private Grafo<MaterialCapacitacion> grafo, grafoPrev;
    private GrafoPanel vistaGrafo;
    private ControlPanel vistaControl;
    private Map<Vertice<MaterialCapacitacion>,VerticeView> vertices;
    private Map<Arista<MaterialCapacitacion>,AristaView> aristas;
    private ArrayList<ArrayList<Arista<MaterialCapacitacion>>> caminos;
    private HashMap<Vertice<MaterialCapacitacion>,Integer> pageRank;
    private int idxCaminos;
    private String tema;

    
    
    public GrafoController(GrafoPanel panelGrf,ControlPanel panelCtrl,ArrayList<MaterialCapacitacion> amc) {
        this.vistaGrafo = panelGrf;
        this.vistaControl = panelCtrl;
        grafo= new Grafo<MaterialCapacitacion>();
        this.vertices = new LinkedHashMap<Vertice<MaterialCapacitacion>,VerticeView>();
        this.aristas = new LinkedHashMap<Arista<MaterialCapacitacion>,AristaView>();
        this.tema=amc.get(0).getTema();
        this.grafoPrev= new Grafo<MaterialCapacitacion>();
        
        
        if(BibliotecaList.getInstance().containsGrafoTema(amc.get(0).getTema())){
            this.grafoPrev=BibliotecaList.getInstance().getGrafoPorTema(amc.get(0).getTema());
        }
        
        
        Integer r=150,xc,yc;
        xc=panelGrf.getWidth()/2;
        yc=panelGrf.getHeight()/2;
        
        Double ang=2*Math.PI/(double)amc.size();
        Color color=new Color(0,0,0);
        
        for(int i=0;i<amc.size();i++) {
            
            if(amc.get(i).esVideo()) color=new Color(255,0,0);
            if(amc.get(i).esLibro()) color=new Color(0,255,0);
            
            crearVertice(((Double)(xc+Math.cos(i*ang)*r)).intValue(),((Double)(yc+Math.sin(i*ang)*r)).intValue(),color,amc.get(i).getTitulo());
        } 
        
        for(int g=0;g<grafoPrev.getAristas().size();g++){
            
            AristaView view= new AristaView();
            VerticeView origen = vertices.get(grafoPrev.getAristas().get(g).getInicio());
            VerticeView destino= vertices.get(grafoPrev.getAristas().get(g).getFin());
            view.setOrigen(origen);
            view.setDestino(destino);
            
            this.crearArista(view);
            
          
            
        }
        
     }
 
    
   
    
    public void crearVertice(Integer coordenadaX, Integer coordenadaY,Color color,String titulo){
        MaterialCapacitacion m = new Libro(_GENERADOR_ID++,titulo);
        Vertice v1 = new Vertice(m);
        grafo.addNodo(v1);        
        VerticeView v = new VerticeView(coordenadaX, coordenadaY, color);
        v.setVertice(v1);
        this.vistaGrafo.agregar(v);
        this.vertices.put(v1, v);
        this.vistaGrafo.repaint();
        
    }
    

    public void crearArista(AristaView arista){
        if(arista.getOrigen()==null || arista.getDestino()==null) return;
        //System.out.println(arista+" "+arista.getOrigen().getVertice());
        if(arista.getOrigen().getVertice()==arista.getDestino().getVertice()) return;
        
        List<AristaView> aristasView = new ArrayList<AristaView>(aristas.values());
        
        for(int k=0;k<aristas.size();k++){
        
            if( (((AristaView)(aristasView.get(k))).getOrigen()==arista.getOrigen()) && (((AristaView)(aristasView.get(k))).getDestino()==arista.getDestino())){
                return;
            }
        
        }
       
       
        Arista a1 = this.grafo.conectar(arista.getOrigen().getVertice(), arista.getDestino().getVertice());
        this.aristas.put(a1, arista);
        this.vistaGrafo.agregar(arista);
        this.vistaGrafo.repaint();
    }
    
    public void resetearColores(){
        Iterator ite = aristas.entrySet().iterator();
        
        while(ite.hasNext()){
            ((Map.Entry<Arista<MaterialCapacitacion>,AristaView>) ite.next()).getValue().resetColor();
        }
        this.vistaGrafo.repaint();
    }

    public Grafo getGrafo(){
        return grafo;
    }
    
    public boolean buscarCamino(String nodo1,String nodo2,Integer saltos)
    {
        caminos=null;
        idxCaminos=0;
        Vertice<MaterialCapacitacion> origen = buscarVerticePorNombre(nodo1);
        Vertice<MaterialCapacitacion> destino = buscarVerticePorNombre(nodo2);
        if(origen==null || destino==null) return false;
        
        caminos=grafo.buscarCamino(origen, destino, saltos);
        
        return siguienteCamino();
    }
    
    public String temaGrafo(){ return tema;}
    
    public GrafoPanel getGrafoPanel(){return this.vistaGrafo;}
    public ControlPanel getControlpanel(){return this.vistaControl;}
    
    public boolean siguienteCamino()
    {
        if(caminos==null || idxCaminos>=caminos.size()) return false;
        for(int i=0;i<caminos.get(idxCaminos).size();i++)
        {
            aristas.get(caminos.get(idxCaminos).get(i)).setHighlighted(vistaGrafo);
            
        }
        this.vistaGrafo.repaint();
        idxCaminos++;
        return (idxCaminos<caminos.size());
    }
    
    private Vertice<MaterialCapacitacion> buscarVerticePorNombre(String nombre){
        for(Vertice<MaterialCapacitacion> unVertice: this.vertices.keySet()){
            if(unVertice.getValor().getTitulo().equals(nombre)) return unVertice;
        }
        return null;
    }
    
    public String calcularPageRank(){
        
        for (Vertice<MaterialCapacitacion> v: vertices.keySet()){
            v.setPR(1.0);
        }
        
        boolean ahiNoma=false;

        
        while(!ahiNoma){

            ArrayList<Double> valoresAnteriores=new ArrayList();
            ArrayList<Double> valoresNuevos=new ArrayList();
            
            for(Vertice<MaterialCapacitacion> v: vertices.keySet()){
                
                ArrayList<Vertice<MaterialCapacitacion>> adyacentes = grafo.getAdyacentesQueLeLlegan(v);
            
                valoresAnteriores.add(v.getPR());
                
                Double nuevopr=0.0;
                Double sumatoria=0.0;
            
                for (int i=0;i<adyacentes.size();i++){
                
                    sumatoria+=(double)adyacentes.get(i).getPR()/grafo.gradoSalida(adyacentes.get(i));
                
                }
            
                nuevopr=0.5+(0.5)*sumatoria;
            
                v.setPR(nuevopr);
            
                valoresNuevos.add(nuevopr);
                
            }
        
            ArrayList verificacion = new ArrayList();
            
            Integer cnt=0;
            
            for(int m=0;m<valoresAnteriores.size();m++){
                
                if(0.00001>Math.abs(valoresAnteriores.get(m)-valoresNuevos.get(m))){
                   
                   cnt++;
                   
                } 
                
            }
                   
            if(cnt==vertices.keySet().size()){ahiNoma=true;}
            
           
        }
        

        
        ArrayList<Vertice<MaterialCapacitacion>> verticesArray = new ArrayList();
        
        for(Vertice<MaterialCapacitacion> v1: vertices.keySet()){
            verticesArray.add(v1);   
        }
        
        //Collections.sort(verticesArray);
        
        String salida="";
        
        for(int i=0;i<verticesArray.size();i++){
            salida=salida.concat((i+1)+"° "+verticesArray.get(i).getValor().getTitulo()+" - PageRank: "+verticesArray.get(i).getPR()+"\n");
        }
        
        return salida;
        
    }


    
    
}