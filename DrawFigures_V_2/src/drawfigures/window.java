package drawfigures;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import javax.swing.JFrame;

public class window extends JFrame {

    Container containerWindow;
    layoutEast East;
    canvasFigure canvasWindow;
    public window(String title) {
        super(title);
        this.setBounds(40, 40, 500, 500);
        this.setBackground(new Color(96, 111, 140));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        East=new layoutEast();
        canvasWindow=new canvasFigure();
        container();
    }

    public void container() {
        containerWindow = this.getContentPane();
        containerWindow.setLayout(new BorderLayout(3, 10));
        
        East.canvas=this.canvasWindow;
        containerWindow.add(East,BorderLayout.EAST);
        containerWindow.add(canvasWindow,BorderLayout.CENTER);
        
        
    }

}
