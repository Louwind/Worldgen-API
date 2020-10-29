package github.Louwind.Features.impl.function;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import github.Louwind.Features.condition.FeatureCondition;
import github.Louwind.Features.context.FeatureContext;
import github.Louwind.Features.function.FeatureFunction;
import github.Louwind.Features.function.FeatureFunctionType;
import github.Louwind.Features.impl.init.FeatureFunctions;
import github.Louwind.Features.util.FeaturesJsonHelper;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.JsonSerializer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.StructureWorldAccess;

import java.util.Arrays;
import java.util.List;

import static github.Louwind.Features.impl.init.FeatureContextParameters.*;

public class ExecuteCommandFunction implements FeatureFunction {

    private final List<FeatureCondition> conditions;
    private final List<String> commands;

    public ExecuteCommandFunction(List<String> commands, FeatureCondition[] conditions) {
        this.conditions = Arrays.asList(conditions);
        this.commands = commands;
    }

    @Override
    public FeatureFunctionType getType() {
        return FeatureFunctions.EXECUTE_COMMAND;
    }

    @Override
    public List<FeatureCondition> getConditions() {
        return this.conditions;
    }

    @Override
    public void accept(FeatureContext context) {
        StructureWorldAccess worldAccess = context.get(WORLD);
        BlockPos pos = context.get(POS);

        ServerWorld world = worldAccess.toServerWorld();
        MinecraftServer server = world.getServer();
        Vec3d vec = Vec3d.ofCenter(pos);

        CommandManager commandManager = server.getCommandManager();
        ServerCommandSource source =  server.getCommandSource().withPosition(vec).withSilent();

        for (String command : this.commands)
            commandManager.execute(source, command);

    }

    public static class Serializer implements JsonSerializer<ExecuteCommandFunction> {

        @Override
        public void toJson(JsonObject json, ExecuteCommandFunction object, JsonSerializationContext context) {

        }

        @Override
        public ExecuteCommandFunction fromJson(JsonObject json, JsonDeserializationContext context) {
            FeatureCondition[] conditions = FeaturesJsonHelper.getConditions(json, context,  "conditions");
            List<String> commands = FeaturesJsonHelper.getList(json, JsonHelper::asString, "commands");

            return new ExecuteCommandFunction(commands, conditions);
        }

    }

}
