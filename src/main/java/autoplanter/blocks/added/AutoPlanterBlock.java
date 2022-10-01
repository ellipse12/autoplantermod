
        package autoplanter.blocks.added;


        import net.minecraft.core.BlockPos;
        import net.minecraft.server.level.ServerPlayer;
        import net.minecraft.world.InteractionHand;
        import net.minecraft.world.InteractionResult;
        import net.minecraft.world.entity.player.Player;
        import net.minecraft.world.item.context.BlockPlaceContext;
        import net.minecraft.world.level.BlockGetter;
        import net.minecraft.world.level.Level;
        import net.minecraft.world.level.block.*;
        import net.minecraft.world.level.block.entity.BlockEntity;
        import net.minecraft.world.level.block.entity.BlockEntityTicker;
        import net.minecraft.world.level.block.entity.BlockEntityType;
        import net.minecraft.world.level.block.state.BlockState;
        import net.minecraft.world.level.block.state.StateDefinition;
        import net.minecraft.world.level.block.state.properties.DirectionProperty;
        import net.minecraft.world.phys.BlockHitResult;
        import net.minecraft.world.phys.shapes.CollisionContext;
        import net.minecraft.world.phys.shapes.VoxelShape;
        import net.minecraftforge.network.NetworkHooks;
        import org.jetbrains.annotations.Nullable;
        import autoplanter.blocks.entities.added.AutoPlanterBlockEntity;

        public class AutoPlanterBlock extends BaseEntityBlock {
            public static final DirectionProperty FACING = DirectionalBlock.FACING;

            public AutoPlanterBlock(Properties properties) {
                super(properties);
            }

            private static final VoxelShape SHAPE = Block.box(0, 0, 0, 16, 15.9, 16);

            @Override
            public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
                return SHAPE;
            }

            @Override
            public BlockState getStateForPlacement(BlockPlaceContext pContext) {
                return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite());
            }

            @Override
            public BlockState rotate(BlockState pState, Rotation pRotation) {
                return pState.setValue(FACING, pRotation.rotate(pState.getValue(FACING)));
            }

            @Override
            public BlockState mirror(BlockState pState, Mirror pMirror) {
                return pState.rotate(pMirror.getRotation(pState.getValue(FACING)));
            }

            @Override
            protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
                pBuilder.add(FACING);
            }

            @Override
            public RenderShape getRenderShape(BlockState pState) {
                return RenderShape.MODEL;
            }

            @Override
            public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
                if (pState.getBlock() != pNewState.getBlock()) {
                    BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
                    if (blockEntity instanceof AutoPlanterBlockEntity planterBlockEntity) {
                        ((AutoPlanterBlockEntity)(blockEntity)).drops();
                    }
                }
                super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
            }

            @Override
            public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos,
                                         Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
                if (!pLevel.isClientSide()) {
                    BlockEntity entity = pLevel.getBlockEntity(pPos);
                    if (entity instanceof AutoPlanterBlockEntity) {
                        NetworkHooks.openGui(((ServerPlayer) pPlayer), (AutoPlanterBlockEntity) entity, pPos);
                    } else {
                        throw new IllegalStateException("Our Container provider is missing!");
                    }
                }

                return InteractionResult.sidedSuccess(pLevel.isClientSide());
            }

            @Nullable
            @Override
            public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
                return new AutoPlanterBlockEntity(pPos, pState);
            }


            @Nullable
            @Override
            public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
                return pLevel.isClientSide() ? null : ((pLevel1, pPos, pState1, pBlockEntity) -> {
                    ((AutoPlanterBlockEntity) pBlockEntity).tick();
                });

            }
        }
