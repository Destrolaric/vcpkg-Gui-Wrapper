package com.vspkg;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;

public abstract class LibWrapper implements ILibWrapper {
    private String default_con = "test";
    File file;
    private Vector<String> libs;
    private String connection;

    public Vector<String> getList() {
        return libs;
    }

    @Override
    public String sanitize(String installationName) {
        return "\"" + installationName + "\"";
    }

    @Override
    public String execute(ProcessBuilder processBuilder) throws IOException, InterruptedException {
        Process process = processBuilder.start();

        StringBuilder output = new StringBuilder();
        StringBuilder error = new StringBuilder();
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream()));
        BufferedReader stderrReader = new BufferedReader(
                new InputStreamReader(process.getErrorStream()));
        String line;
        while ((line = stderrReader.readLine()) != null) {
            error.append(line).append(System.lineSeparator());
        }
        while ((line = reader.readLine()) != null) {
            output.append(line).append(System.lineSeparator());
        }

        int exitVal = process.waitFor();
        if (exitVal == 0) {
            return output.toString();
        } else {
            JOptionPane.showMessageDialog(new JFrame(), output, "Dialog",
                    JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public LibWrapper() {
        if (new File(default_con).exists()) {
            file = new File(default_con);
        }
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
