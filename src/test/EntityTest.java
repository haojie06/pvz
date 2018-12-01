package test;

import views.Canvas;

import javax.swing.*;

public class EntityTest {
    public static void main(String[] args) {
        Canvas canvas = new Canvas();
        JFrame jf = new JFrame();
        jf.setSize(1400, 640);
        canvas.setSize(1400, 600);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.add(canvas);
        jf.setVisible(true);

    }
}
