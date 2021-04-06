package com.vspkg;

import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

public class MainWindow extends JFrame {
    private final LibWrapper libWrapper;

    public MainWindow(LibWrapper libWrapper) throws IOException, InterruptedException {
        if (libWrapper.getFile() == null) {
            JOptionPane.showMessageDialog(new JFrame(), "No vcpkg was found on default path! \n" +
                            "Please enter your vcpkg path.", "Dialog",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        this.libWrapper = libWrapper;
        updateViewedList();

        aboutButton.addActionListener(e -> {
            JFrame frame = new JFrame("Info");
            JOptionPane.showMessageDialog(frame, "Made By Georgi Gvinepadze as internship application task");
        });
        pathButton.addActionListener(e -> {
            JFileChooser fc = new JFileChooser();
            int result = fc.showOpenDialog(Manager);

            if (result == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                try {
                    if (file.getName().contains("vcpkg.exe") && libWrapper.validateInstallation(file.getAbsolutePath())) {
                        MainWindow.this.libWrapper.setFile(file);
                        try {
                            updateViewedList();
                        } catch (IOException | InterruptedException ioException) {
                            ioException.printStackTrace();
                        }
                    } else {
                        JOptionPane.showMessageDialog(new JFrame(), "Wrong file, please make sure what it is vcpkg.exe!", "Dialog",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IOException | InterruptedException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        addButton.addActionListener(e -> {
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
                    String output = libWrapper.installLib(result);
                    if (output != null) {
                        JOptionPane.showMessageDialog(
                                new JFrame(),
                                output,
                                "Dialog",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                    updateViewedList();
                } catch (IOException | InterruptedException ioException) {
                    ioException.printStackTrace();
                }
            } else {
                label.setText("None selected");
            }

        });
        deleteButton.addActionListener(e -> {
            String result = libsLists.getSelectedValue();
            int confirmation = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete" + result, "Delete library", JOptionPane.OK_CANCEL_OPTION);
            if (result != null && result.length() > 0 && confirmation == JOptionPane.OK_OPTION) {
                label.setText("You selected:" + result);
                try {
                    String output = libWrapper.removeLib(result);
                    if (output != null) {
                        JOptionPane.showMessageDialog(new JFrame(), output, "Dialog",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                    updateViewedList();
                } catch (IOException | InterruptedException ioException) {
                    ioException.printStackTrace();
                }
            } else {
                label.setText("None selected");
            }

        });
        searchbar.addActionListener(e -> {
            Vector<String> elements = new Vector<>();

            try {
                MainWindow.this.libWrapper.updateList();
            } catch (IOException | InterruptedException ioException) {
                ioException.printStackTrace();
            }

            for (int i = 0; i < libWrapper.getList().length(); i++) {
                JSONObject item = libWrapper.getList().getJSONObject(i);
                if (item.getString("name").contains(searchbar.getText())) {
                    elements.add(item.getString("name"));
                }
            }
            libsLists.setListData(elements);
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
                                    + "description = " + item.get("desc") + System.lineSeparator();
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
        frame.setSize(new Dimension(640, 640));
        frame.setVisible(true);
    }

    private void updateViewedList() throws IOException, InterruptedException {
        libWrapper.updateList();
        Vector<String> elements = new Vector<>();
        for (int i = 0; i < libWrapper.getList().length(); i++) {
            JSONObject item = libWrapper.getList().getJSONObject(i);
            elements.add(item.getString("name"));
        }
        libsLists.setListData(elements);
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
