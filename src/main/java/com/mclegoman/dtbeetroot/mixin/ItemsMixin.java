/*
    Beetroot Edition
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/dtbeetroot
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtbeetroot.mixin;

import com.mclegoman.dtbeetroot.items.ItemRegistry;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Items.class)
public class ItemsMixin {
	@Inject(at = @At("RETURN"), method = "<clinit>")
	private static void dtbeetroot$bootstrap(CallbackInfo ci) {
		ItemRegistry.init();
	}
}