/*
    Beetroot Edition
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/dtbeetroot
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtbeetroot.mixin;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.BeetrootBlock;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockItem.class)
public abstract class BlockItemMixin {
	@Shadow public abstract Block getBlock();
	@Inject(at = @At("HEAD"), method = "place", cancellable = true)
	private void dtbeetroot$cancelPlacingOnDirt(BlockPlaceContext blockPlaceContext, CallbackInfoReturnable<InteractionResult> cir) {
		if (this.getBlock() instanceof BeetrootBlock) {
			if (blockPlaceContext.getLevel().getBlockState(blockPlaceContext.getClickedPos().below()).is(BlockTags.DIRT)) {
				cir.setReturnValue(InteractionResult.FAIL);
			}
		}
	}
}
