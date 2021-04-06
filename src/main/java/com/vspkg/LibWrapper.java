package com.vspkg;

import org.json.JSONArray;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public abstract class LibWrapper implements ILibWrapper {
    File file;
    JSONArray libs;
    public JSONArray getList() {
        return libs;
    }
    JsonParser jsonParser = new JsonParser();

    @Override
    public String sanitize(String installationName) {
        return "\"" + installationName + "\"";
    }

    @Override
    public String execute(ProcessBuilder processBuilder) throws IOException, InterruptedException {
        Process process = processBuilder.start();

        String line;

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream()));
        BufferedReader stderrReader = new BufferedReader(
                new InputStreamReader(process.getErrorStream()));

        StringBuilder error = new StringBuilder();
        while ((line = stderrReader.readLine()) != null) {
            error.append(line).append(System.lineSeparator());
        }
        StringBuilder output = new StringBuilder();
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
        String default_con = "test";
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
