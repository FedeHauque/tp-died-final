package frsf.isi.died.tp.vista.interfacesByHauche;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class MenuPrincipal extends JFrame {

	private final JButton cargarLibro, cargarVideo, buscarPorDatos,buscarPorContenido;
    private JPanel panel;
    private JFrame frame=this;
    
    public MenuPrincipal(){
    	this.setTitle("Menu Principal - Biblioteca");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setSize(400,250);
        this.setLayout(new BorderLayout());
        panel = new JPanel();
        this.setResizable(false);
        panel.setVisible(true);
        this.add(panel, BorderLayout.CENTER);
        panel.setLayout(new BoxLayout (panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel(" "));
        panel.add(new JLabel(" "));
        panel.add(cargarLibro=new JButton("Cargar nuevo Libro"));
        panel.add(new JLabel(" "));
        panel.add(cargarVideo=new JButton("Cargar nuevo Video"));
        panel.add(new JLabel(" "));
        panel.add(buscarPorDatos=new JButton("Buscar material de capacitación"));
        panel.add(new JLabel(" "));
        panel.add(buscarPorContenido=new JButton("Buscar por contenido del material"));
        
        cargarLibro.setAlignmentX(CENTER_ALIGNMENT);
        cargarVideo.setAlignmentX(CENTER_ALIGNMENT);
        buscarPorDatos.setAlignmentX(CENTER_ALIGNMENT);
        buscarPorContenido.setAlignmentX(CENTER_ALIGNMENT);
        
        // poner ventana en el medio de la pantalla
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth()/2;
        double height = screenSize.getHeight()/2;
        this.setLocation((int)width-this.getWidth()/2,(int)height-this.getHeight()/2);
 
    
    cargarLibro.addActionListener(new ActionListener()
    {
        
        @Override
        public void actionPerformed(java.awt.event.ActionEvent e)
        {
            CargarLibro c = new CargarLibro();
        }
        
    });
    
    cargarVideo.addActionListener(new ActionListener()
    {
        
        @Override
        public void actionPerformed(java.awt.event.ActionEvent e)
        {
            CargarVideo c = new CargarVideo();
        }
        
    });  
    
    buscarPorDatos.addActionListener(new ActionListener()
    {
        
        @Override
        public void actionPerformed(java.awt.event.ActionEvent e)
        {
            BuscarMaterial b = new BuscarMaterial();
        }
        
    });
    }
}
