package frsf.isi.died.tp.vista.interfacesByHauche;

import frsf.isi.died.app.controller.GrafoController;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author martdominguez
 */
public class GrafoPanel extends JPanel {

    private JFrame framePadre;
    private GrafoController controller;

    private List<VerticeView> vertices;
    private List<AristaView> aristas;

    private AristaView auxiliar;

    public GrafoPanel() {
        this.framePadre = (JFrame) this.getParent();
        
        this.vertices = new ArrayList<>();
        this.aristas = new ArrayList<>();


        addMouseListener(new MouseAdapter() {
          

            public void mouseReleased(MouseEvent event) {
                VerticeView vDestino = clicEnUnNodo(event.getPoint());
                if (auxiliar!=null && vDestino != null) {
                    auxiliar.setDestino(vDestino);
                    controller.crearArista(auxiliar);
                }
                auxiliar = null;
            }

        });

        addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent event) {
                VerticeView vOrigen = clicEnUnNodo(event.getPoint());
                if (auxiliar==null && vOrigen != null) {
                    auxiliar = new AristaView();                    
                    auxiliar.setOrigen(vOrigen);
                }
            }
        });
    }

    public void agregar(AristaView arista){
        this.aristas.add(arista);
    }    
    
    public void quitar(AristaView arista){
        this.aristas.remove(arista);
    }
    
    public void agregar(VerticeView vert){
        this.vertices.add(vert);
    }
    
    private void dibujarVertices(Graphics2D g2d) {
        for (VerticeView v : this.vertices) {
            g2d.setPaint(Color.BLUE);
            g2d.drawString(v.getVertice().getValor().getTitulo(),v.getCoordenadaX()-5,v.getCoordenadaY()-5);
            g2d.setPaint(v.getColor());
            g2d.fill(v.getNodo());
        }
    }

    private void dibujarAristas(Graphics2D g2d) {
        Double tita=0.0;
        Point p1 = new Point(0,0);
        Point p2 = new Point(0,0);
        Point center= new Point(0,0);
        
        
        for (AristaView a : this.aristas) {
            
            
            tita=Math.atan((double)(a.getDestino().getCoordenadaY()-a.getOrigen().getCoordenadaY())/(a.getDestino().getCoordenadaX()-a.getOrigen().getCoordenadaX()));
            //if(tita<0){tita=Math.PI+tita;}
            if(tita==0 && a.getOrigen().getCoordenadaX()>a.getDestino().getCoordenadaX()){tita=Math.PI;}
            else if(a.getDestino().getCoordenadaX()<a.getOrigen().getCoordenadaX()){tita=tita+Math.PI;}
            
            g2d.setPaint(a.getColor());
            g2d.setStroke ( a.getFormatoLinea());
            g2d.draw(a.getLinea());
            //dibujo una flecha al final
            // con el color del origen para que se note
            g2d.setPaint(a.getColor());
            Polygon flecha = new Polygon();  
            
            //Centro
            center=new Point(a.getDestino().getCoordenadaX()+10, a.getDestino().getCoordenadaY()+10);
            flecha.addPoint(center.x, center.y);
            
            //p1
            p1=new Point(a.getDestino().getCoordenadaX()+10-18,a.getDestino().getCoordenadaY()+10+5);
            p1=this.rotarAlrededor(p1,tita,center);
            flecha.addPoint(p1.x, p1.y);
            
            //p2
            
            p2=new Point(a.getDestino().getCoordenadaX()+10-18,a.getDestino().getCoordenadaY()+10-5);
            p2=this.rotarAlrededor(p2, tita, center);
            flecha.addPoint(p2.x, p2.y);
            
            /*
            flecha.addPoint(a.getDestino().getCoordenadaX(), a.getDestino().getCoordenadaY()+7);
            flecha.addPoint(a.getDestino().getCoordenadaX()+20, a.getDestino().getCoordenadaY()+10);
            flecha.addPoint(a.getDestino().getCoordenadaX(), a.getDestino().getCoordenadaY()+18);*/
            g2d.fillPolygon(flecha);
            
            
            
            
        }
    }

    private VerticeView clicEnUnNodo(Point p) {
        for (VerticeView v : this.vertices) {
            if (v.getNodo().contains(p)) {
                return v;
            }
        }
        return null;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        dibujarVertices(g2d);
        dibujarAristas(g2d);
    }

    public Dimension getPreferredSize() {
        return new Dimension(450, 400);
    }

    public GrafoController getController() {
        return controller;
    }

    public void setController(GrafoController controller) {
        this.controller = controller;
    }
    
    private Point rotarAlrededor (Point p, Double angle, Point center){
        
        Double s = Math.sin(angle);
        Double c = Math.cos(angle);

        Double cx=center.getX();
        Double cy=center.getY();
        
        // translate point back to origin:
        p.setLocation(p.getX()-cx, p.getY()-cy);
        
        // rotate point
        Double xnew = p.getX() * c - p.getY() * s;
        Double ynew = p.getX() * s + p.getY() * c;

        // translate point back:
        //p.x = xnew + cx;
        //p.y = ynew + cy;
        p.setLocation((xnew+cx),(ynew+cy));
        
        return p;
    }
            
           
    
}
