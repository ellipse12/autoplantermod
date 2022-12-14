package autoplanter.blocks.entities;

import autoplanter.AutoPlanter;
import autoplanter.blocks.BlockRegistry;
import autoplanter.blocks.entities.added.AutoPlanterBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, AutoPlanter.MOD_ID);

    public static final RegistryObject<BlockEntityType<AutoPlanterBlockEntity>> AUTO_PLANTER_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("auto_planter",
                    () -> BlockEntityType.Builder.of(AutoPlanterBlockEntity::new,
                            BlockRegistry.AUTO_PLANTER.get()).build(null));

    public static void register(IEventBus eventBus){
        BLOCK_ENTITIES.register(eventBus);
    }
}
