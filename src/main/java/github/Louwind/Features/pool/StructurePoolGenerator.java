package github.Louwind.Features.pool;

import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.collect.Lists;
import com.google.common.collect.Queues;

import net.minecraft.block.JigsawBlock;
import net.minecraft.structure.JigsawJunction;
import net.minecraft.structure.PoolStructurePiece;
import net.minecraft.structure.Structure;
import net.minecraft.structure.Structure.StructureBlockInfo;
import net.minecraft.structure.StructureFeatures;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructurePiece;
import net.minecraft.structure.pool.EmptyPoolElement;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolBasedGenerator;
import net.minecraft.structure.pool.StructurePoolElement;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.BooleanBiFunction;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.chunk.ChunkGenerator;

public class StructurePoolGenerator {

	private static final Logger LOGGER = LogManager.getLogger();

	public static void addPieces(Identifier startPoolId, int size, BlockRotation blockRotation,
			StructurePoolBasedGenerator.PieceFactory pieceFactory, ChunkGenerator<?> chunkGenerator,
			StructureManager structureManager, BlockPos pos, List<StructurePiece> pieces, Random random) {
		StructureFeatures.initialize();
		new class_4182(startPoolId, size, blockRotation, pieceFactory, chunkGenerator, structureManager, pos, pieces,
				random);
	}

	public static void addPieces(StructurePoolElement structurePoolElement, int size, BlockRotation blockRotation,
			StructurePoolBasedGenerator.PieceFactory pieceFactory, ChunkGenerator<?> chunkGenerator,
			StructureManager structureManager, BlockPos pos, List<StructurePiece> pieces, Random random) {
		StructureFeatures.initialize();
		new class_4182(structurePoolElement, size, blockRotation, pieceFactory, chunkGenerator, structureManager, pos,
				pieces, random);
	}

	public static final class class_4182 {
		private final int field_18700;
		private final StructurePoolBasedGenerator.PieceFactory field_18701;
		private final ChunkGenerator<?> field_18702;
		private final StructureManager field_18703;
		private final List<StructurePiece> field_18704;
		private final Random field_18705;
		private final Deque<class_4181> field_18706 = Queues.newArrayDeque();

		public class_4182(StructurePoolElement structurePoolElement, int i, BlockRotation blockRotation,
				StructurePoolBasedGenerator.PieceFactory pieceFactory, ChunkGenerator<?> chunkGenerator,
				StructureManager structureManager, BlockPos blockPos, List<StructurePiece> list, Random random) {

			this.field_18700 = i;
			this.field_18701 = pieceFactory;
			this.field_18702 = chunkGenerator;
			this.field_18703 = structureManager;
			this.field_18704 = list;
			this.field_18705 = random;

			PoolStructurePiece poolStructurePiece = pieceFactory.create(structureManager, structurePoolElement,
					blockPos, structurePoolElement.method_19308(), blockRotation,
					structurePoolElement.getBoundingBox(structureManager, blockPos, blockRotation));

			BlockBox blockBox = poolStructurePiece.getBoundingBox();

			int j = (blockBox.maxX + blockBox.minX) / 2;
			int k = (blockBox.maxZ + blockBox.minZ) / 2;
			int l = chunkGenerator.method_20402(j, k, Heightmap.Type.WORLD_SURFACE_WG);
			poolStructurePiece.translate(0, l - (blockBox.minY + poolStructurePiece.getGroundLevelDelta()), 0);
			list.add(poolStructurePiece);
			if (i > 0) {
				Box box = new Box((double) (j - 80), (double) (l - 80), (double) (k - 80), (double) (j + 80 + 1),
						(double) (l + 80 + 1), (double) (k + 80 + 1));
				this.field_18706
						.addLast(new class_4181(poolStructurePiece,
								new AtomicReference<VoxelShape>(VoxelShapes.combineAndSimplify(VoxelShapes.cuboid(box),
										VoxelShapes.cuboid(Box.from(blockBox)), BooleanBiFunction.ONLY_FIRST)),
								l + 80, 0));

				while (!this.field_18706.isEmpty()) {
					class_4181 lv = (class_4181) this.field_18706.removeFirst();
					this.method_19306(lv.field_18696, lv.field_18697, lv.field_18698, lv.field_18699);
				}

			}
		}

