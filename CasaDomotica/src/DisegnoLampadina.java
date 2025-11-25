import graphics.*;

import java.util.ArrayList;

public class DisegnoLampadina {
    private Ellipse palla;
    private Picture disegno;
    private Text id = null;
    private double x, y;
    private double width = 40, height = 40;
    private double potenza;

    public DisegnoLampadina(double x, double y, Color color, double potenza, long id) {
        this.x = x;
        this.y = y;
        this.potenza = potenza;

        disegno = new Picture("lampadinaImg.png");

        disegno.translate(x, y);
        disegno.resizeTo(40, 40);


        palla = new Ellipse(x, y, potenza, potenza);
        setColor(color);
        palla.translate((width - potenza) / 2, (height - potenza) / 2);

        double posX = disegno.getMaxX();
        double posY = disegno.getY();
        this.id = new Text(posX, posY, Long.toString(id));
        this.id.grow(15, 15);
    }

    public void remove() {
        palla.remove();
        disegno.remove();
    }

    public void draw(double intensita) {
        double diametro = potenza * intensita / 100.0;

        if (diametro < 0) diametro = 0;

        double nx = x + (width - diametro) / 2;
        double ny = y + (height - diametro) / 2;

        palla.setSizeAndPosition(nx, ny, diametro, diametro);

        palla.fill();
        disegno.draw();
    }

    public void drawId() {
        this.id.draw();
    }

    public void removeId() {
        id.remove();
    }

    public void setColor(Color color) {
        palla.setColor(color);
    }

    public void accendi(double intensita) {
        draw(intensita);
    }

    public void spegni() {
        draw(0);
    }

    public void rimuovi() {
        palla.remove();
        disegno.remove();
    }
}
