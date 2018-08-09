package sergeyer.cring;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class RingItem extends Item {

    public RingItem(String name, int maxStackSize) {
        this.setRegistryName(name);
        this.maxStackSize = maxStackSize;
        this.setCreativeTab(Index.creativeModTab);
        this.setUnlocalizedName(name);
        setHasSubtypes(true);
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
    {
        if (tab == Index.creativeModTab)
        {
            for (int i = 0; i < RingContainer.RingCount; i++)
            {
                items.add(new ItemStack(this, 1, i));
            }
        }
    }
}


