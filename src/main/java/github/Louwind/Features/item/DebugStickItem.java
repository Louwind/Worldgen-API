package github.Louwind.Features.item;

import github.Louwind.Features.impl.feature.GenericFeature;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.FeatureConfig;

import java.util.Random;
import java.util.function.Supplier;

public class DebugStickItem<T extends FeatureConfig> extends Item {

    private final GenericFeature<?, T> feature;
    private final Supplier<T> featureConfig;

    public DebugStickItem(GenericFeature<?, T> feature, Supplier<T> featureConfig, Settings settings) {
        super(settings);

        this.feature = feature;
        this.featureConfig = featureConfig;
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        BlockPos pos = context.getBlockPos();
        World world = context.getWorld();

        Random random = world.getRandom();

        if(!world.isClient) {
            ServerWorld server = (ServerWorld) world;
            ChunkGenerator chunkGenerator = server.getChunkManager().getChunkGenerator();

           if(this.feature.generate(server, chunkGenerator, random, pos, this.featureConfig.get()))
               return ActionResult.CONSUME;
        }

        return super.useOnBlock(context);
    }

}
