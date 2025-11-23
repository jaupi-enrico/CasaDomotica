import graphics.*;

public class DisegnoLampadina {
    private Ellipse palla;
    private Picture disegno;
    private double x, y;
    private double width = 40, height = 40;
    private double potenza;

    public DisegnoLampadina(double x, double y, String color, double potenza) {
        this.x = x;
        this.y = y;
        this.potenza = potenza;

        disegno = new Picture("lampadinaImg.png");
        double originalX = disegno.getWidth();
        double originalY = disegno.getHeight();

        disegno.grow((width - disegno.getWidth()) / 2.0, (height - disegno.getHeight()) / 2.0);
        disegno.translate(x + width/2 - originalX / 2, y + height/2 -originalY / 2);

        palla = new Ellipse(x, y, potenza, potenza);
        if(!setColor(color)) {
            System.out.println("\nColore non trovato");
        }
        palla.translate(width / 2  - potenza / 2, height / 2- potenza / 2);
    }

    public void undraw() {

    }

    public void draw(double intensita) {
        palla.grow(potenza/2 * intensita/100 - palla.getWidth()/2.0, potenza/2 * intensita/100 - palla.getHeight()/2.0);


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

    public void accendi(int intensita) {
        draw(intensita);
    }

    public void spegni() {
        draw(0);
    }
}
