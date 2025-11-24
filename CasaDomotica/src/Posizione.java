import java.util.ArrayList;

public class Posizione {
    private double x;
    private double y;
    private String stanza;

    public Posizione(double x, double y, String stanza){
        this.x = x;
        this.y = y;
        this.stanza = stanza;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public String getStanza() {
        return stanza;
    }

    public void setStanza(String stanza) {
        this.stanza = stanza;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public String toString(){
        return "x=" + x + " y=" + y + " stanza=" + stanza;
    }

    public static Posizione toPosizione(String info){
        Posizione p = new Posizione(0, 0, "");
        String[] tokens = info.split(" ");
        ArrayList<String> chiavi = new ArrayList<>();
        ArrayList<String> valori = new ArrayList<>();

        for (int i = 0; i < tokens.length; i++) {
            String[] chiave_valore_temp = tokens[i].split("=");
            chiavi.add(chiave_valore_temp[0]);
            valori.add(chiave_valore_temp[1]);
        }
        p.x = Double.parseDouble(valori.get(0));
        p.y = Double.parseDouble(valori.get(1));
        p.stanza = valori.get(2);

        return p;
    }
}
