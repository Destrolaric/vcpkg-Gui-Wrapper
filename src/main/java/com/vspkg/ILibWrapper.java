package com.vspkg;

import org.json.JSONArray;

import java.io.IOException;

public interface ILibWrapper {
    /*
    We need to assure out list will be void or contain some libs
     */
    JSONArray getList();

    /*
    Remove the ability to execute multiple commands to prevent commandline injection
     */
    String sanitize(String installationName); //clear string from dangerous commands

    /*

     */
    String installLib(String LibName) throws IOException, InterruptedException;

    /*
    Execute process based on current file path, return result in form of String
     */
    String execute(ProcessBuilder processBuilder) throws IOException, InterruptedException;

    String removeLib(String LibName) throws IOException, InterruptedException;

    /*
    We need to make sure what user selected vcpkg and not selected some other executable
     */
    boolean validateInstallation(String name) throws IOException, InterruptedException;

    void updateList() throws IOException, InterruptedException;
}
