package cat.emir.echotooltip.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.resource.v1.ResourceLoader;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;

import java.util.List;

public class EchoTooltipClient implements ClientModInitializer {

    private static final Identifier FRAME_TEXTURE = Identifier.withDefaultNamespace("textures/gui/sprites/tooltip/frame.png");

    private static volatile boolean serverFrameOverride = false;

    public static boolean isServerFrameOverride() {
        return serverFrameOverride;
    }

    @Override
    public void onInitializeClient() {
        ResourceLoader.get(PackType.CLIENT_RESOURCES).registerReloadListener(
                Identifier.fromNamespaceAndPath("echotooltip", "frame_override_check"),
                (ResourceManagerReloadListener) manager -> {
                    List<Resource> stack = manager.getResourceStack(FRAME_TEXTURE);
                    serverFrameOverride = !stack.isEmpty()
                            && stack.getLast().sourcePackId().startsWith("server/");
                }
        );
    }
}
