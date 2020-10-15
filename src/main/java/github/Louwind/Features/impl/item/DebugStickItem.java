package github.Louwind.Features.impl.item;

import github.Louwind.Features.impl.feature.FeatureWithStart;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;

import java.util.Random;
import java.util.function.Supplier;

/**
 * An item that generates a {@link FeatureWithStart} when you right-click a block
 * */
public class DebugStickItem<T extends FeatureConfig> extends Item {

    private final Supplier<T> featureConfig;
    private final Identifier identifier;

    public DebugStickItem(Identifier identifier, Supplier<T> featureConfig, Settings settings) {
        super(settings);

        this.featureConfig = featureConfig;
        this.identifier = identifier;
    }


    @SuppressWarnings("unchecked")
    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        BlockPos pos = context.getBlockPos();
        World world = context.getWorld();

        Random random = world.getRandom();

        if(!world.isClient) {
            ServerWorld server = (ServerWorld) world;
            ChunkGenerator chunkGenerator = server.getChunkManager().getChunkGenerator();

            if(Registry.FEATURE.containsId(this.identifier)) {
                Feature<T> feature = (Feature<T>) Registry.FEATURE.get(this.identifier);

                if(feature != null && feature.generate(server, chunkGenerator, random, pos.up(), this.featureConfig.get()))
                    return ActionResult.CONSUME;
            }

        }

        return super.useOnBlock(context);
    }

}
