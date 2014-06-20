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

package biblioteca;

import biblioteca.recursos.Biblioteca;
import biblioteca.hilos.Donador;
import biblioteca.hilos.SocioLibros;
import biblioteca.hilos.SocioRevistas;

/**
 * <p>Aplicación que genera una biblioteca virtual
 * donde hay libros y revistas, donadores y
 * socios de libros y de revistas.</p>
 *
 * <p>La acción se genera en un entorno concurrente
 * por lo que hace más divertido ver socios esperando,
 * biblioteca con estantería llena o cosas por el estilo.</p>
 *
 * <p>Aún así no es un juego, por lo que no te tenés que divertir!
 * Mentira, sino te divertis no sirve de nada :)</p>
 *
 * @author Lucio Martinez <luciomartinez at openmailbox dot org>
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("Iniciando..");
        Biblioteca biblioteca = new Biblioteca();

        /*
            Si creo un nuevo libro, y no lo agrego a la
            biblioteca, habra errores. Ya que pueden solicitar
            un ID que existe pero no se encuentra en la biblioteca,
            por lo que el socio esperara eternamente su devolucion.
        */
        //Libro a = new Libro("L1");
        //Libro a1 = new Libro("L2");
        //Libro a2 = new Libro("L3");
        //Libro a3 = new Libro("L4");
        //Libro a4 = new Libro("L5");

        //libros.devolverLibro(a);
        //libros.devolverLibro(a1);
        // libros.devolverLibro(a2);
        // libros.devolverLibro(a3);
        // libros.devolverLibro(a4);

        new Thread(new SocioLibros(biblioteca)).start();
        new Thread(new SocioLibros(biblioteca)).start();

        new Thread(new SocioRevistas(biblioteca)).start();
        new Thread(new SocioRevistas(biblioteca)).start();

        new Thread(new Donador(biblioteca)).start();
    }

}
