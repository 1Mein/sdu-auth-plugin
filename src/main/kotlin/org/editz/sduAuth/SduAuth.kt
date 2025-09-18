package org.editz.sduAuth

import org.bukkit.plugin.java.JavaPlugin
import org.editz.sduAuth.commands.LoginCommand
import org.editz.sduAuth.services.AuthService


class SduAuth : JavaPlugin() {

    override fun onEnable() {
        logger.info("SduAuth enabled!")

        val authService = AuthService()

        getCommand("login")?.setExecutor(LoginCommand(authService))
        getCommand("hello")?.setExecutor { sender, _, _, _ ->
            sender.sendMessage("Hello, world!")
            true
        }
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }

}
