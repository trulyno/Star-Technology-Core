package com.startechnology.start_core.machine.parallel;

import com.gregtechceu.gtceu.api.capability.IParallelHatch;

public interface IStarTPerfectParallelControllerMixin {
    // Allows us to exposve the getPerfectParallelHatchStarT method of our controller
    // through the mixin
    public IParallelHatch getPerfectParallelHatchStarT();
}
