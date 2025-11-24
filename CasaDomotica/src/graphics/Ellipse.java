package graphics;//HIDE
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class Ellipse implements Shape
{
    private Color color = Color.BLACK;
    private boolean filled = false;
    private double x;
    private double y;
    private double width;
    private double height;
    
    /**
     * Constructs an ellipse.
     * @param x the leftmost x-coordinate
     * @param y the topmost y-coordinate
     * @param width the width of the bounding box
     * @param height the height of the bounding box
     */
    public Ellipse(double x, double y, double width, double height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    /**
     * Gets the leftmost x-position of this ellipse.
     * @return the leftmost x-position
     */
    public int getX()
    {
        return (int) Math.round(x);
    }

    /**
     * Gets the topmost y-position of this ellipse.
     * @return the topmost y-position
     */
    public int getY()
    {
        return (int) Math.round(y);
    }
    
    
    /**
     * Gets the width of the bounding box.
     * @return the width
     */
    public int getWidth()
    {
        return (int) Math.round(width);
    }

    /**
     * Gets the height of the bounding box.
     * @return the height
     */
    public int getHeight()
    {
        return (int) Math.round(height);
    }
    
    /**
     * Moves this ellipse by a given amount.
     * @param dx the amount by which to move in x-direction
     * @param dy the amount by which to move in y-direction
     */
    public void translate(double dx, double dy)
    {
        x += dx;
        y += dy;
        Canvas.getInstance().repaint();
    }
    
    /**
     * @param dw the amount by which to resize the width
     * @param dw the amount by which to resize the height
     */
    public void grow(double dw, double dh)
    {
        width += dw;
        height += dh;
        Canvas.getInstance().repaint();
    }

    public void setSizeAndPosition(double newX, double newY, double newWidth, double newHeight) {
        this.x = newX;
        this.y = newY;
        this.width = newWidth;
        this.height = newHeight;
        Canvas.getInstance().repaint();
    }


    /**
     * Sets the color of this ellipse.
     * @param newColor the new color
     */
    public void setColor(Color newColor)
    {
        color = newColor;
        Canvas.getInstance().repaint();
    }

    /**
     * Draws this ellipse.
     */
    public void draw()
    {
        filled = false;
        Canvas.getInstance().show(this);
    }

    /**
     * Fills this ellipse.
     */
    public void fill()
    {
        filled = true;
        Canvas.getInstance().show(this);
    }

    public void remove() {
        Canvas.getInstance().remove(this);
    }

    public String toString()
    {
        return "Ellipse[x=" + getX() + ",y=" + getY() + ",width=" + getWidth() + ",height=" + getHeight() + "]";
    }

    public void paintShape(Graphics2D g2)
    {
        Ellipse2D.Double ellipse = new Ellipse2D.Double(getX(), getY(),
                getWidth(), getHeight());
        g2.setColor(new java.awt.Color((int) color.getRed(), (int) color.getGreen(), (int) color.getBlue()));
        if (filled)
        {
            g2.fill(ellipse);
        }
        else
        {
            g2.draw(ellipse);
        }
    }
}
