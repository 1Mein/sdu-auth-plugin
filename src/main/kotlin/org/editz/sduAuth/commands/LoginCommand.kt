package org.editz.sduAuth.commands


import org.editz.sduAuth.services.AuthService
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class LoginCommand(
    private val authService: AuthService
) : CommandExecutor {

    override fun onCommand(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): Boolean {
        if (args.size < 2) {
            sender.sendMessage("§cИспользование: /login <username> <password>")
            return true
        }

        val username = args[0]
        val password = args[1]

        sender.sendMessage("§7Авторизация...")

        val success = authService.serverLogin(username, password)
        if (success) {
            sender.sendMessage("§aУспешный вход!")
        } else {
            sender.sendMessage("§cОшибка входа")
        }

        return true
    }
}