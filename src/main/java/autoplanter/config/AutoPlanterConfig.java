package autoplanter.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class AutoPlanterConfig {
    public static final ForgeConfigSpec GENERAL_SPEC;
    static {
        ForgeConfigSpec.Builder configBuilder = new ForgeConfigSpec.Builder();
        setupConfig(configBuilder);
        GENERAL_SPEC = configBuilder.build();
    }

    public static ForgeConfigSpec.IntValue timeMultiplier;
    public static ForgeConfigSpec.DoubleValue upgradeMultiplier;

    private static void setupConfig(ForgeConfigSpec.Builder builder) {
        builder.comment("The multiplier for how long a seed will take to \"grow\". It is multiplied by the number of growth stages for the plant (default 7)");
        timeMultiplier = builder.defineInRange("time_multiplier", 100, 1, Integer.MAX_VALUE);
        builder.comment("The multiplier for the speed upgrade for the auto planter");
        upgradeMultiplier = builder.defineInRange("upgrade_multiplier", 0.5, 0.0,1.0);
    }
}
