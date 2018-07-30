package frsf.isi.died.tp.modelo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;

import frsf.isi.died.tp.modelo.productos.Libro;
import frsf.isi.died.tp.modelo.productos.MaterialCapacitacion;
import frsf.isi.died.tp.modelo.productos.Relevancia;
import frsf.isi.died.tp.modelo.productos.Video;
import frsf.isi.died.tp.util.ComparadorTitulo;
import frsf.isi.died.tp.estructuras.Grafo;
import frsf.isi.died.app.dao.LibroDao;
import frsf.isi.died.app.dao.VideoDao;


public class BibliotecaList implements Biblioteca {

	private ArrayList<MaterialCapacitacion> materiales;
	private Predicate<MaterialCapacitacion> esLibro = m -> m.esLibro();
	private Predicate<MaterialCapacitacion> esVideo = m -> m.esVideo();
	private static BibliotecaList instance=null;
	private HashMap<String,Grafo> grafosPorTema;
	
	public BibliotecaList() {
		this.materiales = new ArrayList<>();
	}

	public static BibliotecaList getInstance() {
		
		if(instance==null){
            instance=new BibliotecaList();
        }
        return instance;
	}
	
	@Override
	public void agregar(MaterialCapacitacion material) {
		this.materiales.add(material);
	}
	
	public void eliminar(MaterialCapacitacion material) {
		this.materiales.remove(material);
	}

	@Override
	public Integer cantidadMateriales() {
		return this.materiales.size();
	}

	@Override
	public Integer cantidadLibros() {
		int cantidadLibros = 0;
		for (MaterialCapacitacion mat : this.materiales) {
			if (this.esLibro.test(mat))
				cantidadLibros++;
		}
		return cantidadLibros;
	}

	@Override
	public Integer cantidadVideos() {
		int cantidadVideos = 0;
		for (MaterialCapacitacion mat : this.materiales) {
			if (this.esVideo.test(mat))
				cantidadVideos++;
		}
		return cantidadVideos;
	}

	@Override
	public Collection<MaterialCapacitacion> materiales() {
		return this.materiales;
	}

	@Override
	public void imprimir() {
		for (MaterialCapacitacion mat : this.materiales) {
			System.out.println(mat.toString());
		}
	}

	@Override
	public void ordenarPorPrecio(Boolean b) {
		Collections.sort(this.materiales, (m1, m2) -> m1.precio().intValue() - m2.precio().intValue());
	}

	@Override
	public MaterialCapacitacion buscar(Integer precio) {
		Collections.sort(this.materiales, (m1, m2) -> m1.getCosto().intValue() - m2.getCosto().intValue());
		return buscadorBinario(0, this.materiales.size()-1, precio);
	}

	//[12,15] --> 0 0
	
	private MaterialCapacitacion buscadorBinario(Integer inicio, Integer fin, Integer costo) {
		System.out.println(inicio + "_ " + fin);
		if (fin >= 0 || inicio>fin) {
			int medio = (fin + inicio+1) / 2;
//			System.out.println("medio " + medio);
//			System.out.println(this.materiales.get(medio).getCosto());
			if (costo.intValue() == this.materiales.get(medio).getCosto().intValue()) {
				return this.materiales.get(medio);
			}
			if (this.materiales.get(medio).getCosto() < costo) {
				return buscadorBinario(medio+1, fin, costo);
			} else {
				return buscadorBinario(inicio, medio-1, costo);
			}
		}
		throw new RuntimeException("Material de precio " + costo + " no encontrado");

	}
	
	//BUSQUEDA POR TITULO, CALIFICACION Y FECHA 
	
	public ArrayList<MaterialCapacitacion> buscar (String titulo, Integer calificacion, Date fechaDesde, Date fechaHasta, String tema){
		ArrayList<MaterialCapacitacion> mats = (ArrayList<MaterialCapacitacion>)(this.materiales.clone());
		if (titulo != null )
			mats.removeIf(e -> (!e.getTitulo().equals(titulo)));
		if (calificacion != null) {
			mats.removeIf(e -> (!e.getCalificacion().equals(calificacion)));
		}
		if(fechaDesde != null) {
			mats.removeIf(e -> (e.getFechaPublicacion().before(fechaDesde)));
		}
		if(fechaHasta != null) {
			mats.removeIf(e -> (e.getFechaPublicacion().after(fechaHasta)));
		}
		if (tema != null )
			mats.removeIf(e -> (!e.getTema().equals(tema)));
		return mats;
	}
	
