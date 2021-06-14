package github.Louwind.worldgen.impl.init;

import github.Louwind.worldgen.impl.metadata.BlockStateMetadataHandler;
import github.Louwind.worldgen.impl.metadata.EntityMetadataHandler;
import github.Louwind.worldgen.impl.metadata.LootableBlockStateMetadataHandler;
import github.Louwind.worldgen.metadata.MetadataHandlerType;

public class MetadataHandlers {

    public static final MetadataHandlerType<BlockStateMetadataHandler> BLOCKSTATE = () -> BlockStateMetadataHandler.CODEC;

    public static final MetadataHandlerType<EntityMetadataHandler> ENTITY = () -> EntityMetadataHandler.CODEC;

    public static final MetadataHandlerType<LootableBlockStateMetadataHandler> LOOTABLE_BLOCKSTATE = () ->LootableBlockStateMetadataHandler.CODEC;

}