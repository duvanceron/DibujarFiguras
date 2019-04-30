package drawfigures;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class canvasFigure extends Canvas implements MouseListener, MouseMotionListener {

    private int x1, x2, y1, y2;
    private String figure;
    private boolean fill;
    private String text;
    private Point startDrag, endDrag, pointsPoly;
    private Polygon poly;
    private boolean flag;
    ArrayList<Shape> FiguraMover;
    ArrayList<Figure> figureList2;
    //crear un buffer fuera de pantalla con el AWT, primero crear una imagen del tamaño apropiado y luego obtener un contexto gráfico para manipular la imagen. 
    Dimension offDimension;
    Image offImage;//la imagen creada de forma temporal
    Graphics2D buffer;//almacena datos de forma temporal
    Figure figurite;

    public canvasFigure() {
        x1 = x2 = y1 = y2 = 0;
        figure = "";
        fill = false;
        text = "";
        flag = false;
        FiguraMover = new ArrayList<>();
        figureList2 = new ArrayList<>();
        figurite = new Figure();
        poly = new Polygon();
        this.setMinimumSize(new Dimension(400, 300));
        this.setPreferredSize(new Dimension(400, 300));
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    public String getFigure() {
        return figure;
    }

    public void setFigure(String figure) {
        this.figure = figure;
    }

    public boolean isFill() {
        return fill;
    }

    public void setFill(boolean fill) {
        this.fill = fill;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        flag = true;
        poly.addPoint(me.getPoint().x, me.getPoint().y);
        if (me.isMetaDown()) {
            flag = true;
            //figuresList.add(poly);
            figurite = new Figure();
            poly = createPolygon();
            figurite.setShape(poly);
            figureList2.add(figurite);

        }

    }

    @Override
    public void mousePressed(MouseEvent me) {
        x1 = me.getPoint().x; //devuelve la posicion x donde se presiono(punto inicial): segunda forma
        y1 = me.getPoint().y;//devuelve la posicion y donde se presiono(punto inicial): segunda forma

        switch (figure) {
            case "Mover figura":
                for (int i = 0; i < figureList2.size(); i++) {
                    if (figureList2.get(i).getShape().contains(x1, y1)) {
                        FiguraMover.add(figureList2.get(i).getShape());
                        System.out.println("hello" + figureList2.get(i));
                    }
                }
                break;
        }

    }

    @Override
    public void mouseReleased(MouseEvent me) {
        x2 = me.getPoint().x;
        y2 = me.getPoint().y;
        figurite = new Figure();
        switch (figure) {
            case "Rectangulo":

                Shape rectangle = createRectangle(x1, y1, x2, y2);
                // figuresList.add(rectangle);
                figurite.setShape(rectangle);
                figurite.setFill(fill);
                //rectangle = null;
                figureList2.add(figurite);
                break;
            case "Linea":
                Shape line = createLine(x1, y1, x2, y2);
                figurite.setShape(line);
                figureList2.add(figurite);
                //figuresList.add(line);

                break;
            case "Circulo":
                Shape ellipse = createEllipse(x1, y1, x2, y2);
                figurite.setShape(ellipse);
                figurite.setFill(fill);
                figureList2.add(figurite);
                //figuresList.add(ellipse);
                break;
            case "Mover figura":
                FiguraMover.clear();
                break;
        }

        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }

    @Override
    public void mouseDragged(MouseEvent me) {
        x2 = me.getPoint().x;
        y2 = me.getPoint().y;
        for (int i = 0; i < FiguraMover.size(); i++) {
            if (FiguraMover.get(i) instanceof Rectangle2D) {
                Rectangle2D.Float rd = (Rectangle2D.Float) FiguraMover.get(i);
                rd.x += (-x1 + x2);
                rd.y += (-y1 + y2);
                x1 = x2;
                y1 = y2;
            }
        }
        repaint();

    }

    @Override
    public void mouseMoved(MouseEvent me) {

    }

    @Override
    public void paint(Graphics p) {
        update(p);
    }

    @Override
    public void update(Graphics g) {
        // Graphics2D g2= (Graphics2D)g;//se pierden caracteristicas generales y se vuelvan especifico, en este caso no es necesario por que se usa el buffer.
        Dimension d = size();
        // Cree el contexto de gráficos fuera de pantalla, si no existe uno bueno.
        if ((buffer == null) || (d.width != offDimension.width) || (d.height != offDimension.height)) {
            offDimension = d;
            offImage = createImage(d.width, d.height);
            buffer = (Graphics2D) offImage.getGraphics();//se convierte la imagen en Graphics 2D y lo guardamos en el buffer, se va a pintar con el buffer.
        }
        //inicializar variables y crear el buffer fuera de pantalla
        //borrar la imagen previa 
        buffer.setColor(getBackground());
        buffer.fillRect(0, 0, d.width, d.height);
        buffer.setColor(new Color(69, 92, 123));

        switch (figure) {

            case "Rectangulo":
                Shape rectangle = createRectangle(x1, y1, x2, y2);
               
                if (fill) {
                    System.out.println(""+fill);
                    buffer.fill(rectangle);
                } else {
                    buffer.draw(rectangle);

                }
                break;
            case "Linea":
                Shape line = createLine(x1, y1, x2, y2);
                buffer.draw(line);
                break;
            case "Texto":
                buffer.drawString(text, x1, y1);
                break;
            case "Circulo":
                Shape oval = createEllipse(x1, y1, x2, y2);
                // buffer.setPaint(new GradientPaint(0, 0, Color.RED, 100, 0, Color.WHITE));
                if (fill) {
                    buffer.fill(oval);
                } else {
                    buffer.draw(oval);
                }
                break;
            case "Poligono":
                if (flag == true) {
                    buffer.drawPolygon(poly);
                }
                break;
            case "Mover figura":

                break;
            default:
                buffer.drawString("Opción no valida", x2, y2);
        }
        //dibuja la imgen en la pantalla.
//        int i = 0;
//        for (Shape figure : figuresList) {
//            buffer.draw(figure);
//            i++;
//        }

        int k = 0;
        for (Figure figure : figureList2) {
            if (figureList2.get(k).isFill()) {
                buffer.fill(figureList2.get(k).getShape());
                k++;
            } else {
                buffer.draw(figureList2.get(k).getShape());
                k++;

            }

        }

        g.drawImage(offImage, 0, 0, this);

    }

    private Rectangle2D.Float createRectangle(int x1, int y1, int x2, int y2) {
        return new Rectangle2D.Float(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x2 - x1), Math.abs(y2 - y1));
    }

    private Line2D.Float createLine(int x1, int y1, int x2, int y2) {
        return new Line2D.Float(x1, y1, x2, y2);
    }

    private Ellipse2D.Float createEllipse(int x1, int y1, int x2, int y2) {
        return new Ellipse2D.Float(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x2 - x1), Math.abs(y2 - y1));//Math.min retorna el mas pequeño de los dos
    }

    private Polygon createPolygon() {
        return new Polygon();
    }

}
