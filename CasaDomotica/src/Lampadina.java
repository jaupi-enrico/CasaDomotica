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
        return "ID: " + id +
                "\n Nome: " + nome +
                "\n Posizione: " + posizione.toString() +
                "\n Potenza (W): " + potenza +
                "\n Intensità (%): " + intensità +
                "\n Colore: " + colore +
                "\n Stato: " + stato;
    }

    public Lampadina toLampadina(String info){
        return new Lampadina(3, 23);
    }
}

