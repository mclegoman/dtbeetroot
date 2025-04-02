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
import net.minecraft.world.level.block.BeetrootBlock;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(BeetrootBlock.class)
public abstract class BeetrootBlockMixin extends CropBlock {
	protected BeetrootBlockMixin(Properties properties) {
		super(properties);
	}
	@Override
	protected boolean mayPlaceOn(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
		// BlockItemMixin prevents beetroots from being placed on BlockTags.DIRT,
		// however, if the farmland is trampled, the beetroots will remain, I think this is fine though.
		return blockState.is(BlockTags.DIRT) || super.mayPlaceOn(blockState, blockGetter, blockPos);
	}
}
