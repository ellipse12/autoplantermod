package autoplanter.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.MelonBlock;
import net.minecraft.world.level.block.StemBlock;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;
import autoplanter.AutoPlanter;

public class RecipeItemTags extends ItemTagsProvider {
    private TagKey<Item> itemTagKey = ItemTags.create(new ResourceLocation(AutoPlanter.MOD_ID, "recipe_tags"));
    public RecipeItemTags(DataGenerator pGenerator, BlockTagsProvider pBlockTagsProvider, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(pGenerator, pBlockTagsProvider, modId, existingFileHelper);
    }
    @Override
    protected void addTags() {
        for (Block block : ForgeRegistries.BLOCKS) {
            if(block instanceof CropBlock | block instanceof StemBlock) {
                tag(itemTagKey).add(block.asItem());
            }
        }
        tag(itemTagKey).add(Items.SUGAR_CANE).addTag(ItemTags.create(new ResourceLocation("forge", "seeds")));
        TagKey<Item> mysticalAgricultureTagKey = ItemTags.create(new ResourceLocation("mysticalagriculture", "seeds"));
        if(AutoPlanter.mysticalAgricultureInstalled){
            tag(itemTagKey).addTag(mysticalAgricultureTagKey);
        }
    }
    @Override
    public String getName() {
        return "Recipe Tags";
    }
}
