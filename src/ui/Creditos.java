package ui;

import javax.swing.*;

public class Creditos extends JFrame {
    private JPanel panelPrincipal;
    private JTextArea txtCreditos;
    private JTextArea Creditos;
    private JPanel panel1;

    public Creditos() {
        setContentPane(panelPrincipal);
        setTitle("BookUsers - Créditos");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(null);
        setVisible(true);

        txtCreditos.setEditable(false);
        txtCreditos.setText("Versión alternativa sin tema gráfico.\n\n" +
                "Integrantes:\n" +
                "- juan carlos Pisciotti (aca ponen su carnet)\n" +
                "- pirulin  (carnet)");
    }
}

