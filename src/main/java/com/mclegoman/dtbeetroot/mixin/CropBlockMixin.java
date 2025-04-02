/*
    Beetroot Edition
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/dtbeetroot
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtbeetroot.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CropBlock.class)
public class CropBlockMixin {
	@Inject(method = "mayPlaceOn", at = @At("RETURN"), cancellable = true)
	private void dtbeetroot$mayPlaceOn(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CallbackInfoReturnable<Boolean> cir) {
		// This does make crops be able to be placed on any dirt,
		// however this is required for beetroot to generate... (if I had more time, i probably could work this out)
		if (blockState.is(BlockTags.DIRT)) cir.setReturnValue(true);
	}
}
