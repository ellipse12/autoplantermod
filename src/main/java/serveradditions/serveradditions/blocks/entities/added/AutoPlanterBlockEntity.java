package serveradditions.serveradditions.blocks.entities.added;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.mojang.authlib.properties.Property;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.datafix.fixes.BlockStateData;
import net.minecraft.world.Container;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.StemBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.storage.loot.*;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.property.Properties;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import serveradditions.serveradditions.blocks.added.AutoPlanterBlock;
import serveradditions.serveradditions.blocks.entities.BlockEntities;
import serveradditions.serveradditions.screens.AutoPlanterMenu;

import javax.annotation.Nonnull;
import javax.annotation.PropertyKey;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;



public class AutoPlanterBlockEntity extends BlockEntity implements MenuProvider {
    private static boolean isActive;

    private static Block block = Blocks.AIR;
    private static ItemStack stack = new ItemStack(Items.AIR);
    TagKey<Item> itemTagKey = ItemTags.create(new ResourceLocation("serveradditions", "recipe_tags"));

    private final ItemStackHandler itemHandler = new ItemStackHandler(2){
        @Override
        protected void onContentsChanged(int slot) {
            stack = itemHandler.getStackInSlot(0);
            block = Block.byItem(stack.getItem());
            resetProgress();
            setChanged();
        }
    };



    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 525;


    public AutoPlanterBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(BlockEntities.AUTO_PLANTER_BLOCK_ENTITY.get(), pWorldPosition, pBlockState);
        this.data = new ContainerData() {
            public int get(int index) {
                switch (index) {
                    case 0: return AutoPlanterBlockEntity.this.progress;
                    case 1: return AutoPlanterBlockEntity.this.maxProgress;
                    default: return 0;
                }
            }

            public void set(int index, int value) {
                switch(index) {
                    case 0: AutoPlanterBlockEntity.this.progress = value; break;
                    case 1: AutoPlanterBlockEntity.this.maxProgress = value; break;
                }
            }

            public int getCount() {
                return 2;
            }
        };

    }

    @Override
    public Component getDisplayName() {
        return new TextComponent("Auto Planter");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory, Player pPlayer) {
        return new AutoPlanterMenu(pContainerId, pInventory, this, this.data);
    }
    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {

            return lazyItemHandler.cast();

        }

        return super.getCapability(cap);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        stack = this.itemHandler.getStackInSlot(0);
        block = Block.byItem(stack.getItem());
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps()  {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag) {
        tag.put("inventory", itemHandler.serializeNBT());
        tag.putInt("auto_planter.progress", progress);
        super.saveAdditional(tag);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        itemHandler.deserializeNBT(nbt.getCompound("inventory"));
        progress = nbt.getInt("auto_planter.progress");
    }


    public void tick() {
        BlockEntity container = getNearbyContainer(level, getBlockPos());
        //ItemStack stack = itemHandler.getStackInSlot(0);
        //Block block = Block.byItem(stack.getItem());
        if((stack.is(itemTagKey)) && container != null){
            isActive = true;
        }else{
            isActive = false;
        }
        if(this.itemHandler.getStackInSlot(1).is(Items.DIRT) && isActive){
            this.maxProgress = getMaxTime(block);
            this.progress++;
            if(this.progress >= this.maxProgress){
                this.dropLoot(container, block);
            }
            setChanged(level, getBlockPos(), getBlockState());
        }


    }
    private void resetProgress() {
        this.progress = 0;
    }

    private Block getBlockType(Block block){
        Block stemBlock;
        if (block.getClass() == StemBlock.class) {
            stemBlock = ((StemBlock) block).getFruit();
            return stemBlock;
        }else{
            return block;
        }
    }
    private BlockState getMaxBlockState(Block block){
        BlockState blockState = block.defaultBlockState();
        if (block.getClass() == StemBlock.class) {
            blockState = ((StemBlock) block).getFruit().defaultBlockState();
        } else {
            blockState = block.defaultBlockState().setValue((IntegerProperty) block.getStateDefinition().getProperty("age"), getMaxAge(block));
         }
        return blockState;
    }
    private int getMaxAge(Block block){
        Object[] list = block.getStateDefinition().getProperty("age").getPossibleValues().toArray();
        int max_age = (int)list[list.length - 1];
        return max_age;
    }

    private int getMaxTime(Block block){
        int time = 525;
        if(block.getClass() == CropBlock.class){
            time = ((CropBlock) block).getMaxAge() * 75;
        }else{
            time = getMaxAge(block) * 75;
        }
        return time;
    }


    private void dropLoot(BlockEntity container, Block block) {
        Vec3 position = new Vec3(getBlockPos().getX(), getBlockPos().getY(), getBlockPos().getZ());
        BlockState blockState = getMaxBlockState(block);
        Block block1 = getBlockType(block);
        ResourceLocation lootTableLocation = block1.getLootTable();
        MinecraftServer server = level.getServer();
        LootTables lootTables = server.getLootTables();
        LootTable lootTable = lootTables.get(lootTableLocation);
        ItemStack tool = new ItemStack(Items.NETHERITE_HOE);
        LootContext.Builder builder = new LootContext.Builder((ServerLevel) level)
                .withParameter(LootContextParams.ORIGIN, position)
                .withParameter(LootContextParams.BLOCK_STATE, blockState)
                .withParameter(LootContextParams.TOOL, tool);
        LootContext context = builder.create(LootContextParamSets.BLOCK);
        if (container != null && lootTable != null) {
            fillContainer(container, lootTable, context);
        }
        this.resetProgress();
    }

    private static void fillContainer(BlockEntity container, LootTable lootTable,LootContext lctx) {
        List<ItemStack> generated = lootTable.getRandomItems(lctx);
        container.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(iItemHandler -> {
            for (ItemStack itemStack : generated) {
                    ItemHandlerHelper.insertItemStacked(iItemHandler, itemStack, false);
            }
        });




    }





    public static BlockEntity getNearbyContainer(Level pLevel, BlockPos pPos) {
        BlockEntity blockEntity = pLevel.getBlockEntity(pPos.below());
        if (blockEntity instanceof Container) {
            return blockEntity;
        }
        return null;
    }

}
