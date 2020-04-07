package dev.darkhorizon.es.psAPI.events;

import com.sun.org.apache.bcel.internal.generic.RETURN;
import dev.darkhorizon.es.psAPI.Main;
import net.sacredlabyrinth.Phaed.PreciousStones.PreciousStones;
import net.sacredlabyrinth.Phaed.PreciousStones.field.Field;
import net.sacredlabyrinth.Phaed.PreciousStones.field.FieldFlag;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class PSEvents implements Listener {

    private final Main plugin = Main.getPlugin(Main.class);


    @EventHandler(priority = EventPriority.HIGHEST)
    public void blockCommands(PlayerCommandPreprocessEvent e) {
        Player p = e.getPlayer();
        String cmd = e.getMessage();
        if (cmd.startsWith("/ps allow")) {
             Field launcher_mena = PreciousStones.getInstance().getForceFieldManager().getEnabledSourceField(p.getLocation(), FieldFlag.ALL);
             if (launcher_mena != null && !launcher_mena.getOwner().equals(p.getName())) {
                 e.setCancelled(true);
                 p.sendMessage("¡Solo el propietario de la mena esta autorizado a usar este comando!");
             }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (e.getAction() == Action.LEFT_CLICK_BLOCK) {
            if (PreciousStones.API().isPStone(e.getClickedBlock().getLocation()) &&
                    p.getItemInHand().hasItemMeta()) {
                for (String line : p.getItemInHand().getItemMeta().getLore()) {
                    if (line.contains("Fundicion") || line.contains("Autofundición")) {
                        e.setCancelled(true);
                        p.sendMessage("¡No puedes romper menas con este item!");
                        break;
                    }
                }
            }
        } else if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (p.isOp()) {
                return;
            }
            if (checkItem(p)) {
                if (PreciousStones.API().isFieldProtectingArea(FieldFlag.ALL, e.getClickedBlock().getLocation())
                    || PreciousStones.API().isFieldProtectingArea(FieldFlag.ALL, p.getLocation())) {
                    e.setCancelled(true);
                    p.sendMessage("¡No se puede colocar una mena en una zona ya protegida!");
                }
            }
        }
    }

    private boolean checkItem(Player p) {
        if (p.getItemInHand().hasItemMeta()) {
            return p.getItemInHand().getItemMeta().getDisplayName().equals("§eMena de diamante")
                    || p.getItemInHand().getItemMeta().getDisplayName().equals("§eMena de oro")
                    || p.getItemInHand().getItemMeta().getDisplayName().equals("§eMena de hierro")
                    || p.getItemInHand().getItemMeta().getDisplayName().equals("§eMega mena")
                    || p.getItemInHand().getItemMeta().getDisplayName().equals("§eMena de inicio");
        } else {
            return false;
        }
    }

}
