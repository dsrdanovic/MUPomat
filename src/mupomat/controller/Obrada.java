/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mupomat.controller;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author SrKy
 */
public abstract class Obrada<T> {
    
    public abstract T dodajNovi(T entitet);
    public abstract void promjeniPostojeci(T entitet);
    public abstract void obrisiPostojeci(T entitet) throws SQLException;
    public abstract List<T> dohvatiIzBaze(String uvjet);
    
}
