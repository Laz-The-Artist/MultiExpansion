package com.multiexpansion.client.renderer.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.multiexpansion.entity.monster.TinyGhastEntity;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.GhastModel;
import net.minecraft.entity.monster.GhastEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class TinyGhastRenderer extends MobRenderer<TinyGhastEntity, GhastModel<TinyGhastEntity>> {
	
	private static final ResourceLocation GHAST_TEXTURES = new ResourceLocation("textures/entity/ghast/ghast.png");
	private static final ResourceLocation GHAST_SHOOTING_TEXTURES = new ResourceLocation("textures/entity/ghast/ghast_shooting.png");
	
	public TinyGhastRenderer(EntityRendererManager renderManagerIn) {
		
		super(renderManagerIn, new GhastModel<>(), 0.5F);
		
	}
	
	@Override
	public ResourceLocation getTextureLocation(TinyGhastEntity entity) {
		
		return entity.isAutoSpinAttack() ? GHAST_SHOOTING_TEXTURES : GHAST_TEXTURES;
		
	}
	
	protected void preRenderCallback(GhastEntity entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
		
		matrixStackIn.scale(0.5F, 0.5F, 0.5F);
		
	}
	
	public static class RenderFactory implements IRenderFactory<TinyGhastEntity> {
		
		@Override
		public EntityRenderer<? super TinyGhastEntity> createRenderFor(EntityRendererManager manager) {
			
			return new TinyGhastRenderer(manager);
			
		}
		
	}
	
}