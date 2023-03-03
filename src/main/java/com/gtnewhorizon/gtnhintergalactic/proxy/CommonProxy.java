package com.gtnewhorizon.gtnhintergalactic.proxy;

import net.minecraft.util.IIcon;

import com.gtnewhorizon.gtnhintergalactic.block.IGBlocks;
import com.gtnewhorizon.gtnhintergalactic.config.Config;
import com.gtnewhorizon.gtnhintergalactic.item.IGItems;
import com.gtnewhorizon.gtnhintergalactic.loader.MachineLoader;
import com.gtnewhorizon.gtnhintergalactic.loader.RecipeLoader;
import com.gtnewhorizon.gtnhintergalactic.nei.IMCForNEI;
import com.gtnewhorizon.gtnhintergalactic.recipe.IG_RecipeAdder;
import com.gtnewhorizon.gtnhintergalactic.recipe.MachineRecipes;
import com.gtnewhorizon.gtnhintergalactic.recipe.SpaceProjectRegistration;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import gregtech.api.enums.Textures;
import gregtech.api.interfaces.ITexture;

/**
 * Proxy used by both, the server and the client to load stuff
 *
 * @author minecraft7771
 */
public class CommonProxy {

    // preInit "Run before anything else. Read your config, create blocks, items, etc, and register them with the
    // GameRegistry." (Remove if not needed)
    public void preInit(FMLPreInitializationEvent event) {
        Config.synchronizeConfiguration(event.getSuggestedConfigurationFile());
    }

    // load "Do your mod setup. Build whatever data structures you care about. Register recipes." (Remove if not needed)
    public void init(FMLInitializationEvent event) {
        IMCForNEI.IMCSender();
        if (Textures.BlockIcons.casingTexturePages[32] == null) {
            Textures.BlockIcons.casingTexturePages[32] = new ITexture[128];
        }
        IGItems.init();
        IGBlocks.init();
        new MachineLoader().run();
        IG_RecipeAdder.init();
    }

    // postInit "Handle interaction with other mods, complete your setup based on this." (Remove if not needed)
    public void postInit(FMLPostInitializationEvent event) {
        new RecipeLoader().run();
        new SpaceProjectRegistration().run();
        new MachineRecipes().run();
        IG_RecipeAdder.postInit();
    }

    // register server commands in this event handler (Remove if not needed)
    public void serverStarting(FMLServerStartingEvent event) {}

    /**
     * Mark a texture as used, to prevent hodgepodge from optimizing it
     *
     * @param o Textured to be used
     */
    public void markTextureUsed(IIcon o) {}
}