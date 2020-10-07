package github.Louwind.Features.impl.item;

import github.Louwind.Features.impl.feature.GenericFeature;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;

import java.util.Random;
import java.util.function.Supplier;

/**
 * An item that generates a {@link GenericFeature} when you right-click a block
 * */
public class DebugStickItem<T extends FeatureConfig> extends Item {

    private final ConfiguredFeature<T, ? extends Feature<T>> feature;

    public DebugStickItem(ConfiguredFeature<T, Feature<T>> feature, Settings settings) {
        super(settings);

        this.feature = feature;
    }

    public DebugStickItem(Feature<T> feature, Supplier<T> featureConfig, Settings settings) {
        super(settings);

        this.feature = feature.configure(featureConfig.get());
    }


    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        BlockPos pos = context.getBlockPos();
        World world = context.getWorld();

        Random random = world.getRandom();

        if(!world.isClient) {
            ServerWorld server = (ServerWorld) world;
            ChunkGenerator chunkGenerator = server.getChunkManager().getChunkGenerator();

           if(this.feature.generate(server, chunkGenerator, random, pos.up()))
               return ActionResult.CONSUME;
        }

        return super.useOnBlock(context);
    }

}
