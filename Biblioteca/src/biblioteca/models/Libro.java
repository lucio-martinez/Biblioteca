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


package biblioteca.models;

/**
 *
 * @author Lucio Martinez <luciomartinez at openmailbox dot org>
 */
public class Libro {

    private int idLibro;
    private String Titulo;
    // Variable usada para implementar conteo de IDs automaticamente
    static private int idCounter = 0;


    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String Titulo) {
        this.Titulo = Titulo;
    }



    public Libro() {
        // First ID will start from 1
        idLibro = ++idCounter;
    }

    public Libro(String titulo) {
        this();
        Titulo = titulo;
    }



    @Override
    public String toString() {
        return "Libro: { ID: " + idLibro + "; Titulo: " + Titulo + "; }";
    }

}
