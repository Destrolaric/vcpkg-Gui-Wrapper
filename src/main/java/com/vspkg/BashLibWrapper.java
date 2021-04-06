package com.vspkg;

import java.io.File;
import java.io.IOException;

public class BashLibWrapper extends LibWrapper {
    /*
    Linux based implementation of Wrapper
    Based on powershell commandline
     */
    public BashLibWrapper() throws IOException, InterruptedException {
        String default_con = File.separator +"vcpkg" + File.separator + "vcpkg";
        if (new File(default_con).exists() && validateInstallation(default_con)) {
            file = new File(default_con);
        }
    }

    @Override
    public String installLib(String installationName) throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("bash", sanitize(file.getAbsolutePath()), "install", sanitize(installationName));
        return execute(processBuilder);
    }


    @Override
    public String removeLib(String LibName) throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("bash", sanitize(file.getAbsolutePath()), "remove", sanitize(LibName), "--recurse");
        return execute(processBuilder);
    }

    public boolean validateInstallation(String name) throws IOException, InterruptedException {
        if (!(new File(name).canExecute())){
            return false;
        }
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("bash", sanitize(name), "version");
        return (execute(processBuilder).contains("Vcpkg package management program version"));
    }

    @Override
    public void updateList() throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("bash", sanitize(file.getAbsolutePath()), "list", "--x-json");
        libs = com.vspkg.JsonParser.parseJson("["+execute(processBuilder)+"]");
    }
}
