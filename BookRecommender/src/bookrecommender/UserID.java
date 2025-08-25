package bookrecommender;




// Class per la gestione degli utenti nel sistema di raccomandazione libri
public class UserID {

    // Attributi della classe
    private String nomeCognome;
    private String codiceFiscale;
    private String email;
    private String userID;
    private String password;  // La password sarà memorizzata in forma hash
    
    /**
     * Costruttore della classe UserID
     * @param nome Nome e cognome dell'utente
     * @param codice Codice fiscale dell'utente
     * @param email Email dell'utente
     * @param user Username dell'utente
     * @param pass Password dell'utente (che verrà automaticamente hashatta)
     * @author Mouhammad Toure
     */
    public UserID(String nome, String codice, String email, String user, String pass) {
        nomeCognome = nome;
        this.codiceFiscale = codice;
        this.email = email;
        this.userID = user;
        this.password = hashedPassword(pass);  // Hash della password alla creazione
    }
    
    // Metodi getter e setter
    
    public String getNomeCognome() {
        return nomeCognome;
    }
    
    public void setNomeCognome(String nome) {
        nomeCognome = nome;
    }
    
    public String getCodiceFiscale() {
        return codiceFiscale;
    }
    
    public void setCodiceFiscale(String codice) {
        codiceFiscale = codice;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getUserID() {
        return userID;
    }
    
    public void setUserID(String user) {
        userID = user;
    }
    
    public String getPassword() {
        return password;
    }
    
    /**
     * Imposta una nuova password (viene automaticamente hashatta)
     * @param pass La nuova password in chiaro
     */
    public void setPassword(String pass) {
        password = hashedPassword(pass);
    }
    
    /**
     * Restituisce una stringa con tutti i dati dell'utente, inclusa la password hash
     * @return Stringa concatenata con i dati separati da virgola
     */
    public String toString() {
        return String.join(",", getNomeCognome(), getCodiceFiscale(), getEmail(), getUserID(), getPassword());
    }
    
    /**
     * Restituisce una stringa con i dati dell'utente senza la password
     * @return Stringa concatenata con i dati separati da virgola
     */
    public String toString2() {
        return String.join(",", getNomeCognome(), getCodiceFiscale(), getEmail(), getUserID());
    }

    /**
     * Metodo per generare l'hash di una password
     * @param password La password in chiaro
     * @return L'hash della password
     */
    private String hashedPassword(String password) {
        return String.valueOf(password.hashCode()); 
    }

    /**
     * Metodo per verificare se la password fornita è corretta
     * @param password La password da verificare (in chiaro)
     * @return true se la password è corretta, false altrimenti
     */
    public boolean checkPassword(String password) {
        return this.password.equals(hashedPassword(password));
    }

    public static void main(String[] args) {
        UserID user = new UserID("Mario Rossi", "RSSMRR80A01H501Z", "mario.rossi@example.com", "marioRossi", "password123");
        System.out.println(user.toString());
        System.out.println("Password check: " + user.checkPassword("password123"));
        System.out.println("Hashed password: " + user.hashedPassword("password123"));
    }
}
