package org.editz.sduAuth.listeners

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.block.BlockPlaceEvent

class AuthWorldProtectionListener : Listener {

    @EventHandler
    fun onBlockBreak(event: BlockBreakEvent) {
        if (event.player.world.name == "auth_world") {
            event.isCancelled = true
            event.player.sendMessage("§cВы не можете ломать блоки здесь!")
        }
    }

    @EventHandler
    fun onBlockPlace(event: BlockPlaceEvent) {
        if (event.player.world.name == "auth_world") {
            event.isCancelled = true
            event.player.sendMessage("§cВы не можете ставить блоки здесь!")
        }
    }
}
