/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frsf.isi.died.tp.vista.interfacesByHauche;

import frsf.isi.died.app.controller.GrafoController;
import frsf.isi.died.tp.modelo.productos.MaterialCapacitacion;
import frsf.isi.died.tp.modelo.BibliotecaList;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javafx.util.Pair;
import javax.swing.JFrame;

/**
 *
 * @author haucherecords
 */
public class Relaciones extends JFrame {
    
    private JFrame frame  =this;
    GrafoController gf=null;
    
    public Relaciones (MaterialCapacitacion mcap){
             
        //this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        ControlPanel panelCtrl = new ControlPanel();
        GrafoPanel panelGrf = new GrafoPanel();
        panelGrf.setSize(900,360);
        
        
        gf = new GrafoController(panelGrf, panelCtrl,(ArrayList)BibliotecaList.getInstance().getListadoTema(mcap)); 
        panelGrf.setController(gf);
        panelCtrl.setController(gf);

        this.add(panelCtrl, BorderLayout.PAGE_START);
        this.add(panelGrf);

        this.pack();
        this.setResizable(false);
        this.setVisible(true);
        
       
        
        //centrar ventana
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                double width = screenSize.getWidth()/2;
                double height = screenSize.getHeight()/2;
                this.setLocation((int)width-this.getWidth()/2,(int)height-this.getHeight()/2);
        //
        
        this.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e){
                BibliotecaList.getInstance().setGrafoPorTema(gf.temaGrafo(),gf.getGrafo());
            }
        });
        
    }
    
    
}
