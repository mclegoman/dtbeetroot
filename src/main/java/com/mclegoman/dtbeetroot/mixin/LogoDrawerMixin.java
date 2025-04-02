/*
    Beetroot Edition
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/dtbeetroot
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtbeetroot.mixin;

import com.mclegoman.dtbeetroot.BeetrootEdition;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.LogoRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ARGB;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LogoRenderer.class)
public class LogoDrawerMixin {
	@Shadow @Final private boolean keepLogoThroughFade;
	@Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;blit(Ljava/util/function/Function;Lnet/minecraft/resources/ResourceLocation;IIFFIIIII)V", ordinal = 1), method = "renderLogo(Lnet/minecraft/client/gui/GuiGraphics;IFI)V", cancellable = true)
	private void dtbeetroot$edition(GuiGraphics context, int screenWidth, float alpha, int y, CallbackInfo ci) {
		context.blit(RenderType::guiTextured, ResourceLocation.fromNamespaceAndPath(BeetrootEdition.modId, "textures/gui/title/beetroot.png"), (screenWidth / 2) - 96, y + 37, 0.0F, 0.0F, 192, 14, 192, 16, ARGB.white(this.keepLogoThroughFade ? 1.0F : alpha));
		ci.cancel();
	}
}