package ui;

import model.Usuario;
import javax.swing.*;
import java.util.ArrayList;

public class ListadoForm extends JFrame {
    private JPanel panelPrincipal;
    private JList<Usuario> lstUsuarios;
    private JButton btnVerDetalle;

    private final DefaultListModel<Usuario> modelo = new DefaultListModel<>();

    public ListadoForm(ArrayList<Usuario> usuarios) {
        setContentPane(panelPrincipal);
        setTitle("BookUsers - Listado");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setVisible(true);

        for (Usuario u : usuarios) {
            modelo.addElement(u);
        }
        lstUsuarios.setModel(modelo);

        btnVerDetalle.addActionListener(e -> {
            int sel = lstUsuarios.getSelectedIndex();
            if (sel == -1) {
                JOptionPane.showMessageDialog(this, "Seleccione un usuario de la lista.");
                return;
            }
            new DetalleForm(modelo.getElementAt(sel));
        });
    }
}
