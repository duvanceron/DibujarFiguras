
package drawfigures;

import java.awt.Color;
import java.awt.Shape;
import java.awt.Stroke;


public class Figure {
    
    private Shape shape;
    private Color color;
    private Stroke TypeLine;
    private boolean fill;

    public Figure() {
    }
    
    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Stroke getTypeLine() {
        return TypeLine;
    }

    public void setTypeLine(Stroke TypeLine) {
        this.TypeLine = TypeLine;
    }

    public boolean isFill() {
        return fill;
    }

    public void setFill(boolean fill) {
        this.fill = fill;
    }
    
    
    
    
}
