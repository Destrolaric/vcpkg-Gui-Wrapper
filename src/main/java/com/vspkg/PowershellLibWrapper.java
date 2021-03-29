package com.vspkg;

import java.io.File;
import java.io.IOException;

public class PowershellLibWrapper extends LibWrapper {
    private final String default_con = System.getProperty("user.home") + "\\vcpkg\\vcpkg.exe";

    public PowershellLibWrapper() {
        if (new File(default_con).exists()) {
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
    public Boolean removeLib(String LibName) {
        return null;
    }

    public String validateInstallation() {
        return null;
    }

    @Override
    public void updateList() {

    }
}
