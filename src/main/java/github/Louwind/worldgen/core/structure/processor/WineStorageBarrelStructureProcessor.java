package github.Louwind.worldgen.core.structure.processor;

import com.mojang.serialization.Codec;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.structure.Structure;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.processor.StructureProcessor;
import net.minecraft.structure.processor.StructureProcessorType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;
import org.apache.commons.lang3.RandomUtils;
import org.jetbrains.annotations.Nullable;

import static github.Louwind.worldgen.core.init.WGStructureProcessors.WINE_STORAGE_BARREL_PROCESSOR;
import static net.minecraft.block.Blocks.BARREL;
import static net.minecraft.state.property.Properties.FACING;
import static net.minecraft.util.math.Direction.NORTH;
import static net.minecraft.util.math.Direction.UP;

public class WineStorageBarrelStructureProcessor extends StructureProcessor {

    public static final Codec<WineStorageBarrelStructureProcessor> CODEC = Codec.unit(new WineStorageBarrelStructureProcessor());

    @Nullable
    @Override
    public Structure.StructureBlockInfo process(WorldView world, BlockPos pos, BlockPos blockPos, Structure.StructureBlockInfo structureBlockInfo, Structure.StructureBlockInfo structureBlockInfo2, StructurePlacementData structurePlacementData) {

        if(structureBlockInfo2.state.isOf(BARREL)) {
            var nbtCompound = structureBlockInfo2.nbt != null ? structureBlockInfo2.nbt.copy() : new NbtCompound();
            var direction = RandomUtils.nextBoolean() ? NORTH : UP;
            var seed = RandomUtils.nextLong();

            nbtCompound.putString("LootTable", "worldgen:wine_storage_barrel");
            nbtCompound.putLong("LootTableSeed", seed);

            return new Structure.StructureBlockInfo(structureBlockInfo2.pos, structureBlockInfo2.state.with(FACING, direction), nbtCompound);
        }

        return structureBlockInfo2;
    }

    @Override
    protected StructureProcessorType<?> getType() {
        return WINE_STORAGE_BARREL_PROCESSOR;
    }

}
