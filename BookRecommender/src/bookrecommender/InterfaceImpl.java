package bookrecommender;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

public class InterfaceImpl extends UnicastRemoteObject implements InterfaceBook {

    private static final long serialVersionUID = 1L;
    private BookRecommender bookRecommender;
   
    /**
     * Costruttore della classe che estende UnicastRemoteObject.
     * 
     * @throws java.rmi.RemoteException
     */

    public InterfaceImpl() throws RemoteException {
        super();
        bookRecommender = new BookRecommender();
    }

    @Override
    public String visualizzaLibri() throws RemoteException {
        return bookRecommender.visualizzareLibri();
    }

    @Override
    public String cercaLibroConTitolo(String titolo) throws RemoteException {
        return bookRecommender.cercaLibroConTitolo(titolo);
    }

    @Override
    public String cercaLibroConAutore(String autore) throws RemoteException {
        return bookRecommender.cercaLibroConAutore(autore);
    }

    @Override
    public String cercaLibroConAutoreAnno(String autor, int anno) throws RemoteException {
        return bookRecommender.cercaLibroConAutoreAnno(autor, anno);
    }

    @Override
    public String registrazione(String sessionId, String name, String cf, String email, String userid, String password) throws RemoteException {
        return bookRecommender.registrazione(sessionId, name, cf, email, userid, password);
    }

    @Override
    public String login(String sessionId, String userid, String password) throws RemoteException {
        return bookRecommender.login(sessionId, userid, password);
    }

    @Override
    public String creaLibreria(String sessionId, String nomeLibreria) throws RemoteException {
        return bookRecommender.creareLibreria(sessionId, nomeLibreria);
    }

    @Override
    public String inserisciValutazioneLibro(String sessionId, String title, String style, String content, String pleasantness, String originality, String edition) throws RemoteException {
        return bookRecommender.inserisciValutazioneLibro(sessionId, title, style, content, pleasantness, originality, edition);
    }

    @Override
    public String inserisciConsiglioLibro(String sessionId, String titoloLibro, String consigliati) throws RemoteException {
        return bookRecommender.inserisciConsiglioLibro(sessionId, titoloLibro, consigliati);
    }

    @Override
    public String aggiungiLibroLibreria(String sessionId, String nomeLibreria, String titoloLibro) throws RemoteException{
        return bookRecommender.aggiungiLibroLibreria(sessionId, nomeLibreria, titoloLibro);
    }

    @Override
    public String rimuoviLibroLibreria(String sessionId, String nomeLibreria, String titoloLibro) throws RemoteException{
        return bookRecommender.rimuoviLibroLibreria(sessionId, nomeLibreria, titoloLibro);
    }

    @Override
    public String visualizzaLibreria(String sessionId, String nomeLibreria) throws RemoteException{
        return bookRecommender.visualizzaLibreria(sessionId, nomeLibreria);
    }

    @Override
    public String tornaMenuPrincipale() throws RemoteException{
        return bookRecommender.tornaMenuPrincipale();
    }
    

    @Override
    public String logout(String sessionId) throws RemoteException {
        return bookRecommender.logout(sessionId);
    }

    @Override
    public String esci() throws RemoteException {
        return "OK: Uscita dal sistema...";
    }

    @Override
    public String recuperaPassword(String username) throws RemoteException {
        return bookRecommender.recuperaPassword(username);
    }

    @Override
    public String generaPasswordTemporanea(String username) throws RemoteException {
        return bookRecommender.generaPasswordTemporanea(username);
    }

    @Override
    public String cambiaPassword(String sessionId, String username, String oldPassword, String newPassword) throws RemoteException {
        return bookRecommender.cambiaPassword(sessionId, username, oldPassword, newPassword);
    }

}
