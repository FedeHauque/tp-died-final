/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frsf.isi.died.tp.modelo.productos;

import java.util.Date;

/**
 *
 * @author mdominguez
 */
public class Video extends MaterialCapacitacion {

    private Integer duracionEnSegundos;
    private static final Double _VALOR_SEGUNDO = 0.15;

    public Video() {
        super();
    }

    public Video(Integer id,String titulo){
        super(id,titulo);
        this.duracionEnSegundos=0;
    }
    
    public Video(Integer id,String titulo, Double costo, Integer calificacion, Date fechaPublicacion, Relevancia relevancia, String tema) {
    	super(id, titulo, costo, calificacion, fechaPublicacion, relevancia, tema);
        this.duracionEnSegundos = 0;
    }

    public Video(Integer id,String titulo, Double costo, Integer duracion, Integer calificacion, Date fechaPublicacion, Relevancia relevancia, String tema) {
    	super(id, titulo, costo, calificacion, fechaPublicacion, relevancia, tema);
        this.duracionEnSegundos = duracion;
    }

    @Override
    public Double precio() {
        return costo + (duracionEnSegundos * _VALOR_SEGUNDO);
    }

    public Integer getDuracionEnSegundos() {
        return duracionEnSegundos;
    }

    public void setDuracionEnSegundos(Integer duracionEnSegundos) {
        this.duracionEnSegundos = duracionEnSegundos;
    }

	@Override
	public Boolean esLibro() {
		return false;
	}

	@Override
	public Boolean esVideo() {
		return true;
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Video && super.equals(obj) ;
	}

}