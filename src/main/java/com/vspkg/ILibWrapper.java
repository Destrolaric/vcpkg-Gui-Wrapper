package com.vspkg;

import java.util.Vector;

public interface ILibWrapper {
    /*
    We need to assure out list will be void or contain some libs
     */
    public Vector<String> getList();

    public String sanitize(String installationName); //clear string from dangerous commands

    public String installLib(String LibName);

    public Boolean removeLib(String LibName);

    public String validateInstallation();

    public void updateList();
}
