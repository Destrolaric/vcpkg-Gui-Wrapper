package com.vspkg;

import org.json.JSONArray;

import java.io.IOException;

public interface ILibWrapper {
    /*
    We need to assure out list will be void or contain some libs
     */
    public JSONArray getList();

    public String sanitize(String installationName); //clear string from dangerous commands

    public String installLib(String LibName) throws IOException, InterruptedException;

    String execute(ProcessBuilder processBuilder) throws IOException, InterruptedException;

    public String removeLib(String LibName) throws IOException, InterruptedException;

    public String validateInstallation();

    public void updateList() throws IOException, InterruptedException;
}
