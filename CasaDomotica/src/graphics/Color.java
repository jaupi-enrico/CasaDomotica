package graphics;

import java.util.ArrayList;

//HIDE
public class Color
{
    private int red;
    private int green;
    private int blue;

    // Color constants

    public static final Color RED = new Color(255, 0, 0);
    public static final Color GREEN = new Color(0, 255, 0);
    public static final Color BLUE = new Color(0, 0, 255);
    public static final Color WHITE = new Color(255, 255, 255);
    public static final Color LIGHT_GRAY = new Color(192, 192, 192);
    public static final Color GRAY = new Color(128, 128, 128);
    public static final Color DARK_GRAY = new Color(64, 64, 64);
    public static final Color BLACK = new Color(0, 0, 0);
    public static final Color CYAN = new Color(0, 255, 255);
    public static final Color MAGENTA = new Color(255, 0, 255);
    public static final Color YELLOW = new Color(255, 255, 0);
    public static final Color PINK = new Color(255, 175, 175);
    public static final Color ORANGE = new Color(255, 200, 0);

    public static ArrayList<String> getColorList() {
        ArrayList<String> arr = new ArrayList<>();
        arr.add("red");
        arr.add("yellow");
        arr.add("blue");
        arr.add("green");
        arr.add("white");
        arr.add("orange");
        arr.add("gray");
        arr.add("black");
        arr.add("cyan");
        arr.add("dark gray");
        arr.add("light gray");
        arr.add("pink");
        arr.add("magenta");
        return arr;
    }

    public static Color getColor(int r, int g, int b) {
        return new Color(r, g, b);
    }

    public static Color getColor(String s) {
        switch (s.toLowerCase()) {
            case "red" -> {
                return Color.RED;
            }
            case "yellow" -> {
                return Color.YELLOW;
            }
            case "blue" -> {
                return Color.BLUE;
            }
            case "green" -> {
                return Color.GREEN;
            }
            case "white" -> {
                return Color.WHITE;
            }
            case "orange" -> {
                return Color.ORANGE;
            }
            case "gray" -> {
                return Color.GRAY;
            }
            case "black" -> {
                return Color.BLACK;
            }
            case "cyan" -> {
                return Color.CYAN;
            }
            case "dark gray" -> {
                return Color.DARK_GRAY;
            }
            case "light gray" -> {
                return Color.LIGHT_GRAY;
            }
            case "pink" -> {
                return Color.PINK;
            }
            case "magenta" -> {
                return Color.MAGENTA;
            }
            default -> {
                return Color.YELLOW;
            }
        }
    }

    public static String colorToString(Color c) {
        if (c == null) return "invalido";

        if (c.getRed() == 255 && c.getGreen() == 0 && c.getBlue() == 0) return "red";
        if (c.getRed() == 255 && c.getGreen() == 255 && c.getBlue() == 0) return "yellow";
        if (c.getRed() == 0 && c.getGreen() == 0 && c.getBlue() == 255) return "blue";
        if (c.getRed() == 0 && c.getGreen() == 255 && c.getBlue() == 0) return "green";
        if (c.getRed() == 255 && c.getGreen() == 255 && c.getBlue() == 255) return "white";
        if (c.getRed() == 255 && c.getGreen() == 200 && c.getBlue() == 0) return "orange";
        if (c.getRed() == 128 && c.getGreen() == 128 && c.getBlue() == 128) return "gray";
        if (c.getRed() == 0 && c.getGreen() == 0 && c.getBlue() == 0) return "black";
        if (c.getRed() == 0 && c.getGreen() == 255 && c.getBlue() == 255) return "cyan";
        if (c.getRed() == 64 && c.getGreen() == 64 && c.getBlue() == 64) return "dark gray";
        if (c.getRed() == 192 && c.getGreen() == 192 && c.getBlue() == 192) return "light gray";
        if (c.getRed() == 255 && c.getGreen() == 175 && c.getBlue() == 175) return "pink";
        if (c.getRed() == 255 && c.getGreen() == 0 && c.getBlue() == 255) return "magenta";
        String s = "personalizzato" + "?" + c.getRed() + "&" +  c.getGreen() + "&" + c.getBlue();
        return s;
    }

    /**
     * Constructs a new Color object.
     * @param red the red value of the color (between 0 and 255)
     * @param green the green value of the color (between 0 and 255)
     * @param blue the blue value of the color (between 0 and 255)
     */
    public Color(int red, int green, int blue)
    {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    /**
     * Gets the red value of this color.
     * @return the red value (between 0 and 255)
     */
    public int getRed()
    {
       return red;
    }

    /**
     * Gets the green value of this color.
     * @return the green value (between 0 and 255)
     */
    public int getGreen()
    {
       return green;
    }

    /**
     * Gets the blue value of this color.
     * @return the blue value (between 0 and 255)
     */
    public int getBlue()
    {
       return blue;
    }
}
