package com.startechnology.start_core.mixin;

import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.gregtechceu.gtceu.api.capability.IParallelHatch;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IMultiPart;
import com.gregtechceu.gtceu.api.machine.multiblock.MultiblockControllerMachine;
import com.startechnology.start_core.machine.parallel.IStarTPerfectParallelControllerMixin;
import com.startechnology.start_core.machine.parallel.IStarTPerfectParallelHatch;

import dev.architectury.patchedmixin.staticmixin.spongepowered.asm.mixin.Shadow;

@Mixin(value=MultiblockControllerMachine.class, remap=false)
public class PerfectParallelControllerMixin implements IStarTPerfectParallelControllerMixin {
    // We could search through all parts on the recipe modifier in the controller looking for the perfected parallel hatch.
    // But without some sort of caching it would be quite the slow down as we would search every part.
    // Thus i think a mixin to store the perfected parallel hatch such as found for the parallel hatch is the most logical as we follow
    // a similar logic to the base GT mod.
    @Unique
    private IParallelHatch perfectParallelHatchStarT = null;

    @Unique
    public IParallelHatch getPerfectParallelHatchStarT() {
        return perfectParallelHatchStarT;
    }

    @Shadow
    private List<IMultiPart> parts;

    @Shadow
    private IParallelHatch parallelHatch;

    @Inject(method = "onStructureFormed", at = @At(value = "TAIL"), remap = false)
    private void injectOnStructuredForm(CallbackInfo info) {

        MultiblockControllerMachine controller = ((MultiblockControllerMachine) (Object) this);
        parts.sort(controller.getDefinition().getPartSorter());

        for (var part : parts) {
            if (part instanceof IStarTPerfectParallelHatch && part instanceof IParallelHatch ppHatch) {
                perfectParallelHatchStarT = ppHatch;
                parallelHatch = ppHatch;
            }
        }
    }
}
