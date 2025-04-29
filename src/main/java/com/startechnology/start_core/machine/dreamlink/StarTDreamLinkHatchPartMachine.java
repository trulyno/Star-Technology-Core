package com.startechnology.start_core.machine.dreamlink;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.gui.fancy.IFancyTooltip;
import com.gregtechceu.gtceu.api.gui.fancy.TooltipsPanel;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IMultiController;
import com.gregtechceu.gtceu.api.machine.multiblock.part.TieredIOPartMachine;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder;
import com.startechnology.start_core.api.IStarTDreamLinkFrequencyMachine;
import com.startechnology.start_core.api.capability.StarTNotifiableDreamLinkContainer;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.BlockHitResult;

public class StarTDreamLinkHatchPartMachine extends TieredIOPartMachine implements IStarTDreamLinkFrequencyMachine {

    /*
     * As far as i can understand, the Managed Field Holder allows this class
     * to persist/save data onto the world using NBT with the @Persisted field annotation
     */
    protected static final ManagedFieldHolder MANAGED_FIELD_HOLDER = new ManagedFieldHolder(StarTDreamLinkHatchPartMachine.class,
        TieredIOPartMachine.MANAGED_FIELD_HOLDER);

    /* This will persist the NotifiableDreamLinkContainer using nbt. */
    @Persisted
    protected StarTNotifiableDreamLinkContainer container;

    /* Store the frequency of the hatch too */
    @Persisted
    protected String frequency;

    public StarTDreamLinkHatchPartMachine(IMachineBlockEntity holder, int tier, int amperage) {
        super(holder, tier, IO.IN);

        this.container = StarTNotifiableDreamLinkContainer.receiverContainer(this, GTValues.V[tier] * 64L * amperage,
                GTValues.V[tier], amperage);
            
        this.frequency = IStarTDreamLinkFrequencyMachine.DEFAULT_FREQ;
    }

    @Override
    public boolean shouldOpenUI(Player player, InteractionHand hand, BlockHitResult hit) {
        return true;
    }

    @Override
    public ManagedFieldHolder getFieldHolder() {
        return MANAGED_FIELD_HOLDER;
    }

    //////////////////////////////////////
    // ******* FREQ ********//
    //////////////////////////////////////

    //////////////////////////////////////
    // ********** Misc **********//
    //////////////////////////////////////

    @Override
    public int tintColor(int index) {
        if (index == 2) {
            return GTValues.VC[getTier()];
        }
        return super.tintColor(index);
    }

    @Override
    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    @Override
    public String getFrequency() {
        return this.frequency;
    }
}
