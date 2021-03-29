package com.vspkg;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

public class MainWindow extends JFrame {
    private final LibWrapper libWrapper;
    public MainWindow(LibWrapper libWrapper) {
        if (libWrapper.getFile() == null){
            JOptionPane.showMessageDialog(new JFrame(), "No vcpkg was found on default path, please insert path to it!", "Dialog",
                    JOptionPane.ERROR_MESSAGE);
        }
        this.libWrapper = libWrapper;
        aboutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Info");
                JOptionPane.showMessageDialog(frame, "Made By Georgi Gvinepadze as internship application task");
            }
        });
        pathButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser();
                int result = fc.showOpenDialog(Manager);

                if (result == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    if (file.getName().contains("vcpkg.exe")) {
                        //This is where a real application would open the file.
                        MainWindow.this.libWrapper.setFile(file);
                    }
                    else{
                        JOptionPane.showMessageDialog(new JFrame(), "Wrong filename, please make sure what it is vcpkg.exe!", "Dialog",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        findButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String result = (String) JOptionPane.showInputDialog(Manager, "Lib selection", "Which library you want to install", JOptionPane.PLAIN_MESSAGE, null, null, "Dead");
                if(result != null && result.length() > 0){
                    label.setText("You selected:" + result);
                }else {
                    label.setText("None selected");
                }
                try {
                    libWrapper.installLib(result);
                } catch (IOException | InterruptedException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        searchbar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vector<String> filelements = new Vector<>();
                assert MainWindow.this.libWrapper != null : "Wrapper was not created!";

                MainWindow.this.libWrapper.updateList();
                for (String item : MainWindow.this.libWrapper.getList()){
                    if (item.contains(searchbar.getText())){
                        filelements.add(item);
                    }
                }
                libsLists.setListData(filelements);
            }
        });
    }

    public void main() {
        JFrame frame = new JFrame("MainWindow");
        frame.setContentPane(this.Manager);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private JPanel Manager;
    private JLabel label;
    private JButton aboutButton;
    private JButton pathButton;
    private JButton findButton;
    private JButton deleteButton;
    private JButton addButton;
    private JList<String> libsLists;
    private JTextField searchbar;
    private JTextArea description;


}
