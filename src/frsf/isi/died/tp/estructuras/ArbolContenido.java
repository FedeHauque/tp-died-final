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
		String nombre;
		if(this.tipo.equals(TipoNodo.MEC)) {
			nombre = "MEC (METADATOS DEL CAPITULO)";
		}else if(this.tipo.equals(TipoNodo.MEM)) {
			nombre = "MEM (METADATOS DEL MATERIAL)";
		}else {
			nombre = this.tipo.name();
		}
			dette += "Tipo: " + nombre + " - " + this.contenido;
		ret.add(dette);
		for(ArbolContenido a : hijos) {
			ret.addAll(a.listarArbol(prof+1));
		}
		return ret;
	}
	
	public boolean hay(TipoNodo tipo) {
		boolean valor = false;
		if(this.tipo.equals(tipo)) {
			return true;
		}else {
			for(ArbolContenido a: hijos) {
				if(a.hay(tipo)) {
					valor = true;
				}
			}
		}
		return valor;
	}
	
	public boolean equals(Arbol unArbol) {
		// TODO Auto-generated method stub
		return false;
	}

}