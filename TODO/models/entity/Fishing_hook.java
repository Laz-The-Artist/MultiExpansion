// Date: 2017. 12. 08. 18:15:49
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX






package net.minecraft.src;

public class ModelNew extends ModelBase
{
  //fields
    ModelRenderer body;
    ModelRenderer topstuff;
    ModelRenderer topstuff;
  
  public ModelNew()
  {
    textureWidth = 32;
    textureHeight = 32;
    
      body = new ModelRenderer(this, 0, 0);
      body.addBox(-1.5F, 0F, -1.5F, 3, 3, 3);
      body.setRotationPoint(0F, 17F, 0.03333334F);
      body.setTextureSize(32, 32);
      body.mirror = true;
      setRotation(body, 0F, 0F, 0F);
      topstuff = new ModelRenderer(this, 12, 0);
      topstuff.addBox(-1F, 0F, 0F, 1, 1, 1);
      topstuff.setRotationPoint(0.5F, 16.4F, -0.5F);
      topstuff.setTextureSize(32, 32);
      topstuff.mirror = true;
      setRotation(topstuff, 0F, 0F, 0F);
      topstuff = new ModelRenderer(this, 12, 2);
      topstuff.addBox(-2.5F, 3.5F, 0F, 3, 4, 0);
      topstuff.setRotationPoint(0F, 16.4F, 0F);
      topstuff.setTextureSize(32, 32);
      topstuff.mirror = true;
      setRotation(topstuff, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5);
    body.render(f5);
    topstuff.render(f5);
    topstuff.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5);
  }

}
