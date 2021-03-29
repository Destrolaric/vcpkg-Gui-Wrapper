package com.vspkg;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        try {
            LibWrapper libWrapper = WrapperFactory.createWrapper();
            MainWindow mainWindow = new MainWindow(libWrapper);
            mainWindow.main();
        } catch (UnsupportedOperationException ex) {
            JOptionPane.showMessageDialog(new JFrame(), ex.getMessage(), "Dialog",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

}
