package bookrecommender;

import java.util.ArrayList;
import java.util.List;

/**
 * PROGETTO REALIZZATO DA:
 *
 *  * MOUHAMMAD TOURE              -Matricola:     758051      -Sede: VA
 *  * Daniel Viny Kamdem Tagne     -Matricola:     759563      -Sede: VA
 *  * Agnes Balkaire Makouwe       -Matricola:     759700      -Sede: VA
 *  * Maercel Precieux Moukoko     -Matricola:     759674      -Sede: VA
 */

/**
 * La classe Libreria rappresenta una libreria virtuale creata da un utente logato, contenente una lista di libri.
 * Ogni libreria è associata ad un utente specifico e può contenere un numero illimitato di titoli di libri.
 *
 * @author MOUHAMMAD TOURE
 */

public class Libreria {

    private String userID;
    private String nomeLibreria;
    private List<String> libri;
    
    public Libreria(String user, String nomeLib){

        userID = user;
        nomeLibreria = nomeLib;
        libri = new ArrayList<>();

    }

    public String getUserID(){
        return userID;
    }

    public void setUserID(String user){
        userID = user;
    }

    public String getNomeLibreria(){
        return nomeLibreria;
    }
    
    public void setNomeLibreria(String nomeLib){
        nomeLibreria = nomeLib;
    }
    
    public void aggiungiLibri(String titoloLib){
        libri.add(titoloLib);
    }

    public String getLibriAsString() {
        return String.join(",", libri);  // Unisce tutti i libri con una virgola
    }
    
    public String toString(){
        return getUserID()+","+ getNomeLibreria()+","+ String.join(",", getLibriAsString());
    }
    
}
