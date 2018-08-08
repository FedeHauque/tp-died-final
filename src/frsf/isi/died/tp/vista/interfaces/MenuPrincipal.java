package frsf.isi.died.tp.vista.interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import frsf.isi.died.tp.modelo.BibliotecaList;
import frsf.isi.died.tp.vista.interfaces.abm.CargarLibro;
import frsf.isi.died.tp.vista.interfaces.abm.CargarVideo;
import frsf.isi.died.tp.vista.interfaces.abm.VerWishList;
import frsf.isi.died.tp.vista.interfaces.busquedas.BuscarMaterial;
import frsf.isi.died.tp.vista.interfaces.busquedas.BuscarPorContenido;


public class MenuPrincipal extends JFrame {

	private final JButton cargarLibro, cargarVideo, buscarPorDatos,buscarPorContenido, verWishList;
    private JPanel panel;
    private JFrame frame=this;
    
    public MenuPrincipal(){
    	this.setTitle("Menu Principal");
    	BibliotecaList.getInstance().cargar();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setSize(400,360);
        this.setLayout(new BorderLayout());
        panel = new JPanel();
        this.setResizable(false);
        panel.setVisible(true);
        JLabel titulo = new JLabel("Libro++");
        JLabel subtitulo = new JLabel("SISTEMA BIBLIOTECARIO");
        
        this.add(panel, BorderLayout.CENTER);
        panel.setLayout(new BoxLayout (panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel(" "));
        panel.setBackground(new java.awt.Color(255,255,204));
        panel.add(titulo);         
        panel.add(subtitulo);
        panel.add(new JLabel(" "));
        panel.add(cargarLibro=new JButton("Cargar Libro"));
        panel.add(new JLabel(" "));
        panel.add(cargarVideo=new JButton("Cargar Video"));
        panel.add(new JLabel(" "));
        panel.add(buscarPorDatos=new JButton("Buscar por Material"));
        panel.add(new JLabel(" "));
        panel.add(buscarPorContenido=new JButton("Buscar por Contenido"));
        panel.add(new JLabel(" "));
        panel.add(verWishList=new JButton("Ver WishList"));

        subtitulo.setAlignmentX(CENTER_ALIGNMENT);
        titulo.setAlignmentX(CENTER_ALIGNMENT);
        cargarLibro.setAlignmentX(CENTER_ALIGNMENT);
        cargarLibro.setBackground(Color.ORANGE);
        cargarVideo.setAlignmentX(CENTER_ALIGNMENT);
        cargarVideo.setBackground(Color.ORANGE);
        buscarPorDatos.setAlignmentX(CENTER_ALIGNMENT);
        buscarPorDatos.setBackground(Color.ORANGE);
        buscarPorContenido.setAlignmentX(CENTER_ALIGNMENT);
        buscarPorContenido.setBackground(Color.ORANGE);
        verWishList.setAlignmentX(CENTER_ALIGNMENT);
        verWishList.setBackground(Color.ORANGE);
        titulo.setFont(new Font("Impact", Font.PLAIN, 50));
        subtitulo.setFont(new Font("Consolas", Font.PLAIN, 16));
        
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
    
    verWishList.addActionListener(new ActionListener()
    {
        
        @Override
        public void actionPerformed(java.awt.event.ActionEvent e)
        {
            VerWishList c = new VerWishList();
        }
        
    });
    
    buscarPorContenido.addActionListener(new ActionListener()
    {
        
        @Override
        public void actionPerformed(java.awt.event.ActionEvent e)
        {
        	BuscarPorContenido c = new BuscarPorContenido();
        }
        
    });
    
    this.addWindowListener(new WindowAdapter(){
        @Override
        public void windowClosing(WindowEvent e){

            BibliotecaList.getInstance().guardar();
        }
    });
    
    }
}
