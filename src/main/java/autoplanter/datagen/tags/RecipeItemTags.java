package autoplanter.datagen.tags;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.api.MysticalAgricultureAPI;
import net.minecraft.client.Minecraft;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.player.Player;
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
    private TagKey<Item> MATagKey = ItemTags.create(new ResourceLocation("mysticalagriculture", "seeds"));
    public RecipeItemTags(DataGenerator pGenerator, BlockTagsProvider pBlockTagsProvider, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(pGenerator, pBlockTagsProvider, modId, existingFileHelper);
    }
    @Override
    protected void addTags() {
        //for (Block block : ForgeRegistries.BLOCKS) {
        //    if(block instanceof CropBlock | block instanceof StemBlock) {
        //        tag(itemTagKey).add(block.asItem());
        //    }
        //}
        tag(itemTagKey).add(Items.SUGAR_CANE).addTag(ItemTags.create(new ResourceLocation("forge", "seeds")));



       if(AutoPlanter.isMAInstalled){
           tag(MATagKey);
           TagKey<Item> mysticalAgricultureTagKey = ItemTags.create(MATagKey.location());
           tag(itemTagKey).addTag(mysticalAgricultureTagKey);

       }

    }
    @Override
    public String getName() {
        return "Recipe Tags";
    }
}
