package sergeyer.cring;

import net.minecraft.item.Item;

public class BasicItem extends Item {
    public BasicItem(String name, int maxStackStack) {
        this.setRegistryName(name);
        this.maxStackSize = maxStackStack;
        this.setCreativeTab(Index.creativeModTab);
        this.setUnlocalizedName(name);

    }
}
