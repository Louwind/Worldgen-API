package github.Louwind.worldgen.core.init;

import github.Louwind.worldgen.core.world.gen.feature.JigsawFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;

import static github.Louwind.worldgen.core.init.WGFeatures.JIGSAW;
import static github.Louwind.worldgen.core.init.WGStructurePools.*;
import static github.Louwind.worldgen.util.worldgen.BlockRotationChooser.ALL;

public class WGConfiguredFeatures {

    public static final ConfiguredFeature<?, ?> WELL = JIGSAW.configure(new JigsawFeatureConfig(
            () -> WELLS, 1, ALL,true, false)
    );

    public static final ConfiguredFeature<?, ?> WINE_STORAGE = JIGSAW.configure(new JigsawFeatureConfig(
            () -> WINE_STORAGES, 1, ALL,true, false)
    );

}