		public class_4182(Identifier identifier, int i, BlockRotation blockRotation,
				StructurePoolBasedGenerator.PieceFactory pieceFactory, ChunkGenerator<?> chunkGenerator,
				StructureManager structureManager, BlockPos blockPos, List<StructurePiece> list, Random random) {

			this.field_18700 = i;
			this.field_18701 = pieceFactory;
			this.field_18702 = chunkGenerator;
			this.field_18703 = structureManager;
			this.field_18704 = list;
			this.field_18705 = random;
			StructurePool structurePool = StructurePoolBasedGenerator.REGISTRY.get(identifier);
			StructurePoolElement structurePoolElement = structurePool.getRandomElement(random);
			PoolStructurePiece poolStructurePiece = pieceFactory.create(structureManager, structurePoolElement,
					blockPos, structurePoolElement.method_19308(), blockRotation,
					structurePoolElement.getBoundingBox(structureManager, blockPos, blockRotation));
			BlockBox blockBox = poolStructurePiece.getBoundingBox();

			int j = (blockBox.maxX + blockBox.minX) / 2;
			int k = (blockBox.maxZ + blockBox.minZ) / 2;
			int l = chunkGenerator.method_20402(j, k, Heightmap.Type.WORLD_SURFACE_WG);
			poolStructurePiece.translate(0, l - (blockBox.minY + poolStructurePiece.getGroundLevelDelta()), 0);
			list.add(poolStructurePiece);
			if (i > 0) {
				Box box = new Box((double) (j - 80), (double) (l - 80), (double) (k - 80), (double) (j + 80 + 1),
						(double) (l + 80 + 1), (double) (k + 80 + 1));
				this.field_18706
						.addLast(new class_4181(poolStructurePiece,
								new AtomicReference<VoxelShape>(VoxelShapes.combineAndSimplify(VoxelShapes.cuboid(box),
										VoxelShapes.cuboid(Box.from(blockBox)), BooleanBiFunction.ONLY_FIRST)),
								l + 80, 0));

				while (!this.field_18706.isEmpty()) {
					class_4181 lv = (class_4181) this.field_18706.removeFirst();
					this.method_19306(lv.field_18696, lv.field_18697, lv.field_18698, lv.field_18699);
				}

			}
		}

