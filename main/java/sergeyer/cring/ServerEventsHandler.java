package sergeyer.cring;

import ibxm.Player;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.command.ICommandManager;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Tuple;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;


@Mod.EventBusSubscriber(value = Side.SERVER, modid = "cring")
public class ServerEventsHandler {
    public static String CommandOnUse;
    public static String CommandOnEndOfCooldown;
    public static String MessageOnCooldown;
    public static String MessageOnUse;

    @SubscribeEvent
    public static void OnRightClick(PlayerInteractEvent.RightClickItem event) {
        ItemStack it = event.getEntityPlayer().getHeldItemMainhand();
        MinecraftServer server = event.getEntityPlayer().world.getMinecraftServer();
        EntityPlayer player = event.getEntityPlayer();
        if (it != null && it.getItem() == ItemsRegister.Ring) {
            RingCooldownStats ringStats = RingContainer.RingList.get(it.getMetadata());
            if (ringStats.CanBeUsed == true) {
                player.sendMessage(new TextComponentString(MessageOnUse.replace("%p", player.getName())));
                server.commandManager.executeCommand(server, CommandOnUse.replace("%p", player.getName()));
                ringStats.CanBeUsed = false;
                ringStats.LastTimeofUsage = System.currentTimeMillis();
                ringStats.UserName = player.getName();
            } else {
                Long leftSec = (RingContainer.CoolDownOfUsageMS - System.currentTimeMillis() + ringStats.LastTimeofUsage) / 1000;
                Long hours = leftSec / 3600;
                leftSec %= 3600;
                Long minutes = leftSec / 60;
                leftSec %= 60;
                player.sendMessage(new TextComponentString(MessageOnCooldown.replace("%h", Long.toString(hours)).replace("%m", Long.toString(minutes)).replace("%s", Long.toString(leftSec))));
            }
        }
    }

    @SubscribeEvent
    public static void OnUpdate(TickEvent.WorldTickEvent event) {
        for (RingCooldownStats ring :
                RingContainer.RingList) {
            if (ring.CanBeUsed == false) {
                if (System.currentTimeMillis() - ring.LastTimeofUsage >= RingContainer.CoolDownOfUsageMS) {
                    ring.CanBeUsed = true;
                    MinecraftServer server = event.world.getMinecraftServer();
                    if (ring.UserName == null) {
                        server.commandManager.executeCommand(server, CommandOnEndOfCooldown.replace("%p", ring.UserName));
                    }
                }
            }
        }
    }
}

