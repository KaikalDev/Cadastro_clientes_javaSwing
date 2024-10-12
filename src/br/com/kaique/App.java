package br.com.kaique;

import br.com.kaique.cadastroSwing.gui;

/**
 * @author Kaique
 */
public class App {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                gui.main(args);
            }
        });
    }
}
