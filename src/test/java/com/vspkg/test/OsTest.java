package com.vspkg.test;

import com.vspkg.OSValidator;
import org.junit.Rule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
@RunWith(PowerMockRunner.class)
@PrepareForTest(OSValidator.class)
public class OsTest {
    @Rule
    private OSValidator osValidator;
    @Test
    @DisplayName("Test Mac OS")
    public void insertMac() {
//        PowerMockito.mockStatic(System.class);
//        PowerMockito.when(System.getProperty("os.name")).thenReturn("mac");
//        Assert.assertEquals(false , OSValidator.validate());
    }
    @Test
    @DisplayName("Test Windows")
    public void insertWindows() {
//
//        PowerMockito.when(System.getProperty("os.name")).thenReturn("windows");
//        Assert.assertEquals(true ,OSValidator.validate());
    }

    @Test
    @DisplayName("Test Linux")
    public void insertLinux()  {
//        PowerMockito.mockStatic(System.class);
//        PowerMockito.when(System.getProperty("os.name")).thenReturn("linux");
//        Assert.assertEquals(false ,OSValidator.validate());
    }

    @Test
    @DisplayName("Test unsupported OS")
    public void insertPotato(){
//        PowerMockito.mockStatic(System.class);
//        PowerMockito.when(System.getProperty("os.name")).thenReturn("toster");
//        Assert.assertThrows(UnsupportedOperationException.class , OSValidator::validate);
    }
}
