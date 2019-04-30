package drawfigures;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class layoutEast extends JPanel implements ActionListener, ItemListener {

    canvasFigure canvas;
    private JComboBox figure;
    private JButton Setting;
    private JTextField text;
    private String[] items;
    private JCheckBox fill;

    public layoutEast() {
        this.setBackground(new Color(140, 190, 178));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    initComponents();
    }

    public final void initComponents() {
        Box EastBox = Box.createVerticalBox();
        EastBox.setBorder(BorderFactory.createTitledBorder("Opciones"));
        items = new String[]{"Circulo", "Rectangulo", "Linea", "Texto","Poligono","Mover figura"};
        figure = new JComboBox(items);
        figure.setSelectedIndex(-1);
        figure.addItemListener(this);
        text = new JTextField(10);
        text.setEnabled(false);
        Setting = new JButton("Configurar");
        Setting.addActionListener(this);
        fill = new JCheckBox("Relleno");
        EastBox.add(figure);
        EastBox.add(Box.createVerticalStrut(10));//Crea un espacio entre dos componentes
        EastBox.add(text);
        EastBox.add(Box.createVerticalStrut(10));//Crea un espacio entre dos componentes
        EastBox.add(fill);
        EastBox.add(Box.createVerticalStrut(10));//Crea un espacio entre dos componentes
        EastBox.add(Setting);
        this.add(EastBox);

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String TypeFigure="";
        if (figure.getSelectedIndex() >= 0) {
            TypeFigure = figure.getSelectedItem().toString();
        }
        boolean isFill = fill.isSelected();
        canvas.setFigure(TypeFigure);
        canvas.setFill(isFill);
        canvas.setText(text.getText());
        System.out.println(TypeFigure);
    }

    @Override
    public void itemStateChanged(ItemEvent ie) {
//        if (figure.getSelectedItem() == "Texto") {
//            text.setEnabled(true);
//        }
        text.setEnabled(figure.getSelectedItem().toString().equals("Texto"));//solo se habilita el textField
        text.setText("");

    }

}