	// ORDENAMIENTO DE LA LISTA.
	public ArrayList<MaterialCapacitacion> ordenar (ArrayList<MaterialCapacitacion> mats, Orden ord){
		switch(ord) {
			case ALFABETICO: Collections.sort(mats, (a, b) -> a.getTitulo().compareToIgnoreCase(b.getTitulo())); break;
			case PRECIO: Collections.sort(mats, (a, b) -> a.precio() < b.precio() ? -1 : a.precio() == b.precio() ? 0 : 1); break;
			case CALIFICACION: Collections.sort(mats, (a, b) -> a.getCalificacion() < b.getCalificacion() ? -1 : a.getCalificacion() == b.getCalificacion() ? 0 : 1); break;
			case FECHA: Collections.sort(mats, (a, b) -> a.getFechaPublicacion().before(b.getFechaPublicacion()) ? -1 : a.getFechaPublicacion().equals(b.getFechaPublicacion()) ? 0 : 1); break;
			case REELEVANCIA: Collections.sort(mats, (a, b) -> a.getRelevancia().equals(b.getRelevancia()) ? 0 : a.getRelevancia() == Relevancia.ALTA ? 1 : b.getRelevancia() == Relevancia.ALTA ? -1 : a.getRelevancia() == Relevancia.BAJA ? -1 : 1); break;
		}
		return mats;
	}
	
	//PERSISTENCIA DE DATOS
	public void guardar(){
	 
		LibroDao DAO_L = new LibroDao();
        VideoDao DAO_V = new VideoDao();
	    ArrayList<Libro> libros = new ArrayList();
	    ArrayList<Video> videos = new ArrayList();
	    
	    for(int i=0;i<materiales.size();i++){
            
            MaterialCapacitacion mc = materiales.get(i);
                if(mc.getClass().isInstance(new Libro())){
                    libros.add((Libro)mc);
                }
                else if(mc.getClass().isInstance(new Video())){
                    videos.add((Video)mc);
                }   
        }
        
        DAO_L.guardarLista(libros);
        DAO_V.guardarLista(videos);

	}
	
	public void cargar(){
	     
        LibroDao DAO_L = new LibroDao();
        VideoDao DAO_V = new VideoDao();
        ArrayList<Libro> libros = (ArrayList<Libro>) DAO_L.cargarLista();
        if(libros.isEmpty()) System.out.println("LIBROS_OK");
        ArrayList<Video> videos = (ArrayList<Video>) DAO_V.cargarLista();
        if(videos.isEmpty()) System.out.println("VIDEOS_OK");
        
        this.materiales.addAll(videos);
        this.materiales.addAll(libros);
    }
	
	
    public ArrayList<MaterialCapacitacion> getListadoTema(MaterialCapacitacion m)
    {
        ArrayList<MaterialCapacitacion> salida = new ArrayList();
        for(int i=0;i<materiales.size();i++) if(materiales.get(i).getTema().equals(m.getTema())) salida.add(materiales.get(i));
        return salida;
    }
    
    public ArrayList<MaterialCapacitacion> generar (String titulo, String tema, Integer califMin, Integer califMax, boolean libros, boolean videos) {
        
        ArrayList<MaterialCapacitacion> material=(ArrayList)this.ordenadaAlfabeticamente();
        ArrayList<MaterialCapacitacion> materialFiltrado = new ArrayList();
        
        MaterialCapacitacion current;
        for(int i=0;i<material.size();i++){
            current=material.get(i);
            
            if((current.getTema().equals(tema) || tema.equals("Todos")) && current.getCalificacion()>=califMin && current.getCalificacion()<=califMax){
                
                if((libros && current.esLibro()) || (videos && current.esVideo())){
                
                    if(titulo.isEmpty()){
                        materialFiltrado.add(current);
                    }
                    if(!titulo.isEmpty() && current.getTitulo().contains(titulo)){
                        materialFiltrado.add(current);
                    }
                }
            }
            
        }
        
        return materialFiltrado;
    }
	
    
    public List<MaterialCapacitacion> ordenadaAlfabeticamente()
    {
        Collections.sort(materiales,ComparadorTitulo.getInstance());
        return materiales;
    }
    
    public boolean containsGrafoTema (String tema){
        
        if(grafosPorTema.containsKey(tema)) return true;
        else return false;
        
    }
    
    public Grafo getGrafoPorTema(String tema){
        
        return grafosPorTema.get(tema);
        
    }
    
    public void setGrafoPorTema(String tema, Grafo grafo){
        
        if(grafosPorTema.containsKey(tema)) grafosPorTema.replace(tema, grafo);
        else grafosPorTema.put(tema, grafo);
        
    }
}
