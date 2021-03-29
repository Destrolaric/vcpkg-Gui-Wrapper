package com.vspkg;

import javax.swing.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            LibWrapper libWrapper = WrapperFactory.createWrapper();
            MainWindow mainWindow = new MainWindow(libWrapper);
            mainWindow.main();
        } catch (UnsupportedOperationException ex) {
            JOptionPane.showMessageDialog(new JFrame(), ex.getMessage(), "Dialog",
                    JOptionPane.ERROR_MESSAGE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
