package frsf.isi.died.app.dao;

import frsf.isi.died.tp.modelo.productos.Video;
import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.reflect.TypeToken;

/**
 *
 * @author haucherecords
 */
public class VideoDao {
     private PersistenciaDao data;
    
    public VideoDao(){
    	data = new PersistenciaDao("Videos.json");
    }
    
    public void guardarLista(List<Video> lista){
    	data.guardar(lista);
    }

    public List<Video> cargarLista(){
    	Type deita = new TypeToken<List<Video>>() {}.getType();
        if(new File("Videos.json").exists()) {
        	return data.cargar(deita);
        }
        else {
            return new ArrayList();
        }
    }    
}
