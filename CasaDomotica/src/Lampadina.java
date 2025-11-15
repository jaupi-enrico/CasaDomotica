import java.util.ArrayList;

public class Lampadina {
    private long id;
    private Posizione posizione;
    private int potenza;
    private int intensità;
    private String colore;
    private String nome;
    private boolean acceso;

    public Lampadina(long id, int potenza, Posizione p, String nome, int intensita, String colore) {
        this.id = id;
        if (potenza < 0)
            potenza = -potenza;
        this.potenza = potenza;
        this.nome = nome;
        this.intensità = intensita;
        this.colore = colore;
        this.acceso = true;
        this.posizione = p;
    }
    public Lampadina(Lampadina l){
        this.potenza = l.potenza;
        this.intensità = l.intensità;
        this.colore = l.colore;
        this.acceso = l.acceso;
        this.nome = l.nome;
        this.posizione = l.posizione;
    }


    public long getId() {
        return id;
    }
    public Posizione getPosizione(){ return this.posizione; }
    public int getPotenza(){
        return this.potenza;
    }
    public int getIntensità() {
        return intensità;
    }
    public String getColore(){
        return this.colore;
    }
    public String getNome(){
        return this.nome;
    }
    public boolean getStato(){ return acceso; }

    public void setColore(String colore){
        this.colore = colore;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
    public void setPosizione(Posizione posizione){ this.posizione = posizione; }
    public void setId(long id){ this.id = id; }

    public void aumentaIlluminazione(){
        if (this.intensità != 100){
            this.intensità += 10;
        }
    }

    public void diminuisciIluminazione(){
        if (this.intensità != 0){
            this.intensità -= 10;
        }
    }

    public void accendi(){
        this.acceso = true;
        this.intensità = 50;
    }

    public void spegni(){
        this.acceso = false;
        this.intensità = 0;
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
                ",Intensità:" + intensità +
                ",Colore:" + colore +
                ",Stato:" + stato;
    }

    public Lampadina toLampadina(String info){
        Lampadina l = new Lampadina(0);
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
        l.posizione = Posizione;
        l.potenza = Integer.parseInt(valori.get(3));
        l.intensità = Integer.parseInt(valori.get(4));
        l.colore = valori.get(5);
        if(valori.get(6) == "Acceso") l.acceso = true;
        else l.acceso = false;
    }
}

