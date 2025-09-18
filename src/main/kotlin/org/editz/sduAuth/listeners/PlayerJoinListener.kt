package org.editz.sduAuth.listeners

import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.entity.Player
import org.editz.sduAuth.SduAuth

class PlayerJoinListener(private val plugin: SduAuth) : Listener {

    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) {
        val player: Player = event.player

        // Если игрок первый раз на сервере
        if (!player.hasPlayedBefore()) {
            val world = Bukkit.getWorld("auth_world")
            if (world != null) {
                player.teleport(world.spawnLocation)
                player.sendMessage("Добро пожаловать! Пройдите авторизацию.")
            } else {
                player.sendMessage("Мир авторизации не найден!")
            }
        }
    }
}
