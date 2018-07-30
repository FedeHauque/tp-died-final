package frsf.isi.died.app.dao;

import frsf.isi.died.tp.modelo.productos.Libro;
import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.reflect.TypeToken;

/**
 *
 * @author haucherecords
 */
public class LibroDao {
    private PersistenciaDao data;
    
    public LibroDao(){
    	data = new PersistenciaDao("Libros.json");
    }
    
    public void guardarLista(List<Libro> lista){
    	data.guardar(lista);
    }

    
    public List<Libro> cargarLista(){
    	Type deita = new TypeToken<List<Libro>>() {}.getType();
        if(new File("Libros.json").exists()) {
        	return data.cargar(deita);
        }
        else {
            return new ArrayList();
        }
        
        
        
    }
}
