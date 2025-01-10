package com.startechnology.start_core.machine.bacteria;

import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;

public class BacterialVatMachine extends WorkableElectricMultiblockMachine {
    public BacterialVatMachine(IMachineBlockEntity holder, Object... args) {
        super(holder, args);
    }

    @Override
    public void afterWorking() {
        super.afterWorking();
        getRecipeLogic().markLastRecipeDirty();
    }
}
