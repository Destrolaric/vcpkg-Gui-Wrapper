package com.vspkg.test;

import com.vspkg.OSValidator;
import org.junit.Rule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
public class OsTest {
    @Rule
    private OSValidator osValidator;
    @Test
    @DisplayName("Test Mac OS")
    public void insertMac() {
        //ToDo
    }
    @Test
    @DisplayName("Test Windows")
    public void insertWindows() {
        //ToDo
    }

    @Test
    @DisplayName("Test Linux")
    public void insertLinux()  {
        //ToDo
    }

    @Test
    @DisplayName("Test unsupported OS")
    public void insertPotato(){
        //ToDo
    }
}
