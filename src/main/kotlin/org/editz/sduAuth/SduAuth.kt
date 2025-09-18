package org.editz.sduAuth

import org.bukkit.World
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import org.editz.sduAuth.commands.LoginCommand
import org.editz.sduAuth.listeners.AuthWorldProtectionListener
import org.editz.sduAuth.listeners.PlayerJoinListener
import org.editz.sduAuth.services.AuthService
import org.editz.sduAuth.utils.createWorldIfNotExists


class SduAuth : JavaPlugin() {
    override fun onEnable() {
        logger.info("SduAuth enabled!")

        createWorldIfNotExists("survival_world")

        server.pluginManager.registerEvents(PlayerJoinListener(this), this)
        server.pluginManager.registerEvents(AuthWorldProtectionListener(), this)


        val authService = AuthService()

        getCommand("login")?.setExecutor(LoginCommand(authService))
        getCommand("hello")?.setExecutor { sender, _, _, _ ->
            sender.sendMessage("Hello, world!")
            true
        }
//        sender.sendMessage("§7Добро пожаловать! Пройдите авторизацию командой /login <id> <password>")
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }


    fun teleportToWorld(player: Player, worldName: String) {
        val world = server.getWorld(worldName)
        if (world != null) {
            player.teleport(world.spawnLocation)
            player.sendMessage("Вы перемещены в мир $worldName")
        } else {
            player.sendMessage("Мир $worldName не найден!")
        }
    }
}
