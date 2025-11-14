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
}
