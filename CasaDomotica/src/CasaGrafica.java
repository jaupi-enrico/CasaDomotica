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

        lampadineMenu.clear();

        double voceH = 50;
        double voceY = 0;

        for (Lampadina l : casa.getGestore().getTutte()) {
            Rectangle r = new Rectangle(x, voceY, 300, voceH);
            r.setColor(Color.BLACK);
            OpzioneGrafica opzione = new OpzioneGrafica(r, l.getNome(), Long.toString(l.getId()));
            lampadineMenu.add(opzione);
            voceY += voceH;
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

        for (OpzioneGrafica option : lampadineMenu) {
            option.riquadro.draw();
            option.nome.draw();
        }
    }

    public void chiudiMenu() {
        if (menu == null) return;

        if (menu.getWidth() == 300) {
            menu.translate(-150, 0);
            menu.grow(-150, 0);
        }
    }

    public void addLamp(double potenza, Posizione pos, String nome, int intensita, String colore) throws LampadinaDuplicataException {
        casa.addLampadina(potenza, pos, nome, intensita, colore);
    }

    public OpzioneGrafica getOpzione(long id) throws LampadinaNonTrovataException {
        for(OpzioneGrafica option : lampadineMenu) {
            if(option.id.equals(Long.toString(id))) {
                return option;
            }
        }
        throw new LampadinaNonTrovataException(id);
    }

    public void selectLamp(long id) throws LampadinaNonTrovataException {
        getOpzione(id).riquadro.setColor(Color.BLUE);
        getOpzione(id).riquadro.fill();
    }

    public void deselectLamps() {
        for (OpzioneGrafica opzione : lampadineMenu) {
            opzione.riquadro.setColor(Color.BLACK);
            opzione.riquadro.draw();
        }
    }
}
