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


package biblioteca.threads;

import biblioteca.resources.Biblioteca;
import biblioteca.models.Revista;
import biblioteca.models.Libro;

/**
 *
 * @author Lucio Martinez <luciomartinez at openmailbox dot org>
 */
public class Donador extends Thread {

    private static final boolean NDEBUG = true;
    Biblioteca biblioteca;


    public Donador(Biblioteca b) {
        biblioteca = b;
    }


    private void log(String message) {
        if (NDEBUG)
            System.out.println(message);
    }


    void donarLibro(Libro l, Biblioteca b) {
        log("QUIERO DONAR LIBRO!");
        b.donacionRecibida(l);
    }

    void donarRevista(Revista r, Biblioteca b) {
        log("QUIERO DONAR REVISTA!");
        b.donacionRecibida(r);
    }


    @Override
    public void run() {

        donarLibro(new Libro("El fantasma de la UTN"), biblioteca);
        donarLibro(new Libro("Metele TXT"), biblioteca);
        donarLibro(new Libro("Entrega rapida"), biblioteca);
        donarLibro(new Libro("Converti el TXT"), biblioteca);

        donarRevista(new Revista("El fantasma de la UTN - THE REVIEW"), biblioteca);
        donarRevista(new Revista("Metele TXT - THE REVIEW"), biblioteca);
        donarRevista(new Revista("Entrega rapida - THE REVIEW"), biblioteca);
        donarRevista(new Revista("Converti el TXT - THE REVIEW"), biblioteca);

    }

}
