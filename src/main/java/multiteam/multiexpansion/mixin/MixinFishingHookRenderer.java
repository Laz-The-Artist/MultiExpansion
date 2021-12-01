package multiteam.multiexpansion.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Matrix3f;
import com.mojang.math.Matrix4f;
import com.mojang.math.Vector3f;
import multiteam.multicore_lib.setup.utilities.generic.MathF;
import multiteam.multicore_lib.setup.utilities.render.RenderingTool;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.FishingHookRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@SuppressWarnings("ALL")
@Mixin(FishingHookRenderer.class)
public abstract class MixinFishingHookRenderer extends EntityRenderer<FishingHook> {

    private static final ResourceLocation TEXTURE_LOCATION = new ResourceLocation("textures/entity/fishing_hook.png");
    private static final ResourceLocation TEXTURE_LOCATION2 = new ResourceLocation("multiexpansion:textures/block/ruby_block.png");
    private static final RenderType RENDER_TYPE = RenderType.entityCutout(TEXTURE_LOCATION);

    public MixinFishingHookRenderer(EntityRendererProvider.Context context) {
        super(context);
    }


    @Overwrite
    public void render(FishingHook fishingHook, float float1, float float2, PoseStack stack, MultiBufferSource bufferSource, int randomInt) {
        super.render(fishingHook, float1, float2, stack, bufferSource, randomInt);
        Player player = fishingHook.getPlayerOwner();
        if (player != null) {
            stack.pushPose();
            stack.pushPose();
            float scale = 1f;
            stack.scale(scale, scale, scale);
            //stack.mulPose(this.entityRenderDispatcher.cameraOrientation());
            //stack.mulPose(Vector3f.YP.rotationDegrees(180.0F));
            stack.translate(0, 0, 0f);
            PoseStack.Pose posestack$pose = stack.last();
            Matrix4f matrix4f = posestack$pose.pose();
            Matrix3f matrix3f = posestack$pose.normal();

            VertexConsumer vertexconsumer = bufferSource.getBuffer(RENDER_TYPE);

            Vector3f vec = MathF.BlockToFloatScaleVector3f(7, 20, 7);

            texturedCube2(vertexconsumer, matrix4f, matrix3f, randomInt, MathF.BlockToFloatScaleVector3f(16, 16, 16), new Vector3f(-vec.x(), -vec.y(), -vec.z()), -MathF.BlockToFloatScale(6));

            stack.popPose();
            int i = player.getMainArm() == HumanoidArm.RIGHT ? 1 : -1;
            ItemStack itemstack = player.getMainHandItem();
            if (!itemstack.is(Items.FISHING_ROD)) {
                i = -i;
            }

            float f = player.getAttackAnim(float2);
            float f1 = Mth.sin(Mth.sqrt(f) * (float)Math.PI);
            float f2 = Mth.lerp(float2, player.yBodyRotO, player.yBodyRot) * ((float)Math.PI / 180F);
            double d0 = (double)Mth.sin(f2);
            double d1 = (double)Mth.cos(f2);
            double d2 = (double)i * 0.35D;
            double d3 = 0.8D;
            double d4;
            double d5;
            double d6;
            float f3;
            if ((this.entityRenderDispatcher.options == null || this.entityRenderDispatcher.options.getCameraType().isFirstPerson()) && player == Minecraft.getInstance().player) {
                double d7 = 960.0D / this.entityRenderDispatcher.options.fov;
                Vec3 vec3 = this.entityRenderDispatcher.camera.getNearPlane().getPointOnPlane((float)i * 0.525F, -0.1F);
                vec3 = vec3.scale(d7);
                vec3 = vec3.yRot(f1 * 0.5F);
                vec3 = vec3.xRot(-f1 * 0.7F);
                d4 = Mth.lerp((double)float2, player.xo, player.getX()) + vec3.x;
                d5 = Mth.lerp((double)float2, player.yo, player.getY()) + vec3.y;
                d6 = Mth.lerp((double)float2, player.zo, player.getZ()) + vec3.z;
                f3 = player.getEyeHeight();
            } else {
                d4 = Mth.lerp((double)float2, player.xo, player.getX()) - d1 * d2 - d0 * 0.8D;
                d5 = player.yo + (double)player.getEyeHeight() + (player.getY() - player.yo) * (double)float2 - 0.45D;
                d6 = Mth.lerp((double)float2, player.zo, player.getZ()) - d0 * d2 + d1 * 0.8D;
                f3 = player.isCrouching() ? -0.1875F : 0.0F;
            }

            double d9 = Mth.lerp((double)float2, fishingHook.xo, fishingHook.getX());
            double d10 = Mth.lerp((double)float2, fishingHook.yo, fishingHook.getY()) + 0.25D;
            double d8 = Mth.lerp((double)float2, fishingHook.zo, fishingHook.getZ());
            float f4 = (float)(d4 - d9);
            float f5 = (float)(d5 - d10) + f3;
            float f6 = (float)(d6 - d8);
            VertexConsumer vertexconsumer1 = bufferSource.getBuffer(RenderType.lineStrip());
            PoseStack.Pose posestack$pose1 = stack.last();
            int j = 16;

            for(int k = 0; k <= 16; ++k) {
                stringVertex(f4, f5, f6, vertexconsumer1, posestack$pose1, fraction(k, 16), fraction(k + 1, 16));
            }

            stack.popPose();
            super.render(fishingHook, float1, float2, stack, bufferSource, randomInt);
        }
    }

