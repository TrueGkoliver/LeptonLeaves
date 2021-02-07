package com.gkoliver.leptonleaves.common.block;

import com.minecraftabnormals.abnormals_core.common.blocks.VerticalSlabBlock;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;

public class LeafVerticalSlabBlock extends VerticalSlabBlock {
    public LeafVerticalSlabBlock(Properties properties) {
        super(properties);
    }
    public boolean isSideInvisible(BlockState bs1, BlockState bs2, Direction side) {
        return bs1 == bs2;
    }
}
