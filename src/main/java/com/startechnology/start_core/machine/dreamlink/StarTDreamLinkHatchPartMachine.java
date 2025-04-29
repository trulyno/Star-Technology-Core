package com.startechnology.start_core.machine.dreamlink;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.gui.fancy.FancyMachineUIWidget;
import com.gregtechceu.gtceu.api.gui.fancy.IFancyTooltip;
import com.gregtechceu.gtceu.api.gui.fancy.TooltipsPanel;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.feature.IFancyUIMachine;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IMultiController;
import com.gregtechceu.gtceu.api.machine.multiblock.part.TieredIOPartMachine;
import com.lowdragmc.lowdraglib.gui.modular.ModularUI;
import com.lowdragmc.lowdraglib.gui.widget.DraggableScrollableWidgetGroup;
import com.lowdragmc.lowdraglib.gui.widget.LabelWidget;
import com.lowdragmc.lowdraglib.gui.widget.TextFieldWidget;
import com.lowdragmc.lowdraglib.gui.widget.Widget;
import com.lowdragmc.lowdraglib.gui.widget.WidgetGroup;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder;
import com.startechnology.start_core.api.IStarTDreamLinkNetworkMachine;
import com.startechnology.start_core.api.capability.StarTNotifiableDreamLinkContainer;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.BlockHitResult;

public class StarTDreamLinkHatchPartMachine extends TieredIOPartMachine implements IStarTDreamLinkNetworkMachine {

    /*
     * As far as i can understand, the Managed Field Holder allows this class
     * to persist/save data onto the world using NBT with the @Persisted field annotation
     */
    protected static final ManagedFieldHolder MANAGED_FIELD_HOLDER = new ManagedFieldHolder(StarTDreamLinkHatchPartMachine.class,
        TieredIOPartMachine.MANAGED_FIELD_HOLDER);

    /* This will persist the NotifiableDreamLinkContainer using nbt. */
    @Persisted
    protected StarTNotifiableDreamLinkContainer container;

    /* Store the network of the hatch too */
    @Persisted
    protected String network;

    public StarTDreamLinkHatchPartMachine(IMachineBlockEntity holder, int tier, int amperage) {
        super(holder, tier, IO.IN);

        this.container = StarTNotifiableDreamLinkContainer.receiverContainer(this, GTValues.V[tier] * 64L * amperage,
                GTValues.V[tier], amperage);
            
        this.network = IStarTDreamLinkNetworkMachine.DEFAULT_NETWORK;
    }

    @Override
    public boolean shouldOpenUI(Player player, InteractionHand hand, BlockHitResult hit) {
        return true;
    }

    @Override
    public ManagedFieldHolder getFieldHolder() {
        return MANAGED_FIELD_HOLDER;
    }

    @Override
    public int tintColor(int index) {
        if (index == 2) {
            return GTValues.VC[getTier()];
        }
        return super.tintColor(index);
    }

    @Override
    public Widget createUIWidget() {
        var group = new WidgetGroup(0, 0, 182 + 8, 117 + 8);
        group.addWidget(
            new DraggableScrollableWidgetGroup(4, 4, 182, 117).setBackground(GuiTextures.DISPLAY)
                .addWidget(new LabelWidget(4, 5, "Dream-Link Config"))
                
                .addWidget(new LabelWidget(4, 17, "§7Dream-Network Identifier"))
                .addWidget(
                    new TextFieldWidget(4, 29, 182 - 8, 12, this::getNetwork, this::setNetwork)
                        .setMaxStringLength(64)
                        .setValidator(input -> {
                            if (input == null || input.isBlank()) return IStarTDreamLinkNetworkMachine.DEFAULT_NETWORK + "";
                            return input;
                        })
                        .setHoverTooltips(Component.translatable("start_core.machine.dream_link.network_set_hover"))
                )
        );
        
        group.setBackground(GuiTextures.BACKGROUND_INVERSE);
        return group;
    }

    @Override
    public ModularUI createUI(Player entityPlayer) {
        return new ModularUI(198, 208, this, entityPlayer).widget(new FancyMachineUIWidget(this, 198, 208));
    }


    @Override
    public void setNetwork(String network) {
        this.network = network;
    }

    @Override
    public String getNetwork() {
        return this.network;
    }
}
