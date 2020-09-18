package github.Louwind.Features.impl.feature;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import github.Louwind.Features.context.FeatureContext;
import github.Louwind.Features.structure.RotatedStructurePiece;
import net.minecraft.structure.StructurePiece;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;

import java.util.List;
import java.util.Random;

@Deprecated
public class StructureGeneratorFeature<T extends DefaultFeatureGenerator, FC extends FeatureConfig> extends Feature<FC> {

	protected final T generator;

	public StructureGeneratorFeature(T generator, Codec<FC> codec) {
		super(codec);

		this.generator = generator;
	}

	public FeatureContext getContext(StructureWorldAccess world, Random random, BlockPos pos) {
		/*ServerWorld server = (ServerWorld) world;

		StructurePool pool = this.generator.getPool();
		IdentifiedPoolElement element = (IdentifiedPoolElement) pool.getRandomElement(random);
		
		return new FeatureContext.Builder()
				.put(BOX, this.generator.getBox(pos))
				.put(PIECE_ROTATION, this.generator.getRotation(random))
				.put(GENERATOR, this.generator)
				.put(FEATURE, this)
				.put(MAIN_PIECE, element)
				.put(MAIN_POOL, pool)
				.put(POS, pos)
				.put(WORLD, server)
				.put(PIECES, Lists.newArrayList())
				.build();
		 */
		return null;
	}

	public List<StructurePiece> getPieces(ChunkGenerator generator, FeatureContext context, Random random) {
		List<StructurePiece> pieces = Lists.newArrayList();

		/*
		if(context != EMPTY) {
			StructureGenerator structureGenerator = context.get(GENERATOR);
			StructurePoolElement poolElement = context.get(MAIN_PIECE);

			BlockRotation rotation = context.get(PIECE_ROTATION);
			ServerWorld world = context.get(WORLD);
			BlockPos pos = context.get(POS);

			StructureManager manager = world.getStructureManager();
			int size = structureGenerator.getSize();
		
//			PoolGenerator.addPieces(poolElement, size, rotation, RotatedStructurePiece::new, generator, manager, pos, pieces, random);
		}*/
		
		return pieces;
	}
	
	
	public StructurePool getPool(FeatureContext context, RotatedStructurePiece piece, Random random) {
		/*IdentifiedPoolElement poolElement = (IdentifiedPoolElement) piece.getPoolElement();
		StructureGenerator structureGenerator = context.get(GENERATOR);

		for (StructureGeneratorPool pool : structureGenerator.getPools()) {
			StructurePool structurePool = pool.getStructurePool().get(context);
						
			for (StructurePoolElement elem : structurePool.getElementIndicesInRandomOrder(random)) {
				IdentifiedPoolElement poolElem = (IdentifiedPoolElement) elem;

				Identifier entryId = poolElem.getId();
				Identifier elementId = poolElement.getId();

				if (entryId.equals(elementId))
					return pool.getStructurePool().get(context);
			}

		}*/

		return null;
	}

	@Override
	public boolean generate(StructureWorldAccess world, ChunkGenerator chunkGenerator, Random random, BlockPos blockPos, FC featureConfig) {
		FeatureContext context = this.getContext(world, random, blockPos);
		List<StructurePiece> pieces = this.getPieces(chunkGenerator, context, random);

		/*
		if(pieces.isEmpty())
			return false;

		FeatureGenerator structureGenerator = context.get(GENERATOR);

		BlockBox box = context.get(BOX);
		Chunk chunk = world.getChunk(blockPos);
		ChunkPos chunkPos = chunk.getPos();

		return pieces.stream().map(RotatedStructurePiece.class::cast).allMatch(piece -> {

			FeatureContextBuilder builder = new FeatureContextBuilder(context)
					.put(PIECE, piece)
					.put(PIECES, pieces)
					.put(POOL, this.getPool(context, piece, random));

			List<FeatureContextSetter> setters = structureGenerator.getSetters(builder);

			for (FeatureContextSetter setter : setters)
				setter.set(builder);

			FeatureContext pieceContext = builder.build();

			List<FeatureFunction> functions = structureGenerator.getFunctions(pieceContext);

			for (FeatureFunction function : functions)
				function.apply(pieceContext);

			return piece.generate(world, random, box, chunkPos);
		});*/
		return false;
	}

}
