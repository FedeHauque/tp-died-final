package frsf.isi.died.tp.vista.interfaces.busquedas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import frsf.isi.died.tp.modelo.BibliotecaList;
import frsf.isi.died.tp.modelo.productos.*;
import frsf.isi.died.tp.util.TablaNoEditable;
import frsf.isi.died.tp.vista.interfaces.abm.CargaContenido;
import frsf.isi.died.tp.vista.interfaces.abm.ModificarLibro;
import frsf.isi.died.tp.vista.interfaces.abm.ModificarVideo;
import frsf.isi.died.tp.vista.interfaces.grafos.Relaciones;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ResultadosBusqueda extends JFrame {
	private JPanel panel;
    private JButton actualizar, borrar, asignarRelaciones, cargarContenido, agregarWish;
    private JTable tabla;
    private JFrame frame=this;
    
    public ResultadosBusqueda (ArrayList<MaterialCapacitacion> arm) {
    	this.setSize(800,400);
        this.setTitle("Resultados");
        this.setResizable(false);
        panel = new JPanel();
        this.setVisible(true);
        panel.setVisible(true);
        this.getContentPane().add(panel);
        panel.setLayout(new FlowLayout());
        panel.setBackground(new java.awt.Color(255,255,204));
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
        tabla.setPreferredScrollableViewportSize(new Dimension(790,300));
        tabla.setFillsViewportHeight(true);
        tabla.setCellSelectionEnabled(false);
        tabla.setRowSelectionAllowed(true);
        tabla.setColumnSelectionAllowed(false);
        tabla.getTableHeader().setResizingAllowed(false);
        tabla.getTableHeader().setReorderingAllowed(false);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(12);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(12);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(20);
        scroll.createVerticalScrollBar();
        panel.add(scroll);
        scroll.getViewport().add(tabla);
        
        panel.add(actualizar = new JButton("Modificar Material"));
        panel.add(borrar = new JButton("Eliminar Material"));
        panel.add(asignarRelaciones = new JButton("Asignar Relaciones"));
        panel.add(cargarContenido=new JButton("Cargar Contenido"));
        panel.add(agregarWish=new JButton("Agregar a WishList"));
        actualizar.setBackground(Color.ORANGE);
        borrar.setBackground(Color.ORANGE);
        asignarRelaciones.setBackground(Color.ORANGE);
        cargarContenido.setBackground(Color.ORANGE);
        agregarWish.setBackground(Color.ORANGE);
        
      //centrar ventana
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth()/2;
        double height = screenSize.getHeight()/2;
        this.setLocation((int)width-this.getWidth()/2,(int)height-this.getHeight()/2);
        //
        
        borrar.addActionListener(new ActionListener()
        {
            
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e)
            {
                int filas[] = tabla.getSelectedRows();
                if(filas.length==1){
                    //Se borra el elemento y se informa
                    
                    MaterialCapacitacion mcap = arm.get(filas[0]);
                    BibliotecaList.getInstance().eliminar(mcap);
                    
                    JOptionPane.showMessageDialog(null, "Se borro con exito: "+mcap.getTitulo(), "Borrado exitoso", JOptionPane.INFORMATION_MESSAGE);
                    
                    frame.dispose();
                }
            }
        });
        
        //Click en el boton modificar
        actualizar.addActionListener(new ActionListener()
        {
            
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e)
            {
                int filas[] = tabla.getSelectedRows();
                if(filas.length==1){
                    //Se llama al frame para modificar
                    MaterialCapacitacion mcap = arm.get(filas[0]);
                    if(mcap.esVideo()){
                        ModificarVideo mv = new ModificarVideo ((Video)mcap);
                        frame.dispose();
                    }
                    else if(mcap.esLibro()){
                        ModificarLibro ml = new ModificarLibro ((Libro)mcap);
                        frame.dispose();
                    }
                    
                }
            }
        });
        
        agregarWish.addActionListener(new ActionListener()
        {
            
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e)
            {
                int filas[] = tabla.getSelectedRows();
                if(filas.length==1){
                    //Se llama al frame para modificar
                    MaterialCapacitacion mcap = arm.get(filas[0]);
                    WishList.getInstance().add(mcap);
                    JOptionPane.showMessageDialog(null, "Se ageregó con exito: "+mcap.getTitulo(), "Wishlist", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        
        cargarContenido.addActionListener(new ActionListener()
        {
            
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e)
            {
                int filas[] = tabla.getSelectedRows();
                if(filas.length==1){
                    //Se borra el elemento y se informa
                    
                    MaterialCapacitacion mcap = arm.get(filas[0]);
                    CargaContenido c = new CargaContenido(mcap);
                    
                    frame.dispose();
                }
            }
        });
        
        asignarRelaciones.addActionListener(new ActionListener()
        {
            
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e)
            {
                int filas[] = tabla.getSelectedRows();
                if(filas.length==1){
                    //Se llama al frame para modificar
                    MaterialCapacitacion mcap = arm.get(filas[0]);
                    Relaciones r = new Relaciones(mcap);
                }
            }
        });
        
    }
}
