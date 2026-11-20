package ui;

import model.Usuario;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.Image;
import java.text.Normalizer;
import java.util.ArrayList;

public class RegistroForm extends JFrame {
    private JPanel panelPrincipal;
    private JComboBox<String> cmbTipoId;
    private JTextField txtNumeroId;
    private JTextField txtPrimerNombre;
    private JTextField txtSegundoNombre;
    private JTextField txtPrimerApellido;
    private JTextField txtSegundoApellido;
    private JLabel lblUsername;
    private JPasswordField txtPassword;
    private JTextField txtTelefono;
    private JTextField txtCorreo;
    private JButton btnCargarFoto;
    private JLabel lblFoto;
    private JButton btnGuardar;

    private String rutaFoto = "";

    public RegistroForm(ArrayList<Usuario> usuarios) {

        setContentPane(panelPrincipal);
        setTitle("BookUsers - Registro de Usuario");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(520, 620);
        setLocationRelativeTo(null);
        setVisible(true);

        llenarCombo();

        DocumentListener vigilante = new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { actualizarUsername(); }
            public void removeUpdate(DocumentEvent e) { actualizarUsername(); }
            public void changedUpdate(DocumentEvent e) { actualizarUsername(); }
        };

        txtPrimerNombre.getDocument().addDocumentListener(vigilante);
        txtPrimerApellido.getDocument().addDocumentListener(vigilante);
        txtSegundoApellido.getDocument().addDocumentListener(vigilante);

        btnCargarFoto.addActionListener(e -> {
            JFileChooser selector = new JFileChooser();
            selector.setFileFilter(new FileNameExtensionFilter("Imágenes", "png", "jpg", "jpeg"));
            if (selector.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                rutaFoto = selector.getSelectedFile().getAbsolutePath();
                ImageIcon icono = new ImageIcon(rutaFoto);
                lblFoto.setIcon(new ImageIcon(icono.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
            }
        });

        btnGuardar.addActionListener(e -> {
            String tipo = (String) cmbTipoId.getSelectedItem();
            String numero = txtNumeroId.getText().trim();
            String nombre1 = txtPrimerNombre.getText().trim();
            String nombre2 = txtSegundoNombre.getText().trim();
            String apellido1 = txtPrimerApellido.getText().trim();
            String apellido2 = txtSegundoApellido.getText().trim();
            String password = new String(txtPassword.getPassword());
            String telefono = txtTelefono.getText().trim();
            String correo = txtCorreo.getText().trim();

            if (numero.isEmpty() || nombre1.isEmpty() || apellido1.isEmpty()
                    || password.isEmpty() || telefono.isEmpty() || correo.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor complete los campos obligatorios.");
                return;
            }

            boolean aceptaLetras = tipo != null && (tipo.startsWith("41") || tipo.startsWith("42"));
            if (!aceptaLetras && !soloDigitos(numero)) {
                JOptionPane.showMessageDialog(this, "El número de identificación solo debe contener dígitos.");
                return;
            }

            if (!correo.contains("@")) {
                JOptionPane.showMessageDialog(this, "El correo electrónico debe contener '@'.");
                return;
            }

            Usuario u = new Usuario(tipo, numero, nombre1, nombre2, apellido1, apellido2,
                    lblUsername.getText(), password, telefono, correo, rutaFoto);
            usuarios.add(u);

            JOptionPane.showMessageDialog(this, "¡Usuario registrado con éxito! Username: " + u.getUsername());
            dispose();
        });
    }

    private void llenarCombo() {
        cmbTipoId.addItem("11 - Registro civil de nacimiento");
        cmbTipoId.addItem("12 - Tarjeta de identidad");
        cmbTipoId.addItem("13 - Cédula de ciudadanía");
        cmbTipoId.addItem("21 - Tarjeta de extranjería");
        cmbTipoId.addItem("22 - Cédula de extranjería");
        cmbTipoId.addItem("41 - Pasaporte");
        cmbTipoId.addItem("42 - Documento de identificación extranjero");
        cmbTipoId.addItem("47 - Permiso Especial de Permanencia (PEP)");
        cmbTipoId.addItem("91 - NUIP");
    }

    private String limpiarTexto(String texto) {
        String t = Normalizer.normalize(texto.trim(), Normalizer.Form.NFD);
        return t.replaceAll("\\p{M}", "").toLowerCase().replace(" ", "");
    }

    private void actualizarUsername() {
        String ap1 = limpiarTexto(txtPrimerApellido.getText());
        String ap2 = limpiarTexto(txtSegundoApellido.getText());
        String nom = limpiarTexto(txtPrimerNombre.getText());
        StringBuilder user = new StringBuilder();
        if (!ap1.isEmpty()) user.append(ap1.charAt(0));
        if (!ap2.isEmpty()) user.append(ap2.charAt(0));
        user.append(nom);
        lblUsername.setText(user.toString());
    }

    private boolean soloDigitos(String texto) {
        for (int i = 0; i < texto.length(); i++) {
            if (!Character.isDigit(texto.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
