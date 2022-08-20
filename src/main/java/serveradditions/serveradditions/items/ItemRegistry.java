package serveradditions.serveradditions.items;


import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import serveradditions.serveradditions.ModTab;
import serveradditions.serveradditions.ServerAdditions;
import serveradditions.serveradditions.items.added.lootbagstuff.LootBagItem;

public class ItemRegistry {
    //Registry
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ServerAdditions.MOD_ID);

    //public static final RegistryObject<Item> LOOT_BAG = ITEMS.register("loot_bag",
            //() -> new LootBagItem(new Item.Properties().tab(ModTab.Server_Additions_ModTab)));




    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }

}
