package github.Louwind.Features.impl.feature;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import github.Louwind.Features.Features;
import github.Louwind.Features.context.FeatureContext;
import github.Louwind.Features.context.FeatureContextBuilder;
import github.Louwind.Features.context.FeatureContextProvider;
import github.Louwind.Features.context.setter.FeatureContextSetter;
import github.Louwind.Features.function.FeatureFunction;
import github.Louwind.Features.generator.FeatureGenerator;
import github.Louwind.Features.pool.FeaturePool;
import github.Louwind.Features.properties.FeatureProperties;
import github.Louwind.Features.structure.RotatedStructurePiece;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.PoolStructurePiece;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructurePiece;
import net.minecraft.structure.pool.StructurePoolBasedGenerator;
import net.minecraft.structure.pool.StructurePoolElement;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import org.apache.logging.log4j.LogManager;

import java.util.List;
import java.util.Random;

import static github.Louwind.Features.impl.FeatureContextParameters.BOX;
import static github.Louwind.Features.impl.FeatureContextParameters.CHUNK_POS;

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
        ServerWorld server = world.toServerWorld();

        FeatureGenerator generator = Features.FEATURE_GENERATOR_MANAGER.get(this.identifier);
        StructureManager structureManager = server.getStructureManager();
        DynamicRegistryManager registryManager = server.getRegistryManager();
        StructureAccessor accessor = server.getStructureAccessor();

        FeaturePool pool = generator.getRandomPool(random);
        FeatureProperties properties = generator.getProperties();
        BlockRotation rotation = properties.getRotations(random);

        FeatureContextBuilder builder = this.contextProvider.getContext(pool, rotation, properties, world, random,  blockPos);
        List<StructurePiece> pieces = this.getPieces(pool, rotation, registryManager, structureManager, chunkGenerator, random, blockPos);

        return pieces.stream().map(RotatedStructurePiece.class::cast).allMatch(piece -> {
            StructurePoolElement poolElement = piece.getPoolElement();
            List<FeatureContextSetter> setters = generator.getSetters(pool, poolElement);

            for (FeatureContextSetter<?> setter : setters)
                setter.apply(this.contextProvider, builder);

            try {
                FeatureContext context = builder.build(this.contextProvider);
                List<FeatureFunction> functions = generator.getFunctions(pool, poolElement);

                for (FeatureFunction function: functions)
                    function.apply(piece, context);

                ChunkPos chunkPos = context.get(CHUNK_POS);
                BlockBox box = context.get(BOX);

                return piece.generate(world, accessor, chunkGenerator, random, box, chunkPos, blockPos);

            } catch (IllegalAccessException e) {
                LogManager.getLogger().warn(e);

                return false;
            }

        });
    }

    protected List<StructurePiece> getPieces(FeaturePool pool, BlockRotation rotation, DynamicRegistryManager registryManager, StructureManager structureManager, ChunkGenerator chunkGenerator, Random random, BlockPos blockPos) {
        List<StructurePiece> pieces = Lists.newArrayList();

        StructurePoolElement structurePoolElement = pool.getStructurePool().get().getRandomElement(random);
        BlockBox box = structurePoolElement.getBoundingBox(structureManager, blockPos, rotation);

        int size = pool.getProperties().getSize();
        int groundLevel = structurePoolElement.getGroundLevelDelta();

        PoolStructurePiece poolStructurePiece = new RotatedStructurePiece(structureManager, structurePoolElement, blockPos, groundLevel, rotation, box);

        StructurePoolBasedGenerator.method_27230(registryManager, poolStructurePiece, size, RotatedStructurePiece::new, chunkGenerator, structureManager, pieces, random);

        return pieces;
    }

}
