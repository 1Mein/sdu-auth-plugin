package org.editz.sduAuth.commands


import org.bukkit.Bukkit
import org.editz.sduAuth.services.AuthService
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.editz.sduAuth.SduAuth

class LoginCommand(
    private val authService: AuthService
) : CommandExecutor {

    override fun onCommand(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): Boolean {
        if (sender !is Player) {
            sender.sendMessage("§cЭта команда доступна только игрокам!")
            return true
        }
        val player = sender

        if (args.size < 2) {
            player.sendMessage("§cИспользование: /login <username> <password>")
            return true
        }



        val username = args[0]
        val password = args[1]

        player.sendMessage("§7Авторизация...")

        val success = authService.serverLogin(username, password)
        if (success) {
            player.sendMessage("§aУспешный вход!")
            val plugin = Bukkit.getPluginManager().getPlugin("sdu-auth") as SduAuth
            plugin.teleportToWorld(player, "survival_world")
        } else {
            player.sendMessage("§cОшибка входа")
        }

        return true
    }
}