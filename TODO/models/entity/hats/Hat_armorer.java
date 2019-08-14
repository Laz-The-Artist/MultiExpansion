package Insert_Package_here;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Hat_armorer - King_Of_Creepers
 * Created using Tabula 7.0.1
 */
public class Hat_armorer extends ModelBase {
    public ModelRenderer HEAD_WillBeDeleted;
    public ModelRenderer Mask_Layer;

    public Hat_armorer() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.Mask_Layer = new ModelRenderer(this, 1, 18);
        this.Mask_Layer.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Mask_Layer.addBox(-4.5F, -9.0F, -4.5F, 9, 9, 9, 0.0F);
        this.HEAD_WillBeDeleted = new ModelRenderer(this, 0, 0);
        this.HEAD_WillBeDeleted.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.HEAD_WillBeDeleted.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.Mask_Layer.render(f5);
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
