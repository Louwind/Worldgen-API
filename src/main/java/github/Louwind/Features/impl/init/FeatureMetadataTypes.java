package github.Louwind.Features.impl.init;

import github.Louwind.Features.impl.metadata.EntityMetadata;
import github.Louwind.Features.impl.metadata.BlockstateMetadata;
import github.Louwind.Features.metadata.FeatureMetadataType;

import static github.Louwind.Features.impl.init.FeatureContextParameters.*;

public class FeatureMetadataTypes {

    public static final FeatureMetadataType ENTITY = new FeatureMetadataType(new EntityMetadata.Serializer(), builder -> builder.optional(ROTATION).required(BLOCK_INFO, MIRROR, WORLD));

    public static final FeatureMetadataType BLOCKSTATE = new FeatureMetadataType(new BlockstateMetadata.Serializer(), builder -> builder.required(BLOCK_INFO, MIRROR, ROTATION, WORLD));

}
