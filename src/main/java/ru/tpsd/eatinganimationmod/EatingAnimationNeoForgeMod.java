package ru.tpsd.eatinganimationmod;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.AddPackFindersEvent;

@Mod(EatingAnimationNeoForgeMod.MOD_ID)
public class EatingAnimationNeoForgeMod {

    public static final String MOD_ID = "eatinganimationid";

    public EatingAnimationNeoForgeMod(IEventBus modEventBus) {
        modEventBus.addListener(EatingAnimationNeoForgeMod::onAddPackFinders);
    }

    private static void onAddPackFinders(AddPackFindersEvent event) {
        if (event.getPackType() != PackType.CLIENT_RESOURCES) {
            return;
        }

        event.addPackFinders(
            ResourceLocation.fromNamespaceAndPath(
                MOD_ID,
                "resourcepacks/supporteatinganimation"
            ),
            PackType.CLIENT_RESOURCES,
            Component.literal("Eating Animation Fork - Mods Support"),
            PackSource.BUILT_IN,
            true,
            Pack.Position.TOP
        );
    }
}
