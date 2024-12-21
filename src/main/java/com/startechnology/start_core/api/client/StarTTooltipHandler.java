package com.startechnology.start_core.api.client;

import java.util.List;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.data.lang.LangHandler;
import com.startechnology.start_core.StarTCore;
import com.tterrag.registrate.providers.RegistrateLangProvider;

import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

public class StarTTooltipHandler {

     private static final String ITEM_PREFIX = "item." + StarTCore.MOD_ID;

    public static void appendTooltip(ItemStack stack, TooltipFlag flag, List<Component> tooltips) {
        // Item custom tooltips
        
        String translationKey = stack.getDescriptionId();
        
        if (translationKey.startsWith(ITEM_PREFIX)) {
            String tooltipKey = translationKey + ".tooltip";
            
            if (I18n.exists(tooltipKey)) {
                tooltips.add(1, Component.translatable(tooltipKey));
            } else {
                List<MutableComponent> multiLang = LangHandler.getMultiLang(tooltipKey);
                if (multiLang != null && !multiLang.isEmpty()) {
                    tooltips.addAll(1, multiLang);
                }
            }
        }
    }
}
