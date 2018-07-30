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
	
	public boolean contieneTodo(String titulo, String autor, String editorial, String fecha, String pc, String parrafo, String capitulo, String web, String sec) {
		boolean[] arr = new boolean[9];
		for(int i=0; i<arr.length ; i++) {
			arr[i] = false;
		}
		if(titulo.equals("")) arr[0] = true;
		if(autor.equals("")) arr[1] = true;
		if(editorial.equals("")) arr[2] = true;
		if(fecha.equals("")) arr[3] = true;
		if(pc.equals("")) arr[4] = true;
		if(parrafo.equals("")) arr[5] = true;
		if(capitulo.equals("")) arr[6] = true;
		if(web.equals("")) arr[7] = true;
		if(sec.equals("")) arr[8] = true;
		this.contieneTodo_2(titulo, autor, editorial, fecha, pc, parrafo, capitulo, web, sec, arr);
		boolean ret = false;
		int i = 0;
		while(i < 9 && arr[i]) {
			i++;
		}
		System.out.println(i);
		if(i==9) {
			ret = true;
		}
		return ret;
	}
	
	
	private void contieneTodo_2(String titulo, String autor, String editorial, String fecha, String pc, String parrafo, String capitulo, String web, String sec, boolean[] arr) {
		
		switch (this.tipo) {
			case TITULO: {
				if(!arr[0] && titulo.contains(this.contenido)) {
						arr[0] = true;
					}
					break;
				}
			case AUTOR: {
				if(!arr[1] && autor.contains(this.contenido)) {
					arr[1] = true;
				}
				break;
			}
			case EDITORIAL: {
				if(!arr[2] && editorial.contains(this.contenido)) {
					arr[2] = true;
				}
				break;
			}
			case FECHA_PUBLICACION: {
				if(!arr[3] && fecha.contains(this.contenido)) {
					arr[3] = true;
				}
				break;
			}
			case PALABRA_CLAVE: {
				if(!arr[4] && pc.contains(this.contenido)) {
					arr[4] = true;
				}
				break;
			}
			case PARRAFO: {
				if(!arr[5] && parrafo.contains(this.contenido)) {
					arr[5] = true;
				}
				break;
			}
			case CAPITULO: {
				if(!arr[6] && capitulo.contains(this.contenido)) {
					arr[6] = true;
				}
				break;
			}
			case SITIO: {
				if(!arr[7] && web.contains(this.contenido)) {
					arr[7] = true;
				}
				break;
			}
			case SECCION:{
				if(!arr[8] && sec.contains(this.contenido)) {
					arr[8] = true;
				}
				break;
			}
		}
		for(ArbolContenido a : this.hijos) {
			a.contieneTodo_2(titulo, autor, editorial, fecha, pc, parrafo, capitulo, web, sec, arr);
		}
	}

	public boolean equals(Arbol unArbol) {
		// TODO Auto-generated method stub
		return false;
	}

}