package autoplanter.datagen.tags;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.api.MysticalAgricultureAPI;
import com.blakebr0.mysticalagriculture.api.crop.Crop;
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

    public RecipeItemTags(DataGenerator pGenerator, BlockTagsProvider pBlockTagsProvider, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(pGenerator, pBlockTagsProvider, modId, existingFileHelper);
    }

    @Override
    protected void addTags() {
        tag(itemTagKey).add(Items.SUGAR_CANE).addTag(ItemTags.create(new ResourceLocation("forge", "seeds")));
       if(AutoPlanter.isMAInstalled){
           for(Item item : MysticalAgricultureAPI.getCropRegistry().getCrops().stream().map(Crop::getSeedsItem).toList()){
               tag(itemTagKey).add(item);
           }
       }
    }

    @Override
    public String getName() {
        return "Recipe Tags";
    }
}
