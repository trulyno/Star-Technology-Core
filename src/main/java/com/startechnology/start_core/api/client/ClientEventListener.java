package com.startechnology.start_core.api.client;

import com.gregtechceu.gtceu.client.TooltipsHandler;
import com.startechnology.start_core.StarTCore;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = StarTCore.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
@OnlyIn(Dist.CLIENT)
public class ClientEventListener {

    @SubscribeEvent
    public static void onTooltipEvent(ItemTooltipEvent event) {
        StarTTooltipHandler.appendTooltip(event.getItemStack(), event.getFlags(), event.getToolTip());
    }
}
