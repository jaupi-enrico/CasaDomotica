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
        this.nome.grow(this.riquadro.getWidth() - this.nome.getWidth(), this.riquadro.getHeight() - this.nome.getHeight());
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
