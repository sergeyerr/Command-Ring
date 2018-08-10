package sergeyer.cring;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import sergeyer.cring.proxy.CommonProxy;

@Mod(modid = Index.MODID, version = Index.VERSION)

public class Index {
    public static final String MODID = "cring";
    public static final String VERSION = "0.1";
    public static final CreativeTabs creativeModTab = new CreativeTabs("CommandRing") {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(ItemsRegister.Ring); // тут надо влепить свой мем
        }
    };

    @SidedProxy(clientSide = "sergeyer.cring.proxy.ClientProxy", serverSide = "sergeyer.cring.proxy.CommonProxy")
    public static CommonProxy proxy;

    @EventHandler
    public static void preInit(FMLPreInitializationEvent event)
    {
        proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init(event);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
       proxy.postInit(event);
    }

}










