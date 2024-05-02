package net.pasuki.mccourse.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;

public class AlexandriteLampBlock extends Block {
    public static final BooleanProperty POWERED = BooleanProperty.create("powered");

    @SuppressWarnings("unused")
    public AlexandriteLampBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.defaultBlockState().setValue(POWERED,false));
    }


    @Override
    @SuppressWarnings({"deprecation", "NullableProblems"})
    public  InteractionResult use( BlockState pState, Level pLevel, BlockPos pPos,  Player pPlayer,  InteractionHand pHand,  BlockHitResult pHit) {

        if (!pLevel.isClientSide() && pHand == InteractionHand.MAIN_HAND){
            boolean currentState = pState.getValue(POWERED);
            pLevel.setBlock(pPos, pState.setValue(POWERED, !currentState),3);
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(POWERED, pContext.getLevel().hasNeighborSignal(pContext.getClickedPos()));
    }

    @Override
    @SuppressWarnings({"deprecation", "NullableProblems"})
    public void neighborChanged(BlockState pState, Level pLevel, BlockPos pPos, Block pBlock, BlockPos pFromPos, boolean pIsMoving) {
        if (!pLevel.isClientSide) {
            boolean currentState = pState.getValue(POWERED);
            if (currentState != pLevel.hasNeighborSignal(pPos)) {
                if (currentState) {
                    pLevel.scheduleTick(pPos, this, 4);
                } else {
                    pLevel.setBlock(pPos, pState.cycle(POWERED), 2);
                }
            }

        }
    }

    @SuppressWarnings({"deprecation", "NullableProblems"})
    public void tick(BlockState pState,  ServerLevel pLevel,  BlockPos pPos,  RandomSource pRandom) {
        if (pState.getValue(POWERED) && !pLevel.hasNeighborSignal(pPos)) {
           pLevel.setBlock(pPos, pState.cycle(POWERED), 2);
        }

    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(POWERED);
    }
}
