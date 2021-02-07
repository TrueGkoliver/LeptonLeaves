package com.gkoliver.leptonleaves.core.registry;

import com.gkoliver.leptonleaves.LeptonLeaves;
import com.gkoliver.leptonleaves.common.block.LeafSlabBlock;
import com.gkoliver.leptonleaves.common.block.LeafStairsBlock;
import com.gkoliver.leptonleaves.common.block.LeafVerticalSlabBlock;
import com.gkoliver.leptonleaves.common.block.LeafWallBlock;
import com.google.common.collect.Lists;
import com.minecraftabnormals.abnormals_core.common.blocks.AbnormalsStairsBlock;
import com.minecraftabnormals.abnormals_core.common.blocks.VerticalSlabBlock;
import com.minecraftabnormals.abnormals_core.core.util.registry.BlockSubRegistryHelper;
import com.mojang.datafixers.util.Pair;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.FoliageColors;
import net.minecraft.world.IBlockDisplayReader;
import net.minecraft.world.biome.BiomeColors;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

import java.util.ArrayList;
import java.util.function.Supplier;

public class LeptonLeavesBlocks {
    public static final BlockSubRegistryHelper HELPER = LeptonLeaves.REGISTRY_HELPER.getBlockSubHelper();
    public static ArrayList<RegistryObject<Block>> TRANSPARENT_BLOCKS = Lists.newArrayList();
    public static ArrayList<Pair<RegistryObject<Block>, IBlockColor>> BLOCK_COLORS = Lists.newArrayList();
    public static final AbstractBlock.Properties PROP = AbstractBlock.Properties.create(Material.LEAVES).hardnessAndResistance(0.2F).sound(SoundType.PLANT).notSolid();

    public static ArrayList<RegistryObject<Block>> produceModCompat(String name, String modId) {
        RegistryObject<Block> slab = HELPER.createCompatBlock(modId,name+"_slab", () -> new LeafSlabBlock(PROP), ItemGroup.BUILDING_BLOCKS);
        RegistryObject<Block> stairs = HELPER.createCompatBlock(modId,name + "_stairs", () -> new LeafStairsBlock(Blocks.OAK_WOOD.getDefaultState(), PROP), ItemGroup.BUILDING_BLOCKS);
        RegistryObject<Block> wall = HELPER.createCompatBlock(modId,name + "_wall", () -> new LeafWallBlock(PROP), ItemGroup.DECORATIONS);
        RegistryObject<Block> vertical_slab = HELPER.createCompatBlock( name + "_vertical_slab", () -> new LeafVerticalSlabBlock(PROP), ItemGroup.BUILDING_BLOCKS, "quark",modId);
        ArrayList<RegistryObject<Block>> tbr = Lists.newArrayList();
        tbr.add(slab);
        tbr.add(stairs);
        tbr.add(wall);
        tbr.add(vertical_slab);
        TRANSPARENT_BLOCKS.addAll(tbr);
        return tbr;
    }

    public static ArrayList<RegistryObject<Block>> produce(String name, Block baseBlock) {
        RegistryObject<Block> slab = HELPER.createBlock(name + "_slab", () -> new LeafSlabBlock(Block.Properties.from(baseBlock)), ItemGroup.BUILDING_BLOCKS);
        RegistryObject<Block> stairs = HELPER.createBlock(name + "_stairs", () -> new LeafStairsBlock(Blocks.OAK_WOOD.getDefaultState(), Block.Properties.from(baseBlock)), ItemGroup.BUILDING_BLOCKS);
        RegistryObject<Block> wall = HELPER.createBlock(name + "_wall", () -> new LeafWallBlock(Block.Properties.from(baseBlock)), ItemGroup.DECORATIONS);
        RegistryObject<Block> vertical_slab = HELPER.createCompatBlock("quark", name + "_vertical_slab", () -> new LeafVerticalSlabBlock(Block.Properties.from(baseBlock)), ItemGroup.BUILDING_BLOCKS);
        ArrayList<RegistryObject<Block>> tbr = Lists.newArrayList();
        tbr.add(slab);
        tbr.add(stairs);
        tbr.add(wall);
        tbr.add(vertical_slab);
        TRANSPARENT_BLOCKS.addAll(tbr);
        return tbr;

    }

    public static ArrayList<RegistryObject<Block>> produce(String name, Block baseBlock, IBlockColor color) {
        RegistryObject<Block> slab = HELPER.createBlock(name+"_slab", () -> new LeafSlabBlock(PROP), ItemGroup.BUILDING_BLOCKS);
        RegistryObject<Block> stairs = HELPER.createBlock(name + "_stairs", () -> new LeafStairsBlock(Blocks.OAK_WOOD.getDefaultState(), PROP), ItemGroup.BUILDING_BLOCKS);
        RegistryObject<Block> wall = HELPER.createBlock(name + "_wall", () -> new LeafWallBlock(PROP), ItemGroup.DECORATIONS);
        RegistryObject<Block> vertical_slab = HELPER.createCompatBlock("quark", name + "_vertical_slab", () -> new LeafVerticalSlabBlock(PROP), ItemGroup.BUILDING_BLOCKS);
        ArrayList<RegistryObject<Block>> tbr = Lists.newArrayList();
        tbr.add(slab);
        tbr.add(stairs);
        tbr.add(wall);
        tbr.add(vertical_slab);
        TRANSPARENT_BLOCKS.addAll(tbr);
        tbr.forEach((x) -> {
            BLOCK_COLORS.add(Pair.of(x, color));
        });
        return tbr;
    }
    public static final IBlockColor BASE_FUNC = (state, reader, pos, color) -> {return reader != null && pos != null ? BiomeColors.getFoliageColor(reader, pos) : FoliageColors.getDefault();};
    public static void defineVariables() {
        OAK = produce("oak_leaves", Blocks.OAK_LEAVES, BASE_FUNC);
        BIRCH = produce("birch_leaves", Blocks.BIRCH_LEAVES, (state, reader, pos, color)->{return FoliageColors.getBirch();});
        JUNGLE = produce("jungle_leaves", Blocks.JUNGLE_LEAVES, BASE_FUNC);
        ACACIA = produce("acacia_leaves", Blocks.ACACIA_LEAVES, BASE_FUNC);
        DARK_OAK = produce("dark_oak_leaves", Blocks.DARK_OAK_LEAVES, BASE_FUNC);
        SPRUCE = produce("spruce_leaves", Blocks.SPRUCE_LEAVES, (state, reader, pos, color)->{return FoliageColors.getSpruce();});
    }

