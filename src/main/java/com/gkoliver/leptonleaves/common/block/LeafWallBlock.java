package com.gkoliver.leptonleaves.common.block;

import eltrut.lepton.common.blocks.AlphaLogWallBlock;
import eltrut.lepton.common.blocks.AlphaStrippedLogWallBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.WallBlock;
import net.minecraft.util.Direction;

public class LeafWallBlock extends WallBlock {
    public LeafWallBlock(Properties properties) {
        super(properties);
    }
    public boolean isSideInvisible(BlockState bs1, BlockState bs2, Direction side) {
        return bs1 == bs2;
    }
}
