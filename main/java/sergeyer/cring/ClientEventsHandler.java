package sergeyer.cring;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(value = Side.CLIENT, modid = "cring")
public class ClientEventsHandler {
    @SubscribeEvent
    public static void RegisterModels(ModelRegistryEvent event) {
        final Item ring = ItemsRegister.Ring;
        for (int i = 0; i < RingContainer.RingCount; i++)
        {
            ModelLoader.setCustomModelResourceLocation(ring, i, new ModelResourceLocation(ring.getRegistryName() + "_" + i, "inventory"));
        }
    }
}