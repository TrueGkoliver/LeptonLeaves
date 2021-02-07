package com.gkoliver.leptonleaves.common.block;

import com.minecraftabnormals.abnormals_core.common.blocks.AbnormalsStairsBlock;
import eltrut.lepton.common.blocks.AlphaLogWallBlock;
import eltrut.lepton.common.blocks.AlphaStrippedLogWallBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.StairsBlock;
import net.minecraft.util.Direction;

public class LeafStairsBlock extends AbnormalsStairsBlock {
    public LeafStairsBlock(BlockState state, Properties properties) {
        super(state, properties);
    }
    public boolean isSideInvisible(BlockState bs1, BlockState bs2, Direction side) {
        return bs1 == bs2;
    }
}
