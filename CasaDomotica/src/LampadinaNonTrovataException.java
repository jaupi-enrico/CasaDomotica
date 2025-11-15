public class LampadinaNonTrovataException extends Exception {
    public LampadinaNonTrovataException(long id) {
        super("Lampadina con id " + id + " non trovata");
    }
}