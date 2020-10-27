package github.Louwind.Features.impl.world.gen.structure;

import com.mojang.serialization.Codec;
import github.Louwind.Features.impl.config.JigsawFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

public class JigsawStructureFeature extends StructureFeature<JigsawFeatureConfig> {

    public JigsawStructureFeature(Codec<JigsawFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public StructureStartFactory<JigsawFeatureConfig> getStructureStartFactory() {
        return null;
    }

}
