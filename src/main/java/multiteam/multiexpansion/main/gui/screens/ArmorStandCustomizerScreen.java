package multiteam.multiexpansion.main.gui.screens;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import multiteam.multiexpansion.MultiExpansion;
import multiteam.multiexpansion.main.gui.containers.ArmorStandCustomizerContainer;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;


public class ArmorStandCustomizerScreen extends AbstractContainerScreen<ArmorStandCustomizerContainer> {

    private static final int WIDTH = 235;
    private static final int HEIGHT = 166;
    private static final ResourceLocation GUI = new ResourceLocation(MultiExpansion.MOD_ID, "textures/gui/container/armorstand_customizer.png");

    public ArmorStandCustomizerScreen(ArmorStandCustomizerContainer menu, Inventory inventory, Component label) {
        super(menu, inventory, label);
        this.imageHeight = HEIGHT;
        this.imageWidth = WIDTH;
    }


    @Override
    protected void renderBg(PoseStack matrixStack, float mouseX, int mouseY, int partialTicks) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, GUI);

        int i = this.getGuiLeft();
        int j = this.getGuiTop();
        this.blit(matrixStack, i, j, 0, 0, this.getXSize(), this.getYSize());
    }

    public int getOffsetX(){
        return (this.width - WIDTH) / 2;
    }

    public int getOffsetY(){
        return (this.height - HEIGHT) / 2;
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return true;
    }
}
