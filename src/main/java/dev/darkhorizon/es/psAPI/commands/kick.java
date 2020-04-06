package dev.darkhorizon.es.psAPI.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class kick implements CommandExecutor {
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            manageCommand((Player) commandSender, strings);
        } else {
            commandSender.sendMessage("Commando solo disponible para jugadores");
        }
        return true;
    }

    private void manageCommand(Player p, String[] args) {
        if (args.length == 0) {
            p.sendMessage("USAGE");
        } else {
            Player target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                p.sendMessage("El jugador " + args[0] + " no esta conectado al servidor");
            } else {
                target.getLocation();
            }
        }

    }
}
