package autoplanter.datagen.lootTables;

import com.mojang.datafixers.util.Pair;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTables;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class MALootTables extends LootTableProvider {
    public MALootTables(DataGenerator pGenerator) {
        super(pGenerator);
    }
    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, @NotNull ValidationContext validationTracker) {
        map.forEach((location, lootTable) -> LootTables.validate(validationTracker, location, lootTable));
    }

    @Override
    protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>> getTables() {
        return List.of(Pair.of(MABlockLoot::new, LootContextParamSets.BLOCK));
    }
}
