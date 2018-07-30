/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frsf.isi.died.tp.util;

import frsf.isi.died.tp.modelo.productos.MaterialCapacitacion;
import java.util.Comparator;

/**
 *
 * @author martdominguez
 */
public class ComparadorTitulo implements Comparator<MaterialCapacitacion>{
        public int compare(MaterialCapacitacion o1, MaterialCapacitacion o2) {
            return o1.getTitulo().compareTo(o2.getTitulo());
        };

        private static ComparadorTitulo _instancia = null;
        private ComparadorTitulo(){
            
        }
        
        public static ComparadorTitulo getInstance(){
            if(_instancia==null) _instancia = new ComparadorTitulo();
            return _instancia;
        }
}
