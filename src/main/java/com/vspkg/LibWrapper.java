package com.vspkg;

import java.io.File;
import java.util.Vector;

public abstract class LibWrapper implements ILibWrapper {
    private File Path ;
    private Vector<String> libs;
    private String connection;
    public Vector<String> getList() {
        return libs;
    }

    public File getPath() {
        return Path;
    }

    public void setPath(File path) {
        Path = path;
    }
}
