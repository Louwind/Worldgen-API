package github.Louwind.Features.impl.feature;

import com.mojang.serialization.Codec;
import github.Louwind.Features.context.FeatureContextProvider;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;

import java.util.Random;

public class GenericFeature<CT extends FeatureContextProvider, FC extends FeatureConfig> extends Feature<FC> {

    protected final Identifier identifier;
    protected final CT contextProvider;

    public GenericFeature(Identifier identifier, CT contextProvider, Codec<FC> configCodec) {
        super(configCodec);

        this.identifier = identifier;
        this.contextProvider = contextProvider;
    }

    @Override
    public boolean generate(StructureWorldAccess world, ChunkGenerator chunkGenerator, Random random, BlockPos blockPos, FC featureConfig) {
        return false;
    }

}
