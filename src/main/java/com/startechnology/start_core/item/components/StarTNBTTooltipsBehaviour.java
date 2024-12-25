package com.startechnology.start_core.item.components;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import com.gregtechceu.gtceu.api.item.component.IAddInformation;
import com.startechnology.start_core.api.custom_tooltips.StarTCustomTooltip;
import com.startechnology.start_core.api.custom_tooltips.StarTCustomTooltipsManager;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class StarTNBTTooltipsBehaviour implements IAddInformation {

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltipComponents,
            TooltipFlag isAdvanced) {
        StarTCustomTooltip tooltips = StarTCustomTooltipsManager.customTooltipFromTag(stack);

        if (tooltips != null) {
            tooltipComponents.subList(1, tooltipComponents.size()).clear();
            tooltipComponents.addAll(tooltips.getTooltips());
        }
    }
    
}
