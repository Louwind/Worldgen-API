package github.Louwind.Features.impl.metadata;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import github.Louwind.Features.metadata.MetadataHandlerType;
import github.Louwind.Features.metadata.condition.MetadataCondition;
import github.Louwind.Features.metadata.condition.MetadataConditionType;
import github.Louwind.Features.util.LootBehaviorList;
import github.Louwind.Features.util.codec.CodecHelper;
import net.minecraft.block.BlockState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.Structure;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;

import java.util.List;
import java.util.Random;

import static github.Louwind.Features.impl.init.MetadataHandlers.LOOTABLE_BLOCKSTATE;

public class LootableBlockStateMetadataHandler extends BlockStateMetadataHandler {

    public static final Codec<LootableBlockStateMetadataHandler> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
            CodecHelper.BLOCK_STATE.fieldOf("output_state").forGetter(handler -> handler.state),
            Codec.INT.fieldOf("flag").orElse(3).forGetter(handler -> handler.flag),
            LootBehaviorList.CODEC.fieldOf("loot_behaviors").forGetter(handle -> handle.lootBehaviors),
            MetadataConditionType.CODEC.listOf().fieldOf("conditions").orElseGet(Lists::newArrayList).forGetter(handler -> handler.conditions)
    ).apply(instance, LootableBlockStateMetadataHandler::new));

    protected final LootBehaviorList lootBehaviors;

    public LootableBlockStateMetadataHandler(BlockState state, int flag, LootBehaviorList lootBehaviors, List<MetadataCondition> conditions) {
        super(state, flag, conditions);

        this.lootBehaviors = lootBehaviors;
    }

    @Override
    public MetadataHandlerType<?> getType() {
        return LOOTABLE_BLOCKSTATE;
    }

    @Override
    public void process(ServerWorld world, Structure.StructureBlockInfo blockInfo, BlockPos blockPos, BlockRotation rotation, Random random) {
        super.process(world, blockInfo, blockPos, rotation, random);

        var blockEntity = world.getBlockEntity(blockInfo.pos);

        if(blockEntity != null)
            this.lootBehaviors.generate(world, blockEntity, blockInfo.pos);
    }

}