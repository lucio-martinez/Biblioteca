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
import biblioteca.models.Libro;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lucio Martinez <luciomartinez at openmailbox dot org>
 */
public class SocioLibros extends Thread {

    private static final boolean NDEBUG = true;
    Biblioteca biblioteca;
    static final int MAX_ID_LIBRO = 4,
            MS_SLEEP = 2000;
    Random tiempo = new Random(),
            idLibro = new Random();



    public SocioLibros(Biblioteca b){
        biblioteca = b;
    }


    private void log(String message) {
        if (NDEBUG)
            System.out.println(message);
    }



    private Libro retirarLibro() {
        int idLibro = this.idLibro.nextInt(MAX_ID_LIBRO) + 1;
        log("QUIERO EL LIBRO: " + idLibro);
        return biblioteca.prestarLibro(idLibro);
    }

    private void devolverLibro(Libro libro) {
        log("LIBRO A DEVOLVER: " + libro.getIdLibro());
        biblioteca.devolverLibro(libro);
    }


    @Override
    public void run(){
        try {

            sleep(tiempo.nextInt(MS_SLEEP));
            Libro l = retirarLibro();

            sleep(tiempo.nextInt(MS_SLEEP));
            devolverLibro(l);

        } catch (InterruptedException ex) {
            Logger.getLogger(SocioLibros.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
