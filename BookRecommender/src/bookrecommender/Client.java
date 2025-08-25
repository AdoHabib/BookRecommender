package bookrecommender;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import java.util.UUID;

public class Client {

    private static final int PORT = 1099;
    private String sessionId;

    public Client() {
        // Genera un sessionId univoco per questo client
        this.sessionId = UUID.randomUUID().toString();
    }

    public void start() {

        try {
            Scanner scanner = new Scanner(System.in);
            Registry registry = LocateRegistry.getRegistry("localhost", PORT);
            InterfaceBook interfaceBook = (InterfaceBook) registry.lookup("BookRecommender");
            System.out.println("Connessione al server effettuata con successo");
            System.out.println("Session ID: " + sessionId);
            
            while (true) {
                System.out.println("Menu:");
                System.out.println("1. Visualizza libri");
                System.out.println("2. Cerca libro con titolo");
                System.out.println("3. Cerca libro con autore");
                System.out.println("4. Cerca libro con autore e anno");
                System.out.println("5. Registrazione");
                System.out.println("6. Login");
                System.out.println("7. Gestione libreria");
                System.out.println("8. Inserisci valutazione libro");
                System.out.println("9. Inserisci consiglio libro");
                System.out.println("10. Logout");
                System.out.println("11. Recupera password");
                System.out.println("0. Esci");

                int scelta = leggiIntero(scanner.nextLine());
                switch (scelta) {
                    case 1:
                        System.out.println("Visualizza libri");
                        System.out.println(interfaceBook.visualizzaLibri());
                        break;
                    case 2:
                        System.out.println("Cerca libro con titolo");
                        System.out.println("Inserisci titolo: ");
                        String titolo = scanner.nextLine();
                        System.out.println(interfaceBook.cercaLibroConTitolo(titolo));
                        break;
                    case 3:
                        System.out.println("Cerca libro con autore");
                        System.out.println("Inserisci autore: ");
                        String autore = scanner.nextLine();
                        System.out.println(interfaceBook.cercaLibroConAutore(autore));
                        break;
                    case 4:
                        System.out.println("Cerca libro con autore e anno");
                        System.out.println("Inserisci autore: ");
                        String autore2 = scanner.nextLine();
                        System.out.println("Inserisci anno: ");
                        int anno = leggiIntero(scanner.nextLine());
                        System.out.println(interfaceBook.cercaLibroConAutoreAnno(autore2, anno));
                        break;
                    case 5:
                        System.out.println("Registrazione");
                        System.out.println("Inserisci nome e cognome: ");
                        String nomeCognome = scanner.nextLine();
                        System.out.println("Inserisci codice fiscale: ");
                        String cf = scanner.nextLine();
                        System.out.println("Inserisci email: ");
                        String email = scanner.nextLine();
                        System.out.println("Inserisci username: ");
                        String username = scanner.nextLine();
                        System.out.println("Inserisci password: ");
                        String password = scanner.nextLine();
                        System.out.println(interfaceBook.registrazione(sessionId, nomeCognome, cf, email, username, password));
                        break;
                    case 6:
                        System.out.println("Login");
                        System.out.println("Inserisci username: ");
                        String username2 = scanner.nextLine();
                        System.out.println("Inserisci password: ");
                        String password2 = scanner.nextLine();
                        System.out.println(interfaceBook.login(sessionId, username2, password2));
                        break;
                    case 7:                                            
                        while(true){                                        
                        System.out.println("""
                        Scegli un'opzione:
                        1 → Crea libreria
                        2 → Aggiungi un libro alla libreria
                        3 → Rimuovi un libro dalla libreria
                        4 → Visualizza la libreria
                        5 → Torna al menu principale
                            0 → Esci""");
                        int scelta0 = Integer.parseInt(scanner.nextLine());
                        switch (scelta0) {
                            case 1:
                                System.out.println("Crea libreria");
                                System.out.println("Inserisci nome libreria: ");
                                String nomeLibreria = scanner.nextLine();
                                    System.out.println(interfaceBook.creaLibreria(sessionId, nomeLibreria));
                                break;
                            case 2:
                                System.out.println("Inserisci nome libreria: ");
                                String nomeLibreria2 = scanner.nextLine();
                                System.out.println("Inserisci il titolo del libro da aggiungere: ");
                                String titoloLibro = scanner.nextLine();
                                    System.out.println(interfaceBook.aggiungiLibroLibreria(sessionId, nomeLibreria2, titoloLibro));
                                break;
                            case 3:
                                    System.out.println("Inserisci nome libreria: ");
                                String nomeLibreria3 = scanner.nextLine();
                                System.out.println("Inserisci il titolo del libro da rimuovere: ");
                                    String titoloLibro2 = scanner.nextLine();
                                    System.out.println(interfaceBook.rimuoviLibroLibreria(sessionId, nomeLibreria3, titoloLibro2));
                                break;
                            case 4:
                                System.out.println("Inserisci nome libreria: ");
                                String nomeLibreria4 = scanner.nextLine();
                                    System.out.println(interfaceBook.visualizzaLibreria(sessionId, nomeLibreria4));
                                break;
                            case 5:
                                    System.out.println("Torno al menu principale");
                                break;
                                case 0:
                                    System.out.println("Uscita effettuata con successo.");
                                    System.exit(0);
                                default:
                                    System.out.println("Scelta non valida. Riprova.");
                                }
                            if (scelta0 == 5) break;
                        }
                        break;
                    case 8:
                        System.out.println("Inserisci valutazione libro");
                        System.out.println("Inserisci titolo libro: ");
                        String titoloLibro3 = scanner.nextLine();
                        System.out.println("Inserisci valutazione stile (1-5): ");
                        String stile = scanner.nextLine();
                        System.out.println("Inserisci valutazione contenuto (1-5): ");
                        String contenuto = scanner.nextLine();
                        System.out.println("Inserisci valutazione gradevolezza (1-5): ");
                        String gradevolezza = scanner.nextLine();
                        System.out.println("Inserisci valutazione originalità (1-5): ");
                        String originalita = scanner.nextLine();
                        System.out.println("Inserisci valutazione edizione (1-5): ");
                        String edizione = scanner.nextLine();
                        System.out.println(interfaceBook.inserisciValutazioneLibro(sessionId, titoloLibro3, stile, contenuto, gradevolezza, originalita, edizione));
                        break;
                    case 9:
                        System.out.println("Inserisci consiglio libro");
                        System.out.println("Inserisci titolo libro referenziale: ");
                        String titoloLibro4 = scanner.nextLine();
                        System.out.println("Inserisci libri consigliati (separati da virgola): ");
                        String consigliati = scanner.nextLine();
                        System.out.println(interfaceBook.inserisciConsiglioLibro(sessionId, titoloLibro4, consigliati));
                        break;
                    case 10:
                        System.out.println("Logout");
                        System.out.println(interfaceBook.logout(sessionId));
                        break;
                    case 11:
                        System.out.println("=== RECUPERO PASSWORD ===");
                        System.out.println("Inserisci username: ");
                        String username3 = scanner.nextLine();
                        
                        // Mostra le informazioni dell'utente
                        String userInfo = interfaceBook.recuperaPassword(username3);
                        System.out.println(userInfo);
                        
                        // Se l'utente è stato trovato, mostra le opzioni
                        if (userInfo.contains("✅ UTENTE TROVATO")) {
                            System.out.println("\n📋 OPZIONI DISPONIBILI:");
                            System.out.println("1. Genera nuova password temporanea");
                            System.out.println("2. Cambia password (richiede login)");
                            System.out.println("3. Torna al menu principale");
                            System.out.print("Scegli un'opzione: ");
                            
                            String sceltaPassword = scanner.nextLine();
                            int opzionePassword = leggiIntero(sceltaPassword);
                            
                            switch (opzionePassword) {
                                case 1:
                                    System.out.println("🔄 Generazione password temporanea...");
                                    String nuovaPassword = interfaceBook.generaPasswordTemporanea(username3);
                                    System.out.println(nuovaPassword);
                                    System.out.println("\n⚠️  IMPORTANTE: Cambia questa password al prossimo login!");
                                    break;
                                case 2:
                                    System.out.println("=== CAMBIA PASSWORD ===");
                                    System.out.println("⚠️  NOTA: Devi essere loggato per cambiare la password");
                                    System.out.println("Inserisci username: ");
                                    String usernameCambio = scanner.nextLine();
                                    System.out.println("Inserisci password attuale: ");
                                    String oldPassword = scanner.nextLine();
                                    System.out.println("Inserisci nuova password: ");
                                    String newPassword = scanner.nextLine();
                                    System.out.println("Conferma nuova password: ");
                                    String confirmPassword = scanner.nextLine();
                                    
                                    if (newPassword.equals(confirmPassword)) {
                                        String risultatoCambio = interfaceBook.cambiaPassword(sessionId, usernameCambio, oldPassword, newPassword);
                                        System.out.println(risultatoCambio);
                                    } else {
                                        System.out.println("❌ ERRORE: Le password non coincidono!");
                                    }
                                    break;
                                case 3:
                                    System.out.println("Torno al menu principale");
                                    break;
                                default:
                                    System.out.println("Opzione non valida. Torno al menu principale.");
                                    break;
                            }
                        }
                        break;                
                    case 0:
                        System.out.println("Uscita effettuata con successo.");
                        System.exit(0);
                    default:
                        System.out.println("Scelta non valida. Riprova.");
                }
            }
        } catch (Exception e) {
            System.err.println("Errore del client: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static int leggiIntero(String intero) {
        try {
            return Integer.parseInt(intero);
        } catch (NumberFormatException e) {
            System.out.println("Input non valido");
            // You may want to prompt again or return a default value
            return -1;
        }
    }

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            InterfaceBook interfaceBook = (InterfaceBook) registry.lookup("BookRecommender");
            Client client = new Client();
            client.start();
        } catch (Exception e) {
            System.err.println("Errore nel client: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
