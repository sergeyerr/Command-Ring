package sergeyer.cring.proxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import sergeyer.cring.ItemsRegister;
import sergeyer.cring.RingContainer;
import sergeyer.cring.RingCooldownStats;
import sergeyer.cring.RingItem;

public class CommonProxy {

    public void preInit(FMLPreInitializationEvent event)
    {
        System.out.println("\u001B[32m" + "[Starting Index Mod PRE-INITIALIZATION]" + "\u001B[0m");
        ItemsRegister.register();
    }


    public void init(FMLInitializationEvent event)
    {
        // Инициализация
        System.out.println("\u001B[32m" + "[Starting Index Mod INITIALIZATION]" + "\u001B[0m");

    }

    public void postInit(FMLPostInitializationEvent event)
    {
        // Инициализация
        System.out.println("\u001B[32m" + "[Starting Index Mod POST-INITIALIZATION]" + "\u001B[0m");
    }
}
