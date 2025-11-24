public class CasaIntelligente {

    private GestoreLampadine gestore;

    public CasaIntelligente() {
        this.gestore = new GestoreLampadine();
    }

    public void accendiStanza(String stanza) {
        for (Lampadina l : gestore.getTutte()) {
            if (l.getPosizione().getStanza().equalsIgnoreCase(stanza)) {
                l.accendi();
            }
        }
    }

    public void spegniStanza(String stanza) {
        for (Lampadina l : gestore.getTutte()) {
            if (l.getPosizione().getStanza().equalsIgnoreCase(stanza)) {
                l.spegni();
            }
        }
    }

    public String riepilogoCasa() {
        int accese = 0;
        int spente = 0;

        for (Lampadina l : gestore.getTutte()) {
            if (l.getStato()) accese++;
            else spente++;
        }

        return "Lampadine accese: " + accese +
                ", lampadine spente: " + spente;
    }

    public GestoreLampadine getGestore() {
        return gestore;
    }

    public void accediLampadina(long id) throws LampadinaNonTrovataException {
        gestore.accendi(id);
    }

    public void spegnilampadina(long id) throws LampadinaNonTrovataException {
        gestore.spegni(id);
    }

    public void cambiaColore(long id, String color) throws LampadinaNonTrovataException {
        gestore.setColor(id, color);
    }

    public void cambiaIntensita(long id, double intensita) throws LampadinaNonTrovataException{
        gestore.setIntensita(id, intensita);
    }

    public void accendiTutte() {
        gestore.accendiTutte();
    }

    public void spegniTutte() {
        gestore.spegniTutte();
    }

    public void addLampadina(double potenza, Posizione pos, String nome, int intensita, String colore) throws LampadinaDuplicataException {
        Lampadina l = new Lampadina(gestore.newId(), potenza, pos, nome, intensita, colore);
        gestore.aggiungiLampadina(l);
    }
}