    private static void texturedCube2(VertexConsumer vertexconsumer, Matrix4f matrix4f, Matrix3f matrix3f, int randomInt, Vector3f size, Vector3f offset, float shrink) {
        //back moves on -z
        RenderingTool.texturedPlane(vertexconsumer, matrix4f, randomInt, new Vector3f(offset.x() + size.x(), offset.y(), offset.z()-shrink), new Vector3f(offset.x(), offset.y(), offset.z()-shrink), new Vector3f(offset.x(), offset.y() + size.y(), offset.z()-shrink), new Vector3f(offset.x() + size.x(), offset.y() + size.y(), offset.z()-shrink), new Vector3f(0.0F, 1.0F, 0.0F));
        //right moves on -x
        RenderingTool.texturedPlane(vertexconsumer, matrix4f, randomInt, new Vector3f(offset.x()-shrink, offset.y(), offset.z()), new Vector3f(offset.x()-shrink, offset.y(), offset.z() + size.z()), new Vector3f(offset.x()-shrink, offset.y() + size.y(), offset.z() + size.z()), new Vector3f(offset.x()-shrink, offset.y() + size.y(), offset.z()), new Vector3f(0.0F, 1.0F, 0.0F));
        //front moves on +z
        RenderingTool.texturedPlane(vertexconsumer, matrix4f, randomInt, new Vector3f(offset.x(), offset.y(), offset.z() + size.z()+shrink), new Vector3f(offset.x() + size.x(), offset.y(), offset.z() + size.z()+shrink), new Vector3f(offset.x() + size.x(), offset.y() + size.y(), offset.z() + size.z()+shrink), new Vector3f(offset.x(), offset.y() + size.y(), offset.z() + size.z()+shrink), new Vector3f(0.0F, 1.0F, 0.0F));
        //left moves on +x
        RenderingTool.texturedPlane(vertexconsumer, matrix4f, randomInt, new Vector3f(offset.x() + size.x()+shrink, offset.y(), offset.z()), new Vector3f(offset.x() + size.x()+shrink, offset.y() + size.y(), offset.z()), new Vector3f(offset.x() + size.x()+shrink, offset.y() + size.y(), offset.z() + size.z()), new Vector3f(offset.x() + size.x()+shrink, offset.y(), offset.z() + size.z()), new Vector3f(0.0F, 1.0F, 0.0F));
        //top moves on +y
        RenderingTool.texturedPlane(vertexconsumer, matrix4f, randomInt, new Vector3f(offset.x(), offset.y() + size.y()+shrink, offset.z()), new Vector3f(offset.x(), offset.y() + size.y()+shrink, offset.z() + size.z()), new Vector3f(offset.x() + size.x(), offset.y() + size.y()+shrink, offset.z() + size.z()), new Vector3f(offset.x() + size.x(), offset.y() + size.y()+shrink, offset.z()), new Vector3f(0.0F, 1.0F, 0.0F));
        //bottom moves on -y
        RenderingTool.texturedPlane(vertexconsumer, matrix4f, randomInt, new Vector3f(offset.x(), offset.y()-shrink, offset.z()), new Vector3f(offset.x() + size.x(), offset.y()-shrink, offset.z()), new Vector3f(offset.x() + size.x(), offset.y()-shrink, offset.z() + size.z()), new Vector3f(offset.x(), offset.y()-shrink, offset.z() + size.z()), new Vector3f(0.0F, -1.0F, 0.0F));
    }

    private static void stringVertex(float p_174119_, float p_174120_, float p_174121_, VertexConsumer p_174122_, PoseStack.Pose p_174123_, float p_174124_, float p_174125_) {
        float f = p_174119_ * p_174124_;
        float f1 = p_174120_ * (p_174124_ * p_174124_ + p_174124_) * 0.5F + 0.25F;
        float f2 = p_174121_ * p_174124_;
        float f3 = p_174119_ * p_174125_ - f;
        float f4 = p_174120_ * (p_174125_ * p_174125_ + p_174125_) * 0.5F + 0.25F - f1;
        float f5 = p_174121_ * p_174125_ - f2;
        float f6 = Mth.sqrt(f3 * f3 + f4 * f4 + f5 * f5);
        f3 = f3 / f6;
        f4 = f4 / f6;
        f5 = f5 / f6;
        p_174122_.vertex(p_174123_.pose(), f, f1, f2).color(0, 0, 0, 255).normal(p_174123_.normal(), f3, f4, f5).endVertex();
    }

    private static float fraction(int p_114691_, int p_114692_) {
        return (float)p_114691_ / (float)p_114692_;
    }
}

