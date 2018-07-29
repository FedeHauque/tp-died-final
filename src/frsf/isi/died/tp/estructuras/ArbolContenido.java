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
	
	public void add(String contenido, TipoNodo tipo, TipoNodo padre) {
		if(this.tipo.equals(padre)) {
			hijos.add(new ArbolContenido(contenido, tipo));
			System.out.println(contenido + " " + tipo.name());
		}else {
			for(ArbolContenido a : hijos) {
				a.add(contenido, tipo, padre);
			}
		}
	}
	
	public ArrayList<String> listarArbol(int prof){
		ArrayList<String> ret = new ArrayList<String>();
		String dette = "";
		for(int i=0; i<prof; i++) {
			dette += "   ";
		}
			dette += "Tipo: " + this.tipo.name() + " - " + this.contenido;
		ret.add(dette);
		for(ArbolContenido a : hijos) {
			ret.addAll(a.listarArbol(prof+1));
		}
		return ret;
	}
	
	public boolean equals(Arbol unArbol) {
		// TODO Auto-generated method stub
		return false;
	}

}