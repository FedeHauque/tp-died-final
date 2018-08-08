package frsf.isi.died.tp.vista.interfaces.busquedas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import frsf.isi.died.tp.modelo.BibliotecaList;
import frsf.isi.died.tp.modelo.Orden;
import frsf.isi.died.tp.modelo.productos.MaterialCapacitacion;




public class BuscarMaterial extends JFrame {
	private final JButton buscar;
    private JFrame frame=this;
    private final JLabel ordenL, tituloL, temaL, calificacionL, fechaDesdeL, fechaHastaL;
    private JTextField tituloTF, calificacionTF;
    private JComboBox ordenTF, temaTF;
    private JFormattedTextField fechaDesdeTF, fechaHastaTF;
    private JPanel panel;
    
    public BuscarMaterial() {
    	
    	//Seteo del panel y frame
        this.setTitle("Buscar Materiales");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);
        this.setSize(250,410);
        panel = new JPanel();
        this.setResizable(false);
        panel.setVisible(true);
        this.getContentPane().add(panel);
        panel.setLayout(new BoxLayout (panel, BoxLayout.Y_AXIS));
        panel.setBackground(new java.awt.Color(255,255,204));
        
        panel.add(new JLabel(" "));
  
        
        panel.add(tituloL = new JLabel(" Titulo"));
        panel.add(tituloTF = new JTextField());
        tituloTF.setAlignmentX(LEFT_ALIGNMENT);
        tituloTF.setMaximumSize(new Dimension(250,25));
        
        panel.add(new JLabel(" "));
        
        String[] temas = {"Todas", "Administración y Economía", "Agronomía", "Arquitectura y Albañilería", "Autoayuda", "Ciencias Naturales y Biología", "Ciencias Sociales y Filosofía", "Computación e Informática", "Ficción", "Historia, Arte y Geografía", "Lengua y Literatura", "Matemática, Física y Química", "Música",  "Política", "Psicología y Educación", "Religión", "Tecnología", "Turismo, Gastronomía y Deportes"};
        panel.add(temaL = new JLabel(" Tema"));
        panel.add(temaTF = new JComboBox(temas));
        temaTF.setAlignmentX(LEFT_ALIGNMENT);
        temaTF.setMaximumSize(new Dimension(250,25));
        
        panel.add(new JLabel(" "));
        
        panel.add(calificacionL = new JLabel(" Calificacion (1-100)"));
        panel.add(calificacionTF = new JTextField());
        calificacionTF.setMaximumSize(new Dimension(100,25));
        calificacionTF.setAlignmentX(LEFT_ALIGNMENT);
        
        panel.add(new JLabel(" "));
        
     // Fecha DESDE
        DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        panel.add(fechaDesdeL = new JLabel(" Fecha desde (dd-mm-aaaa)"));
        panel.add(fechaDesdeTF = new JFormattedTextField(format));
        fechaDesdeTF.setAlignmentX(LEFT_ALIGNMENT);
        fechaDesdeTF.setMaximumSize(new Dimension(250,25));
        //
        
        panel.add(new JLabel(" "));
        
        // Fecha HASTA
        panel.add(fechaHastaL = new JLabel(" Fecha hasta (dd-mm-aaaa)"));
        panel.add(fechaHastaTF = new JFormattedTextField(format));
        fechaHastaTF.setAlignmentX(LEFT_ALIGNMENT);
        fechaHastaTF.setMaximumSize(new Dimension(250,25));
        //
        
        panel.add(new JLabel(" "));
        
        String[] ord = {"Alfabetico", "Precio", "Fecha", "Calificacion", "Relevancia"};
        panel.add(ordenL = new JLabel(" ¿En qué orden desea listar los registros?"));
        panel.add(ordenTF = new JComboBox(ord));
        ordenTF.setAlignmentX(LEFT_ALIGNMENT);
        ordenTF.setMaximumSize(new Dimension(250,25));
        
        panel.add(new JLabel(" "));
        
        panel.add(buscar = new JButton("Buscar Resultados"));
        buscar.setBackground(Color.ORANGE);
        
        //pongo ventana en el medio
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                double width = screenSize.getWidth()/2;
                double height = screenSize.getHeight()/2;
                this.setLocation((int)width-this.getWidth()/2,(int)height-this.getHeight()/2);
    	
    
                buscar.addActionListener(new ActionListener()
                {
                    
                    @Override
                    public void actionPerformed(java.awt.event.ActionEvent e)
                    {
                        String titulo = null;
                        if(!tituloTF.getText().equals("")) {
                        	titulo = tituloTF.getText();
                        }
                        String tema = null;
                        if(!temas[temaTF.getSelectedIndex()].equals("Todas")) {
                        	tema = temas[temaTF.getSelectedIndex()]; 
                        }
                        Integer cal = null;
                        if(!calificacionTF.getText().equals("")) {
                        	cal = Integer.parseInt(calificacionTF.getText());
                        }
                         
                        Date fechaDesde = null, fechaHasta = null;
                        try {
                            if (!fechaDesdeTF.getText().equals(""))
                            	fechaDesde = format.parse(fechaDesdeTF.getText());
                            if (!fechaHastaTF.getText().equals(""))
                            	fechaHasta = format.parse(fechaHastaTF.getText());
                        } catch (ParseException ex) {
                            Logger.getLogger(BuscarMaterial.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        Orden orden = null;
                        switch(ord[ordenTF.getSelectedIndex()]) {
                        	case "Alfabetico": orden = Orden.ALFABETICO; break;
                        	case "Precio": orden = Orden.PRECIO; break;
                        	case "Fecha": orden = Orden.FECHA; break;
                        	case "Calificacion": orden = Orden.CALIFICACION; break;
                        	case "Relevancia": orden = Orden.REELEVANCIA; break;
                        }
                        
                        ArrayList<MaterialCapacitacion> arm = BibliotecaList.getInstance().ordenar(BibliotecaList.getInstance().buscar(titulo, cal, fechaDesde, fechaHasta, tema), orden); 
                        new ResultadosBusqueda(arm);
                    }
                });
    
    }
}
