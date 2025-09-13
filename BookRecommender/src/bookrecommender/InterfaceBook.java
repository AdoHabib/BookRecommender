package bookrecommender;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interfaccia RMI per il sistema BookRecommender.
 * Definisce i metodi che possono essere invocati dai client per interagire con il server.
 * Include funzionalità per visualizzare e cercare libri, gestire librerie, inserire valutazioni e consigli, e gestire account utente.
 * @author Mouhammad Toure
 */
public interface InterfaceBook extends Remote {

  public String visualizzaLibri() throws RemoteException;
  public String cercaLibroConTitolo(String titolo) throws RemoteException;
  public String cercaLibroConAutore(String autore) throws RemoteException;
  public String cercaLibroConAutoreAnno(String autore, int anno) throws RemoteException;
  public String registrazione(String sessionId, String name, String cf, String email, String userid, String password) throws RemoteException;
  public String login(String sessionId, String userid, String password) throws RemoteException;
  public String creaLibreria(String sessionId, String nomeLibreria) throws RemoteException;
  public String inserisciValutazioneLibro(String sessionId, String title, String style, String content, String pleasantness, String originality, String edition) throws RemoteException;
  public String inserisciConsiglioLibro(String sessionId, String titoloLibro, String consigliati) throws RemoteException;
  public String logout(String sessionId) throws RemoteException;
  public String esci() throws RemoteException;
  public String aggiungiLibroLibreria(String sessionId, String nomeLibreria, String titoloLibro) throws RemoteException;
  public String rimuoviLibroLibreria(String sessionId, String nomeLibreria, String titoloLibro) throws RemoteException;
  public String visualizzaLibreria(String sessionId, String nomeLibreria) throws RemoteException;
  public String tornaMenuPrincipale() throws RemoteException;
  public String recuperaPassword(String username) throws RemoteException;
  public String generaPasswordTemporanea(String username) throws RemoteException;
  public String cambiaPassword(String sessionId, String username, String oldPassword, String newPassword) throws RemoteException;
 
}