    public static ArrayList<RegistryObject<Block>> OAK;
    public static ArrayList<RegistryObject<Block>> BIRCH;
    public static ArrayList<RegistryObject<Block>> SPRUCE;
    public static ArrayList<RegistryObject<Block>> ACACIA;
    public static ArrayList<RegistryObject<Block>> JUNGLE;
    public static ArrayList<RegistryObject<Block>> DARK_OAK;

    //Modded momente
    public static ArrayList<RegistryObject<Block>> RED_MAPLE = produceModCompat("red_maple_leaves", "autumnity");
    public static ArrayList<RegistryObject<Block>> ORANGE_MAPLE = produceModCompat("orange_maple_leaves", "autumnity");
    public static ArrayList<RegistryObject<Block>> YELLOW_MAPLE = produceModCompat("yellow_maple_leaves", "autumnity");
    public static ArrayList<RegistryObject<Block>> GREEN_MAPLE = produceModCompat("maple_leaves", "autumnity");

    public static ArrayList<RegistryObject<Block>> PINK_WISTERIA = produceModCompat("pink_wisteria_leaves", "environmental");
    public static ArrayList<RegistryObject<Block>> BLUE_WISTERIA = produceModCompat("blue_wisteria_leaves", "environmental");
    public static ArrayList<RegistryObject<Block>> PURPLE_WISTERIA = produceModCompat("purple_wisteria_leaves", "environmental");
    public static ArrayList<RegistryObject<Block>> WHITE_WISTERIA = produceModCompat("white_wisteria_leaves", "environmental");

    public static ArrayList<RegistryObject<Block>> WILLOW = produceModCompat("willow_leaves", "environmental");
    public static ArrayList<RegistryObject<Block>> CHERRY = produceModCompat("cherry_leaves", "environmental");

    public static ArrayList<RegistryObject<Block>> BLUE_BLOSSOM = produceModCompat("blue_blossom_leaves", "quark");
    public static ArrayList<RegistryObject<Block>> LAVENDER_BLOSSOM = produceModCompat("lavender_blossom_leaves", "quark");
    public static ArrayList<RegistryObject<Block>> ORANGE_BLOSSOM = produceModCompat("orange_blossom_leaves", "quark");
    public static ArrayList<RegistryObject<Block>> PINK_BLOSSOM = produceModCompat("pink_blossom_leaves", "quark");
    public static ArrayList<RegistryObject<Block>> YELLOW_BLOSSOM = produceModCompat("yellow_blossom_leaves", "quark");
    public static ArrayList<RegistryObject<Block>> RED_BLOSSOM = produceModCompat("red_blossom_leaves", "quark");

    public static ArrayList<RegistryObject<Block>> RIVER = produceModCompat("river_leaves", "upgrade_aquatic");

    public static ArrayList<RegistryObject<Block>> MORADO = produceModCompat("morado_leaves", "atmospheric");
    public static ArrayList<RegistryObject<Block>> ROSEWOOD = produceModCompat("rosewood_leaves", "atmospheric");
    public static ArrayList<RegistryObject<Block>> FLOWERING_MORADO = produceModCompat("flowering_morado_leaves", "atmospheric");
    public static ArrayList<RegistryObject<Block>> YUCCA = produceModCompat("yucca_leaves", "atmospheric");
    public static ArrayList<RegistryObject<Block>> KOUSA = produceModCompat("kousa_leaves", "atmospheric");
    public static ArrayList<RegistryObject<Block>> ASPEN = produceModCompat("aspen_leaves", "atmospheric");
    public static ArrayList<RegistryObject<Block>> GRIMWOOD = produceModCompat("grimwood_leaves", "atmospheric");

    public static ArrayList<RegistryObject<Block>> TWISTED = produceModCompat("twisted_leaves", "architects_palette");

    public static void doClient() {
        for (RegistryObject<Block> blockR : TRANSPARENT_BLOCKS) {
            RenderTypeLookup.setRenderLayer(blockR.get(), RenderType.getCutout());
        }

    }

    public static void onColors(ColorHandlerEvent.Block event) {
        for (Pair<RegistryObject<Block>, IBlockColor> pair : BLOCK_COLORS) {
            event.getBlockColors().register(pair.getSecond(), pair.getFirst().get());
        }
    }
    public static void onItems(ColorHandlerEvent.Item event) {
        for (Pair<RegistryObject<Block>, IBlockColor> pair : BLOCK_COLORS) {
            event.getItemColors().register((stack, color) -> {
                BlockState blockstate = ((BlockItem)stack.getItem()).getBlock().getDefaultState();
                return event.getBlockColors().getColor(blockstate, (IBlockDisplayReader)null, (BlockPos)null, color);
            }, pair.getFirst().get());
        }
    }
}