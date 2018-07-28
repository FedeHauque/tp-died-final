package frsf.isi.died.tp.modelo.productos;

import java.util.Comparator;
import java.util.PriorityQueue;

public class WishList extends PriorityQueue<MaterialCapacitacion> {
	
	private static WishList instance = null;
	
	public WishList() {
		super(11, new MonticuloComparator());
	}
	
	public static WishList getInstance() {
		if(instance == null) {
			instance = new WishList();
		}
		return instance;
	}
	
	static class MonticuloComparator implements Comparator<MaterialCapacitacion>{

		public int compare(MaterialCapacitacion m1, MaterialCapacitacion m2) {
			double retornador = 0;
			if (m1.getRelevancia().equals(m2.getRelevancia())) {
				//Primer criterio dio igual
				if(m1.getCalificacion().equals(m2.getCalificacion())) {
					//Segundo criterio dio igual
					if(m1.precio().equals(m2.precio())) {
						retornador = 0;
					}else{
						retornador = (m1.precio()-m2.precio());
					}
				}else{
					retornador = m1.getCalificacion()-m2.getCalificacion();
				}
			}else {
				if(m1.getRelevancia().equals(Relevancia.BAJA)) {
					retornador = -1;
				}else if(m1.getRelevancia().equals(Relevancia.ALTA)){
					retornador = 1;
				}else if(m2.getRelevancia().equals(Relevancia.ALTA)){
					retornador = -1;
				}else if(m2.getRelevancia().equals(Relevancia.BAJA)){
					retornador = 1;
				}
			}
			if (retornador > 0 && ((int)retornador) == 0)  
				return 1;
			else if (retornador < 0 && ((int)retornador) == 0) 
				return -1;
			else return ((int) retornador);
		}
		
	}
	
}

