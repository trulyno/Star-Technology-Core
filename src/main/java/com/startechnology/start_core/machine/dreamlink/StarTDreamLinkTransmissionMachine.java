package com.startechnology.start_core.machine.dreamlink;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.github.davidmoten.rtree.Entry;
import com.github.davidmoten.rtree.geometry.Geometry;
import com.gregtechceu.gtceu.api.capability.IEnergyContainer;
import com.gregtechceu.gtceu.api.capability.recipe.EURecipeCapability;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.machine.ConditionalSubscriptionHandler;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.TickableSubscription;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IMultiPart;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableMultiblockMachine;
import com.gregtechceu.gtceu.api.misc.EnergyContainerList;
import com.startechnology.start_core.api.capability.StarTNotifiableDreamLinkContainer;

import it.unimi.dsi.fastutil.longs.Long2ObjectMaps;
import net.minecraft.core.BlockPos;
import rx.Observable;

public class StarTDreamLinkTransmissionMachine extends WorkableMultiblockMachine {

    private EnergyContainerList inputHatches;
    protected ConditionalSubscriptionHandler tickSubscription;
    protected TickableSubscription tryTickSub;

    private Observable<Entry<StarTDreamLinkHatchPartMachine, Geometry>> recieverCache;
    private boolean isReadyToTransmit;

    public StarTDreamLinkTransmissionMachine(IMachineBlockEntity holder) {
        super(holder);
        this.tickSubscription = new ConditionalSubscriptionHandler(this, this::transferEnergyTick, this::isFormed);
        this.isReadyToTransmit = false;
    }

    @Override
    public void onStructureFormed() {
        super.onStructureFormed();

        List<IEnergyContainer> inputs = new ArrayList<>();
        Map<Long, IO> ioMap = getMultiblockState().getMatchContext().getOrCreate("ioMap", Long2ObjectMaps::emptyMap);
        
        // Add update subscription to EUCap trait
        // TODO: Changed in gt 1.7
        for (IMultiPart part : getParts()) {
            IO io = ioMap.getOrDefault(part.self().getPos().asLong(), IO.IN);
            if (io == IO.NONE) continue;

            for (var handler : part.getRecipeHandlers()) {
                var handlerIO = handler.getHandlerIO();
                // If IO not compatible
                if (io != IO.IN && handlerIO != IO.IN && io != handlerIO) continue;
                if (handler.getCapability() == EURecipeCapability.CAP &&
                        handler instanceof IEnergyContainer container) {
                    
                    inputs.add(container);
                    traitSubscriptions.add(handler.addChangedListener(tickSubscription::updateSubscription));
                }
            }
        }

        this.inputHatches = new EnergyContainerList(inputs);
        this.isReadyToTransmit = true;
    }

    @Override
    public void onLoad() {
        super.onLoad();

        if (getLevel().isClientSide)
            return;

        tryTickSub = subscribeServerTick(tryTickSub, this::tryTransferEnergy);
    }

    @Override
    public void onUnload() {
        super.onUnload();
    
        if (getLevel().isClientSide)
            return;

        if (tryTickSub != null) {
            tryTickSub.unsubscribe();
            tryTickSub = null;

            tickSubscription.unsubscribe();
            tickSubscription = null;
        }
    }

    protected void tryTransferEnergy() {
        // Lower frequency every second try transfer.
        // This is so that if we have all our buffers full
        // we can still transfer out/attempt after etc.
        if (getOffsetTimer() % 20 == 0 && this.isReadyToTransmit) {
            updateTransferCache();
            transferEnergyTick();
        }
    }

    private void updateTransferCache() {
        if (getLevel().isClientSide)
            return;
       
        if (this.isReadyToTransmit && isWorkingEnabled()) {
            // Centre of this transmission machine
            BlockPos centre = getPos();

            int x = centre.getX();
            int z = centre.getZ();

            // Get dream-link hatches
            var machines = StarTDreamLinkManager.getPartMachines(x + 5, z + 5, x - 5, z - 5);
            recieverCache = machines;
        }
    }

    protected void transferEnergyTick() {
        // Here we will transfer energy while we are formed.
        if (getLevel().isClientSide)
            return;

        if (this.isReadyToTransmit && isWorkingEnabled()) {
            if (recieverCache == null) {
                updateTransferCache();
            }

            recieverCache.forEach((entry) -> {
                long energyStored = inputHatches.getEnergyStored();

                StarTDreamLinkHatchPartMachine hatch = entry.value();
                
                // What is a law of demeter? ;p
                StarTNotifiableDreamLinkContainer container = hatch.container;
                
                long hatchEnergyChange = container.changeEnergy(Math.min(energyStored, container.getInputVoltage() * container.getInputAmperage()));
                
                inputHatches.removeEnergy(hatchEnergyChange);
            });
        }
    }
    
}
