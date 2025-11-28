import graphics.*;

public class CasaIntelligente {

    private GestoreLampadine gestore;

    public CasaIntelligente() {
        this.gestore = new GestoreLampadine();
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

    public void cambiaColore(long id, Color color) throws LampadinaNonTrovataException {
        gestore.setColor(id, color);
    }

    public void cambiaIntensita(long id, double intensita) throws LampadinaNonTrovataException{
        gestore.setIntensita(id, intensita);
    }

    public void disegna(long id) throws LampadinaNonTrovataException {
        gestore.disegna(id);
    }

    public void togliDisegno(long id) throws LampadinaNonTrovataException {
        gestore.togliDisegno(id);
    }

    public void disegnaWithId(long id) throws LampadinaNonTrovataException {
        gestore.disegnaWithId(id);
    }

    public void togliDisegnoWithId(long id) throws LampadinaNonTrovataException {
        gestore.togliDisegnoWithId(id);
    }

    public void removeLampadina(long id) throws LampadinaNonTrovataException {
        gestore.removeLampadina(id);
    }

    public void accendiTutte() {
        gestore.accendiTutte();
    }

    public void spegniTutte() {
        gestore.spegniTutte();
    }

    public void addLampadina(double potenza, Posizione pos, String nome, int intensita, Color colore) throws LampadinaDuplicataException {
        Lampadina l = new Lampadina(gestore.newId(), potenza, pos, nome, intensita, colore);
        gestore.aggiungiLampadina(l);
    }
}
