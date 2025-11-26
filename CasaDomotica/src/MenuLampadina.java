import graphics.*;

import java.util.ArrayList;

public class MenuLampadina {
    private final Picture lampImg = new Picture("lampadinaImg.png");
    private Ellipse pallaColore;
    private ArrayList<Line> rays;
    private Lampadina lamp;
    private Text nome;
    private Text potenza;
    private Text stanza;
    private Text intensita;


    MenuLampadina(Lampadina lampadina, double size) {
        lamp = lampadina;
        double Y = 50;
        lampImg.resizeTo(500, 500);
        lampImg.setPosition(size / 2.0 - 250, Y);
        pallaColore = new Ellipse(size / 2.0 - 100, Y + 25, 200, 200);
        pallaColore.setColor(lamp.getColore());
        rays = new ArrayList<>();
        nome = new Text(1000, 350, "Nome: " + lamp.getNome());
        potenza = new Text(1000, 500, "Potenza: " + (int) lamp.getPotenza() + "W");
        stanza = new Text(100, 500, "Stanza: " + lamp.getPosizione().getStanza());
        intensita = new Text(size/2, Y + 100, (int) lamp.getIntensita() + "%");
        nome.grow(100, 75);
        potenza.grow(100, 75);
        stanza.grow(100, 75);
        intensita.grow(50, 50);
        rays.add(new Line(size / 2.0 - 250, 100, size / 2.0 - 275, 75));
    }

    public void show() {
        lampImg.draw();
        pallaColore.fill();
        if (lamp.getStato()) {
            for (Line ray : rays) {
                ray.draw();
            }
        }
        nome.draw();
        potenza.draw();
        stanza.draw();
        intensita.draw();
    }

    public void remove() {
        lampImg.remove();
        pallaColore.remove();
        if (lamp.getStato()) {
            for (Line ray : rays) {
                ray.remove();
            }
        }
        nome.remove();
        potenza.remove();
        stanza.remove();
        intensita.remove();
    }


    public void cambiaColore(Color colore) {
        pallaColore.setColor(colore);
    }


    public void accendi() {
        for (Line ray : rays) {
            ray.draw();
        }
    }


    public void spegni() {
        for (Line ray : rays) {
            ray.remove();
        }
    }

    public void cambiaIntensita(double intensita) {

    }
}
