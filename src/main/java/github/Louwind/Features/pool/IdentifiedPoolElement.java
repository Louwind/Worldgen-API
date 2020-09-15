package github.Louwind.Features.pool;

import static net.minecraft.structure.processor.BlockIgnoreStructureProcessor.IGNORE_AIR_AND_STRUCTURE_BLOCKS;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import github.KingVampyre.DeepTrenches.api.world.structure.generator.util.StructureJsonHelper;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.pool.SinglePoolElement;
import net.minecraft.structure.pool.StructurePool.Projection;
import net.minecraft.structure.processor.JigsawReplacementStructureProcessor;
import net.minecraft.structure.processor.StructureProcessor;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.math.BlockBox;

public class IdentifiedPoolElement extends SinglePoolElement {

	public IdentifiedPoolElement(String string) {
		super(string);
	}
	
	public IdentifiedPoolElement(String string, List<StructureProcessor> list, Projection projection) {
		super(string, list, projection);
	}

	public IdentifiedPoolElement(String string, StructureProcessor... processors) {
		super(string, Arrays.asList(processors), Projection.RIGID);
	}

	public Identifier getId() {
		return this.location;
	}

	@Override
	protected StructurePlacementData method_16616(BlockRotation blockRotation, BlockBox blockBox) {
		StructurePlacementData placementData = new StructurePlacementData();
		
		placementData.setBoundingBox(blockBox);
		placementData.setRotation(blockRotation);
		placementData.method_15131(true);
		placementData.setIgnoreEntities(false);
		placementData.addProcessor(IGNORE_AIR_AND_STRUCTURE_BLOCKS);
		placementData.addProcessor(JigsawReplacementStructureProcessor.INSTANCE);
		
		this.processors.forEach(placementData::addProcessor);
		this.getProjection().getProcessors().forEach(placementData::addProcessor);
		
		return placementData;
	}

	public static class Deserializer implements JsonDeserializer<IdentifiedPoolElement> {

		@Override
		public IdentifiedPoolElement deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonSyntaxException {
			JsonObject object = json.getAsJsonObject();
			
			String structure = JsonHelper.getString(object, "structure");
			
			Projection projection = StructureJsonHelper.getProjection(object, "projection");
			StructureProcessor[] processors = JsonHelper.deserialize(object, "processors", new StructureProcessor[] {}, context, StructureProcessor[].class);
						
			return new IdentifiedPoolElement(structure, Arrays.asList(processors), projection);
		}

	}
	
}
