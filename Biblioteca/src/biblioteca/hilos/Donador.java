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


package biblioteca.hilos;

import biblioteca.recursos.Biblioteca;
import biblioteca.modelos.Revista;
import biblioteca.modelos.Libro;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lucio Martinez <luciomartinez at openmailbox dot org>
 */
public class Donador implements Runnable {

    private static final boolean NDEBUG = true;
    Biblioteca biblioteca;


    public Donador(Biblioteca b) {
        biblioteca = b;
    }


    private void log(String message) {
        if (NDEBUG)
            System.out.println(message);
    }


    void donarLibro(Libro l, Biblioteca b) throws InterruptedException {
        log("QUIERO DONAR LIBRO!");
        b.donacionRecibida(l);
    }

    void donarRevista(Revista r, Biblioteca b) throws InterruptedException {
        log("QUIERO DONAR REVISTA!");
        b.donacionRecibida(r);
    }


    @Override
    public void run() {

        try {
            // Please, do not sue me to mention your book. I'm helping you!

            donarLibro(new Libro("The Call of the Wild"), biblioteca);
            donarLibro(new Libro("Immensee"), biblioteca);
            donarLibro(new Libro("Lettres de mon moulin"), biblioteca);
            donarLibro(new Libro("The Trojan Women"), biblioteca);

            donarRevista(new Revista("Memories and portraits - THE REVIEW"), biblioteca);
            donarRevista(new Revista("The Complete Novels of Jane Austen - THE REVIEW"), biblioteca);
            donarRevista(new Revista("The Scholemaster - THE REVIEW"), biblioteca);
            donarRevista(new Revista("Prayers and Meditations - THE REVIEW"), biblioteca);

        } catch (InterruptedException ex) {
            Logger.getLogger(Donador.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
