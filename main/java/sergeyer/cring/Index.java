package sergeyer.cring;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import sergeyer.cring.proxy.CommonProxy;

import java.io.File;

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
        for (int i = 0; i < RingContainer.RingCount; i++)
            RingContainer.RingList.add(new RingCooldownStats());
        Configuration Config = new Configuration(new File("config/CommandRing.cfg"));
        Config.load();
        RingContainer.CoolDownOfUsageMS = Config.get("ringConfig","CoolDown of ring in second", 6*60*60).getLong() * 1000;
        ServerEventsHandler.CommandOnUse = Config.get("ringConfig","Command On Use", "lp user %p parent add mage").getString();
        ServerEventsHandler.MessageOnUse= Config.get("ringConfig","Message On Use", "say %p is a MAGE!").getString();
        ServerEventsHandler.MessageOnCooldown = Config.get("ringConfig","Message on Ring Cooldown", "Wait %h:%m:%s").getString();
        ServerEventsHandler.CommandOnEndOfCooldown = Config.get("ringConfig","Command on End of Ring Cooldown", "lp user %p parent remove mage").getString();
        Config.save();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
       proxy.postInit(event);
    }

}










