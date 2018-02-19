package com.example.dimitris.falldetector;

/**
 * Defines several constants used between {@link AccelerometerService} and the UI.
 */
public interface Constants {

    // Message types sent from the Accelerometer Handler
    public static final int MESSAGE_CHANGED = 1;
    public static final int MESSAGE_EMERGENCY = 2;
    public static final int MESSAGE_TOAST = 3;

    // Key names received from the Accelerometer Handler
    public static final String VALUE = "value";
    public static final String TOAST = "toast";

}
