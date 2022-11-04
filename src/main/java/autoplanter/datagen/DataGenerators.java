package autoplanter.datagen;

import autoplanter.AutoPlanter;
import autoplanter.datagen.lootTables.MALootTables;
import autoplanter.datagen.tags.BlockTags;
import autoplanter.datagen.tags.RecipeItemTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = AutoPlanter.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        BlockTagsProvider blockTags = new BlockTags(generator, AutoPlanter.MOD_ID, event.getExistingFileHelper());
        if(event.includeServer()){
            generator.addProvider(true, new RecipeItemTags(generator, blockTags, AutoPlanter.MOD_ID,event.getExistingFileHelper()));
            if(AutoPlanter.isMAInstalled) {
                generator.addProvider(true, new MALootTables(generator));
            }

        }
    }
}
