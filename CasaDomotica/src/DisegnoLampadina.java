import graphics.*;

public class DisegnoLampadina {
    private Ellipse palla;
    private Picture disegno;
    private double x, y;
    private double width = 40, height = 40;

    public DisegnoLampadina(double x, double y, String color) {
        this.x = x;
        this.y = y;

        disegno = new Picture("lampadinaImg.png");
        double originalX = disegno.getWidth();
        double originalY = disegno.getHeight();

        disegno.grow((width - disegno.getWidth()) / 2.0, (height - disegno.getHeight()) / 2.0);
        disegno.translate(x + width/2 - originalX / 2, y + height/2 -originalY / 2);

        palla = new Ellipse(x, y, width, height);
        if(!setColor(color)) {
            System.out.println("\nColore non trovato");
        }
    }

    public void undraw() {

    }

    public void draw() {
        palla.fill();
        disegno.draw();
    }

    public boolean setColor(String color) {
        switch (color.toLowerCase()) {
            case "red" -> palla.setColor(Color.RED);
            case "yellow" -> palla.setColor(Color.YELLOW);
            case "blue" -> palla.setColor(Color.BLUE);
            case "green" -> palla.setColor(Color.GREEN);
            case "white" -> palla.setColor(Color.WHITE);
            case "orange" -> palla.setColor(Color.ORANGE);
            case "gray" -> palla.setColor(Color.GRAY);
            default -> {
                palla.setColor(Color.YELLOW);
                return false;
            }
        }
        return true;
    }

    public void accendi(String color, int intensita) {
        setColor(color);
        palla.grow(intensita * 0.1, intensita * 0.1);
        draw();
    }

    public void spegni() {
        setColor("gray");
        draw();
    }
}
