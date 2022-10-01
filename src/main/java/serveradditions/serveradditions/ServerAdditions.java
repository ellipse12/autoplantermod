package serveradditions.serveradditions;

import com.mojang.logging.LogUtils;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.core.util.Loader;
import org.slf4j.Logger;
import serveradditions.serveradditions.blocks.BlockRegistry;
import serveradditions.serveradditions.blocks.entities.BlockEntities;
import serveradditions.serveradditions.items.ItemRegistry;

import serveradditions.serveradditions.screens.AutoPlanterScreen;
import serveradditions.serveradditions.screens.MenuTypes;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("serveradditions")
public class ServerAdditions {

    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final String MOD_ID = "serveradditions";
    public static boolean mysticalAgricultureInstalled = false;

    public ServerAdditions() {
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ItemRegistry.register(eventBus);
        BlockRegistry.register(eventBus);
        BlockEntities.register(eventBus);
        MenuTypes.register(eventBus);


        MinecraftForge.EVENT_BUS.register(this);
        mysticalAgricultureInstalled = Loader.isClassAvailable("MysticalAgriculture");

    }

    private void clientSetup(final FMLClientSetupEvent event){
        MenuScreens.register(MenuTypes.AUTO_PLANTER_MENU.get(), AutoPlanterScreen::new);
    }



    private void setup(final FMLCommonSetupEvent event) {

        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }


    }
