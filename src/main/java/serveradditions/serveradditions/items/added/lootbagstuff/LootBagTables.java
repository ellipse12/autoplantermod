package serveradditions.serveradditions.items.added.lootbagstuff;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;

import java.util.List;

public class LootBagTables {
    List<ItemStack> commonBag;
    List<ItemStack> bookBag;
    List<ItemStack> foodBag;
    List<ItemStack> valuablesBag;
    List<ItemStack> dustBag;
    List<ItemStack> bagsBag;
    List<ItemStack> epicBag;
    List<ItemStack> enderBag;
    List<ItemStack> uncommonBag;
    List<ItemStack> rareBag;

    public LootBagTables() {
       super();
    }

    public List<ItemStack> getCommonBag() {
        TagKey<Item> logs = ItemTags.create(new ResourceLocation("forge", "logs"));


        commonBag.add(new ItemStack(Items.COBBLESTONE));


        return commonBag;
    }

    public List<ItemStack> getBookBag() {
        return bookBag;
    }

    public List<ItemStack> getFoodBag() {
        return foodBag;
    }

    public List<ItemStack> getValuablesBag() {
        return valuablesBag;
    }

    public List<ItemStack> getDustBag() {
        return dustBag;
    }

    public List<ItemStack> getBagsBag() {
        return bagsBag;
    }

    public List<ItemStack> getEpicBag() {
        return epicBag;
    }

    public List<ItemStack> getEnderBag() {
        return enderBag;
    }

    public List<ItemStack> getUncommonBag() {
        return uncommonBag;
    }

    public List<ItemStack> getRareBag() {
        return rareBag;
    }
}
