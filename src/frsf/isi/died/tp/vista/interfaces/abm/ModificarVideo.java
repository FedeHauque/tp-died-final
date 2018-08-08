package frsf.isi.died.tp.vista.interfaces.abm;

import java.awt.Color;
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
import frsf.isi.died.tp.modelo.productos.Video;

public class ModificarVideo extends JFrame {
	private JPanel panel;
    private JLabel block;
    private JLabel idL, tituloL,calificacionL,costoL, fechaL, relevanciaL, duracionL,temaL;
    private JTextField tituloTF, costoTF, calificacionTF, duracionTF;
    private JComboBox temaTF, relevanciaTF;
    private JFormattedTextField fechaTF;
    private JButton aceptar;
    private JFrame frame=this;
    
    public ModificarVideo(Video v) {
    	super("Modificar Video");
    	this.setSize(300, 500);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        panel=new JPanel();
        this.getContentPane().add(panel);
        this.setVisible(true);
        panel.setVisible(true);
        panel.setLayout(new BoxLayout (panel, BoxLayout.Y_AXIS));
        panel.setBackground(new java.awt.Color(255,255,204));
        panel.add(idL = new JLabel(" ID del Video : " + v.getId()));
        panel.add(new JLabel(" "));
        
        panel.add(tituloL = new JLabel(" Título"));
        panel.add(tituloTF = new JTextField(v.getTitulo()));
        tituloTF.setAlignmentX(LEFT_ALIGNMENT);
        tituloTF.setMaximumSize(new Dimension(300,25));
        
        panel.add(new JLabel(" "));
        
        panel.add(costoL = new JLabel(" Costo (en AR$)"));
        panel.add(costoTF = new JTextField(v.getCosto().toString()));
        costoTF.setAlignmentX(LEFT_ALIGNMENT);
        costoTF.setMaximumSize(new Dimension(300,25));
        
        panel.add(new JLabel(" "));
        
        panel.add(calificacionL = new JLabel(" Calificación (1-100)"));
        panel.add(calificacionTF = new JFormattedTextField(v.getCalificacion()));
        calificacionTF.setAlignmentX(LEFT_ALIGNMENT);
        calificacionTF.setMaximumSize(new Dimension(300,25));
        
        panel.add(new JLabel(" "));
        
        // Fecha
        DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        panel.add(fechaL = new JLabel(" Fecha (dd-mm-aaaa)"));
        panel.add(fechaTF = new JFormattedTextField(format));
        fechaTF.setAlignmentX(LEFT_ALIGNMENT);
        fechaTF.setMaximumSize(new Dimension(300,25));
        fechaTF.setText(format.format(v.getFechaPublicacion()));
        //
        
        panel.add(new JLabel(" "));
        
        String[] rel = {"ALTA", "MEDIA", "BAJA"};
        panel.add(relevanciaL = new JLabel(" Relevancia"));
        panel.add(relevanciaTF = new JComboBox(rel));
        relevanciaTF.setAlignmentX(LEFT_ALIGNMENT);
        relevanciaTF.setMaximumSize(new Dimension(300,25));
        switch(v.getRelevancia()) {
	    	case ALTA: relevanciaTF.setSelectedIndex(0); break;
	    	case MEDIA:relevanciaTF.setSelectedIndex(1); break;
	    	case BAJA: relevanciaTF.setSelectedIndex(2); break;
}
        
        panel.add(new JLabel(" "));
        
        String[] temas = {"Administración y Economía", "Agronomía", "Arquitectura y Albañilería", "Autoayuda", "Ciencias Naturales y Biología", "Ciencias Sociales y Filosofía", "Computación e Informática", "Ficción", "Historia, Arte y Geografía", "Lengua y Literatura", "Matemática, Física y Química", "Música",  "Política", "Psicología y Educación", "Religión", "Tecnología", "Turismo, Gastronomía y Deportes"};
        panel.add(temaL = new JLabel(" Tema"));
        panel.add(temaTF = new JComboBox(temas));
        temaTF.setAlignmentX(LEFT_ALIGNMENT);
        temaTF.setMaximumSize(new Dimension(300,25));
        for(int i=0; i<17; i++) {
        	if(temas[i].equals(v.getTema())) {
        		temaTF.setSelectedIndex(i);
        	}
        }
        
        
        panel.add(new JLabel(" "));
        
        panel.add(duracionL = new JLabel(" Duración (en segundos)"));
        panel.add(duracionTF = new JTextField());
        duracionTF.setAlignmentX(LEFT_ALIGNMENT);
        duracionTF.setMaximumSize(new Dimension(300,25));
        duracionTF.setText(v.getDuracionEnSegundos().toString());
        
        panel.add(new JLabel(" "));
        
        panel.add(aceptar = new JButton("Aceptar y Actualizar"));
        aceptar.setBackground(Color.ORANGE);
        
        // poner ventana en el medio
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth()/2;
        double height = screenSize.getHeight()/2;
        this.setLocation((int)width-this.getWidth()/2,(int)height-this.getHeight()/2);

        aceptar.addActionListener(new ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent e)
            {
                
                //Primero se crea una nueva libro con los datos
                Integer id = v.getId();
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
                Integer duracion = Integer.parseInt(duracionTF.getText());
                BibliotecaList.getInstance().eliminar(v);
                BibliotecaList.getInstance().agregar(new Video (id, titulo, costo, duracion, calificacion, fecha, relevancia, tema));
                System.out.println(BibliotecaList.getInstance().materiales());
                frame.dispose();

            }
        });
        
    }
}
