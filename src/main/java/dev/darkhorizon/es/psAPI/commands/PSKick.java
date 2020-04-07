package dev.darkhorizon.es.psAPI.commands;

import net.sacredlabyrinth.Phaed.PreciousStones.PreciousStones;
import net.sacredlabyrinth.Phaed.PreciousStones.field.Field;
import net.sacredlabyrinth.Phaed.PreciousStones.field.FieldFlag;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PSKick implements CommandExecutor {
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            manageCommand((Player) commandSender, strings);
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
                if (!PreciousStones.API().isFieldProtectingArea(FieldFlag.ALL, p.getLocation())) {
                    p.sendMessage("No te encuentras en una zona protegida");
                    return;
                }
                if (!PreciousStones.API().isFieldProtectingArea(FieldFlag.ALL, target.getLocation())) {
                    p.sendMessage("El jugador " + target.getName() + " no se encuentra en una zona protegida");
                    return;
                }
                Field launcher_mena = PreciousStones.getInstance().getForceFieldManager().getEnabledSourceField(p.getLocation(), FieldFlag.ALL);
                Field target_mena = PreciousStones.getInstance().getForceFieldManager().getEnabledSourceField(target.getLocation(), FieldFlag.ALL);
                if (!launcher_mena.getOwner().equals(p.getName())) {
                    p.sendMessage("No eres el owner de esta mena!");
                    return;
                }
                if (launcher_mena.getId() != target_mena.getId()) {
                    p.sendMessage("Tu y " + target.getName() + " no estais en la misma mena!");
                    return;
                }
                for (String allowed : launcher_mena.getAllowed()) {
                    if (allowed.equalsIgnoreCase(target.getName())) {
                        p.sendMessage(target.getName() + " es un miembro de la mena");
                        return;
                    }
                }
                p.sendMessage("has expulsado a " + target.getName());
                target.sendMessage("Te han expulsado de la mena");
                //TODO TELEPORT TARGET TO SPAWN
            }
        }

    }
}
