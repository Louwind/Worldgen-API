package github.Louwind.Features.world.gen.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import github.Louwind.Features.util.CodecHelper;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.util.BlockRotation;
import net.minecraft.world.gen.feature.StructurePoolFeatureConfig;

import java.util.List;
import java.util.function.Supplier;

public class JigsawFeatureConfig extends StructurePoolFeatureConfig {

    public static final Codec<BlockRotation> BLOCK_ROTATION_CODEC = CodecHelper.createEnum(BlockRotation::values);

    public static final Codec<JigsawFeatureConfig> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
            StructurePool.REGISTRY_CODEC.fieldOf("start_pool").forGetter(JigsawFeatureConfig::getStartPool),
            Codec.list(BLOCK_ROTATION_CODEC).fieldOf("rotations").forGetter(JigsawFeatureConfig::getRotations),
            Codec.BOOL.fieldOf("keepJigsaws").forGetter(JigsawFeatureConfig::getKeepJigsaws),
            Codec.BOOL.fieldOf("surface").forGetter(JigsawFeatureConfig::isSurface),
            Codec.intRange(0, Integer.MAX_VALUE).fieldOf("size").forGetter(JigsawFeatureConfig::getSize))
            .apply(instance, JigsawFeatureConfig::new));

    private final boolean keepJigsaws;
    private final boolean surface;
    private final List<BlockRotation> rotations;

    public JigsawFeatureConfig(Supplier<StructurePool> startPool, List<BlockRotation> rotations, boolean keepJigsaws, boolean surface, int size) {
        super(startPool, size);

        this.keepJigsaws = keepJigsaws;
        this.rotations = rotations;
        this.surface = surface;
    }

    public boolean getKeepJigsaws() {
        return this.keepJigsaws;
    }

    public boolean isSurface() {
        return this.surface;
    }

    public List<BlockRotation> getRotations() {
        return this.rotations;
    }

}
