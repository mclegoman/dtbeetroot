/*
    Beetroot Edition
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/dtbeetroot
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtbeetroot.mixin;

import com.mclegoman.dtbeetroot.player_unlocks.PlayerUnlockRegistry;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.server.players.PlayerUnlock;
import net.minecraft.server.players.PlayerUnlocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerUnlocks.class)
public class PlayerUnlocksMixin {
	@Inject(at = @At("RETURN"), method = "bootstrap")
	private static void dtbeetroot$bootstrap(Registry<PlayerUnlock> registry, CallbackInfoReturnable<Holder<PlayerUnlock>> cir) {
		PlayerUnlockRegistry.init();
	}
}