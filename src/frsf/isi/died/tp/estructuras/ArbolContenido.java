package frsf.isi.died.tp.estructuras;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import frsf.isi.died.tp.modelo.productos.MaterialCapacitacion;

public class ArbolContenido {

	private String contenido;
	private TipoNodo tipo;
	private ArrayList<ArbolContenido> hijos;

	public boolean esVacio() {
		return false;
	}

	public ArbolContenido(String titulo) {
		this.hijos = new ArrayList<ArbolContenido>();
		this.contenido = titulo;
		this.tipo = TipoNodo.TITULO;
	}
	public ArbolContenido(String titulo, TipoNodo tipo) {
		this.hijos = new ArrayList<ArbolContenido>();
		this.contenido = titulo;
		this.tipo = tipo;
	}
	
	public void add(String contenido, TipoNodo tipo, TipoNodo padre, String contenidoPadre) {
		if(this.tipo.equals(padre) && this.contenido.equals(contenidoPadre)) {
			hijos.add(new ArbolContenido(contenido, tipo));
		}else {
			for(ArbolContenido a : hijos) {
				a.add(contenido, tipo, padre, contenidoPadre);
			}
		}
	}


	
	public boolean equals(Arbol unArbol) {
		// TODO Auto-generated method stub
		return false;
	}

}