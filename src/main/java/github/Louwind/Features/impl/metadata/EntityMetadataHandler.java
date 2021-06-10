package github.Louwind.Features.impl.metadata;

import github.Louwind.Features.metadata.condition.MetadataCondition;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.Structure;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;

import java.util.Random;

import static net.minecraft.entity.SpawnReason.STRUCTURE;

public class EntityMetadataHandler extends ConditionalMetadataHandler {

    private final NbtCompound compound;
    private final boolean initialize;
    private final Identifier entityTypeId;
    private final Vec3d vec3d;

    public EntityMetadataHandler(Identifier entityTypeId, Vec3d pos, NbtCompound compound, boolean initialize, MetadataCondition[] conditions) {
        super(conditions);

        this.initialize = initialize;
        this.compound = compound;
        this.vec3d = pos;
        this.entityTypeId = entityTypeId;
    }

    public Entity getEntity(World world, NbtCompound compound, BlockRotation rotation, double x, double y, double z) {
        return EntityType.loadEntityWithPassengers(compound, world, e -> {
            e.refreshPositionAndAngles(x, y, z, e.getYaw() - e.applyRotation(rotation), e.getPitch());
            return e;
        });
    }

    @Override
    void process(ServerWorld world, Structure.StructureBlockInfo blockInfo, BlockPos blockPos, BlockRotation rotation, Random random) {
        var compound = this.compound.copy();
        var server = world.toServerWorld();

        var x = blockInfo.pos.getX() + this.vec3d.getX();
        var y = blockInfo.pos.getY() + this.vec3d.getY();
        var z = blockInfo.pos.getZ() + this.vec3d.getZ();

        compound.putString("id", this.entityTypeId.toString());
        var entity = this.getEntity(world, compound, rotation, x, y, z);

        if (entity == null)
            LogManager.getLogger().warn("Unknown id for Entity: " + this.entityTypeId);
        else {
            var pos = entity.getBlockPos();

            if (this.initialize && entity instanceof MobEntity)
                ((MobEntity)entity).initialize(server, server.getLocalDifficulty(pos), STRUCTURE, null, null);

            if (!world.shouldCreateNewEntityWithPassenger(entity))
                LogManager.getLogger().warn("Passengers have the same uuid!");
        }

    }

}
