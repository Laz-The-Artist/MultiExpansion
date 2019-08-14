package Insert_Package_here;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;

/**
 * Hat_fletcher - King_Of_Creepers
 * Created using Tabula 7.0.1
 */
public class Hat_fletcher extends ModelBase {
    public ModelRenderer HEAD_WillBeDeleted;
    public ModelRenderer Mask_Layer;
    public ModelRenderer Hat_Layer;

    public Hat_fletcher() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.Mask_Layer = new ModelRenderer(this, 1, 18);
        this.Mask_Layer.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Mask_Layer.addBox(-4.5F, -9.0F, -4.5F, 9, 9, 9, 0.0F);
        this.Hat_Layer = new ModelRenderer(this, 1, 37);
        this.Hat_Layer.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Hat_Layer.addBox(-4.0F, -9.0F, -4.0F, 8, 9, 8, 0.01F);
        this.HEAD_WillBeDeleted = new ModelRenderer(this, 0, 0);
        this.HEAD_WillBeDeleted.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.HEAD_WillBeDeleted.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.Mask_Layer.render(f5);
        GlStateManager.pushMatrix();
        GlStateManager.translate(this.Hat_Layer.offsetX, this.Hat_Layer.offsetY, this.Hat_Layer.offsetZ);
        GlStateManager.translate(this.Hat_Layer.rotationPointX * f5, this.Hat_Layer.rotationPointY * f5, this.Hat_Layer.rotationPointZ * f5);
        GlStateManager.scale(1.01D, 1.01D, 1.01D);
        GlStateManager.translate(-this.Hat_Layer.offsetX, -this.Hat_Layer.offsetY, -this.Hat_Layer.offsetZ);
        GlStateManager.translate(-this.Hat_Layer.rotationPointX * f5, -this.Hat_Layer.rotationPointY * f5, -this.Hat_Layer.rotationPointZ * f5);
        this.Hat_Layer.render(f5);
        GlStateManager.popMatrix();
        this.HEAD_WillBeDeleted.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
