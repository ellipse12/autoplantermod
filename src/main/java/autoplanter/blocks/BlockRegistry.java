package autoplanter.blocks;


import autoplanter.AutoPlanter;
import autoplanter.blocks.added.AutoPlanterBlock;
import autoplanter.items.ItemRegistry;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class BlockRegistry {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, AutoPlanter.MOD_ID);
    public static final RegistryObject<Block> AUTO_PLANTER = registerBlock("auto_planter",
            () -> new AutoPlanterBlock(BlockBehaviour.Properties.of(Material.DIRT).strength(2f).noOcclusion()));
    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }

    private static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block){
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block){
        ItemRegistry.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));

    }

}
