package github.Louwind.Features.context.provider;

import github.Louwind.Features.context.FeatureContext;
import github.Louwind.Features.context.FeatureContextAware;
import github.Louwind.Features.context.FeatureContextBuilder;
import github.Louwind.Features.context.setter.FeatureContextSetter;
import github.Louwind.Features.pool.FeaturePool;
import github.Louwind.Features.properties.FeatureProperties;
import net.minecraft.structure.StructurePiece;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;

import java.util.List;
import java.util.Random;

public interface FeatureContextProvider  extends FeatureContextAware {

    FeatureContextBuilder getBuilder(FeaturePool pool, List<StructurePiece> pieces, BlockRotation rotation, FeatureProperties properties, StructureWorldAccess world, Random random, BlockPos pos);

    default FeatureContext getContext(FeaturePool pool, List<StructurePiece> pieces, BlockRotation rotation, FeatureProperties properties, StructureWorldAccess world, Random random, BlockPos pos) throws IllegalAccessException {
        FeatureContextBuilder builder = this.getBuilder(pool, pieces, rotation, properties, world, random,  pos);

        for (FeatureContextSetter setter : this.getContextSetters())
            setter.accept(this, builder);

        return builder.build(this);
    }

    List<FeatureContextSetter> getContextSetters();

    FeatureContextProviderType getType();

}
