import graphics.*;
import java.util.ArrayList;

public class MenuLampadina {

    private final Lampadina lamp;
    private final Picture lampImg;
    private final Ellipse pallaColore;
    private final ArrayList<Line> rays = new ArrayList<>();

    private final Text nome;
    private final Text potenza;
    private final Text stanza;
    private Text intensita;

    private final double size;
    private final double baseY = 50;

    public MenuLampadina(Lampadina lampadina, double size) {
        this.lamp = lampadina;
        this.size = size;

        lampImg = new Picture("lampadinaImg.png");
        lampImg.resizeTo(500, 500);
        lampImg.setPosition(size / 2.0 - 250, baseY);

        pallaColore = new Ellipse(size / 2.0 - 100, baseY + 25, 200, 200);
        pallaColore.setColor(lamp.getColore());

        buildRays();

        nome = new Text(1000, 350, "Nome: " + lamp.getNome());
        potenza = new Text(1000, 500, "Potenza: " + (int) lamp.getPotenza() + "W");
        stanza = new Text(150, 500, "Stanza: " + lamp.getPosizione().getStanza());

        intensita = new Text(size / 2.0, baseY + 100, (int) lamp.getIntensita() + "%");

        stileTesti();
    }

    private void buildRays() {
        double cx = size / 2.0;
        double cy = baseY + 125;

        double r = 160;
        double len = 40;

        for (int i = 0; i < 8; i++) {
            double angle = Math.toRadians(i * 45);
            double x1 = cx + Math.cos(angle) * r;
            double y1 = cy + Math.sin(angle) * r;

            double x2 = cx + Math.cos(angle) * (r + len);
            double y2 = cy + Math.sin(angle) * (r + len);

            rays.add(new Line(x1, y1, x2, y2));
        }
    }

    private void stileTesti() {
        nome.grow(130, 75);
        potenza.grow(130, 75);
        stanza.grow(130, 75);
        intensita.grow(50, 50);
    }

    public void show() {
        lampImg.draw();
        pallaColore.fill();
        nome.draw();
        potenza.draw();
        stanza.draw();
        intensita.draw();

        if (lamp.getStato()) {
            for (Line ray : rays) ray.draw();
        }
    }

    public void remove() {
        lampImg.remove();
        pallaColore.remove();
        nome.remove();
        potenza.remove();
        stanza.remove();
        intensita.remove();
        for (Line ray : rays) ray.remove();
    }

    public void cambiaColore(Color colore) {
        pallaColore.setColor(colore);
    }

    public void accendi() {
        for (Line ray : rays) ray.draw();
    }

    public void spegni() {
        for (Line ray : rays) ray.remove();
    }

    public void cambiaIntensita(double nuovoValore) {
        double cx = intensita.getX();
        double cy = intensita.getY();

        intensita.remove();
        intensita = new Text(cx, cy, (int) nuovoValore + "%");

        intensita.grow(50, 50);
        intensita.setPosition(cx, cy);

        intensita.draw();
    }
}
