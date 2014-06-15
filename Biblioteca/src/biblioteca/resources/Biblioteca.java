/*
 * Copyright (C) 2014 Lucio Martinez <luciomartinez at openmailbox dot org>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */


package biblioteca.resources;

import biblioteca.models.Libro;
import biblioteca.models.Revista;
import java.util.Enumeration;
import java.util.concurrent.ConcurrentHashMap;
//import java.util.ConcurrentHashMap;

/**
 *
 * @author Lucio Martinez <luciomartinez at openmailbox dot org>
 */
public class Biblioteca {
    // Colecciones
    private ConcurrentHashMap<Integer, Libro> libros = new ConcurrentHashMap();
    private ConcurrentHashMap<Integer, Revista> revistas = new ConcurrentHashMap();
    // Cantidad maxima de elementos almacenables en la biblioteca
    private final static int MAX_STOCK_LIBROS = 4;
    private final static int MAX_STOCK_REVISTAS = 4;
    // Bloques para sincronización granularizada
    private final Object bloquearLibros = new Object();
    private final Object bloquearRevistas = new Object();

    private static final boolean NDEBUG = true;


    private void log(String message) {
        if (NDEBUG)
            System.out.println(message);
    }

    // Imprime en salida los objetos utilizando su método toString
    private void imprimir(Enumeration e) {
        while(e.hasMoreElements()) {
            System.out.println(e.nextElement());
        }
    }



    // Cantidad de libros actualmente disponibles en la biblioteca
    private int stockLibros() {
        return libros.size();
    }

    // Cantidad de revistas actualmente disponibles en la biblioteca
    private int stockRevistas() {
        return revistas.size();
    }




    // ************ //
    // ** LIBROS ** //
    // ************ //

    public Libro prestarLibro(int id) {
        synchronized(bloquearLibros) {

            log("Entro gente a la biblioteca de Libros...");

            while(stockLibros() == 0 || libros.get(id) == null){
                try{
                    log("Pero el Libro deseado no esta...");
                    bloquearLibros.wait();
                }
                catch ( InterruptedException e){}
            }

            Libro libro = libros.remove(id);

            log("El socio se llevo su Libro... " + id);

            bloquearLibros.notifyAll();
            return libro;
        } // END synchronized
    }

    public void devolverLibro(Libro l) {
        synchronized(bloquearLibros) {
            while(stockLibros() == MAX_STOCK_LIBROS) {
                try {
                    log("No se puede devolver el Libro por falta de espacio...");
                    bloquearLibros.wait();
                }
                catch ( InterruptedException e){}
            }

            libros.put(l.getIdLibro(), l);

            log("Ingresó el Libro: " + l.getTitulo());

            bloquearLibros.notifyAll();
        }
    }

    public void donacionRecibida(Libro l) {
        synchronized(bloquearLibros) {
            while (stockLibros() == MAX_STOCK_LIBROS) {
                try{
                    log("La estanteria de Libros esta llena para recibir donaciones ¬¬");
                    bloquearLibros.wait();
                }
                catch ( InterruptedException e){}
            }
            log("DONACION RECIBIDA!!! \\o/");
            devolverLibro(l);
        }
    }


    void mostrarLibros() {
        imprimir(libros.elements());
    }









    // ************* //
    // ** REVISTAS **//
    // ************* //

    public Revista prestarRevista(int id) {
        synchronized(bloquearRevistas) {

            log("Entro gente a la biblioteca de Revistas...");

            while(stockRevistas() == 0) {
                try{
                    log("Pero no hay revistas en la estanteria...");
                    bloquearRevistas.wait();
                }
                catch ( InterruptedException e){}
            }

            while(revistas.get(id) == null){
                try{
                    log("Pero la Revista no esta...");
                    bloquearRevistas.wait();
                }
                catch ( InterruptedException e){}
            }

            log("Encontro su Revista...");

            Revista revista = revistas.remove(id);
            log("Sacada la Revista..." + id);
            bloquearRevistas.notifyAll();

            return revista;
        } // END synchronized
    }

    public void devolverRevista(Revista r) {
        synchronized(bloquearRevistas) {
            while(stockRevistas() == MAX_STOCK_REVISTAS) {
                try {
                    log("No se puede devolver la Revista...");
                    bloquearRevistas.wait();
                }
                catch ( InterruptedException e){}
            }
            log("Insertada la Revista: " + r.getTitulo());
            revistas.put(r.getIdRevista(), r);
            bloquearRevistas.notifyAll();
        }
    }

    public void donacionRecibida(Revista r) {
        synchronized(bloquearRevistas) {
            while (stockRevistas() == MAX_STOCK_REVISTAS) {
                try{
                    log("Pero la estanteria de Revistas esta llena (nos estan cachando ¬¬)...");
                    bloquearRevistas.wait();
                }
                catch ( InterruptedException e){}
            }
            log("DONACION RECIBIDA!!! \\o/");
            devolverRevista(r);
        }
    }


    public void mostrarRevistas() {
        imprimir(revistas.elements());
    }
}

