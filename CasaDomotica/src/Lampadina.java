import java.util.ArrayList;

public class Lampadina {
    private long id;
    private Posizione posizione;
    private double potenza;
    private double intensita;
    private String colore;
    private String nome;
    private boolean acceso;
    private DisegnoLampadina disegno;

    public Lampadina(long id, double potenza, Posizione p, String nome, double intensita, String colore) {
        this.id = id;
        if (potenza < 0)
            potenza = -potenza;
        this.potenza = potenza;
        this.nome = nome;
        this.intensita = intensita;
        this.colore = colore;
        this.acceso = true;
        this.posizione = p;
        this.disegno = new DisegnoLampadina(p.getX(), p.getY(), this.colore, potenza, this.id);
    }

    public Lampadina (String info){
        String[] tokens_values = info.split(",");
        ArrayList <String> chiavi = new ArrayList<>();
        ArrayList <String> valori = new ArrayList<>();
        for (int i = 0; i < tokens_values.length; i++) {
            String[] chiave_valore_temp = tokens_values[i].split(":");
            chiavi.add(chiave_valore_temp[0]);
            valori.add(chiave_valore_temp[1]);
        }
        this.id = Long.parseLong(valori.get(0));
        this.nome = valori.get(1);
        this.posizione = Posizione.toPosizione(valori.get(2));
        this.potenza = Double.parseDouble(valori.get(3));
        this.intensita = Double.parseDouble(valori.get(4));
        this.colore = valori.get(5);
        this.acceso = valori.get(6).equals("Acceso");
        this.disegno = new DisegnoLampadina(this.posizione.getX(), this.posizione.getY(), this.colore, this.potenza, this.id);
    }

    public long getId() {
        return id;
    }
    public Posizione getPosizione() {
        return this.posizione;
    }
    public double getPotenza() {
        return this.potenza;
    }
    public double getIntensita() {
        return intensita;
    }
    public String getColore() {
        return this.colore;
    }
    public String getNome() {
        return this.nome;
    }
    public boolean getStato() {
        return acceso;
    }

    public void setColore(String colore) {
        this.colore = colore;
        disegno.setColor(colore);
        disegna();
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setPosizione(Posizione posizione) {
        this.posizione = posizione;
    }
    public void setId(long id) {
        this.id = id;
    }

    public void setIntensita(double intensita) {
        this.intensita = intensita;
        disegno.draw(intensita);
    }

    public void accendi() {
        this.acceso = true;
        disegna();
    }

    public void spegni() {
        this.acceso = false;
        disegno.spegni();
    }

    public void disegna() {
        disegno.accendi(intensita);
    }

    public void togliDisegno() {
        disegno.rimuovi();
    }

    public void disegnaWithId() {
        disegno.accendi(intensita);
        disegno.drawId();
    }

    public void togliDisegnoWithId() {
        disegno.rimuovi();
        disegno.removeId();
    }

    @Override
    public String toString(){
        String stato;
        if(acceso) stato = "Acceso";
        else stato = "Spento";
        return "ID:" + id +
                ",Nome:" + nome +
                ",Posizione:" + posizione.toString() +
                ",Potenza:" + potenza +
                ",Intensità:" + intensita +
                ",Colore:" + colore +
                ",Stato:" + stato;
    }
}

