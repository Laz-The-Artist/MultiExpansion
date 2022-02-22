package multiteam.multiexpansion.main.gui.containers;

import multiteam.multiexpansion.main.gui.ModContainers;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;

public class ArmorStandCustomizerContainer extends AbstractItemHandlerContainer {

    public ArmorStandCustomizerContainer(int windowId, Inventory playerInventory, Player player) {
        super(ModContainers.ARMOR_STAND_CUSTOMIZER_CONTAINER.get(), windowId, playerInventory, player);
        layoutPlayerInventorySlots(8, 84);
    }

    public ArmorStandCustomizerContainer(int windowId, Inventory playerInventory, FriendlyByteBuf friendlyByteBuf){
        this(windowId, playerInventory, playerInventory.player);
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(player);
    }

    @Override
    protected int getSlotNum() {
        return 2;
    }
}
