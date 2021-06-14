package github.Louwind.worldgen.impl.metadata;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import github.Louwind.worldgen.metadata.MetadataHandlerType;
import github.Louwind.worldgen.metadata.condition.MetadataCondition;
import github.Louwind.worldgen.metadata.condition.MetadataConditionType;
import github.Louwind.worldgen.util.codec.CodecHelper;
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

import java.util.List;
import java.util.Random;

import static github.Louwind.worldgen.impl.init.MetadataHandlers.ENTITY;
import static net.minecraft.entity.SpawnReason.STRUCTURE;

public class EntityMetadataHandler extends ConditionalMetadataHandler {

    public static final Codec<EntityMetadataHandler> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
            Identifier.CODEC.fieldOf("entity_type").forGetter(handler -> handler.entityTypeId),
            NbtCompound.CODEC.fieldOf("compound").forGetter(handler -> handler.compound),
            CodecHelper.VEC3D.fieldOf("vec3d").orElse(Vec3d.ZERO).forGetter(handler -> handler.vec3d),
            Codec.BOOL.fieldOf("initialize").orElse(false).forGetter(handler -> handler.initialize),
            MetadataConditionType.CODEC.listOf().fieldOf("conditions").orElseGet(Lists::newArrayList).forGetter(handler -> handler.conditions)
    ).apply(instance, EntityMetadataHandler::new));

    private final NbtCompound compound;
    private final boolean initialize;
    private final Identifier entityTypeId;
    private final Vec3d vec3d;

    public EntityMetadataHandler(Identifier entityTypeId, NbtCompound compound, Vec3d vec3d, boolean initialize, List<MetadataCondition> conditions) {
        super(conditions);

        this.initialize = initialize;
        this.compound = compound;
        this.vec3d = vec3d;
        this.entityTypeId = entityTypeId;
    }

    public Entity getEntity(World world, NbtCompound compound, BlockRotation rotation, double x, double y, double z) {
        return EntityType.loadEntityWithPassengers(compound, world, e -> {
            e.refreshPositionAndAngles(x, y, z, e.getYaw() - e.applyRotation(rotation), e.getPitch());
            return e;
        });
    }

    @Override
    public MetadataHandlerType<?> getType() {
        return ENTITY;
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
