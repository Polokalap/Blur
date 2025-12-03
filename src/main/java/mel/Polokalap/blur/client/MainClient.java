package mel.Polokalap.blur.client;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Identifier;
import org.ladysnake.satin.api.event.ShaderEffectRenderCallback;
import org.ladysnake.satin.api.managed.ManagedShaderEffect;
import org.ladysnake.satin.api.managed.ShaderEffectManager;


import static mel.Polokalap.blur.Main.CONFIG;

public class MainClient implements ClientModInitializer {

    private final ManagedShaderEffect motionblur = ShaderEffectManager.getInstance().manage(Identifier.of("blur", "shaders/post/motion_blur.json"), (shader) -> {
        shader.setUniformValue("BlendFactor", getBlur());
    });

    private float currentBlur;

    public void onInitializeClient() {

        ShaderEffectRenderCallback.EVENT.register((deltaTick) -> {
            if (CONFIG.ENABLE()) {
                if (getBlur() != 0.0F) {

                    if (CONFIG.SMART_MOTIONBLUR() && MinecraftClient.getInstance().getCurrentFps() < CONFIG.DISABLE_MOTIONBLUR_LIMIT())
                        return;

                    if (currentBlur != getBlur()) {
                        motionblur.setUniformValue("BlendFactor", getBlur());
                        currentBlur = getBlur();
                    }

                    motionblur.render(deltaTick);

                }

            }

        });

    }

    public float getBlur() {
        return Math.min(CONFIG.MOTIONBLUR_AMOUNT(), 99) / 100F;
    }

}
