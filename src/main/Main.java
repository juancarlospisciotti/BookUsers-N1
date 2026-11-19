package main;

import ui.MenuForm;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(MenuForm::new);
    }
}
