package sergeyer.cring;

import ibxm.Player;
import net.minecraft.command.ICommandManager;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(value = Side.SERVER, modid = "cring")
public class EventsHandler {
    @SubscribeEvent
    public static void OnRightClick(PlayerInteractEvent.RightClickItem event) {
        ItemStack it = event.getEntityPlayer().getHeldItemMainhand();
        MinecraftServer server = event.getEntityPlayer().world.getMinecraftServer();
        if (it != null && it.getItem() == ItemsRegister.Ring) {
            server.commandManager.executeCommand(server, "say yeah, it's work");
    }
    }
}