		private void method_19306(PoolStructurePiece poolStructurePiece, AtomicReference<VoxelShape> atomicReference,
				int i, int j) {
			StructurePoolElement structurePoolElement = poolStructurePiece.getPoolElement();
			BlockPos blockPos = poolStructurePiece.getPos();
			BlockRotation blockRotation = poolStructurePiece.getRotation();
			StructurePool.Projection projection = structurePoolElement.getProjection();
			boolean bl = projection == StructurePool.Projection.RIGID;
			AtomicReference<VoxelShape> atomicReference2 = new AtomicReference<VoxelShape>();
			BlockBox blockBox = poolStructurePiece.getBoundingBox();
			int k = blockBox.minY;
			Iterator<StructureBlockInfo> var13 = structurePoolElement
					.getStructureBlockInfos(this.field_18703, blockPos, blockRotation, this.field_18705).iterator();

			while (true) {
				while (true) {
					label90: while (var13.hasNext()) {
						Structure.StructureBlockInfo structureBlockInfo = (Structure.StructureBlockInfo) var13.next();
						Direction direction = (Direction) structureBlockInfo.state.get(JigsawBlock.FACING);
						BlockPos blockPos2 = structureBlockInfo.pos;
						BlockPos blockPos3 = blockPos2.offset(direction);
						int l = blockPos2.getY() - k;
						int m = -1;
						StructurePool structurePool = StructurePoolBasedGenerator.REGISTRY
								.get(new Identifier(structureBlockInfo.tag.getString("target_pool")));
						StructurePool structurePool2 = StructurePoolBasedGenerator.REGISTRY
								.get(structurePool.getTerminatorsId());
						if (structurePool != StructurePool.INVALID
								&& (structurePool.getElementCount() != 0 || structurePool == StructurePool.EMPTY)) {
							boolean bl2 = blockBox.contains(blockPos3);
							AtomicReference<VoxelShape> atomicReference4;
							int o;
							if (bl2) {
								atomicReference4 = atomicReference2;
								o = k;
								if (atomicReference2.get() == null) {
									atomicReference2.set(VoxelShapes.cuboid(Box.from(blockBox)));
								}
							} else {
								atomicReference4 = atomicReference;
								o = i;
							}

							List<StructurePoolElement> list = Lists.newArrayList();
							if (j != this.field_18700) {
								list.addAll(structurePool.getElementIndicesInRandomOrder(this.field_18705));
							}

							list.addAll(structurePool2.getElementIndicesInRandomOrder(this.field_18705));
							Iterator<StructurePoolElement> var26 = list.iterator();

							while (var26.hasNext()) {
								StructurePoolElement structurePoolElement2 = (StructurePoolElement) var26.next();
								if (structurePoolElement2 == EmptyPoolElement.INSTANCE) {
									break;
								}

								Iterator<BlockRotation> var28 = BlockRotation.randomRotationOrder(this.field_18705)
										.iterator();

								label117: while (var28.hasNext()) {
									BlockRotation blockRotation2 = (BlockRotation) var28.next();
									List<Structure.StructureBlockInfo> list2 = structurePoolElement2
											.getStructureBlockInfos(this.field_18703, BlockPos.ORIGIN, blockRotation2,
													this.field_18705);
									BlockBox blockBox2 = structurePoolElement2.getBoundingBox(this.field_18703,
											BlockPos.ORIGIN, blockRotation2);
									int q;
									if (blockBox2.getBlockCountY() > 16) {
										q = 0;
									} else {
										q = list2.stream().mapToInt((structureBlockInfox) -> {
											if (!blockBox2.contains(structureBlockInfox.pos.offset(
													(Direction) structureBlockInfox.state.get(JigsawBlock.FACING)))) {
												return 0;
											} else {
												Identifier identifier = new Identifier(
														structureBlockInfox.tag.getString("target_pool"));
												StructurePool pool = StructurePoolBasedGenerator.REGISTRY
														.get(identifier);
												StructurePool pool1 = StructurePoolBasedGenerator.REGISTRY
														.get(pool.getTerminatorsId());

												return Math.max(pool.method_19309(this.field_18703),
														pool1.method_19309(this.field_18703));
											}
										}).max().orElse(0);
									}

									Iterator<StructureBlockInfo> var33 = list2.iterator();

									StructurePool.Projection projection2;
									boolean bl3;
									int s;
									int t;
									int v;
									BlockBox blockBox4;
									BlockPos blockPos6;
									int y;
									do {
										Structure.StructureBlockInfo structureBlockInfo2;
										do {
											if (!var33.hasNext()) {
												continue label117;
											}

											structureBlockInfo2 = (Structure.StructureBlockInfo) var33.next();
										} while (!JigsawBlock.attachmentMatches(structureBlockInfo,
												structureBlockInfo2));

										BlockPos blockPos4 = structureBlockInfo2.pos;
										BlockPos blockPos5 = new BlockPos(blockPos3.getX() - blockPos4.getX(),
												blockPos3.getY() - blockPos4.getY(),
												blockPos3.getZ() - blockPos4.getZ());
										BlockBox blockBox3 = structurePoolElement2.getBoundingBox(this.field_18703,
												blockPos5, blockRotation2);
										int r = blockBox3.minY;
										projection2 = structurePoolElement2.getProjection();
										bl3 = projection2 == StructurePool.Projection.RIGID;
										s = blockPos4.getY();
										t = l - s + ((Direction) structureBlockInfo.state.get(JigsawBlock.FACING))
												.getOffsetY();
										if (bl && bl3) {
											v = k + t;
										} else {
											if (m == -1) {
												m = this.field_18702.method_20402(blockPos2.getX(), blockPos2.getZ(),
														Heightmap.Type.WORLD_SURFACE_WG);
											}

											v = m - s;
										}

										int w = v - r;
										blockBox4 = blockBox3.method_19311(0, w, 0);
										blockPos6 = blockPos5.add(0, w, 0);
										if (q > 0) {
											y = Math.max(q + 1, blockBox4.maxY - blockBox4.minY);
											blockBox4.maxY = blockBox4.minY + y;
										}
									} while (VoxelShapes.matchesAnywhere((VoxelShape) atomicReference4.get(),
											VoxelShapes.cuboid(Box.from(blockBox4).contract(0.25D)),
											BooleanBiFunction.ONLY_SECOND));

									atomicReference4.set(VoxelShapes.combine((VoxelShape) atomicReference4.get(),
											VoxelShapes.cuboid(Box.from(blockBox4)), BooleanBiFunction.ONLY_FIRST));
									y = poolStructurePiece.getGroundLevelDelta();
									int aa;
									if (bl3) {
										aa = y - t;
									} else {
										aa = structurePoolElement2.method_19308();
									}

									PoolStructurePiece poolStructurePiece2 = this.field_18701.create(this.field_18703,
											structurePoolElement2, blockPos6, aa, blockRotation2, blockBox4);
									int ad;
									if (bl) {
										ad = k + l;
									} else if (bl3) {
										ad = v + s;
									} else {
										if (m == -1) {
											m = this.field_18702.method_20402(blockPos2.getX(), blockPos2.getZ(),
													Heightmap.Type.WORLD_SURFACE_WG);
										}

										ad = m + t / 2;
									}

									poolStructurePiece.addJunction(new JigsawJunction(blockPos3.getX(), ad - l + y,
											blockPos3.getZ(), t, projection2));
									poolStructurePiece2.addJunction(new JigsawJunction(blockPos2.getX(), ad - s + aa,
											blockPos2.getZ(), -t, projection));
									this.field_18704.add(poolStructurePiece2);
									if (j + 1 <= this.field_18700) {
										this.field_18706.addLast(
												new class_4181(poolStructurePiece2, atomicReference4, o, j + 1));
									}
									continue label90;
								}
							}
						} else {
							LOGGER.warn("Empty or none existent pool: {}",
									structureBlockInfo.tag.getString("target_pool"));
						}
					}

					return;
				}
			}
		}
	}

	public static final class class_4181 {
		private final PoolStructurePiece field_18696;
		private final AtomicReference<VoxelShape> field_18697;
		private final int field_18698;
		private final int field_18699;

		public class_4181(PoolStructurePiece poolStructurePiece, AtomicReference<VoxelShape> atomicReference, int i, int j) {
			this.field_18696 = poolStructurePiece;
			this.field_18697 = atomicReference;
			this.field_18698 = i;
			this.field_18699 = j;
		}
	}

}
