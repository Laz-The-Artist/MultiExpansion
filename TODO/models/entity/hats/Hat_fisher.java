package Insert_Package_here;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Hat_fisher - King_Of_Creepers
 * Created using Tabula 7.0.1
 */
public class Hat_fisher extends ModelBase {
    public ModelRenderer HEAD_WillBeDeleted;
    public ModelRenderer Mask_Layer;
    public ModelRenderer Rim_Layer;
    public ModelRenderer Top_Layer;

    public Hat_fisher() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.Rim_Layer = new ModelRenderer(this, 16, 48);
        this.Rim_Layer.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Rim_Layer.addBox(-8.0F, -5.51F, -8.0F, 16, 0, 16, 0.0F);
        this.HEAD_WillBeDeleted = new ModelRenderer(this, 0, 0);
        this.HEAD_WillBeDeleted.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.HEAD_WillBeDeleted.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
        this.Top_Layer = new ModelRenderer(this, 1, 37);
        this.Top_Layer.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Top_Layer.addBox(-4.0F, -10.0F, -4.0F, 8, 2, 8, 0.0F);
        this.Mask_Layer = new ModelRenderer(this, 1, 18);
        this.Mask_Layer.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Mask_Layer.addBox(-4.5F, -8.51F, -4.5F, 9, 8, 9, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.Rim_Layer.render(f5);
        this.HEAD_WillBeDeleted.render(f5);
        this.Top_Layer.render(f5);
        this.Mask_Layer.render(f5);
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
