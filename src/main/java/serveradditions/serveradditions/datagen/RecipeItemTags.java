package serveradditions.serveradditions.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import serveradditions.serveradditions.ServerAdditions;

public class RecipeItemTags extends ItemTagsProvider {
    private TagKey<Item> itemTagKey = ItemTags.create(new ResourceLocation("serveradditions", "recipe_tags"));
    public RecipeItemTags(DataGenerator pGenerator, BlockTagsProvider pBlockTagsProvider, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(pGenerator, pBlockTagsProvider, modId, existingFileHelper);
    }
    @Override
    protected void addTags() {
        TagKey<Item> seedsTagKey = ItemTags.create(new ResourceLocation("forge", "seeds"));
        TagKey<Item> mysticalAgricultureTagKey = ItemTags.create(new ResourceLocation("mysticalagriculture", "seeds"));
            if(!ServerAdditions.mysticalAgricultureInstalled){
                tag(itemTagKey).addTag(seedsTagKey).add(Items.SUGAR_CANE);
            }else{
                tag(itemTagKey).addTag(seedsTagKey).add(Items.SUGAR_CANE).addTag(mysticalAgricultureTagKey);
            }
    }
    @Override
    public String getName() {
        return "Recipe Tags";
    }
}
