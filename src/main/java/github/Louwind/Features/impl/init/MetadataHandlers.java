package github.Louwind.Features.impl.init;

import github.Louwind.Features.impl.metadata.BlockStateMetadataHandler;
import github.Louwind.Features.impl.metadata.EntityMetadataHandler;
import github.Louwind.Features.impl.metadata.LootableBlockStateMetadataHandler;
import github.Louwind.Features.metadata.MetadataHandlerType;

public class MetadataHandlers {

    public static final MetadataHandlerType<BlockStateMetadataHandler> BLOCKSTATE = () -> BlockStateMetadataHandler.CODEC;

    public static final MetadataHandlerType<EntityMetadataHandler> ENTITY = () -> EntityMetadataHandler.CODEC;

    public static final MetadataHandlerType<LootableBlockStateMetadataHandler> LOOTABLE_BLOCKSTATE = () ->LootableBlockStateMetadataHandler.CODEC;

}