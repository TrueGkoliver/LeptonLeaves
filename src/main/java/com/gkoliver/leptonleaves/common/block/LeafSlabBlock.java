package com.gkoliver.leptonleaves.common.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.SlabBlock;
import net.minecraft.util.Direction;

public class LeafSlabBlock extends SlabBlock {
    public LeafSlabBlock(Properties properties) {
        super(properties);
    }
    public boolean isSideInvisible(BlockState bs1, BlockState bs2, Direction side) {
        return bs1 == bs2;
    }
}
