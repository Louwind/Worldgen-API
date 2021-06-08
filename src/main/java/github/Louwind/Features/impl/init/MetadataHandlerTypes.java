package github.Louwind.Features.impl.init;

import github.Louwind.Features.impl.metadata.LootableBlockStateMetadataHandler;
import github.Louwind.Features.impl.metadata.EntityMetadataHandler;
import github.Louwind.Features.impl.metadata.BlockStateMetadataHandler;
import github.Louwind.Features.metadata.MetadataHandlerType;

public class MetadataHandlerTypes {

    public static final MetadataHandlerType BLOCKSTATE = new MetadataHandlerType(new BlockStateMetadataHandler.Serializer());

    public static final MetadataHandlerType ENTITY = new MetadataHandlerType(new EntityMetadataHandler.Serializer());

    public static final MetadataHandlerType LOOTABLE_BLOCKSTATE = new MetadataHandlerType(new LootableBlockStateMetadataHandler.Serializer());

}