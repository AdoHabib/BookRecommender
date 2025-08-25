package bookrecommender;

import javax.swing.*;
import java.awt.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.UUID;

public class BookRecommenderGUI extends JFrame {
    private InterfaceBook interfaceBook;
    private String sessionId; // Session ID per questo client GUI
    private boolean isLoggedIn = false; // Stato di login

    public BookRecommenderGUI() {
        setTitle("Book Recommender");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Genera un Session ID univoco per questo client GUI
        this.sessionId = UUID.randomUUID().toString();

        // Connessione RMI
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            interfaceBook = (InterfaceBook) registry.lookup("BookRecommender");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Errore di connessione al server RMI: " + e.getMessage());
            System.exit(1);
        }

        mostraMenuPrincipale();
    }

    private void mostraMenuPrincipale() {
        getContentPane().removeAll();
        setTitle("Book Recommender - Menu Principale");

        // Layout base
        JPanel panel = new JPanel(new GridLayout(0, 1, 10, 10));

        JButton btnVisualizzaLibri = new JButton("Visualizza Libri (Senza Login)");
        JButton btnCercaTitolo = new JButton("Cerca Libro per Titolo (Senza Login)");
        JButton btnCercaAutore = new JButton("Cerca Libro per Autore (Senza Login)");
        JButton btnCercaAutoreAnno = new JButton("Cerca Libro per Autore e Anno (Senza Login)");
        JButton btnRegistrazione = new JButton("Registrazione");
        JButton btnLogin = new JButton("Login");
        JButton btnRecuperaPassword = new JButton("Recupera Password");
        JButton btnEsci = new JButton("Esci");

        panel.add(btnVisualizzaLibri);
        panel.add(btnCercaTitolo);
        panel.add(btnCercaAutore);
        panel.add(btnCercaAutoreAnno);
        panel.add(btnRegistrazione);
        panel.add(btnLogin);
        panel.add(btnRecuperaPassword);
        panel.add(btnEsci);

        add(panel);

        // Azioni pulsanti
        btnVisualizzaLibri.addActionListener(e -> mostraLibri());
        btnCercaTitolo.addActionListener(e -> cercaLibroPerTitolo());
        btnCercaAutore.addActionListener(e -> cercaLibroPerAutore());
        btnCercaAutoreAnno.addActionListener(e -> cercaLibroPerAutoreAnno());
        btnRegistrazione.addActionListener(e -> mostraDialogRegistrazione());
        btnLogin.addActionListener(e -> mostraDialogLogin());
        btnRecuperaPassword.addActionListener(e -> mostraDialogRecuperaPassword());
        btnEsci.addActionListener(e -> System.exit(0));
        
        revalidate();
        repaint();
    }

    private void mostraMenuUtenteLoggato() {
        getContentPane().removeAll();
        setTitle("Book Recommender - Menu Utente");
        
        // Layout per utenti loggati
        JPanel panel = new JPanel(new GridLayout(0, 1, 10, 10));
        
        // Sezione Visualizzazione e Ricerca
        JLabel lblVisualizzazione = new JLabel("📚 VISUALIZZAZIONE E RICERCA");
        lblVisualizzazione.setHorizontalAlignment(SwingConstants.CENTER);
        lblVisualizzazione.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(lblVisualizzazione);
        
        JButton btnVisualizzaLibri = new JButton("Visualizza Tutti i Libri");
        JButton btnCercaTitolo = new JButton("Cerca Libro per Titolo");
        JButton btnCercaAutore = new JButton("Cerca Libro per Autore");
        JButton btnCercaAutoreAnno = new JButton("Cerca Libro per Autore e Anno");
        
        panel.add(btnVisualizzaLibri);
        panel.add(btnCercaTitolo);
        panel.add(btnCercaAutore);
        panel.add(btnCercaAutoreAnno);
        
        // Sezione Gestione Librerie
        JLabel lblLibrerie = new JLabel("📖 GESTIONE LIBRERIE");
        lblLibrerie.setHorizontalAlignment(SwingConstants.CENTER);
        lblLibrerie.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(lblLibrerie);
        
        JButton btnCreaLibreria = new JButton("Crea Nuova Libreria");
        JButton btnAggiungiLibro = new JButton("Aggiungi Libro a Libreria");
        JButton btnVisualizzaLibreria = new JButton("Visualizza Libreria");
        JButton btnRimuoviLibro = new JButton("Rimuovi Libro da Libreria");
   
        
        panel.add(btnCreaLibreria);
        panel.add(btnAggiungiLibro);
        panel.add(btnVisualizzaLibreria);
        panel.add(btnRimuoviLibro);

        
        // Sezione Valutazioni e Consigli
        JLabel lblValutazioni = new JLabel("⭐ VALUTAZIONI E CONSIGLI");
        lblValutazioni.setHorizontalAlignment(SwingConstants.CENTER);
        lblValutazioni.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(lblValutazioni);
        
        JButton btnInserisciValutazione = new JButton("Inserisci Valutazione Libro");
        JButton btnInserisciConsiglio = new JButton("Inserisci Consiglio Libro");
        JButton btnRecuperaPassword = new JButton("Recupera Password");
        JButton btnCambiaPassword = new JButton("Cambia Password");
        JButton btnTestConnessione = new JButton("Test Connessione Database");
        
        panel.add(btnInserisciValutazione);
        panel.add(btnInserisciConsiglio);
        panel.add(btnRecuperaPassword);
        panel.add(btnCambiaPassword);
        panel.add(btnTestConnessione);
        
        // Sezione Account
        JLabel lblAccount = new JLabel("👤 ACCOUNT");
        lblAccount.setHorizontalAlignment(SwingConstants.CENTER);
        lblAccount.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(lblAccount);
        
        JButton btnLogout = new JButton("Logout");
        JButton btnTornaMenu = new JButton("Torna al Menu Principale");
        JButton btnEsci = new JButton("Esci");
        
        panel.add(btnLogout);
        panel.add(btnTornaMenu);
        panel.add(btnEsci);

        // Scroll pane per gestire molti pulsanti
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(scrollPane);

        // Azioni pulsanti
        btnVisualizzaLibri.addActionListener(e -> mostraLibri());
        btnCercaTitolo.addActionListener(e -> cercaLibroPerTitolo());
        btnCercaAutore.addActionListener(e -> cercaLibroPerAutore());
        btnCercaAutoreAnno.addActionListener(e -> cercaLibroPerAutoreAnno());
        
        btnCreaLibreria.addActionListener(e -> creaLibreria());
        btnAggiungiLibro.addActionListener(e -> aggiungiLibroLibreria());
        btnRimuoviLibro.addActionListener(e -> rimuoviLibroLibreria());
        btnVisualizzaLibreria.addActionListener(e -> visualizzaLibreria());
        
        btnInserisciValutazione.addActionListener(e -> inserisciValutazione());
        btnInserisciConsiglio.addActionListener(e -> inserisciConsiglio());
        btnRecuperaPassword.addActionListener(e -> mostraDialogRecuperaPassword());
        btnCambiaPassword.addActionListener(e -> mostraDialogCambiaPassword());
        btnTestConnessione.addActionListener(e -> testConnessioneDatabase());
        
        btnLogout.addActionListener(e -> logout());
        btnTornaMenu.addActionListener(e -> {
            isLoggedIn = false;
            mostraMenuPrincipale();
        });
        btnEsci.addActionListener(e -> System.exit(0));
        
        revalidate();
        repaint();
    }

    private void mostraDialogLogin() {
        JTextField userField = new JTextField();
        JPasswordField passField = new JPasswordField();
        Object[] fields = {
            "Username:", userField,
            "Password:", passField
        };
        int option = JOptionPane.showConfirmDialog(this, fields, "Login", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                String result = interfaceBook.login(sessionId, userField.getText(), new String(passField.getPassword()));
                JOptionPane.showMessageDialog(this, result);
                if (result.contains("Login effettuato con successo")) {
                    isLoggedIn = true;
                    mostraMenuUtenteLoggato();
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Errore: " + ex.getMessage());
            }
        }
    }

    private void mostraDialogRegistrazione() {
        JTextField nameField = new JTextField();
        JTextField cfField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField userField = new JTextField();
        JPasswordField passField = new JPasswordField();
        
        Object[] fields = {
            "Nome e Cognome:", nameField,
            "Codice Fiscale:", cfField,
            "Email:", emailField,
            "Username:", userField,
            "Password:", passField
        };
        
        int option = JOptionPane.showConfirmDialog(this, fields, "Registrazione", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                String result = interfaceBook.registrazione(sessionId, nameField.getText(), cfField.getText(), 
                                                         emailField.getText(), userField.getText(), new String(passField.getPassword()));
                JOptionPane.showMessageDialog(this, result);
                if (result.contains("Registrazione completata con successo")) {
                    isLoggedIn = true;
                    mostraMenuUtenteLoggato();
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Errore: " + ex.getMessage());
            }
        }
    }

    private void logout() {
        try {
            String result = interfaceBook.logout(sessionId);
            JOptionPane.showMessageDialog(this, result);
            isLoggedIn = false;
            mostraMenuPrincipale();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Errore: " + ex.getMessage());
        }
    }

    private void mostraLibri() {
        try {
            // Mostra un messaggio di caricamento
            JOptionPane.showMessageDialog(this, "Caricamento libri in corso...", "Caricamento", JOptionPane.INFORMATION_MESSAGE);
            
            String libri = interfaceBook.visualizzaLibri();
            
            if (libri == null || libri.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nessun libro disponibile nel database.", "Libri", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            
            // Crea una finestra più grande e meglio formattata
            JTextArea area = new JTextArea(libri);
            area.setEditable(false);
            area.setLineWrap(true);
            area.setWrapStyleWord(true);
            area.setFont(new Font("Monospaced", Font.PLAIN, 12));

            JScrollPane scroll = new JScrollPane(area);
            scroll.setPreferredSize(new Dimension(800, 600));

            JFrame frame = new JFrame("📚 Libri Disponibili nel Sistema");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.getContentPane().add(scroll);
            frame.pack();
            frame.setLocationRelativeTo(this);
            frame.setVisible(true);
            
            // Mostra un messaggio di successo
            JOptionPane.showMessageDialog(this, "Libri caricati con successo!", "Successo", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, 
                "Errore durante il caricamento dei libri:\n" + ex.getMessage() + 
                "\n\nVerifica che:\n1. Il server sia in esecuzione\n2. Il database sia configurato\n3. Ci siano libri nel database", 
                "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cercaLibroPerTitolo() {
        String titolo = JOptionPane.showInputDialog(this, "Inserisci il titolo del libro:");
        if (titolo != null && !titolo.isEmpty()) {
            try {
                String result = interfaceBook.cercaLibroConTitolo(titolo);
                JOptionPane.showMessageDialog(this, result);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Errore: " + ex.getMessage());
            }
        }
    }

    private void cercaLibroPerAutore() {
        String autore = JOptionPane.showInputDialog(this, "Inserisci l'autore:");
        if (autore != null && !autore.isEmpty()) {
            try {
                String result = interfaceBook.cercaLibroConAutore(autore);
                JOptionPane.showMessageDialog(this, result);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Errore: " + ex.getMessage());
            }
        }
    }

    private void cercaLibroPerAutoreAnno() {
        String autore = JOptionPane.showInputDialog(this, "Inserisci l'autore:");
        if (autore != null && !autore.isEmpty()) {
            String annoStr = JOptionPane.showInputDialog(this, "Inserisci l'anno:");
            if (annoStr != null && !annoStr.isEmpty()) {
                try {
                    int anno = Integer.parseInt(annoStr);
                    String result = interfaceBook.cercaLibroConAutoreAnno(autore, anno);
                    JOptionPane.showMessageDialog(this, result);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Errore: Anno non valido");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Errore: " + ex.getMessage());
                }
            }
        }
    }

    private void creaLibreria() {
        String nomeLibreria = JOptionPane.showInputDialog(this, "Inserisci il nome della libreria:");
        if (nomeLibreria != null && !nomeLibreria.isEmpty()) {
            try {
                String result = interfaceBook.creaLibreria(sessionId, nomeLibreria);
                JOptionPane.showMessageDialog(this, result);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Errore: " + ex.getMessage());
            }
        }
    }

    private void aggiungiLibroLibreria() {
        String nomeLibreria = JOptionPane.showInputDialog(this, "Inserisci il nome della libreria:");
        if (nomeLibreria != null && !nomeLibreria.isEmpty()) {
            String titoloLibro = JOptionPane.showInputDialog(this, "Inserisci il titolo del libro:");
            if (titoloLibro != null && !titoloLibro.isEmpty()) {
                try {
                    String result = interfaceBook.aggiungiLibroLibreria(sessionId, nomeLibreria, titoloLibro);
                    JOptionPane.showMessageDialog(this, result);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Errore: " + ex.getMessage());
                }
            }
        }
    }

    private void visualizzaLibreria() {
        String nomeLibreria = JOptionPane.showInputDialog(this, "Inserisci il nome della libreria:");
        if (nomeLibreria != null && !nomeLibreria.isEmpty()) {
            try {
                String result = interfaceBook.visualizzaLibreria(sessionId, nomeLibreria);
                JOptionPane.showMessageDialog(this, result);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Errore: " + ex.getMessage());
            }
        }
    }

    private void rimuoviLibroLibreria() {
        String nomeLibreria = JOptionPane.showInputDialog(this, "Inserisci il nome della libreria:");
        if (nomeLibreria != null && !nomeLibreria.isEmpty()) {
            String titoloLibro = JOptionPane.showInputDialog(this, "Inserisci il titolo del libro:");
            if (titoloLibro != null && !titoloLibro.isEmpty()) {
                try {
                    String result = interfaceBook.rimuoviLibroLibreria(sessionId, nomeLibreria, titoloLibro);
                    JOptionPane.showMessageDialog(this, result);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Errore: " + ex.getMessage());
                }
            }
        }
    }
   
    private void inserisciValutazione() {
        String titoloLibro = JOptionPane.showInputDialog(this, "Inserisci il titolo del libro:");
        if (titoloLibro != null && !titoloLibro.isEmpty()) {
            try {
                // Input per le valutazioni
                String stileStr = JOptionPane.showInputDialog(this, "Valutazione Stile (1-5):");
                String contenutoStr = JOptionPane.showInputDialog(this, "Valutazione Contenuto (1-5):");
                String gradevolezzaStr = JOptionPane.showInputDialog(this, "Valutazione Gradevolezza (1-5):");
                String originalitaStr = JOptionPane.showInputDialog(this, "Valutazione Originalità (1-5):");
                String edizioneStr = JOptionPane.showInputDialog(this, "Valutazione Edizione (1-5):");
                
                if (stileStr != null && contenutoStr != null && gradevolezzaStr != null && 
                    originalitaStr != null && edizioneStr != null) {
                    
                    int stile = Integer.parseInt(stileStr);
                    int contenuto = Integer.parseInt(contenutoStr);
                    int gradevolezza = Integer.parseInt(gradevolezzaStr);
                    int originalita = Integer.parseInt(originalitaStr);
                    int edizione = Integer.parseInt(edizioneStr);
                    
                    String result = interfaceBook.inserisciValutazioneLibro(sessionId, titoloLibro, 
                                                                          String.valueOf(stile), String.valueOf(contenuto), 
                                                                          String.valueOf(gradevolezza), String.valueOf(originalita), 
                                                                          String.valueOf(edizione));
                    JOptionPane.showMessageDialog(this, result);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Errore: Inserisci numeri validi per le valutazioni");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Errore: " + ex.getMessage());
            }
        }
    }

    private void inserisciConsiglio() {
        String libroReferenziale = JOptionPane.showInputDialog(this, "Inserisci il titolo del libro referenziale:");
        if (libroReferenziale != null && !libroReferenziale.isEmpty()) {
            String libriConsigliati = JOptionPane.showInputDialog(this, "Inserisci i libri consigliati (separati da virgola):");
            if (libriConsigliati != null && !libriConsigliati.isEmpty()) {
                try {
                    String result = interfaceBook.inserisciConsiglioLibro(sessionId, libroReferenziale, libriConsigliati);
                    JOptionPane.showMessageDialog(this, result);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Errore: " + ex.getMessage());
                }
            }
        }
    }

    private void mostraDialogRecuperaPassword() {
        String username = JOptionPane.showInputDialog(this, "Inserisci l'username per il recupero password:");
        if (username != null && !username.isEmpty()) {
            try {
                String[] options = {"Visualizza Informazioni", "Genera Password Temporanea", "Cambia Password"};
                int choice = JOptionPane.showOptionDialog(this, 
                    "Scegli un'opzione per il recupero password:", 
                    "Recupero Password", 
                    JOptionPane.DEFAULT_OPTION, 
                    JOptionPane.QUESTION_MESSAGE, 
                    null, 
                    options, 
                    options[0]);
                
                if (choice == 0) {
                    String result = interfaceBook.recuperaPassword(username);
                    JOptionPane.showMessageDialog(this, result);
                } else if (choice == 1) {
                    String result = interfaceBook.generaPasswordTemporanea(username);
                    JOptionPane.showMessageDialog(this, result);
                } else if (choice == 2) {
                    mostraDialogCambiaPassword();
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Errore: " + ex.getMessage());
            }
        }
    }

    private void mostraDialogCambiaPassword() {
        JTextField usernameField = new JTextField();
        JPasswordField oldPasswordField = new JPasswordField();
        JPasswordField newPasswordField = new JPasswordField();
        JPasswordField confirmPasswordField = new JPasswordField();
        
        Object[] fields = {
            "Username:", usernameField,
            "Password Attuale:", oldPasswordField,
            "Nuova Password:", newPasswordField,
            "Conferma Nuova Password:", confirmPasswordField
        };
        
        int option = JOptionPane.showConfirmDialog(this, fields, "Cambia Password", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                String username = usernameField.getText();
                String oldPassword = new String(oldPasswordField.getPassword());
                String newPassword = new String(newPasswordField.getPassword());
                String confirmPassword = new String(confirmPasswordField.getPassword());
                
                if (newPassword.equals(confirmPassword)) {
                    String result = interfaceBook.cambiaPassword(sessionId, username, oldPassword, newPassword);
                    JOptionPane.showMessageDialog(this, result);
                } else {
                    JOptionPane.showMessageDialog(this, "❌ ERRORE: Le password non coincidono!");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Errore: " + ex.getMessage());
            }
        }
    }

    private void testConnessioneDatabase() {
        try {
            // Test semplice della connessione
            String result = interfaceBook.visualizzaLibri();
            
            if (result != null && !result.contains("ERRORE")) {
                JOptionPane.showMessageDialog(this, 
                    "✅ Connessione al database riuscita!\n\n" +
                    "Il database è configurato correttamente e contiene libri.", 
                    "Test Connessione", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, 
                    "❌ Problema con la connessione al database:\n\n" + result, 
                    "Test Connessione", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, 
                "❌ Errore di connessione:\n\n" + ex.getMessage() + 
                "\n\nVerifica che:\n1. PostgreSQL sia in esecuzione\n2. Il database 'bookrecommender' esista\n3. La password del database sia corretta", 
                "Test Connessione", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BookRecommenderGUI().setVisible(true));
    }
}