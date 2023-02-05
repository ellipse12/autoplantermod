package autoplanter.datagen.lootTables;

import com.blakebr0.mysticalagriculture.api.MysticalAgricultureAPI;
import com.blakebr0.mysticalagriculture.api.crop.Crop;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;
import java.util.function.BiConsumer;

public class MABlockLoot extends BlockLootSubProvider {


    public MABlockLoot() {
        super(Collections.emptySet(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        List<Crop> crops = MysticalAgricultureAPI.getCropRegistry().getCrops().stream().toList();
        LootPool.Builder pool;
        for (Crop crop : crops) {
            Item item = crop.getCropBlock().asItem();
            pool = LootPool.lootPool().add(LootItem.lootTableItem(crop.getEssenceItem()));
            add(Block.byItem(item), LootTable.lootTable().withPool(pool.name(crop.getNameWithSuffix("seeds"))));
        }
    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        return MysticalAgricultureAPI.getCropRegistry().getCrops().stream().map(Crop::getCropBlock).map(CropBlock::asItem).map(Block::byItem).toList();
    }

    @Override
    public void generate(BiConsumer<ResourceLocation, LootTable.Builder> p_249322_) {
        super.generate(p_249322_);
    }
}
