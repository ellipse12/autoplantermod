package serveradditions.serveradditions.items;


import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import serveradditions.serveradditions.ModTab;
import serveradditions.serveradditions.ServerAdditions;


public class ItemRegistry {
    //Registry
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ServerAdditions.MOD_ID);

    public static final RegistryObject<Item> UPGRADE_ITEM = ITEMS.register("upgrade", () -> new Item(new Item.Properties().tab(ModTab.Server_Additions_ModTab)));





    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }

}
