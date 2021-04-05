package com.vspkg;

import java.io.File;
import java.io.IOException;

public class PowershellLibWrapper extends LibWrapper {
    /*
    Windows based implementation of Wrapper
    Based on powershell commandline
     */
    public PowershellLibWrapper() throws IOException, InterruptedException {
        String default_con = System.getProperty("user.home") + File.separator +"vcpkg" + File.separator + "vcpkg.exe";
        if (new File(default_con).exists() && validateInstallation(default_con)) {
            file = new File(default_con);
        }
    }

    @Override
    public String installLib(String installationName) throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("powershell.exe", sanitize(file.getAbsolutePath()), "install", sanitize(installationName));
        return execute(processBuilder);
    }


    @Override
    public String removeLib(String LibName) throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("powershell.exe", sanitize(file.getAbsolutePath()), "remove", sanitize(LibName), "--recurse");
        return execute(processBuilder);
    }

    public boolean validateInstallation(String name) throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("powershell.exe", sanitize(name), "version");
        return (execute(processBuilder).contains("Vcpkg package management program version"));
    }

    @Override
    public void updateList() throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("powershell.exe", sanitize(file.getAbsolutePath()), "list", "--x-json");
        libs = com.vspkg.JsonParser.parseJson("["+execute(processBuilder)+"]");
    }
}
