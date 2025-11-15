public class CasaIntelligente {

    private GestoreLampadine gestore;

    public CasaIntelligente(GestoreLampadine gestore) {
        this.gestore = gestore;
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
}
