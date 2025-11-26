import graphics.*;

public class OpzioneGrafica {
    private Rectangle riquadro;
    private Text nome;
    private Text idText;
    private String id;

    private boolean isOpen = false;

    OpzioneGrafica(Rectangle riquadro, String nomeStr, String id) {
        this.id = id;
        this.riquadro = riquadro;
        this.nome = new Text(riquadro.getX(), riquadro.getY(), nomeStr);
        this.idText = new Text(riquadro.getX(), riquadro.getY(), id);
        double diffLenght = riquadro.getWidth() - nome.getWidth() - 80;
        double diffHeight = riquadro.getHeight() - nome.getHeight();
        this.nome.grow(diffLenght / 2.0, diffHeight / 2.0);
        double diffHeightId = riquadro.getHeight() - idText.getHeight();
        this.idText.grow(40, diffHeightId / 2.0);
        this.nome.translate(diffLenght / 2 + 80, diffHeight/2);
        this.idText.translate(40, diffHeightId / 2.0);
    }

    public void open() {
        if (!isOpen) {
            riquadro.draw();
            nome.draw();
            idText.draw();
            isOpen = true;
        }
    }

    public void close() {
        if (isOpen) {
            riquadro.remove();
            nome.remove();
            idText.remove();
            isOpen = false;
        }
    }

    public void spostaSopra() {
        riquadro.setPosition(riquadro.getX(), riquadro.getY() - 50);
        idText.translate(0, -50);
        nome.translate(0, -50);
    }

    public void remove() {
        riquadro.remove();
        idText.remove();
        nome.remove();
    }

    public String getId() {
        return id;
    }

    public Rectangle getRiquadro() {
        return riquadro;
    }
}
