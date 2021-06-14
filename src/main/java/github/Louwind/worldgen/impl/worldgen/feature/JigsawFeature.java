package github.Louwind.worldgen.impl.worldgen.feature;

import com.mojang.serialization.Codec;
import github.Louwind.worldgen.util.worldgen.JigsawPieceGenerator;
import net.minecraft.util.math.BlockBox;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class JigsawFeature extends Feature<JigsawFeatureConfig> {

    public JigsawFeature(Codec<JigsawFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext<JigsawFeatureConfig> context) {
        var server = context.getWorld().toServerWorld();

        var accessor = server.getStructureAccessor();
        var chunkGenerator = context.getGenerator();
        var config = context.getConfig();
        var origin = context.getOrigin();
        var random = context.getRandom();
        var chunk = server.getChunk(origin);

        return JigsawPieceGenerator.addPieces(server, config, chunk, origin)
                .getPieces()
                .stream()
                .allMatch(piece -> piece.generate(server, accessor, chunkGenerator, random, BlockBox.empty(), chunk.getPos(), origin));
    }

}