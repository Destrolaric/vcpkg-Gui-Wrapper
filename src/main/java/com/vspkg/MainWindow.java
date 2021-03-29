package com.vspkg;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Vector;

public class MainWindow extends JFrame {
    private final LibWrapper libWrapper;

    public MainWindow(LibWrapper libWrapper) {
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
                        MainWindow.this.libWrapper.setPath(file);
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
                Vector<String> filteredelements = new Vector<>();
                assert MainWindow.this.libWrapper != null : "Wrapper was not created!";

                MainWindow.this.libWrapper.updateList();
                for (String item : MainWindow.this.libWrapper.getList()){
                    if (item.contains(searchbar.getText())){
                        filteredelements.add(item);
                    }
                }
                libsLists.setListData(filteredelements);
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
    private JList libsLists;
    private JTextField searchbar;
    private JTextArea description;


}
