package bookrecommender;

import java.sql.*;
import java.util.Scanner;

/**
 * Classe per l'inizializzazione del database PostgreSQL per BookRecommender.
 * Crea il database e tutte le tabelle necessarie per il funzionamento del sistema.
 */
public class DatabaseInitializer {
    
    // Configurazione del database
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/";
    private static final String DB_NAME = "bookrecommender";
    private static final String DB_USER = "postgres";
    private static String DB_PASSWORD; // Password verrà richiesta all'utente
    
    /**
     * Richiede la password del database all'utente.
     * @return true se la password è valida, false altrimenti
     */
    private static boolean requestDatabasePassword() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== BookRecommender Database Setup ===");
        System.out.println("Inserisci la password del database PostgreSQL:");
        System.out.print("Password: ");
        
        String password = scanner.nextLine();
        
        if (password == null || password.trim().isEmpty()) {
            System.out.println("❌ ERRORE: Password non può essere vuota!");
            return false;
        }
        
        // Test della connessione con la password inserita
        try {
            Connection testConnection = DriverManager.getConnection(DB_URL + "postgres", DB_USER, password);
            testConnection.close();
            DB_PASSWORD = password;
            System.out.println("✅ Password del database verificata correttamente!");
            return true;
        } catch (SQLException e) {
            System.out.println("❌ ERRORE: Impossibile connettersi al database con la password inserita!");
            System.out.println("Messaggio: " + e.getMessage());
            System.out.println("Verifica che:");
            System.out.println("1. PostgreSQL sia in esecuzione");
            System.out.println("2. La password sia corretta");
            System.out.println("3. L'utente 'postgres' abbia i permessi necessari");
            return false;
        }
    }
    
    /**
     * Metodo principale per inizializzare il database.
     * @param args Argomenti da riga di comando (non utilizzati)
     */
    public static void main(String[] args) {
        DatabaseInitializer initializer = new DatabaseInitializer();
        
        System.out.println("=== BookRecommender Database Initializer ===");
        System.out.println("Inizializzazione del database PostgreSQL...");
        
        // Richiedi la password del database
        boolean passwordValid = false;
        Scanner scanner = new Scanner(System.in);
        
        while (!passwordValid) {
            passwordValid = requestDatabasePassword();
            if (!passwordValid) {
                System.out.println("\nVuoi riprovare? (s/n): ");
                String retry = scanner.nextLine().toLowerCase();
                if (!retry.equals("s") && !retry.equals("si") && !retry.equals("y") && !retry.equals("yes")) {
                    System.out.println("❌ Setup interrotto. Password del database non valida.");
                    System.exit(1);
                }
            }
        }
        
        try {
            // Crea il database se non esiste
            initializer.createDatabaseIfNotExists();
            
            // Crea tutte le tabelle
            initializer.createTables();
            
            System.out.println("✅ Database inizializzato con successo!");
            System.out.println("Database: " + DB_NAME);
            System.out.println("Utente: " + DB_USER);
            System.out.println("Host: localhost:5432");
            
        } catch (SQLException e) {
            System.err.println("❌ Errore durante l'inizializzazione del database:");
            System.err.println("Messaggio: " + e.getMessage());
            System.err.println("Codice SQL: " + e.getSQLState());
            e.printStackTrace();
            System.exit(1);
        } catch (Exception e) {
            System.err.println("❌ Errore generico durante l'inizializzazione:");
            e.printStackTrace();
            System.exit(1);
        }
    }
    
    /**
     * Crea il database se non esiste.
     * @throws SQLException Se c'è un errore di connessione o creazione
     */
    private void createDatabaseIfNotExists() throws SQLException {
        // Prima connessione al database di default "postgres" per verificare/creare il database
        try (Connection conn = DriverManager.getConnection(DB_URL + "postgres", DB_USER, DB_PASSWORD)) {
            // Verifica se il database esiste già
            String checkDbSql = "SELECT 1 FROM pg_database WHERE datname = ?";
            try (PreparedStatement stmt = conn.prepareStatement(checkDbSql)) {
                stmt.setString(1, DB_NAME);
                ResultSet rs = stmt.executeQuery();
                
                if (!rs.next()) {
                    // Il database non esiste, lo crea
                    System.out.println("📁 Creazione del database '" + DB_NAME + "'...");
                    String createDbSql = "CREATE DATABASE " + DB_NAME;
                    try (Statement stmt2 = conn.createStatement()) {
                        stmt2.executeUpdate(createDbSql);
                        System.out.println("✅ Database '" + DB_NAME + "' creato con successo!");
                    }
                } else {
                    System.out.println("ℹ️ Database '" + DB_NAME + "' già esistente.");
                }
            }
        }
    }
    
    /**
     * Crea tutte le tabelle necessarie per il sistema.
     * @throws SQLException Se c'è un errore nella creazione delle tabelle
     */
    private void createTables() throws SQLException {
        try (Connection conn = DriverManager.getConnection(DB_URL + DB_NAME, DB_USER, DB_PASSWORD)) {
            System.out.println("📋 Creazione delle tabelle...");
            
            // Tabella userid
            createUseridTable(conn);
            
            // Tabella libri
            createLibriTable(conn);
            
            // Tabella valutazioni
            createValutazioniTable(conn);
            
            // Tabella consigli
            createConsigliTable(conn);
            
            // Tabella librerie
            createLibrerieTable(conn);
            
            System.out.println("✅ Tutte le tabelle sono state create con successo!");
        }
    }
    
    /**
     * Crea la tabella userid per gli utenti registrati.
     * @param conn Connessione al database
     * @throws SQLException Se c'è un errore nella creazione
     */
    private void createUseridTable(Connection conn) throws SQLException {
        String sql = """
            CREATE TABLE IF NOT EXISTS userid (
                nome_cognome VARCHAR(100) NOT NULL,
                codice_fiscale VARCHAR(16) NOT NULL,
                email VARCHAR(100) UNIQUE NOT NULL,
                userid VARCHAR(50) PRIMARY KEY,
                password VARCHAR(256) NOT NULL
            )
            """;
        
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("✅ Tabella 'userid' creata/verificata");
        }
    }
    
    /**
     * Crea la tabella libri per il catalogo dei libri.
     * @param conn Connessione al database
     * @throws SQLException Se c'è un errore nella creazione
     */
    private void createLibriTable(Connection conn) throws SQLException {
        String sql = """
            CREATE TABLE IF NOT EXISTS libri (
                titolo VARCHAR(600) PRIMARY KEY,
                autore VARCHAR(100),
                genere VARCHAR(50),
                editore VARCHAR(100),
                anno INT NOT NULL
            )
            """;
        
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("✅ Tabella 'libri' creata/verificata");
        }
    }
    
    /**
     * Crea la tabella valutazioni per le valutazioni degli utenti.
     * @param conn Connessione al database
     * @throws SQLException Se c'è un errore nella creazione
     */
    private void createValutazioniTable(Connection conn) throws SQLException {
        String sql = """
            CREATE TABLE IF NOT EXISTS valutazioni (
                userid VARCHAR(50) NOT NULL,
                titolo_libro VARCHAR(600) NOT NULL,
                stile INT NOT NULL CHECK (stile >= 1 AND stile <= 5),
                contenuto INT NOT NULL CHECK (contenuto >= 1 AND contenuto <= 5),
                gradevolezza INT NOT NULL CHECK (gradevolezza >= 1 AND gradevolezza <= 5),
                originalita INT NOT NULL CHECK (originalita >= 1 AND originalita <= 5),
                edizione INT NOT NULL CHECK (edizione >= 1 AND edizione <= 5),
                voto_finale FLOAT NOT NULL,
                PRIMARY KEY (userid, titolo_libro),
                FOREIGN KEY (userid) REFERENCES userid(userid) ON DELETE CASCADE,
                FOREIGN KEY (titolo_libro) REFERENCES libri(titolo) ON DELETE CASCADE
            )
            """;
        
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("✅ Tabella 'valutazioni' creata/verificata");
        }
    }
    
    /**
     * Crea la tabella consigli per le raccomandazioni tra utenti.
     * @param conn Connessione al database
     * @throws SQLException Se c'è un errore nella creazione
     */
    private void createConsigliTable(Connection conn) throws SQLException {
        String sql = """
            CREATE TABLE IF NOT EXISTS consigli (
                userid VARCHAR(50) NOT NULL,
                libro_referenziale VARCHAR(600) NOT NULL,
                libro_consigliato VARCHAR(600) NOT NULL,
                FOREIGN KEY (userid) REFERENCES userid(userid) ON DELETE CASCADE,
                FOREIGN KEY (libro_referenziale) REFERENCES libri(titolo) ON DELETE CASCADE,
                FOREIGN KEY (libro_consigliato) REFERENCES libri(titolo) ON DELETE CASCADE
            )
            """;
        
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("✅ Tabella 'consigli' creata/verificata");
        }
    }
    
    /**
     * Crea la tabella librerie per le librerie personali degli utenti.
     * @param conn Connessione al database
     * @throws SQLException Se c'è un errore nella creazione
     */
    private void createLibrerieTable(Connection conn) throws SQLException {
        String sql = """
            CREATE TABLE IF NOT EXISTS librerie (
                id SERIAL PRIMARY KEY,
                userid VARCHAR(50) NOT NULL,
                nome_libreria VARCHAR(100) NOT NULL,
                libro VARCHAR(600),
                UNIQUE(userid, nome_libreria, libro),
                FOREIGN KEY (userid) REFERENCES userid(userid) ON DELETE CASCADE,
                FOREIGN KEY (libro) REFERENCES libri(titolo) ON DELETE CASCADE
            )
            """;
        
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("✅ Tabella 'librerie' creata/verificata");
        }
    }   
    
    /**
     * Inserisce utenti di esempio.
     * @param conn Connessione al database
     * @throws SQLException Se c'è un errore nell'inserimento
     */
    private void insertSampleUsers(Connection conn) throws SQLException {
        String sql = "INSERT INTO userid (nome_cognome, codice_fiscale, email, userid, password) VALUES (?, ?, ?, ?, ?) ON CONFLICT (userid) DO NOTHING";
        
        String[][] users = {
            {"Mario Rossi", "RSSMRA80A01H501U", "mario.rossi@email.com", "mario_rossi", "password123"},
            {"Giulia Bianchi", "BNCGLI85B15F205X", "giulia.bianchi@email.com", "giulia_bianchi", "password123"},
            {"Luca Verdi", "VRDLCU90C20I301Y", "luca.verdi@email.com", "luca_verdi", "password123"}
        };
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            for (String[] user : users) {
                stmt.setString(1, user[0]);
                stmt.setString(2, user[1]);
                stmt.setString(3, user[2]);
                stmt.setString(4, user[3]);
                stmt.setString(5, user[4]); // In produzione, hashare la password
                stmt.executeUpdate();
            }
        }
        
        System.out.println("✅ " + users.length + " utenti di esempio inseriti");
    }
    
    /**
     * Verifica la connessione al database.
     * @return true se la connessione è riuscita, false altrimenti
     */
    public static boolean testConnection() {
        try (Connection conn = DriverManager.getConnection(DB_URL + DB_NAME, DB_USER, DB_PASSWORD)) {
            System.out.println("✅ Connessione al database riuscita!");
            return true;
        } catch (SQLException e) {
            System.err.println("❌ Errore di connessione al database:");
            System.err.println("Messaggio: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Mostra le informazioni sulle tabelle del database.
     */
    public static void showDatabaseInfo() {
        try (Connection conn = DriverManager.getConnection(DB_URL + DB_NAME, DB_USER, DB_PASSWORD)) {
            System.out.println("\n=== Informazioni Database ===");
            
            // Conta le righe in ogni tabella
            String[] tables = {"userid", "libri", "valutazioni", "consigli", "librerie"};
            
            for (String table : tables) {
                try (Statement stmt = conn.createStatement();
                     ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM " + table)) {
                    if (rs.next()) {
                        System.out.println("📊 Tabella '" + table + "': " + rs.getInt(1) + " righe");
                    }
                }
            }
            
        } catch (SQLException e) {
            System.err.println("❌ Errore nel recupero delle informazioni del database: " + e.getMessage());
        }
    }
} 