package autoplanter.datagen;

import autoplanter.AutoPlanter;
import autoplanter.datagen.lootTables.MABlockLoot;
import autoplanter.datagen.lootTables.MALootTables;
import autoplanter.datagen.tags.RecipeItemTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Collections;

@Mod.EventBusSubscriber(modid = AutoPlanter.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        ExistingFileHelper fileHelper = event.getExistingFileHelper();
        var lookUpProvider = event.getLookupProvider();
        generator.addProvider(event.includeServer(), new RecipeItemTags(output, lookUpProvider, AutoPlanter.MOD_ID, fileHelper));
        if(AutoPlanter.isMAInstalled) {
            generator.addProvider(true, new MALootTables(output, Collections.singletonList(new LootTableProvider.SubProviderEntry(MABlockLoot::new, LootContextParamSets.EMPTY))));

        }
    }
}
