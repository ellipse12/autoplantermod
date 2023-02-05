package autoplanter;

import autoplanter.blocks.BlockRegistry;
import autoplanter.items.ItemRegistry;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
@Mod.EventBusSubscriber(modid = AutoPlanter.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModTab {

    public static CreativeModeTab AUTO_PLANTER_TAB;

    @SubscribeEvent
    public static void registerCreativeTabs(CreativeModeTabEvent.Register event){
        AUTO_PLANTER_TAB = event.registerCreativeModeTab(new ResourceLocation(AutoPlanter.MOD_ID, "auto_planter_tab"),
                builder-> builder.icon(()-> new ItemStack(BlockRegistry.AUTO_PLANTER.get().asItem())).title(Component.translatable("auto_planter_tab")).build());
    }

    public static void addCreative(CreativeModeTabEvent.BuildContents event){
        if(event.getTab() == ModTab.AUTO_PLANTER_TAB){
            event.accept(ItemRegistry.UPGRADE_ITEM);
            event.accept(BlockRegistry.AUTO_PLANTER.get().asItem());
        }
    }


}

