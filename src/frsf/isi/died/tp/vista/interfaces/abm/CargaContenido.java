package frsf.isi.died.tp.vista.interfaces.abm;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import frsf.isi.died.tp.estructuras.TipoNodo;
import frsf.isi.died.tp.modelo.productos.MaterialCapacitacion;



public class CargaContenido extends JFrame {
	private JPanel panel;
    private JList lista;
    private JComboBox cb;
    private JButton agregar;
    private JScrollPane jsp;
    private DefaultListModel lm;
    private ArrayList<String> ls;
    
    public CargaContenido (MaterialCapacitacion mcap){
        
        jsp=new JScrollPane();
        jsp.createVerticalScrollBar();
        this.setSize(500,500); //325,465
        this.setTitle("Cargar Contenido");
        this.setResizable(false);
        panel = new JPanel();
        this.setVisible(true);
        panel.setVisible(true);
        this.getContentPane().add(panel);
        this.setLayout(new FlowLayout());
        panel.setLayout(new FlowLayout());
        
         //centrar ventana
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                double width = screenSize.getWidth()/2;
                double height = screenSize.getHeight()/2;
                this.setLocation((int)width-this.getWidth()/2,(int)height-this.getHeight()/2);
        //
                
                //Lista
                lm = new DefaultListModel<String>();
                ls = mcap.getArbol().listarArbol(0); 
                for(int i=0;i<ls.size();i++)lm.addElement(ls.get(i));
                jsp.setPreferredSize(new Dimension(400,400));
                jsp.add(lista = new JList(lm));
                panel.add(jsp);
                jsp.getViewport().add(lista);
                lista.setDragEnabled(false);
                
              //Combo
                this.add(cb= new JComboBox());
                cb.setPreferredSize(new Dimension(150,20));
              //Boton
                this.add(agregar=new JButton("Agregar"));
                agregar.setMaximumSize(new Dimension(50,20));
    
                lista.addListSelectionListener(new ListSelectionListener(){
                    
                    @Override
                    public void valueChanged(ListSelectionEvent arg0){
                        if (!arg0.getValueIsAdjusting()) {
                        	
                            if(lista.getSelectedValue()!=null){
                            
                            	ArrayList<String> atnString = new ArrayList();
	                            String seleccionado= ((String)lista.getSelectedValue()).replaceAll("\\s", "").substring(5, 8);
	                            switch(seleccionado) {
	                            	case "TIT": cb.setVisible(true);agregar.setVisible(true);if(!mcap.getArbol().hay(TipoNodo.MEM)) atnString.add("Metadatos - Material"); if(!mcap.getArbol().hay(TipoNodo.RESUMEN)) atnString.add("Resumen"); atnString.add("Capitulos"); break;
	                            	case "MEM":	cb.setVisible(true);agregar.setVisible(true);atnString.add("Autor");if(!mcap.getArbol().hay(TipoNodo.EDITORIAL)) atnString.add("Editorial"); if(!mcap.getArbol().hay(TipoNodo.FECHA_PUBLICACION)) atnString.add("Fecha"); atnString.add("Palabras Clave");break;
	                            	case "MEC": cb.setVisible(true);agregar.setVisible(true);if(!mcap.getArbol().hay(TipoNodo.SITIO))atnString.add("Sitio Web");if(!mcap.getArbol().hay(TipoNodo.EJERCICIOS)) atnString.add("Ejercicios"); atnString.add("Palabras Clave");break;
	                            	case "SEC": cb.setVisible(true);agregar.setVisible(true);atnString.add("Parrafo");break;
	                            	case "CAP": cb.setVisible(true);agregar.setVisible(true);atnString.add("Seccion");if(!mcap.getArbol().hay(TipoNodo.MEC)) atnString.add("Metadatos - Capitulo");break;
	                            	case "RES": cb.setVisible(true);agregar.setVisible(true);atnString.add("Parrafo");break;
	                            	default: cb.setVisible(false);agregar.setVisible(false);
	                            }
	                            ComboBoxModel cbm = new DefaultComboBoxModel(atnString.toArray());
	
	                            cb.setModel(cbm);
                            }
                        
                        }
                    }
                    
                });
                
                agregar.addActionListener(new ActionListener()
                {
                    
                    @Override
                    public void actionPerformed(java.awt.event.ActionEvent e)
                    {
                        
                        
                        String valor=agregarPopUp((String)cb.getSelectedItem());
                        
                        if(valor.isEmpty()) JOptionPane.showMessageDialog(null, "Debe ingresar un dato", "Error", JOptionPane.ERROR_MESSAGE);
                        else{
                            JOptionPane.showMessageDialog(null, "Se agregó un nodo", "Exito", JOptionPane.INFORMATION_MESSAGE);
                            
                            String seleccionado= ((String)lista.getSelectedValue()).replaceAll("\\s","").substring(5, 8);
                            TipoNodo tipo = null;
                            System.out.println((String)cb.getSelectedItem());
                            switch((String)cb.getSelectedItem()) {
                            	case "Metadatos - Material": tipo = TipoNodo.MEM; break;
                            	case "Resumen": tipo = TipoNodo.RESUMEN; break;
                            	case "Capitulos": tipo = TipoNodo.CAPITULO; break;
                            	case "Autor": tipo = TipoNodo.AUTOR; break;
                            	case "Editorial": tipo = TipoNodo.EDITORIAL; break;
                            	case "Fecha": tipo = TipoNodo.FECHA_PUBLICACION; break;
                            	case "Palabras Clave": tipo = TipoNodo.PALABRA_CLAVE; break;
                            	case "Sitio Web": tipo = TipoNodo.SITIO; break;
                            	case "Ejercicios": tipo = TipoNodo.EJERCICIOS; break;
                            	case "Parrafo": tipo = TipoNodo.PARRAFO; break;
                            	case "Seccion": tipo = TipoNodo.SECCION; break;
                            	case "Metadatos - Capitulo": tipo = TipoNodo.MEC; break;
                            }
                            System.out.println(seleccionado);
                            switch(seleccionado) {
	                        	case "TIT": mcap.getArbol().add(valor, tipo, TipoNodo.TITULO); break;
	                        	case "MEM":	mcap.getArbol().add(valor, tipo, TipoNodo.MEM); break;
	                        	case "MEC": mcap.getArbol().add(valor, tipo, TipoNodo.MEC); break;
	                        	case "SEC": mcap.getArbol().add(valor, tipo, TipoNodo.SECCION); break;
	                        	case "CAP": mcap.getArbol().add(valor, tipo, TipoNodo.CAPITULO); break;
	                        	case "RES": mcap.getArbol().add(valor, tipo, TipoNodo.RESUMEN); break;
                            }
                           
                            lm=new DefaultListModel();
                            ls=mcap.getArbol().listarArbol(0);
                        
                            for(int i=0;i<ls.size();i++)lm.addElement(ls.get(i));

                            lista.setModel(lm);
                           
                        }

                        
                    }
                });
    
    
    }
    
    private String agregarPopUp (String s){
        
        if(s.equals("Metadatos - Material") || s.equals("Resumen") || s.equals("Metadatos - Capitulo") ) return " ";
        
        else {
            
            return JOptionPane.showInputDialog("Ingrese el dato");
            
        }
         
     }
    
}
