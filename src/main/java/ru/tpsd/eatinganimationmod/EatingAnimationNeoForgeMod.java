package ru.tpsd.eatinganimationmod;

import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.AddPackFindersEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(EatingAnimationNeoForgeMod.MOD_ID)
public class EatingAnimationNeoForgeMod {

    public static final String MOD_ID = "eatinganimationid";
    private static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public EatingAnimationNeoForgeMod(IEventBus modEventBus) {
        modEventBus.addListener(this::onAddPackFinders);
    }

    private void onAddPackFinders(AddPackFindersEvent event) {
        if (event.getPackType() == PackType.CLIENT_RESOURCES) {
            try {
                event.addPackFinders(
                    ResourceLocation.fromNamespaceAndPath(MOD_ID, "resourcepacks/supporteatinganimation"),
                    PackType.CLIENT_RESOURCES,
                    Component.literal("Support Eating Animation"),
                    PackSource.BUILT_IN,
                    true,
                    Pack.Position.TOP
                );
            } catch (Exception e) {
                LOGGER.error("Failed to register bundled resource pack for Eating Animation Fork", e);
            }
        }
    }
}
