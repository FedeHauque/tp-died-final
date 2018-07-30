package frsf.isi.died.tp.vista.interfacesByHauche;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import frsf.isi.died.tp.modelo.BibliotecaList;
import frsf.isi.died.tp.modelo.productos.MaterialCapacitacion;



public class BuscarPorContenido extends JFrame {
	
	 private JButton buscar;
	    private JFrame frame=this;
	    private JLabel  secL, tituloL, autorL, editorialL, fechaL, pcL, parrafoL, capituloL, webL,block;
	    private JTextField secTF, tituloTF, autorTF, editorialTF, fechaTF, pcTF, parrafoTF, capituloTF, webTF;
	    private JPanel panel;
	    
	    public BuscarPorContenido(){
	        
	        this.setTitle("Buscar por contenido");
	        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	        this.setVisible(true);
	        this.setSize(275,430);
	        panel=new JPanel();
	        this.setResizable(false);
	        panel.setVisible(true);
	        this.getContentPane().add(panel);
	        panel.setLayout(new BoxLayout (panel, BoxLayout.Y_AXIS));
	        //centrar ventana
	                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	                double width = screenSize.getWidth()/2;
	                double height = screenSize.getHeight()/2;
	                this.setLocation((int)width-this.getWidth()/2,(int)height-this.getHeight()/2);
	        //
	        panel.add(block=new JLabel(" "));
	        block.setAlignmentX(RIGHT_ALIGNMENT);
	        
	        panel.add(tituloL = new JLabel("Titulo"));
	        panel.add(tituloTF = new JTextField());
	        tituloTF.setAlignmentX(LEFT_ALIGNMENT);
	        tituloTF.setMaximumSize(new Dimension(250,25));
	        
	        panel.add(autorL = new JLabel("Autor"));
	        panel.add(autorTF = new JTextField());
	        autorTF.setAlignmentX(LEFT_ALIGNMENT);
	        autorTF.setMaximumSize(new Dimension(250,25));
	        
	        panel.add(editorialL = new JLabel("Editorial"));
	        panel.add(editorialTF = new JTextField());
	        editorialTF.setAlignmentX(LEFT_ALIGNMENT);
	        editorialTF.setMaximumSize(new Dimension(250,25));
	        
	        panel.add(fechaL = new JLabel("Fecha de publicacion"));
	        panel.add(fechaTF = new JTextField());
	        fechaTF.setAlignmentX(LEFT_ALIGNMENT);
	        fechaTF.setMaximumSize(new Dimension(250,25));
	        
	        panel.add(pcL = new JLabel("Palabra clave"));
	        panel.add(pcTF = new JTextField());
	        pcTF.setAlignmentX(LEFT_ALIGNMENT);
	        pcTF.setMaximumSize(new Dimension(250,25));
	        
	        panel.add(parrafoL = new JLabel("Parrafo"));
	        panel.add(parrafoTF = new JTextField());
	        parrafoTF.setAlignmentX(LEFT_ALIGNMENT);
	        parrafoTF.setMaximumSize(new Dimension(250,25));
	        
	        panel.add(capituloL = new JLabel("Capitulo"));
	        panel.add(capituloTF = new JTextField());
	        capituloTF.setAlignmentX(LEFT_ALIGNMENT);
	        capituloTF.setMaximumSize(new Dimension(250,25));
	        
	        panel.add(secL = new JLabel("Seccion"));
	        panel.add(secTF = new JTextField());
	        secTF.setAlignmentX(LEFT_ALIGNMENT);
	        secTF.setMaximumSize(new Dimension(250,25));
	        
	        panel.add(webL = new JLabel("Sitio Web"));
	        panel.add(webTF = new JTextField());
	        webTF.setAlignmentX(LEFT_ALIGNMENT);
	        webTF.setMaximumSize(new Dimension(250,25));
	        
	        
	        panel.add(new JLabel(" "));
	        
	        panel.add(buscar = new JButton("Buscar"));
	        
	        buscar.addActionListener(new ActionListener()
	        {
	            
	            @Override
	            public void actionPerformed(java.awt.event.ActionEvent e)
	            {
	               String titulo =tituloTF.getText();
	               String autor =autorTF.getText();
	               String editorial =editorialTF.getText();
	               String fecha=fechaTF.getText();
	               String pc = pcTF.getText();
	               String parrafo=parrafoTF.getText();
	               String capitulo=capituloTF.getText();
	               String web=webTF.getText();
	               String sec=webTF.getText();
	               
	               if(titulo.isEmpty()){titulo="";}
	               if(autor.isEmpty()){autor="";}
	               if(editorial.isEmpty()){editorial="";}
	               if(fecha.isEmpty()){fecha="";}
	               if(pc.isEmpty()){pc="";}
	               if(parrafo.isEmpty()){parrafo="";}
	               if(capitulo.isEmpty()){capitulo="";}
	               if(web.isEmpty()){web="";}
	               if(sec.isEmpty()){sec="";}
	               
	                
	                ArrayList<MaterialCapacitacion> arm = (ArrayList)BibliotecaList.getInstance().materiales();
	                ArrayList<MaterialCapacitacion> arm2=new ArrayList<MaterialCapacitacion>();
	                
	                for(int i=0;i<arm.size();i++){
	                    
	                    if(arm.get(i).getArbol().contieneTodo(titulo, autor, editorial, fecha, pc, parrafo, capitulo, web, sec)){
	                        arm2.add(arm.get(i));
	                    }
	                    
	                }
	                
	                new ResultadosBusqueda(arm2);
	                
	                
	            }
	        });
	        
	    }
}
