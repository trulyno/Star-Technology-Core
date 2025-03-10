package com.startechnology.start_core.machine.parallel;

import com.gregtechceu.gtceu.api.capability.IParallelHatch;

public interface IStarTAbsoluteParallelControllerMixin {
    // Allows us to exposve the getAbsoluteParallelHatchStarT method of our controller
    // through the mixin
    public IParallelHatch getAbsoluteParallelHatchStarT();
}
