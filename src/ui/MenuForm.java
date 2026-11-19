package ui;

import model.Usuario;
import javax.swing.*;
import java.util.ArrayList;

public class MenuForm extends JFrame {
    private JPanel panelPrincipal;
    private JButton btnRegistrar;
    private JButton btnListado;
    private JButton btnCreditos;

    private final ArrayList<Usuario> usuarios = new ArrayList<>();

    public MenuForm() {
        setContentPane(panelPrincipal);
        setTitle("BookUsers - Menú Principal");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setVisible(true);

        btnRegistrar.addActionListener(e -> new RegistroForm(usuarios));
        btnListado.addActionListener(e -> new ListadoForm(usuarios));
        btnCreditos.addActionListener(e -> new CreditosForm());
    }
}
