import graphics.*;

public class OpzioneGrafica {
    private Rectangle riquadro;
    private Text nome;
    private String id;

    private boolean isOpen = false;

    OpzioneGrafica(Rectangle riquadro, String nomeStr, String id) {
        this.id = id;
        this.riquadro = riquadro;
        this.nome = new Text(riquadro.getX(), riquadro.getY(), nomeStr);
        double diffLenght = riquadro.getWidth() - nome.getWidth();
        double diffHeight = riquadro.getHeight() - nome.getHeight();
        this.nome.grow(diffLenght / 2.0, diffHeight / 2.0);
        this.nome.translate(diffLenght / 2, diffHeight/2);
    }

    public void open() {
        if (!isOpen) {
            riquadro.draw();
            nome.draw();
            isOpen = true;
        }
    }


    public void close() {
        if (isOpen) {
            riquadro.remove();
            nome.remove();
            isOpen = false;
        }
    }

    public String getId() {
        return id;
    }

    public Rectangle getRiquadro() {
        return riquadro;
    }
}
