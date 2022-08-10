package serveradditions.serveradditions.blocks.entities;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import serveradditions.serveradditions.ServerAdditions;
import serveradditions.serveradditions.blocks.BlockRegistry;
import serveradditions.serveradditions.blocks.entities.added.AutoPlanterBlockEntity;

public class BlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, ServerAdditions.MOD_ID);

    public static final RegistryObject<BlockEntityType<AutoPlanterBlockEntity>> AUTO_PLANTER_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("auto_planter",
                    () -> BlockEntityType.Builder.of(AutoPlanterBlockEntity::new,
                            BlockRegistry.AUTO_PLANTER.get()).build(null));

    public static void register(IEventBus eventBus){
        BLOCK_ENTITIES.register(eventBus);
    }
}
