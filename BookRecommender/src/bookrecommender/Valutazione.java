package bookrecommender;

/**
 *  PROGETTO REALIZZATO DA:
 *
 *  * MOUHAMMAD TOURE              -Matricola:     758051      -Sede: VA
 *  * Daniel Viny Kamdem Tagne     -Matricola:     759563      -Sede: VA
 *  * Agnes Balkaire Makouwe       -Matricola:     759700      -Sede: VA
 *  * Maercel Precieux Moukoko     -Matricola:     759674      -Sede: VA
 */

/**
 * La classe Valutazione rappresenta una valutazione di un libro da parte di un utente.
 * Ogni valutazione include punteggi sui vari aspetti del libro e un voto finale calcolato come media di questi punteggi.
 *
 * @author MOUHAMMAD TOURE
 */

public class Valutazione {

    private String userID;
    private String titoloLibro;
    private int stile;
    private int contenuto;
    private int gradevolezza;
    private int originalita;
    private int edizione;
    private double votoFinale;
    
    public Valutazione(String user, String titolo, int stil, int cont, int grad, int orig, int ediz){

        userID = user;
        titoloLibro = titolo;
        stile = stil;
        contenuto = cont;
        gradevolezza = grad;
        originalita = orig;
        edizione = ediz;
        votoFinale = calcoloVotoFinale();

    }
    
    /**
     * Me
     */
    private double calcoloVotoFinale(){
        return (stile + contenuto + gradevolezza + originalita + edizione)/5;
    }
    
    public String getUserID(){
        return userID;
    }

    public void setUserID(String user){
        userID = user;
    }

    public String getTitoloLibro(){
        return titoloLibro;
    }

    public void setTitoloLibro(String titolo){
        titoloLibro = titolo;
    }

    public int getStile(){
        return stile;
    }

    public void setStile(int stil){
        stile = stil;
    }

    public int getContenuto(){
        return contenuto;
    }

    public void setContenuto(int cont){
        contenuto = cont;
    }

    public int getGradevolezza(){
        return gradevolezza;
    }

    public void setGradevolezza(int grad){
        gradevolezza = grad;
    }

    public int getOriginalita(){
        return originalita;
    }

    public void setOriginalita(int orig){
        originalita = orig;
    }

    public int getEdizione(){
        return edizione;
    }

    public void setEdizione(int ediz){
        edizione = ediz;
    }

    public double getVotoFinale(){
        return votoFinale;
    }

    public void setVotoFinale(double votoFin){
        votoFinale = votoFin;
    }

    public String toString(){
        return getUserID()+","+ getTitoloLibro()+","+ getStile()+","+ getContenuto()+","+
        getGradevolezza()+","+ getOriginalita()+","+ getEdizione()+","+ getVotoFinale();
    }
    
}