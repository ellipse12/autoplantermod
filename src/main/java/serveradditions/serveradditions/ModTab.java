package serveradditions.serveradditions;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class ModTab {

    public static final CreativeModeTab Server_Additions_ModTab = new CreativeModeTab("serveradditions") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(Items.DIAMOND);
        }
    };



}

