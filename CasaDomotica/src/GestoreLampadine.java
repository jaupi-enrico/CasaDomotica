import java.util.ArrayList;

public class GestoreLampadine {
    private ArrayList<Lampadina> arrayLampadine;

    public GestoreLampadine() {
        this.arrayLampadine = new ArrayList<>();
    }

    public void aggiungiLampadina(Lampadina l) throws LampadinaDuplicataException {
        for (Lampadina lamp : arrayLampadine) {
            if (lamp.getPosizione().getStanza().equals(l.getPosizione().getStanza())) {
                throw new LampadinaDuplicataException(l.getPosizione().getStanza());
            }
        }
        arrayLampadine.add(l);
    }

    public void eliminaLampadina(long id) throws LampadinaNonTrovataException {
        Lampadina l = getLampadinaById(id);
        if (l == null) {
            throw new LampadinaNonTrovataException(id);
        }
        arrayLampadine.remove(l);
    }

    public void accendi(long id) throws LampadinaNonTrovataException {
        Lampadina l = getLampadinaById(id);
        if (l == null) {
            throw new LampadinaNonTrovataException(id);
        }
        l.accendi();
    }

    public void spegni(long id) throws LampadinaNonTrovataException {
        Lampadina l = getLampadinaById(id);
        if (l == null) {
            throw new LampadinaNonTrovataException(id);
        }
        l.spegni();
    }

    public void accendiTutte() {
        for (Lampadina l : arrayLampadine) {
            l.accendi();
        }
    }

    public void spegniTutte() {
        for (Lampadina l : arrayLampadine) {
            l.spegni();
        }
    }

    private Lampadina getLampadinaById(long id) {
        for (Lampadina l : arrayLampadine) {
            if (l.getId() == id) {
                return l;
            }
        }
        return null;
    }

    public Lampadina getLampadina(long id) throws LampadinaNonTrovataException {
        Lampadina l = getLampadinaById(id);
        if (l == null) {
            throw new LampadinaNonTrovataException(id);
        }
        return l;
    }

    public ArrayList<Lampadina> getTutte() {
        return arrayLampadine;
    }

    public long newId() {
        if (arrayLampadine.isEmpty()) {
            return 1;
        }
        return arrayLampadine.getLast().getId() + 1;
    }
}
