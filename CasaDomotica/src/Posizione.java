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

    @Override
    public String toString(){
        return "x=" + x + " y=" + y + " stanza=" + stanza;
    }

    public Posizione toPosizione(String info){
        Posizione p = new Posizione(0, 0, "");
        String[] tokens = info.
    }
}
