package autoplanter.datagen.lootTables;

import autoplanter.blocks.BlockRegistry;
import com.blakebr0.mysticalagriculture.api.MysticalAgricultureAPI;
import com.blakebr0.mysticalagriculture.api.crop.Crop;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.storage.loot.ItemModifierManager;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.*;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctions;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;
import net.minecraft.world.level.storage.loot.providers.number.NumberProviders;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;

public class MABlockLoot extends BlockLoot {
    @Override
    protected void addTables() {
        List<Crop> crops = MysticalAgricultureAPI.getCropRegistry().getCrops().stream().toList();
        LootPool.Builder pool;
        for (Crop crop : crops) {
            Item item = crop.getCropBlock().asItem();
            pool = LootPool.lootPool().add(LootItem.lootTableItem(crop.getEssenceItem()));
            add(Block.byItem(item), LootTable.lootTable().withPool(pool.name(item.getName(new ItemStack(item)).getString())));
        }
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return MysticalAgricultureAPI.getCropRegistry().getCrops().stream().map(Crop::getCropBlock).map(CropBlock::asItem).map(Block::byItem).toList();
    }
}
