public class LampadinaDuplicataException extends Exception {
    public LampadinaDuplicataException(long id) {
        super("Esiste già una lampadina con id " + id);
    }
}
