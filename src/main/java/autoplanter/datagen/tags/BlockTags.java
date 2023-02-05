package autoplanter.datagen.tags;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class BlockTags extends TagsProvider<Block> {


    protected BlockTags(PackOutput output, CompletableFuture<HolderLookup.Provider> lookup, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, ForgeRegistries.Keys.BLOCKS, lookup, modId, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {

    }
}
