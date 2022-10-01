package autoplanter.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
import autoplanter.AutoPlanter;

@Mod.EventBusSubscriber(modid = AutoPlanter.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        BlockTagsProvider blockTags = new BlockTags(generator, AutoPlanter.MOD_ID, event.getExistingFileHelper());
        if(event.includeServer()){
            generator.addProvider(new RecipeItemTags(generator, blockTags, AutoPlanter.MOD_ID,event.getExistingFileHelper()));
        }
    }
}
