import graphics.Rectangle;
import graphics.Text;

public class OpzioneGrafica {
    Rectangle riquadro;
    Text nome;
    Text idTesto;
    String id;
    double rapportoNome;

    OpzioneGrafica(Rectangle riquadro, String nome, String id) {
        this.id = id;
        this.riquadro = riquadro;
        this.nome = new Text(riquadro.getX(), riquadro.getY(), nome);
        resize();
    }

    private void resize() {
        if (riquadro.getWidth() != 0) {
            rapportoNome = (double) nome.getWidth() / nome.getHeight();
            nome.translate(0, -nome.getHeight() / 2.0);
            nome.grow(0, riquadro.getHeight() / 2.0 - nome.getHeight() / 2.0);
            nome.translate((riquadro.getHeight() / rapportoNome) / 2, riquadro.getHeight() / 2.0);

        }
    }

    public void open(double mesure) {
        if (riquadro.getWidth() == 0) {
            riquadro.translate(mesure / 2, 0);
            riquadro.grow(mesure / 2, 0);
        }
        riquadro.draw();
        nome.draw();
    }

    public void close() {
        riquadro.translate(-riquadro.getWidth()/2.0, 0);
        riquadro.grow(-riquadro.getWidth()/2.0, 0);
    }
}
