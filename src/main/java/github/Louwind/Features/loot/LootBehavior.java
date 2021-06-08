package github.Louwind.Features.loot;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

import static net.minecraft.loot.context.LootContextParameters.ORIGIN;

public interface LootBehavior<T extends BlockEntity> {

    default LootContext getContext(ServerWorld world, BlockPos pos, long seed) {
        return new LootContext.Builder(world)
                .parameter(ORIGIN, Vec3d.ofCenter(pos))
                .random(seed)
                .build(LootContextTypes.CHEST);
    }

    default void generate(ServerWorld server, T blockEntity, BlockPos pos) {
        var seed = server.getSeed();
        var context = this.getContext(server, pos, seed);

        this.setLootTable(context, server, blockEntity, pos, seed);
    }

    LootTable getLootTable(ServerWorld world);

    LootBehaviorType getType();

    void setLootTable(LootContext context, ServerWorld server, T blockEntity, BlockPos pos, long seed);

}
