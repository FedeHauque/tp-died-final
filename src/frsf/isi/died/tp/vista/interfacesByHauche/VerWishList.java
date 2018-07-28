package frsf.isi.died.tp.vista.interfacesByHauche;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Date;
import java.util.PriorityQueue;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import frsf.isi.died.tp.modelo.productos.MaterialCapacitacion;
import frsf.isi.died.tp.modelo.productos.WishList;

public class VerWishList extends JFrame {
	private JPanel panel;
    private JFrame frame=this;
    
    public VerWishList() {
    	this.setTitle("Ver Wish List");
        this.setVisible(true);
        this.setSize(400,300);
        this.setLayout(new BorderLayout());
        panel = new JPanel();
        this.setResizable(false);
        this.add(panel, BorderLayout.CENTER);
        panel.setLayout(new BoxLayout (panel, BoxLayout.Y_AXIS));
        panel.setVisible(true);
        panel.add(new JLabel(" "));
        panel.add(new JLabel("    WishList: "));
        panel.add(new JLabel(" "));
        if(WishList.getInstance().isEmpty()) {
        	panel.add(new JLabel("    WishList Vacia!"));
        }else{
        	PriorityQueue<MaterialCapacitacion> copia = new PriorityQueue(WishList.getInstance());
        	MaterialCapacitacion m = copia.poll();
        	while(m != null){
        		String titulo = m.getTitulo();
        		Date fecha = m.getFechaPublicacion();
        		String relevancia = m.getRelevancia().name();
        		Double precio = m.precio();
        		panel.add(new JLabel("    " + titulo + "   -  RELEVANCIA:  " + relevancia + "   -   PRECIO:  " + precio));
        		m = copia.poll();
        	}
        }
        // poner ventana en el medio de la pantalla
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth()/2;
        double height = screenSize.getHeight()/2;
        this.setLocation((int)width-this.getWidth()/2,(int)height-this.getHeight()/2);
        
    }
    
    
    
}
