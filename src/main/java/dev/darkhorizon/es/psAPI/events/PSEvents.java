package dev.darkhorizon.es.psAPI.events;

import com.sun.org.apache.bcel.internal.generic.RETURN;
import dev.darkhorizon.es.psAPI.Main;
import net.sacredlabyrinth.Phaed.PreciousStones.PreciousStones;
import net.sacredlabyrinth.Phaed.PreciousStones.field.FieldFlag;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class PSEvents implements Listener {

    private final Main plugin = Main.getPlugin(Main.class);

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (e.getAction() == Action.LEFT_CLICK_BLOCK) {
            if (PreciousStones.API().isPStone(e.getClickedBlock().getLocation()) &&
                    p.getItemInHand().hasItemMeta() &&
                    p.getItemInHand().getItemMeta().getDisplayName().equals("")) {
                //e.setCancelled(true);
                p.sendMessage("¡No puedes romper menas con este item!");
            }
        } else if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
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
                    || p.getItemInHand().getItemMeta().getDisplayName().equals("§eMena de inicio");
        } else {
            return false;
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlace(BlockPlaceEvent e) {
        if (PreciousStones.API().isFieldProtectingArea(FieldFlag.ALL, e.getBlock().getLocation())) {

            //e.setCancelled(true);
        }
    }
}
