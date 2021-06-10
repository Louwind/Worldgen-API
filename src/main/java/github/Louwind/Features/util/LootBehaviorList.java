package github.Louwind.Features.util;

import github.Louwind.Features.loot.LootBehavior;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

import java.util.List;

public class LootBehaviorList {

    private final List<LootBehavior<BlockEntity>> lootBehaviors;

    public LootBehaviorList(List<LootBehavior<BlockEntity>> lootBehaviors) {
        this.lootBehaviors = lootBehaviors;
    }

    public void generate(ServerWorld server, BlockEntity blockEntity, BlockPos pos) {
        this.lootBehaviors.forEach(lootBehavior -> lootBehavior.generate(server, blockEntity, pos));
    }

}
