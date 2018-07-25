package frsf.isi.died.tp.vista.interfacesByHauche;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CargarLibro extends JFrame {
    private JPanel panel;
    private JLabel block;
    private JLabel idL, tituloL,calificacionL,costoL, fechaL, precioCompraL, relevanciaL, paginasL,temaL;
    private JTextField tituloTF, costoTF, calificacionTF, precioCompraTF, paginasTF;
    private JComboBox temaTF, relevanciaTF;
    private JFormattedTextField fechaTF;
    private JButton aceptar;
    private JFrame frame=this;
    
    public CargarLibro() {
    	super("Cargar Libro");
    	this.setSize(300, 550);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        panel=new JPanel();
        this.getContentPane().add(panel);
        this.setVisible(true);
        panel.setVisible(true);
        panel.setLayout(new BoxLayout (panel, BoxLayout.Y_AXIS));
        
        panel.add(idL = new JLabel("ID del Libro = 6896"));
        panel.add(new JLabel(" "));
        
        panel.add(tituloL = new JLabel("Titulo"));
        panel.add(tituloTF = new JTextField());
        tituloTF.setAlignmentX(LEFT_ALIGNMENT);
        tituloTF.setMaximumSize(new Dimension(300,25));
        
        panel.add(new JLabel(" "));
        
        panel.add(costoL = new JLabel("Costo ($)"));
        panel.add(costoTF = new JTextField());
        costoTF.setAlignmentX(LEFT_ALIGNMENT);
        costoTF.setMaximumSize(new Dimension(300,25));
        
        panel.add(new JLabel(" "));
        
        panel.add(calificacionL = new JLabel("Calificación (1-100)"));
        panel.add(calificacionTF = new JFormattedTextField(new Integer(100)));
        calificacionTF.setAlignmentX(LEFT_ALIGNMENT);
        calificacionTF.setMaximumSize(new Dimension(300,25));
        
        panel.add(new JLabel(" "));
        
        // Fecha
        DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        panel.add(fechaL = new JLabel("Fecha (dd-mm-aaaa)"));
        panel.add(fechaTF = new JFormattedTextField(format));
        fechaTF.setAlignmentX(LEFT_ALIGNMENT);
        fechaTF.setMaximumSize(new Dimension(300,25));
        //
        
        panel.add(new JLabel(" "));
        
        String[] rel = {"ALTA", "MEDIA", "BAJA"};
        panel.add(relevanciaL = new JLabel("Relevancia"));
        panel.add(relevanciaTF = new JComboBox(rel));
        relevanciaTF.setAlignmentX(LEFT_ALIGNMENT);
        relevanciaTF.setMaximumSize(new Dimension(300,25));
        
        panel.add(new JLabel(" "));
        
        String[] temas = {"Albañileria", "Ocultismo", "Economía", "Politica", "Programación", "Periodismo"};
        panel.add(temaL = new JLabel("Tema"));
        panel.add(temaTF = new JComboBox(temas));
        temaTF.setAlignmentX(LEFT_ALIGNMENT);
        temaTF.setMaximumSize(new Dimension(300,25));
        
        panel.add(new JLabel(" "));
        
        panel.add(paginasL = new JLabel("Cantidad de paginas"));
        panel.add(paginasTF = new JTextField());
        paginasTF.setAlignmentX(LEFT_ALIGNMENT);
        paginasTF.setMaximumSize(new Dimension(300,25));
        
        panel.add(new JLabel(" "));
        
        panel.add(precioCompraL = new JLabel("Precio compra ($)"));
        panel.add(precioCompraTF = new JTextField());
        precioCompraTF.setAlignmentX(LEFT_ALIGNMENT);
        precioCompraTF.setMaximumSize(new Dimension(300,25));
        
        panel.add(new JLabel(" "));
        
        panel.add(aceptar = new JButton("Aceptar"));
        
        // poner ventana en el medio
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth()/2;
        double height = screenSize.getHeight()/2;
        this.setLocation((int)width-this.getWidth()/2,(int)height-this.getHeight()/2);

    }
}
