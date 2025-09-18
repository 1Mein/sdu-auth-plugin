package org.editz.sduAuth.utils

import org.bukkit.Bukkit
import org.bukkit.World
import org.bukkit.WorldCreator
import org.bukkit.WorldType
import org.bukkit.entity.Player

fun createWorldIfNotExists(worldName: String): World? {
    var world = Bukkit.getWorld(worldName)
    if (world == null) {
        val creator = WorldCreator(worldName)
        // По желанию, можно указать тип мира:
        // creator.environment(World.Environment.NORMAL)
        // creator.type(WorldType.FLAT)
        if (worldName == "auth_world") {
            creator.environment(World.Environment.NORMAL)
            creator.type(WorldType.FLAT)
        }

        world = creator.createWorld()

        if (worldName == "auth_world") {

            if (world != null) {
                world.setDifficulty(org.bukkit.Difficulty.PEACEFUL)  // мирный режим
                world.pvp = false
            }                                    // отключаем PvP
        }

        println("Мир $worldName создан!")
    }

    return world
}

