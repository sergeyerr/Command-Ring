package sergeyer.cring;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemsRegister
{
    public static net.minecraft.item.Item Ring = new RingItem("ring", 1);

    public static void register()
    {
        setRegister(Ring);
    }

    @SideOnly(Side.CLIENT)
    public static void registerRender()
    {
        setRender(Ring);

    }

    private static void setRegister(net.minecraft.item.Item item)
    {
        ForgeRegistries.ITEMS.register(item);
    }

    @SideOnly(Side.CLIENT)
    private static void setRender(net.minecraft.item.Item item)
    {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }
}