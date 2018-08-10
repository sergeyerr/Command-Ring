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
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;


@Mod.EventBusSubscriber(value = Side.SERVER, modid = "cring")
public class ServerEventsHandler {
    @SubscribeEvent
    public static void OnRightClick(PlayerInteractEvent.RightClickItem event) {
        ItemStack it = event.getEntityPlayer().getHeldItemMainhand();
        MinecraftServer server = event.getEntityPlayer().world.getMinecraftServer();
        if (it != null && it.getItem() == ItemsRegister.Ring) {
            RingCooldownStats ringStats = RingContainer.RingList.get(it.getMetadata());
            if (ringStats.CanBeUsed == true) {
                server.commandManager.executeCommand(server, "say yeah, it's work");
                ringStats.CanBeUsed = false;
                ringStats.LastTimeofUsage = System.currentTimeMillis();
            } else {
                server.commandManager.executeCommand(server, "say wait %t sec".replace("%t", Long.toString((RingContainer.CoolDownOfUsageMS - System.currentTimeMillis() + ringStats.LastTimeofUsage) / 1000)));
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
                }
            }
        }
    }
}
