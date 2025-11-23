import graphics.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class CasaGrafica {
    CasaIntelligente casa;
    Picture casaImg;
    Rectangle menu;
    ArrayList<Rectangle> lampadineMenu;
    ArrayList<DisegnoLampadina> lampadineDisegni;

    public CasaGrafica(String src) throws FileNotFoundException {
        casa = new CasaIntelligente();
        casaImg = new Picture(src);
        casaImg.draw();
        lampadineMenu = new ArrayList<>();
        lampadineDisegni = new ArrayList<>();
        menu = null;
    }

    public CasaIntelligente getGestore() {
        return casa;
    }

    public void disegnaLampadine() {
        ArrayList<Lampadina> arr = casa.getGestore().getTutte();
        for (Lampadina l : arr) {
            l.disegna();
        }
    }

    public void togliDisegnoLamopadine() {

    }

    public void disegnaLampadina(long id) {

    }

    public void togliDisegnoLampadina(long id) {

    }

    public void creaMenu() {
        double x = casaImg.getMaxX();
        double y = 0;
        double height = casaImg.getHeight();

        menu = new Rectangle(x, y, 0, height);
        menu.setColor(Color.GRAY);
        menu.fill();
        menu.draw();

        lampadineMenu.clear();

        double voceH = 50;
        double voceY = 0;

        for (int i = 0; i < casa.getGestore().getTutte().size(); i++) {
            Rectangle r = new Rectangle(x, voceY, 300, voceH);
            r.setColor(Color.BLACK);
            voceY += voceH;
            lampadineMenu.add(r);
            r.draw();
        }
    }

    public void apriMenu() {
        if (menu == null) {
            creaMenu();
        }

        if (menu.getWidth() == 0) {
            menu.grow(150, 0);
            menu.translate(150, 0);
        }
    }

    public void chiudiMenu() {
        if (menu == null) return;

        if (menu.getWidth() == 300) {
            menu.translate(-150, 0);
            menu.grow(-150, 0);
        }
    }

    public void selectLamp(long id) throws LampadinaNonTrovataException {
        lampadineMenu.get((int) id - 1).setColor(Color.BLUE);
        lampadineMenu.get((int) id - 1).fill();
    }

    public void deselectLamps() {
        for (Rectangle r : lampadineMenu) {
            r.setColor(Color.BLACK);
            r.draw();
        }
    }
}
