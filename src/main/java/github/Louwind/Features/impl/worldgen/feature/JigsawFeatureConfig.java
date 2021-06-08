package github.Louwind.Features.impl.worldgen.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.world.gen.feature.StructurePoolFeatureConfig;

import java.util.function.Supplier;

import static net.minecraft.structure.pool.StructurePool.REGISTRY_CODEC;

public class JigsawFeatureConfig extends StructurePoolFeatureConfig {

    public static final Codec<JigsawFeatureConfig> CODEC = RecordCodecBuilder.create((instance) -> instance.group(REGISTRY_CODEC.fieldOf("start_pool").forGetter(JigsawFeatureConfig::getStartPool),
            Codec.intRange(0, 24).fieldOf("size").forGetter(JigsawFeatureConfig::getSize),
            Codec.BOOL.fieldOf("keep_jigsaw").orElse(false).forGetter(JigsawFeatureConfig::getKeepJigsaws),
            Codec.BOOL.fieldOf("is_surface").orElse(false).forGetter(JigsawFeatureConfig::isSurface)
    ).apply(instance, JigsawFeatureConfig::new));

    protected boolean keepJigsaw;
    protected boolean isSurface;

    public JigsawFeatureConfig(Supplier<StructurePool> startPool, int size, boolean keepJigsaw, boolean isSurface) {
        super(startPool, size);

        this.keepJigsaw = keepJigsaw;
        this.isSurface = isSurface;
    }

    public boolean getKeepJigsaws() {
        return this.keepJigsaw;
    }

    public boolean isSurface() {
        return this.isSurface;
    }

}