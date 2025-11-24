import graphics.*;

public class OpzioneGrafica {
    private Rectangle riquadro;
    private Text nome;
    private String id;

    private double initialX, initialY; // posizione iniziale del testo
    private double initialWidth; // larghezza iniziale riquadro
    private boolean isOpen = false;

    OpzioneGrafica(Rectangle riquadro, String nomeStr, String id) {
        this.id = id;
        this.riquadro = riquadro;
        this.nome = new Text(riquadro.getX(), riquadro.getY(), nomeStr);

        this.initialX = nome.getX();
        this.initialY = nome.getY();
        this.initialWidth = riquadro.getWidth();
    }

    public void open() {
        if (!isOpen) {
            riquadro.grow(150, 0);
            riquadro.translate(150, 0);
            nome.translate( nome.getWidth(), 0 );
            System.out.println(nome);
            isOpen = true;
        }
        riquadro.draw();
        nome.draw();
    }


    public void close() {
        if (isOpen) {
            riquadro.translate(-150, 0);
            riquadro.grow(-150, 0);
            nome.translate(-nome.getWidth(), 0);
            System.out.println(nome);
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
