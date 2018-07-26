package frsf.isi.died.tp.vista.interfacesByHauche;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.util.ArrayList;
import frsf.isi.died.tp.modelo.productos.MaterialCapacitacion;
import frsf.isi.died.tp.util.TablaNoEditable;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ResultadosBusqueda extends JFrame {
	private JPanel panel;
    private JButton actualizar, borrar, asignarRelaciones, cargarContenido;
    private JTable tabla;
    private JFrame frame=this;
    
    public ResultadosBusqueda (ArrayList<MaterialCapacitacion> arm) {
    	this.setSize(600,400);
        this.setTitle("Resultados");
        this.setResizable(false);
        panel = new JPanel();
        this.setVisible(true);
        panel.setVisible(true);
        this.getContentPane().add(panel);
        panel.setLayout(new FlowLayout());
        
        //TABLA
        
        TablaNoEditable modelo = new TablaNoEditable();
        modelo.addColumn("Titulo");
        modelo.addColumn("Precio ($)");
        modelo.addColumn("Calificacion");
        modelo.addColumn("Fecha Publicación");
        
        Object row[]=new Object[4];
        
        for(int i=0;i<arm.size();i++){
            row[0]=arm.get(i).getTitulo();
            row[1]=arm.get(i).precio();
            row[2]=arm.get(i).getCalificacion();
            row[3]=arm.get(i).getFechaPublicacion();
            modelo.addRow(row);
        }
        
        tabla=new JTable(modelo);
        JScrollPane scroll = new JScrollPane();
        tabla.setModel(modelo);
        tabla.setPreferredScrollableViewportSize(new Dimension(600,300));
        tabla.setFillsViewportHeight(true);
        tabla.setCellSelectionEnabled(false);
        tabla.setRowSelectionAllowed(true);
        tabla.setColumnSelectionAllowed(false);
        tabla.getTableHeader().setResizingAllowed(false);
        tabla.getTableHeader().setReorderingAllowed(false);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(12);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(12);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(12);
        scroll.createVerticalScrollBar();
        panel.add(scroll);
        scroll.getViewport().add(tabla);
        
        panel.add(actualizar = new JButton("Modificar material"));
        panel.add(borrar = new JButton("Eliminar material"));
        panel.add(asignarRelaciones = new JButton("Asignar relaciones"));
        panel.add(cargarContenido=new JButton("Cargar contenido"));
        
      //centrar ventana
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth()/2;
        double height = screenSize.getHeight()/2;
        this.setLocation((int)width-this.getWidth()/2,(int)height-this.getHeight()/2);
//
        
    }
}
