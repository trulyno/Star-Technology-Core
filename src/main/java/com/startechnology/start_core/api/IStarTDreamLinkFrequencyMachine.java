package com.startechnology.start_core.api;

public interface IStarTDreamLinkFrequencyMachine {
    String DEFAULT_FREQ = "Untitled Frequency";

    /* Set the dream link frequency of this machine */
    public void setFrequency(String frequency);

    /* Get the frequency of this machine */
    public String getFrequency();
}
