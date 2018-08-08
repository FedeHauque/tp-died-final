package frsf.isi.died.tp.vista.interfaces.grafos;

import frsf.isi.died.app.controller.GrafoController;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;


/**
 *
 * @author mdominguez
 */
public class Controles extends JPanel {
    
    private GrafoController controller;
    private JTextField verticeOrigen,verticeDestino,cantSaltos;
    private JButton b1, siguiente, pageRank;

    public Controles(){
        verticeOrigen = new JTextField();
        verticeDestino = new JTextField();
        cantSaltos = new JTextField();
        verticeOrigen.setColumns(10);
        verticeDestino.setColumns(10);
        cantSaltos.setColumns(6);
        b1 = new JButton();
        this.armarPanel();
        pageRank = new JButton("Page Rank");
       
        this.add(siguiente = new JButton("Siguiente"));
        this.add(pageRank);
        this.setBackground(new java.awt.Color(255,255,102));
        siguiente.setEnabled(false);
        
        siguiente.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e){
                controller.resetearColores();
                if(!controller.siguienteCamino()){siguiente.setEnabled(false);}
                
            }
        });
        
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                controller.resetearColores();
                boolean aux=true;
            Integer cantsaltos=0;
            siguiente.setEnabled(false);
            if(cantSaltos.getText().isEmpty()) cantsaltos=1000000000;
            else
            {
            try
            {
                cantsaltos = Integer.parseInt(cantSaltos.getText());
            }
            catch(NumberFormatException exc) 
            {
                aux = false;
            }
        }
        
        if(aux && !verticeOrigen.getText().isEmpty() && !verticeDestino.getText().isEmpty())
        {
            if(controller.buscarCamino(verticeOrigen.getText(),verticeDestino.getText(),cantsaltos))
            {
                siguiente.setEnabled(true);
            }
            
        }
            }
        });
        
        pageRank.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e){
                
                String orden=controller.calcularPageRank();
                
                JOptionPane.showMessageDialog(null, orden,"Page Rank", JOptionPane.PLAIN_MESSAGE);
            }
        });
        
    }
    
    private void armarPanel(){
        this.add(new JLabel("Vertice Origen"));
        this.add(verticeOrigen);
        this.add(new JLabel("Vertice Destino"));
        this.add(verticeDestino);
        this.add(new JLabel("Cantidad de Saltos"));   
        this.add(cantSaltos);
        b1.setText("Buscar camino");
        this.add(b1);


    }

    public GrafoController getController() {
        return controller;
    }

    public void setController(GrafoController controller) {
        this.controller = controller;
    }

    
}
