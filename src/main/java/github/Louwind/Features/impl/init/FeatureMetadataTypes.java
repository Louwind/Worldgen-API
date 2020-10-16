package github.Louwind.Features.impl.init;

import github.Louwind.Features.impl.metadata.EntityMetadata;
import github.Louwind.Features.impl.metadata.BlockstateMetadata;
import github.Louwind.Features.metadata.FeatureMetadataType;

public class FeatureMetadataTypes {

    public static final FeatureMetadataType ENTITY = new FeatureMetadataType(new EntityMetadata.Serializer());

    public static final FeatureMetadataType BLOCKSTATE = new FeatureMetadataType(new BlockstateMetadata.Serializer());

}
