package ui;

import javax.swing.*;

public class CreditosForm extends JFrame {
    private JPanel panelPrincipal;
    private JTextArea txtCreditos;

    public CreditosForm() {
        setContentPane(panelPrincipal);
        setTitle("BookUsers - Créditos");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(null);
        setVisible(true);

        txtCreditos.setEditable(false);
        txtCreditos.setText("Versión alternativa sin tema gráfico.\n\n" +
                "Integrantes:\n" +
                "- juan carlos Pisciotti (UNP-UMAB-9FUD)\n" +
                "- juan sebastian Quintero (UNP-JFNY-XXP3)");
    }
}

