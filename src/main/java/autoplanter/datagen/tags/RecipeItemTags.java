package autoplanter.datagen.tags;

import autoplanter.AutoPlanter;
import com.blakebr0.mysticalagriculture.api.MysticalAgricultureAPI;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.StemBlock;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class RecipeItemTags extends TagsProvider<Item> {
    private final PackOutput output;
    private TagKey<Item> itemTagKey = ItemTags.create(new ResourceLocation(AutoPlanter.MOD_ID, "recipe_tags"));

    public RecipeItemTags(PackOutput output, CompletableFuture<HolderLookup.Provider> lookup, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, ForgeRegistries.Keys.ITEMS, lookup, modId, existingFileHelper);
        this.output = output;
    }

    @Override
    public void addTags(HolderLookup.Provider pProvider) {
        this.tag(itemTagKey).add(ForgeRegistries.ITEMS.getResourceKey(Items.COCOA_BEANS).get()).add(ForgeRegistries.ITEMS.getResourceKey(Items.SUGAR_CANE).get()).addTag(ItemTags.create(new ResourceLocation("forge", "seeds")));
        for(Block block : ForgeRegistries.BLOCKS.getValues()){
            if(block instanceof CropBlock || block instanceof StemBlock){
                this.tag(itemTagKey).add(ForgeRegistries.ITEMS.getResourceKey(Item.byBlock(block)).get());
            }
        }
        if(AutoPlanter.isMAInstalled){
            for(var item: MysticalAgricultureAPI.getCropRegistry().getCrops()){
                var seed = ForgeRegistries.ITEMS.getResourceKey(item.getSeedsItem()).get();
                tag(itemTagKey).add(seed);
            }
        }
    }

    @Override
    public String getName() {
        return "Recipe Tags";
    }
}
