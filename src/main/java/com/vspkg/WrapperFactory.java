package com.vspkg;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class WrapperFactory {
    @NotNull
    public static LibWrapper createWrapper() throws UnsupportedOperationException, IOException, InterruptedException {
        return  OSValidator.validate() ? new PowershellLibWrapper() : new BashLibWrapper();
    }
}
