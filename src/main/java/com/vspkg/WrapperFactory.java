package com.vspkg;

import org.jetbrains.annotations.NotNull;

public class WrapperFactory {
    @NotNull
    public static LibWrapper createWrapper() throws UnsupportedOperationException{
        return  OSValidator.validate() ? new PowershellLibWrapper() : new BashILibWrapper();
    }
}
