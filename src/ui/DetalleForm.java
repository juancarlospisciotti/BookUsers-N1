package ui;

import model.Usuario;
import com.sha2cs.generatepdf.DocumentoPDF;
import javax.swing.*;
import java.awt.Desktop;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

public class DetalleForm extends JFrame {
    private JPanel panelPrincipal;
    private JLabel lblFoto;
    private JTextArea txtInfo;
    private JButton btnGenerarPdf;
    private final Usuario usuario;

    public DetalleForm(Usuario usuario) {
        this.usuario = usuario;
        setContentPane(panelPrincipal);
        setTitle("BookUsers - Detalle");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(420, 340);
        setLocationRelativeTo(null);
        setVisible(true);

        if (usuario.getRutaFoto() != null && !usuario.getRutaFoto().isEmpty()) {
            ImageIcon icono = new ImageIcon(usuario.getRutaFoto());
            lblFoto.setText("");
            lblFoto.setIcon(new ImageIcon(icono.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
        }

        txtInfo.setEditable(false);
        txtInfo.setText("ID: " + usuario.getId() + "\n" +
                "Tipo ID: " + usuario.getTipoIdentificacion() + "\n" +
                "Número: " + usuario.getNumeroIdentificacion() + "\n" +
                "Nombre: " + usuario.getPrimerNombre() + " " + usuario.getSegundoNombre() + "\n" +
                "Apellidos: " + usuario.getPrimerApellido() + " " + usuario.getSegundoApellido() + "\n" +
                "Username: " + usuario.getUsername() + "\n" +
                "Teléfono: " + usuario.getTelefono() + "\n" +
                "Correo: " + usuario.getCorreo());

        btnGenerarPdf.addActionListener(e -> generarPdf());
    }

    private void generarPdf() {
        try {
            DocumentoPDF doc = new DocumentoPDF("Ficha de Usuario");
            if (usuario.getRutaFoto() != null && new File(usuario.getRutaFoto()).isFile()) {
                doc.imagen(usuario.getRutaFoto());
            }
            doc.campo("Id", usuario.getId());
            doc.campo("Tipo de identificación", usuario.getTipoIdentificacion());
            doc.campo("Número de identificación", usuario.getNumeroIdentificacion());
            doc.campo("Primer nombre", usuario.getPrimerNombre());
            doc.campo("Segundo nombre", usuario.getSegundoNombre());
            doc.campo("Primer apellido", usuario.getPrimerApellido());
            doc.campo("Segundo apellido", usuario.getSegundoApellido());
            doc.campo("Username", usuario.getUsername());
            doc.campo("Teléfono", usuario.getTelefono());
            doc.campo("Correo", usuario.getCorreo());

            File pdf = doc.guardar("usuario-" + usuario.getId() + ".pdf");
            JOptionPane.showMessageDialog(this, "PDF generado con éxito en:\n" + pdf.getAbsolutePath());
            Desktop.getDesktop().open(pdf);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error al generar PDF: " + ex.getMessage());
        }
    }
}
