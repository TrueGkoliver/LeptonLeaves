package com.gkoliver.leptonleaves;

import com.gkoliver.leptonleaves.core.registry.LeptonLeavesBlocks;
import com.minecraftabnormals.abnormals_core.core.util.registry.RegistryHelper;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(LeptonLeaves.MOD_ID)
public class LeptonLeaves
{
    public static final String MOD_ID = "leptonleaves";
    public static final RegistryHelper REGISTRY_HELPER = new RegistryHelper(LeptonLeaves.MOD_ID);
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public LeptonLeaves() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        REGISTRY_HELPER.register(eventBus);
        LeptonLeavesBlocks.defineVariables();
        eventBus.addListener(this::clientSetup);
        eventBus.addListener(this::onColor);
        eventBus.addListener(this::onItemColor);
        MinecraftForge.EVENT_BUS.register(this);
    }



    private void clientSetup(final FMLClientSetupEvent event) {
        LeptonLeavesBlocks.doClient();
    }
    @OnlyIn(Dist.CLIENT)
    private void onColor(ColorHandlerEvent.Block event) {
        LeptonLeavesBlocks.onColors(event);
    }
    @OnlyIn(Dist.CLIENT)
    private void onItemColor(ColorHandlerEvent.Item event) {
        LeptonLeavesBlocks.onItems(event);
    }
}
