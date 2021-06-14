package github.Louwind.Features.impl.metadata;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import github.Louwind.Features.metadata.MetadataHandlerType;
import github.Louwind.Features.metadata.condition.MetadataCondition;
import github.Louwind.Features.metadata.condition.MetadataConditionType;
import github.Louwind.Features.util.codec.CodecHelper;
import net.minecraft.block.BlockState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.Structure;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;

import java.util.List;
import java.util.Random;

import static github.Louwind.Features.impl.init.MetadataHandlers.BLOCKSTATE;

public class BlockStateMetadataHandler extends ConditionalMetadataHandler {

    public static final Codec<BlockStateMetadataHandler> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
            CodecHelper.BLOCK_STATE.fieldOf("output_state").forGetter(handler -> handler.state),
            Codec.INT.fieldOf("flag").orElse(3).forGetter(handler -> handler.flag),
            MetadataConditionType.CODEC.listOf().fieldOf("conditions").orElseGet(Lists::newArrayList).forGetter(handler -> handler.conditions)
    ).apply(instance, BlockStateMetadataHandler::new));

    protected final int flag;
    protected final BlockState state;

    public BlockStateMetadataHandler(BlockState state, int flag, List<MetadataCondition> conditions) {
        super(conditions);

        this.flag = flag;
        this.state = state;
    }

    @Override
    public MetadataHandlerType<?> getType() {
        return BLOCKSTATE;
    }

    @Override
    public void process(ServerWorld world, Structure.StructureBlockInfo blockInfo, BlockPos blockPos, BlockRotation rotation, Random random) {
        world.setBlockState(blockInfo.pos, this.state.rotate(rotation), this.flag);
    }

}