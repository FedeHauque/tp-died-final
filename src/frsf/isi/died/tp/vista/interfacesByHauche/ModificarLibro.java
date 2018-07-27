package frsf.isi.died.tp.vista.interfacesByHauche;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import frsf.isi.died.tp.modelo.BibliotecaList;
import frsf.isi.died.tp.modelo.productos.Libro;
import frsf.isi.died.tp.modelo.productos.Relevancia;

public class ModificarLibro extends JFrame {
    private JPanel panel;
    private JLabel block;
    private JLabel idL, tituloL,calificacionL,costoL, fechaL, precioCompraL, relevanciaL, paginasL,temaL;
    private JTextField tituloTF, costoTF, calificacionTF, precioCompraTF, paginasTF;
    private JComboBox temaTF, relevanciaTF;
    private JFormattedTextField fechaTF;
    private JButton aceptar;
    private JFrame frame=this;
    
    public ModificarLibro(Libro l) {
    	super("Modificar Libro");
    	this.setSize(300, 550);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        panel=new JPanel();
        this.getContentPane().add(panel);
        this.setVisible(true);
        panel.setVisible(true);
        panel.setLayout(new BoxLayout (panel, BoxLayout.Y_AXIS));
        
        panel.add(idL = new JLabel("ID del Libro = " + l.getId()));
        panel.add(new JLabel(" "));
        
        panel.add(tituloL = new JLabel("Titulo"));
        panel.add(tituloTF = new JTextField(l.getTitulo()));
        tituloTF.setAlignmentX(LEFT_ALIGNMENT);
        tituloTF.setMaximumSize(new Dimension(300,25));
        
        panel.add(new JLabel(" "));
        
        panel.add(costoL = new JLabel("Costo ($)"));
        panel.add(costoTF = new JTextField(l.getCosto().toString()));
        costoTF.setAlignmentX(LEFT_ALIGNMENT);
        costoTF.setMaximumSize(new Dimension(300,25));
        
        panel.add(new JLabel(" "));
        
        panel.add(calificacionL = new JLabel("Calificación (1-100)"));
        panel.add(calificacionTF = new JFormattedTextField(l.getCalificacion()));
        calificacionTF.setAlignmentX(LEFT_ALIGNMENT);
        calificacionTF.setMaximumSize(new Dimension(300,25));
        
        panel.add(new JLabel(" "));
        
        // Fecha
        DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        panel.add(fechaL = new JLabel("Fecha (dd-mm-aaaa)"));
        panel.add(fechaTF = new JFormattedTextField(format));
        fechaTF.setAlignmentX(LEFT_ALIGNMENT);
        fechaTF.setMaximumSize(new Dimension(300,25));
        fechaTF.setText(format.format(l.getFechaPublicacion()));
        //
        
        panel.add(new JLabel(" "));
        
        String[] rel = {"ALTA", "MEDIA", "BAJA"};
        panel.add(relevanciaL = new JLabel("Relevancia"));
        panel.add(relevanciaTF = new JComboBox(rel));
        relevanciaTF.setAlignmentX(LEFT_ALIGNMENT);
        relevanciaTF.setMaximumSize(new Dimension(300,25));
        switch(l.getRelevancia()) {
        	case ALTA: relevanciaTF.setSelectedIndex(0); break;
        	case MEDIA:relevanciaTF.setSelectedIndex(1); break;
        	case BAJA: relevanciaTF.setSelectedIndex(2); break;
        }
        
        panel.add(new JLabel(" "));
        
        String[] temas = {"Albañileria", "Ocultismo", "Economía", "Politica", "Programación", "Periodismo"};
        panel.add(temaL = new JLabel("Tema"));
        panel.add(temaTF = new JComboBox(temas));
        temaTF.setAlignmentX(LEFT_ALIGNMENT);
        temaTF.setMaximumSize(new Dimension(300,25));
        for(int i=0; i<6; i++) {
        	if(temas[i].equals(l.getTema())) {
        		temaTF.setSelectedIndex(i);
        	}
        }
        
        panel.add(new JLabel(" "));
        
        panel.add(paginasL = new JLabel("Cantidad de paginas"));
        panel.add(paginasTF = new JTextField());
        paginasTF.setAlignmentX(LEFT_ALIGNMENT);
        paginasTF.setMaximumSize(new Dimension(300,25));
        paginasTF.setText(l.getPaginas().toString());
        
        panel.add(new JLabel(" "));
        
        panel.add(precioCompraL = new JLabel("Precio compra ($)"));
        panel.add(precioCompraTF = new JTextField());
        precioCompraTF.setAlignmentX(LEFT_ALIGNMENT);
        precioCompraTF.setMaximumSize(new Dimension(300,25));
        precioCompraTF.setText(l.getPrecioCompra().toString());
        
        panel.add(new JLabel(" "));
        
        panel.add(aceptar = new JButton("Aceptar"));
        
        // poner ventana en el medio
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth()/2;
        double height = screenSize.getHeight()/2;
        this.setLocation((int)width-this.getWidth()/2,(int)height-this.getHeight()/2);

        aceptar.addActionListener(new ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent e)
            {
                
                //Primero se crea una nueva libro con los datos
                Integer id = l.getId();
            	String titulo = tituloTF.getText();
                String tema=temas[temaTF.getSelectedIndex()];
                Integer calificacion = Integer.parseInt(calificacionTF.getText());
                Double costo = Double.parseDouble(costoTF.getText());
                Date fecha = null;
                try {
                    fecha = format.parse(fechaTF.getText());
                } catch (ParseException ex) {
                    Logger.getLogger(CargarLibro.class.getName()).log(Level.SEVERE, null, ex);
                }
                //
                Relevancia relevancia = Relevancia.ALTA;
                switch(rel[relevanciaTF.getSelectedIndex()]) {
                	case "ALTA": relevancia = Relevancia.ALTA; break;
                	case "MEDIA": relevancia = Relevancia.MEDIA; break;
                	case "BAJA": relevancia = Relevancia.BAJA; break;
                }
                Double precioCompra = Double.parseDouble(precioCompraTF.getText());
                Integer paginas = Integer.parseInt(paginasTF.getText());
                BibliotecaList.getInstance().eliminar(l);
                BibliotecaList.getInstance().agregar(new Libro (id, titulo, costo, precioCompra, paginas, calificacion, fecha, relevancia, tema));
                System.out.println(BibliotecaList.getInstance().materiales());
                frame.dispose();
            }
        });
        
        
    }

}
