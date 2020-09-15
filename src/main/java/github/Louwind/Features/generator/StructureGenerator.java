package github.Louwind.Features.generator;

import github.Louwind.Features.RotatedStructurePiece;
import github.Louwind.Features.Settings;
import github.Louwind.Features.context.FeatureContext;
import github.Louwind.Features.context.setter.FeatureContextSetter;
import github.Louwind.Features.function.FeatureFunction;
import github.Louwind.Features.pool.IdentifiedPoolElement;
import github.Louwind.Features.pool.StructureGeneratorPool;
import github.Louwind.Features.pool.StructurePoolEntry;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StructureGenerator {

	@Deprecated
	protected final BlockBox box;
	@Deprecated
	protected final Identifier parent;
	@Deprecated
	protected final StructurePool pool;
	@Deprecated
	protected final StructureGeneratorPool[] pools;
	@Deprecated
	protected final BlockRotation[] rotations;
	@Deprecated
	protected final int size;

	public StructureGenerator(Settings settings) {
		this.box = settings.getBox();
		this.parent = settings.getParent();
		this.pool = settings.getPool();
		this.size = settings.getSize();
		this.pools = settings.getPools();
		this.rotations = settings.getRotations();
	}

	protected <T> List<T> fromPools(Function<StructureGeneratorPool, List<T>> poolfunction, Function<StructurePoolEntry, List<T>> entryFunction, FeatureContext context) {
		// TODO get pool
		StructurePool structurePool = null;
		StructureGeneratorPool[] generatorPools = this.getPools();

		List<StructureGeneratorPool> pools = Stream.of(generatorPools)
				.filter(pool -> pool.getStructurePool().get(context) == structurePool).collect(Collectors.toList());
		List<T> functions = pools.stream().map(poolfunction::apply).flatMap(List::stream).collect(Collectors.toList());

		for (StructureGeneratorPool pool : pools) {
			Optional<StructurePoolEntry> entryOptional = this.getEntry(pool, context);

			if (entryOptional.isPresent()) {
				StructurePoolEntry entry = entryOptional.get();
				List<T> entryFunctions = entryFunction.apply(entry);

				return Stream.concat(functions.stream(), entryFunctions.stream()).collect(Collectors.toList());
			}

		}

		return functions;
	}

	public BlockBox getBox() {
		return this.box;
	}

	public BlockBox getBox(BlockPos pos) {
		return this.box.offset(pos.getX(), pos.getY(), pos.getZ());
	}

	protected Optional<StructurePoolEntry> getEntry(StructureGeneratorPool pool, FeatureContext context) {
		// TODO get piece
		RotatedStructurePiece piece = null;
		IdentifiedPoolElement poolElement = (IdentifiedPoolElement) piece.getPoolElement();

		return pool.getEntries().stream().filter(entry -> poolElement.getId().equals(entry.getId())).findFirst();
	}

	public List<FeatureFunction> getFunctions(FeatureContext context) {
		return this.fromPools(StructureGeneratorPool::getFunctions, StructurePoolEntry::getFunctions, context);
	}

	public Identifier getParent() {
		return this.parent;
	}

	public StructurePool getPool() {
		return this.pool;
	}

	public StructureGeneratorPool[] getPools() {

		if (this.parent != null) {
			// TODO get parent
			Optional<StructureGenerator> optional = Optional.empty();

			if (optional.isPresent()) {
				StructureGenerator parent = optional.get();

				return Stream.of(parent.getPools(), this.pools).flatMap(Stream::of).toArray(StructureGeneratorPool[]::new);
			}
		}

		return this.pools;
	}

	public BlockRotation getRotation(Random random) {
		return this.rotations[random.nextInt(rotations.length)];
	}

	public BlockRotation[] getRotations() {
		return this.rotations;
	}

	public List<FeatureContextSetter> getSetters(FeatureContext.Builder builder) {
		return this.fromPools(StructureGeneratorPool::getSetters, StructurePoolEntry::getSetters, builder.build());
	}

	public int getSize() {
		return this.size;
	}

}
