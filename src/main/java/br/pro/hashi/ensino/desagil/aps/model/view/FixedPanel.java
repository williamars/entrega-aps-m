package br.pro.hashi.ensino.desagil.aps.model.view;

import javax.swing.*;
import java.awt.*;

// Componente criada especialmente para o projeto, que é
// basicamente um JPanel de tamanho fixo. Fixar tamanho
// geralmente não é considerada uma boa prática de design
// de interface, mas fazemos isso aqui por simplicidade.
public class FixedPanel extends JPanel {

    // O construtor recebe a largura e a altura do painel.
    protected FixedPanel(int width, int height) {

        // É muito importante estabelecer que esse painel
        // não tem layout, pois caso contrário seu tamanho
        // vai ser definido pelo tamanho de seu conteúdo.
        setLayout(null);

        // Usamos esse método nos Desafios, vocês lembram?
        setPreferredSize(new Dimension(width, height));
    }

}
