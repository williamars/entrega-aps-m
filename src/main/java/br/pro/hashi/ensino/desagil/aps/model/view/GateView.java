package br.pro.hashi.ensino.desagil.aps.model.view;

// IMAGENS USADAS FORAM RETIRADAS DO SITE: https://pt.wikipedia.org/wiki/Porta_l%C3%B3gica

import br.pro.hashi.ensino.desagil.aps.model.model.Gate;
import br.pro.hashi.ensino.desagil.aps.model.model.Light;
import br.pro.hashi.ensino.desagil.aps.model.model.Switch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

public class GateView extends FixedPanel implements ItemListener, MouseListener {
    private final Gate gate;
    private final Switch[] switches;

    private final JCheckBox[] entradas;
    private final Image image;
    private final Light light;

    public GateView(Gate gate) {
        super(245, 250);

        this.gate = gate;

        int a = 0;
        switches = new Switch[gate.getInputSize()];
        while (a < gate.getInputSize()) {
            switches[a] = new Switch();
            a++;
        }

        entradas = new JCheckBox[gate.getInputSize()];
        int i = 0;
        while (i < gate.getInputSize()) {
            entradas[i] = new JCheckBox();
            entradas[i].setMnemonic(KeyEvent.VK_C);
            entradas[i].setSelected(false);
            if (gate.getInputSize() > 1) {
                if (i == 0) {
                    add(entradas[i], 20, 87, 20, 25);
                } else if (i == 1) {
                    add(entradas[i], 20, 127, 20, 25);
                }
            } else if (gate.getInputSize() == 1) {
                add(entradas[i], 20, 107, 20, 25);
            }
            i++;
        }

        JCheckBox saidaField = new JCheckBox();
        saidaField.setMnemonic(KeyEvent.VK_C);
        saidaField.setSelected(false);

        light = new Light(255, 0, 0);

        String name = gate.toString() + ".png";
        URL url = getClass().getClassLoader().getResource(name);
        image = getToolkit().getImage(url);

        int c = 0;
        while (c < gate.getInputSize()) {
            gate.connect(c, switches[c]);
            c++;
        }

        int d = 0;
        while (d < gate.getInputSize()) {
            entradas[d].addItemListener(this);
            d++;
        }

        addMouseListener(this);

        update();
    }

    private void update() {

        int e = 0;
        while (e < entradas.length) {
            if (entradas[e].isSelected()) {
                switches[e].turnOn();
            } else {
                switches[e].turnOff();
            }
            e++;
        }

        light.connect(0, this.gate);
        repaint();
    }

    @Override
    public void itemStateChanged(ItemEvent itemEvent) {
        update();
    }

    @Override
    public void mouseClicked(MouseEvent event) {
        int x = event.getX();
        int y = event.getY();

        if (Math.pow(x - 217, 2) + Math.pow(y - 120, 2) <= 156.25) {

            // ...então abrimos a janela seletora de cor...
            Color color = JColorChooser.showDialog(this, null, light.getColor());
            light.setColor(color);

            // ...e chamamos repaint para atualizar a tela.
            repaint();
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        g.drawImage(image, 30, 70, 180, 100, this);

        g.setColor(light.getColor());
        g.fillOval(205, 107, 25, 25);

        getToolkit().sync();
    }
}