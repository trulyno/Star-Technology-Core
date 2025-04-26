package com.startechnology.start_core.machine.dreamlink;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.multiblock.part.TieredIOPartMachine;
import com.lowdragmc.lowdraglib.syncdata.IEnhancedManaged;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder;
import com.startechnology.start_core.api.capability.StarTNotifiableDreamLinkContainer;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.BlockHitResult;

public class StarTDreamLinkHatchPartMachine extends TieredIOPartMachine {

    /*
     * As far as i can understand, the Managed Field Holder allows this class
     * to persist/save data onto the world using NBT with the @Persisted field annotation
     */
    protected static final ManagedFieldHolder MANAGED_FIELD_HOLDER = new ManagedFieldHolder(StarTDreamLinkHatchPartMachine.class,
        TieredIOPartMachine.MANAGED_FIELD_HOLDER);


    /* This will persist the NotifiableDreamLinkContainer using nbt. */
    @Persisted
    protected StarTNotifiableDreamLinkContainer container;

    public StarTDreamLinkHatchPartMachine(IMachineBlockEntity holder, int tier, int amperage) {
        super(holder, tier, IO.IN);

        this.container = StarTNotifiableDreamLinkContainer.receiverContainer(this, GTValues.V[tier] * 64L * amperage,
                GTValues.V[tier], amperage);
    }

    @Override
    public boolean shouldOpenUI(Player player, InteractionHand hand, BlockHitResult hit) {
        return false;
    }

    @Override
    public ManagedFieldHolder getFieldHolder() {
        return MANAGED_FIELD_HOLDER;
    }

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
}
