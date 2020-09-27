package github.Louwind.Features.mixin;

import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.RuleTestType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(RuleTest.class)
public interface RuleTestInvoker {

    @Invoker("getType")
    RuleTestType<?> getType();

}
