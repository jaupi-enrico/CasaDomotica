import graphics.Color;
import graphics.Ellipse;
import graphics.Picture;
import graphics.Rectangle;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class CasaGrafica {
    CasaIntelligente casa;
    Picture casaImg;
    Rectangle menu;
    ArrayList<Rectangle> lampadineMenu;

    public CasaGrafica(String src) throws FileNotFoundException {
        casa = new CasaIntelligente();
        casaImg = new Picture(src);
        casaImg.draw();
        lampadineMenu = new ArrayList<>();
        creaMenu();
    }

    public CasaIntelligente getGestore() {
        return casa;
    }

    public void disegnaLampadine() {

        ArrayList<Lampadina> l = casa.getGestore().getTutte();
        for (int i = 0; i < l.size(); i++) {
            Ellipse palle = new Ellipse(l.get(i).getPosizione().getX(), l.get(i).getPosizione().getY(), 50, 50);
            palle.setColor(Color.YELLOW);
            palle.fill();
            palle.draw();
        }


    }

    public void togliDisegnoLamopadine() {

    }

    public void disegnaLampadina(long id) {

    }

    public void togliDisegnoLampadina(long id) {

    }

    public void creaMenu() {
        double x = casaImg.getWidth();
        double y = 0;
        double width = 300;
        double height = casaImg.getHeight();
        menu = new Rectangle(x, y, width, height);

        for (int i = 0; i < 4; i++) {
            if (!(lampadineMenu.isEmpty())) {
                lampadineMenu.add(new Rectangle(x, lampadineMenu.getLast().getY() + lampadineMenu.getLast().getHeight(), width, 50));
            }
            else {
                lampadineMenu.add(new Rectangle(x, y, width, 50));
            }
        }
    }

    public void apriMenu() {
        menu.draw();
        for (int i = 0; i < lampadineMenu.size(); i++) {
            lampadineMenu.get(i).draw();
        }
        if (menu.getWidth() == 0) {
            menu.grow(300, 0);
        }
        for (int i = 0; i < lampadineMenu.size(); i++) {
            if (lampadineMenu.get(i).getWidth() == 0) {
                lampadineMenu.get(i).grow(300, 0);
            }
        }
    }

    public void chiudiMenu() {
        menu.grow(-300, 0);
        for (int i = 0; i < lampadineMenu.size(); i++) {
            if (lampadineMenu.get(i).getWidth() == 300) {
                lampadineMenu.get(i).grow(-300, 0);
            }
        }
    }
}
