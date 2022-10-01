package autoplanter;

import autoplanter.blocks.BlockRegistry;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.registries.ForgeRegistries;

public class ModTab {

    public static final CreativeModeTab Auto_Planter_ModTab = new CreativeModeTab(AutoPlanter.MOD_ID) {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(BlockRegistry.AUTO_PLANTER.get().asItem());
        }
    };



}

