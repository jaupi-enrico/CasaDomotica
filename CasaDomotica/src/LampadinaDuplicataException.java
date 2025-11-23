public class LampadinaDuplicataException extends Exception {
    public LampadinaDuplicataException(String luogo) {
        super("Esiste già una lampadina con luogo " + luogo);
    }
}
