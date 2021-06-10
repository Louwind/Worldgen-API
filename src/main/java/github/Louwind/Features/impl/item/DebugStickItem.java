package github.Louwind.Features.impl.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.world.World;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.ConfiguredFeature;

import java.util.Random;

/**
 * An item that generates a configured feature when you right-click a block
 * */
public class DebugStickItem extends Item {

    private final Identifier identifier;

    public DebugStickItem(Identifier identifier, Settings settings) {
        super(settings);

        this.identifier = identifier;
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        BlockPos pos = context.getBlockPos();
        World world = context.getWorld();

        Random random = world.getRandom();
        BlockPos origin = pos.up(2);

        if(!world.isClient) {
            ServerWorld server = (ServerWorld) world;
            ChunkGenerator chunkGenerator = server.getChunkManager().getChunkGenerator();

            if(BuiltinRegistries.CONFIGURED_FEATURE.containsId(this.identifier)) {
                ConfiguredFeature<?, ?> feature = BuiltinRegistries.CONFIGURED_FEATURE.get(this.identifier);

                if(feature != null && feature.generate(server, chunkGenerator, random, origin))
                    return ActionResult.CONSUME;
            }

        }

        return super.useOnBlock(context);
    }

}
