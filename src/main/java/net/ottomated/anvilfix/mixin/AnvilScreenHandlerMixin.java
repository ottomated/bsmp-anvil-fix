package net.ottomated.anvilfix.mixin;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.AnvilScreenHandler;
import net.minecraft.text.TranslatableText;
import net.ottomated.anvilfix.AnvilFixMod;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Iterator;
import java.util.Map;

@Mixin(AnvilScreenHandler.class)
public class AnvilScreenHandlerMixin {

    @Inject(method = "updateResult", at = @At(value = "INVOKE", target = "Ljava/util/Map;put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", shift = At.Shift.AFTER), locals = LocalCapture.CAPTURE_FAILHARD)
    private void overrideMaxLevel(
        CallbackInfo info,
        ItemStack leftStack,
        int i,
        int j,
        int k,
        ItemStack leftStackCopy,
        ItemStack rightStack,
        Map<Enchantment, Integer> leftAndFinalStackEnchants,
        boolean bl,
        Map<Enchantment, Integer> rightStackEnchants,
        boolean bl2,
        boolean bl3,
        Iterator var12,
        Enchantment enchantment,
        int leftStackLevel,
        int finalLevel
    ) {
        int rightStackLevel = rightStackEnchants.get(enchantment);
        int modifiedFinal = Math.max(leftStackLevel, Math.max(rightStackLevel, finalLevel));
        AnvilFixMod.LOGGER.info("{}: {} + {} = {} (was {})", new TranslatableText(enchantment.getTranslationKey()).getString(), leftStackLevel, rightStackLevel, modifiedFinal, finalLevel);
        leftAndFinalStackEnchants.put(enchantment, modifiedFinal);
    }
}
