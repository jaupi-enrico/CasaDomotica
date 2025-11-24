import graphics.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class CasaGrafica {
    CasaIntelligente casa;
    Picture casaImg;
    Rectangle menu;
    ArrayList<OpzioneGrafica> lampadineMenu;

    public CasaGrafica(String src) throws FileNotFoundException {
        casa = new CasaIntelligente();
        casaImg = new Picture(src);
        casaImg.draw();
        lampadineMenu = new ArrayList<>();
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
        ArrayList<Lampadina> arr = casa.getGestore().getTutte();
        for (Lampadina l : arr) {
            l.togliDisegno();
        }
    }

    public void disegnaLampadina(long id) throws LampadinaNonTrovataException {
        casa.disegna(id);
    }

    public void togliDisegnoLampadina(long id) throws LampadinaNonTrovataException {
        casa.togliDisegno(id);
    }

    public void creaMenu() {
        double x = casaImg.getMaxX();
        double y = 0;
        double height = casaImg.getHeight();

        menu = new Rectangle(x, y, 0, height);
        menu.setColor(Color.GRAY);
        menu.fill();

        lampadineMenu.clear();
        double voceY = 0;

        for (Lampadina l : casa.getGestore().getTutte()) {
            Rectangle r = new Rectangle(x, voceY, 300, 50);
            r.setColor(Color.BLACK);
            OpzioneGrafica opzione = new OpzioneGrafica(r, l.getNome(), Long.toString(l.getId()));
            lampadineMenu.add(opzione);
            voceY += 50;
        }
    }

    public void apriMenu() {
        if (menu == null) {
            creaMenu();
        }

        if (menu.getWidth() == 0) {
            menu.grow(300, 0);
        }

        for (OpzioneGrafica option : lampadineMenu) {
            option.open();
        }
    }

    public void chiudiMenu() {
        if (menu == null) return;

        if (menu.getWidth() == 300) {
            menu.grow(-300, 0);
        }

        for (OpzioneGrafica option : lampadineMenu) {
            option.close();
        }
    }

    public void addLamp(double potenza, Posizione pos, String nome, int intensita, String colore) throws LampadinaDuplicataException {
        casa.addLampadina(potenza, pos, nome, intensita, colore);
        if (menu != null) {
            if (!lampadineMenu.isEmpty()) {
                OpzioneGrafica last = lampadineMenu.getLast();
                Rectangle r = new Rectangle(last.getRiquadro().getX(), last.getRiquadro().getY() + last.getRiquadro().getHeight(), 300, 50);
                r.setColor(Color.BLACK);
                OpzioneGrafica opzione = new OpzioneGrafica(r, nome, Long.toString(casa.getGestore().getTutte().getLast().getId()));
                lampadineMenu.add(opzione);
            }
            else {
                Rectangle r = new Rectangle(casaImg.getMaxX(), 0, 300, 50);
                r.setColor(Color.BLACK);
                OpzioneGrafica opzione = new OpzioneGrafica(r, nome, Long.toString(casa.getGestore().getTutte().getLast().getId()));
                lampadineMenu.add(opzione);
            }
            if (menu.getWidth() == 300) {
                apriMenu();
            }
        }
    }

    public OpzioneGrafica getOpzione(long id) throws LampadinaNonTrovataException {
        for(OpzioneGrafica option : lampadineMenu) {
            if(option.getId().equals(Long.toString(id))) {
                return option;
            }
        }
        throw new LampadinaNonTrovataException(id);
    }

    public void selectLamp(long id) throws LampadinaNonTrovataException {
        getOpzione(id).getRiquadro().setColor(Color.BLUE);
        getOpzione(id).getRiquadro().fill();
    }

    public void deselectLamps() {
        for (OpzioneGrafica opzione : lampadineMenu) {
            opzione.getRiquadro().setColor(Color.BLACK);
            opzione.getRiquadro().draw();
        }
    }
}
