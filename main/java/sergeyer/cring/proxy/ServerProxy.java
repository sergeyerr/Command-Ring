package sergeyer.cring.proxy;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.commons.io.IOUtils;
import sergeyer.cring.RingContainer;
import sergeyer.cring.RingCooldownStats;
import sergeyer.cring.ServerEventsHandler;

import java.io.*;
import java.util.List;

public class ServerProxy extends CommonProxy {
    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
        Configuration Config = new Configuration(new File("config/CommandRing.cfg"));
        Config.load();
        RingContainer.CoolDownOfUsageMS = Config.get("ringConfig", "CoolDown of ring in second", 6 * 60 * 60).getLong() * 1000;
        ServerEventsHandler.CommandOnUse = Config.get("ringConfig", "Command On Use", "lp user %p parent add mage").getString();
        ServerEventsHandler.MessageOnUse = Config.get("ringConfig", "Message On Use", "say %p is a MAGE!").getString();
        ServerEventsHandler.MessageOnCooldown = Config.get("ringConfig", "Message on Ring Cooldown", "Wait %h:%m:%s").getString();
        ServerEventsHandler.CommandOnEndOfCooldown = Config.get("ringConfig", "Command on End of Ring Cooldown", "lp user %p parent remove mage").getString();
        Config.save();
        try {
            RingContainer.Init();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
    }
}
