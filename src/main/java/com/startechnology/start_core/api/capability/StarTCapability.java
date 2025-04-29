package com.startechnology.start_core.api.capability;

import com.startechnology.start_core.api.IStarTDreamLinkFrequencyMachine;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;

public class StarTCapability {
        public static final Capability<IStarTDreamLinkFrequencyMachine> CAPABILITY_DREAM_LINK_FREQUENCY_MACHINE = CapabilityManager
            .get(new CapabilityToken<>() {});
}
