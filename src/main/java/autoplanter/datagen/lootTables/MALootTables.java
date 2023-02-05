package autoplanter.datagen.lootTables;

import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTables;
import net.minecraft.world.level.storage.loot.ValidationContext;

import java.util.List;
import java.util.Map;

public class MALootTables extends LootTableProvider {

    public MALootTables(PackOutput output, List<LootTableProvider.SubProviderEntry> providerEntries) {
        super(output, BuiltInLootTables.all(), providerEntries);
    }

    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, ValidationContext validationcontext) {
        map.forEach((location, lootTable) -> LootTables.validate(validationcontext, location, lootTable));
    }
}
