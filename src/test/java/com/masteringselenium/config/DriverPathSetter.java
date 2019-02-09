package com.masteringselenium.config;

public interface DriverPathSetter {
    void set(DriverType driverType, String driverName, String directoryName, String executableName);
}
