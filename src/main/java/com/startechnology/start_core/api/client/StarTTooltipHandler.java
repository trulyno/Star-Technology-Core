package com.startechnology.start_core.api.client;

import java.util.List;

import com.startechnology.start_core.StarTCore;

import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

public class StarTTooltipHandler {

    private static final String START_REGEX = String.format("^.+\\.%s\\..+", StarTCore.MOD_ID);

    public static void appendTooltip(ItemStack stack, TooltipFlag flag, List<Component> tooltips) {
        // Item custom tooltips
        String stackDescriptionId = stack.getDescriptionId();

        if (stackDescriptionId.matches(START_REGEX)) {
            String stackTooltipLocalKey = stackDescriptionId + ".tooltip";

            if (I18n.exists(stackTooltipLocalKey)) tooltips.add(1, Component.translatable(stackTooltipLocalKey));
        }
    }
}
