package com.vspkg;

import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

public class MainWindow extends JFrame {
    private final LibWrapper libWrapper;

    public MainWindow(LibWrapper libWrapper) throws IOException, InterruptedException {
        if (libWrapper.getFile() == null) {
            JOptionPane.showMessageDialog(new JFrame(), "No vcpkg was found on default path, please insert path to it!", "Dialog",
                    JOptionPane.ERROR_MESSAGE);
        }
        this.libWrapper = libWrapper;
        updateViewedList();

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
                        try {
                            updateViewedList();
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        } catch (InterruptedException interruptedException) {
                            interruptedException.printStackTrace();
                        }
                    } else {
                        JOptionPane.showMessageDialog(new JFrame(), "Wrong filename, please make sure what it is vcpkg.exe!", "Dialog",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String result = (String) JOptionPane.showInputDialog(
                        Manager,
                        "Lib selection",
                        "Which library you want to install",
                        JOptionPane.PLAIN_MESSAGE,
                        null, null,
                        null
                );

                if (result != null && result.length() > 0) {
                    label.setText("You selected:" + result);
                    try {
                        JOptionPane.showMessageDialog(
                                new JFrame(),
                                libWrapper.installLib(result),
                                "Dialog",
                                JOptionPane.INFORMATION_MESSAGE);
                        updateViewedList();
                    } catch (IOException | InterruptedException ioException) {
                        ioException.printStackTrace();
                    }
                } else {
                    label.setText("None selected");
                }

            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String result = (String) JOptionPane.showInputDialog
                        (
                                Manager,
                                "Lib selection",
                                "Which library you want to remove",
                                JOptionPane.PLAIN_MESSAGE,
                                null,
                                null,
                                null
                        );
                if (result != null && result.length() > 0) {
                    label.setText("You selected:" + result);
                    try {
                        JOptionPane.showMessageDialog(new JFrame(), libWrapper.removeLib(result), "Dialog",
                                JOptionPane.INFORMATION_MESSAGE);
                        updateViewedList();
                    } catch (IOException | InterruptedException ioException) {
                        ioException.printStackTrace();
                    }
                } else {
                    label.setText("None selected");
                }

            }
        });
        searchbar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vector<String> filelements = new Vector<>();
                assert MainWindow.this.libWrapper != null : "Wrapper was not created!";

                try {
                    MainWindow.this.libWrapper.updateList();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }

                for (int i = 0; i < libWrapper.getList().length(); i++) {
                    JSONObject item = libWrapper.getList().getJSONObject(i);
                    if (item.getString("name").contains(searchbar.getText())) {
                        filelements.add(item.getString("name"));
                    }
                }
                libsLists.setListData(filelements);
            }
        });
        libsLists.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JList theList = (JList) e.getSource();
                int index = theList.locationToIndex(e.getPoint());
                if (index >= 0) {
                    Object o = theList.getModel().getElementAt(index);
                    for (int i = 0; i < libWrapper.getList().length(); i++) {
                        JSONObject item = libWrapper.getList().getJSONObject(i);
                        if (item.getString("name").contains(o.toString())) {
                            String Phrase = "name = " + item.getString("name") + System.lineSeparator()
                                    + "package name = " + item.getString("package_name") + System.lineSeparator()
                                    + "triplet = " + item.getString("triplet") + System.lineSeparator()
                                    + "version = " + item.getString("version") + System.lineSeparator()
                                    + "port_version = " + item.getInt("port_version") + System.lineSeparator()
                                    + "features = " + item.getJSONArray("features") + System.lineSeparator()
                                    + "description = " + item.get("desc")+ System.lineSeparator();
                            description.setText(Phrase);
                        }
                    }
                }
            }
        });
    }

    public void main() {
        JFrame frame = new JFrame("MainWindow");
        frame.setContentPane(this.Manager);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(new Dimension( 640, 640));
        frame.setVisible(true);
    }

    private void updateViewedList() throws IOException, InterruptedException {
        libWrapper.updateList();
        Vector<String> filelements = new Vector<>();
        for (int i = 0; i < libWrapper.getList().length(); i++) {
            JSONObject item = libWrapper.getList().getJSONObject(i);
            filelements.add(item.getString("name"));
        }
        libsLists.setListData(filelements);
    }

    private JPanel Manager;
    private JLabel label;
    private JButton aboutButton;
    private JButton pathButton;

    private JButton deleteButton;
    private JButton addButton;
    private JList<String> libsLists;
    private JTextField searchbar;
    private JTextArea description;


}
