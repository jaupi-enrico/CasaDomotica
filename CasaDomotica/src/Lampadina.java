import java.util.ArrayList;

public class Lampadina {
    private long id;
    private Posizione posizione;
    private double potenza;
    private int intensita;
    private String colore;
    private String nome;
    private boolean acceso;
    private DisegnoLampadina disegno;

    public Lampadina(long id, double potenza, Posizione p, String nome, int intensita, String colore) {
        this.id = id;
        if (potenza < 0)
            potenza = -potenza;
        this.potenza = potenza;
        this.nome = nome;
        this.intensita = intensita;
        this.colore = colore;
        this.acceso = true;
        this.posizione = p;
        this.disegno = new DisegnoLampadina(p.getX(), p.getY(), this.colore, potenza);
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
    public int getIntensita() {
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

    public void aumentaIlluminazione() {
        if (this.intensita != 100){
            this.intensita += 10;
        }
    }

    public void diminuisciIluminazione() {
        if (this.intensita != 0){
            this.intensita -= 10;
        }
    }

    public void accendi() {
        this.acceso = true;
        disegno.accendi(intensita);
    }

    public void spegni() {
        this.acceso = false;
        disegno.spegni();
    }

    public void disegna() {
        disegno.draw(intensita);
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

    public Lampadina toLampadina(String info){
        Lampadina l = new Lampadina(0, 0, null, "", 0, "");
        String[] tokens_values = info.split(",");
        ArrayList <String> chiavi = new ArrayList<>();
        ArrayList <String> valori = new ArrayList<>();
        for (int i = 0; i < tokens_values.length; i++) {
            String[] chiave_valore_temp = tokens_values[i].split(":");
            chiavi.add(chiave_valore_temp[0]);
            valori.add(chiave_valore_temp[1]);
        }
        l.id = Long.parseLong(valori.get(0));
        l.nome = valori.get(1);
        l.posizione = l.posizione.toPosizione(valori.get(2));
        l.potenza = Double.parseDouble(valori.get(3));
        l.intensita = Integer.parseInt(valori.get(4));
        l.colore = valori.get(5);
        if(valori.get(6).equals("Acceso") ) l.acceso = true;
        else l.acceso = false;
        
        return l;
    }
}

