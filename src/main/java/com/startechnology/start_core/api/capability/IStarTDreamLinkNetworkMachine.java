package com.startechnology.start_core.api.capability;

public interface IStarTDreamLinkNetworkMachine {
    String DEFAULT_NETWORK = "Untitled Network";

    /* Set the dream link network of this machine */
    public void setNetwork(String network);

    /* Get the network of this machine */
    public String getNetwork();
}
