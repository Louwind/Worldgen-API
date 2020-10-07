package github.Louwind.Features.impl.init;

import github.Louwind.Features.impl.util.serializer.test.*;
import github.Louwind.Features.processor.FeatureRuleTestType;

public class FeatureRuleTests {

    public static final FeatureRuleTestType ALWAYS_TRUE = new FeatureRuleTestType(new AlwaysTrueRuleTestSerializer());
    public static final FeatureRuleTestType BLOCK_MATCH = new FeatureRuleTestType(new BlockMatchRuleTestSerializer());
    public static final FeatureRuleTestType BLOCKSTATE_MATCH = new FeatureRuleTestType(new BlockstateMatchRuleTestSerializer());
    public static final FeatureRuleTestType TAG_MATCH = new FeatureRuleTestType(new TagMatchRuleTestSerializer());
    public static final FeatureRuleTestType RANDOM_BLOCK_MATCH = new FeatureRuleTestType(new RandomBlockMatchRuleTestSerializer());
    public static final FeatureRuleTestType RANDOM_BLOCKSTATE_MATCH = new FeatureRuleTestType(new RandomBlockstateMatchRuleTestSerializer());

}
