package github.Louwind.worldgen.core.world.gen.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import github.Louwind.worldgen.util.worldgen.BlockRotationChooser;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.world.gen.feature.StructurePoolFeatureConfig;

import java.util.function.Supplier;

import static net.minecraft.structure.pool.StructurePool.REGISTRY_CODEC;

public class JigsawFeatureConfig extends StructurePoolFeatureConfig {

    public static final Codec<JigsawFeatureConfig> CODEC = RecordCodecBuilder.create((instance) ->
            instance.group(REGISTRY_CODEC.fieldOf("start_pool").forGetter(JigsawFeatureConfig::getStartPool),
            Codec.intRange(0, 24).fieldOf("size").forGetter(JigsawFeatureConfig::getSize),
            BlockRotationChooser.CODEC.fieldOf("rotations").forGetter(JigsawFeatureConfig::getRotationChooser),
            Codec.BOOL.fieldOf("modify_box").forGetter(JigsawFeatureConfig::getModifyBox),
            Codec.BOOL.fieldOf("is_surface").forGetter(JigsawFeatureConfig::isSurface)
    ).apply(instance, JigsawFeatureConfig::new));

    protected BlockRotationChooser chooser;
    protected boolean modifyBox;
    protected boolean isSurface;

    public JigsawFeatureConfig(Supplier<StructurePool> startPool, int size, BlockRotationChooser chooser, boolean modifyBox, boolean isSurface) {
        super(startPool, size);

        this.chooser = chooser;
        this.modifyBox = modifyBox;
        this.isSurface = isSurface;
    }

    public boolean getModifyBox() {
        return this.modifyBox;
    }

    public BlockRotationChooser getRotationChooser() {
        return this.chooser;
    }

    public boolean isSurface() {
        return this.isSurface;
    }

}