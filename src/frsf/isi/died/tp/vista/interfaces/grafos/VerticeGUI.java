package frsf.isi.died.tp.vista.interfaces.grafos;

import frsf.isi.died.tp.modelo.productos.MaterialCapacitacion;
import frsf.isi.died.tp.estructuras.Vertice;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

/**
 *
 * @author martdominguez
 */
public class VerticeGUI {
    private Paint color = Color.RED;
    private Color colorBase;

    private Integer coordenadaX;
    private Integer coordenadaY;
    private final Integer RADIO = 20;
    private Shape nodo;
    private Vertice<MaterialCapacitacion> vertice;

    public VerticeGUI() {
    }

    public VerticeGUI(Integer coordenadaX, Integer coordenadaY,Color color) {
        this.colorBase = color;
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;
        this.nodo = new Ellipse2D.Double(coordenadaX,coordenadaY,RADIO,RADIO);
    }

    public boolean pertenece(Point2D p){
        return this.nodo.contains(p);
    }

    public Paint getColor() {
        return color;
    }

    public void setColor(Paint color) {
        this.color = color;
    }

    
    public Integer getCoordenadaX() {
        return coordenadaX;
    }

    public void setCoordenadaX(Integer coordenadaX) {
        this.coordenadaX = coordenadaX;
    }

    public Integer getCoordenadaY() {
        return coordenadaY;
    }

    public void setCoordenadaY(Integer coordenadaY) {
        this.coordenadaY = coordenadaY;
    }

    public Shape getNodo() {
        return nodo;
    }

    public void setNodo(Shape nodo) {
        this.nodo = nodo;
    }

    public Vertice<MaterialCapacitacion> getVertice() {
        return vertice;
    }

    public void setVertice(Vertice<MaterialCapacitacion> vertice) {
        this.vertice = vertice;
    }
    
    
    public Color getColorBase() {
        return colorBase;
    }

    public void setColorBase(Color colorBase) {
        this.colorBase = colorBase;
    }
    
    @Override
    public String toString() {
        return "Vertice{" + "coordenadaX=" + coordenadaX + ", coordenadaY=" + coordenadaY + '}';
    }

    
    
}
