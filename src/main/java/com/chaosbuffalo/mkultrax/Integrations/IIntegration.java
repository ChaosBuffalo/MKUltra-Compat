package com.chaosbuffalo.mkultrax.Integrations;

/**
 * Created by Jacob on 7/21/2018.
 */
public interface IIntegration {

    boolean needsRun();

    void setup();
}
