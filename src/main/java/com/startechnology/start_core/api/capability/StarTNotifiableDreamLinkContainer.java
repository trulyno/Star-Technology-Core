package com.startechnology.start_core.api.capability;

import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.trait.NotifiableEnergyContainer;
import com.gregtechceu.gtceu.api.machine.trait.NotifiableRecipeHandlerTrait;
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder;
import com.startechnology.start_core.machine.dreamlink.StarTDreamLinkHatchPartMachine;
import com.startechnology.start_core.machine.dreamlink.StarTDreamLinkManager;

import net.minecraft.core.Direction;

public class StarTNotifiableDreamLinkContainer extends NotifiableEnergyContainer {

    public static final ManagedFieldHolder MANAGED_FIELD_HOLDER = new ManagedFieldHolder(
        StarTNotifiableDreamLinkContainer .class, NotifiableRecipeHandlerTrait.MANAGED_FIELD_HOLDER);

    public StarTNotifiableDreamLinkContainer(MetaMachine machine, long maxCapacity, long maxInputVoltage,
            long maxInputAmperage, long maxOutputVoltage, long maxOutputAmperage) {
        super(machine, maxCapacity, maxInputVoltage, maxInputAmperage, maxOutputVoltage, maxOutputAmperage);
    }

    /* Shorthand for reciever version constructor */
    public static StarTNotifiableDreamLinkContainer receiverContainer(MetaMachine machine, long maxCapacity,
                                                             long maxInputVoltage, long maxInputAmperage) {
        return new StarTNotifiableDreamLinkContainer(machine, maxCapacity, maxInputVoltage, maxInputAmperage, 0L, 0L);
    }

    @Override
    public void onMachineLoad() {
        super.onMachineLoad();

        if (getMachine().getLevel().isClientSide)
            return;

        StarTDreamLinkManager.addPartMachine((StarTDreamLinkHatchPartMachine)getMachine());
    }

    @Override
    public void onMachineUnLoad() {
        super.onMachineUnLoad();

        if (getMachine().getLevel().isClientSide)
            return;

        StarTDreamLinkManager.removePartMachine((StarTDreamLinkHatchPartMachine)getMachine());
    }

    /* Disable input from all sides */
    @Override
    public boolean inputsEnergy(Direction side) {
        return false;
    }


}