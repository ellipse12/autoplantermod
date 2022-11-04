package autoplanter;

import autoplanter.blocks.BlockRegistry;
import autoplanter.blocks.entities.BlockEntities;
import autoplanter.items.ItemRegistry;
import autoplanter.screens.AutoPlanterScreen;
import autoplanter.screens.ModMenuTypes;
import com.mojang.logging.LogUtils;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import static autoplanter.AutoPlanter.MOD_ID;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(MOD_ID)
public class AutoPlanter {

    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final String MOD_ID = "autoplanter";
    public static boolean isMAInstalled = false;

    public AutoPlanter() {
        // Register the setup method for modloading
        var bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.register(this);
        bus.addListener(this::setup);
        bus.addListener(this::clientSetup);

        ItemRegistry.register(bus);
        BlockRegistry.register(bus);
        BlockEntities.register(bus);
        ModMenuTypes.register(bus);
        isMAInstalled = ModList.get().isLoaded("mysticalagriculture");
        MinecraftForge.EVENT_BUS.register(this);





    }

    private void clientSetup(final FMLClientSetupEvent event){
        MenuScreens.register(ModMenuTypes.AUTO_PLANTER_MENU.get(), AutoPlanterScreen::new);
    }







    private void setup(final FMLCommonSetupEvent event) {

        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getName());

    }


    }
