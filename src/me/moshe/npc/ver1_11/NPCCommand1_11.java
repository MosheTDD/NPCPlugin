package me.moshe.npc.ver1_11;

import me.moshe.npc.NPC;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import static me.moshe.npc.util.Utils.*;

public class NPCCommand1_11 implements CommandExecutor {
    private NPC plugin;

    public NPCCommand1_11(NPC plugin) {
        this.plugin = plugin;
        plugin.getCommand("npc").setExecutor(this);
    }

    public NPCManager1_11 npcManager11;

    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args){
        if(!playerCheck(sender))return true;
        Player p = (Player) sender;
        if(!permCheck(p, "npc.npc"))return true;
        if(!argsCheck(p, 0, args))return true;
        Location location = new Location(p.getWorld(), cd("npcLocation.x"), cd("npcLocation.y"), cd("npcLocation.z"));
        npcManager11 = new NPCManager1_11("Bob", location);
        npcManager11.spawn();
        new BukkitRunnable(){
            public void run(){
                npcManager11.teleport(location.add(0, 1.2, 0));
                Bukkit.getScheduler().runTaskLater(plugin, () -> npcManager11.teleport(location.add(0, -1.2, 0)), 5L);
                new BukkitRunnable(){
                    public void run(){
                        npcManager11.teleport(location.add(0, 1.2, 0));
                        Bukkit.getScheduler().runTaskLater(plugin, () -> npcManager11.teleport(location.add(0, -1.2, 0)), 5L);
                    }
                }.runTaskLater(plugin, 20L);
                new BukkitRunnable(){
                    public void run(){
                        npcManager11.entityMetadata((byte) 0x02);
                        Bukkit.getScheduler().runTaskLater(plugin, () -> npcManager11.entityMetadata((byte) 0), 20L);
                    }
                }.runTaskLater(plugin, 40L);
                new BukkitRunnable(){
                    public void run(){
                        npcManager11.entityMetadata((byte) 0x02);
                        Bukkit.getScheduler().runTaskLater(plugin, () -> npcManager11.animation(0), 20L);
                        Bukkit.getScheduler().runTaskLater(plugin, () -> npcManager11.entityMetadata((byte) 0), 40L);
                    }
                }.runTaskLater(plugin, 80L);
            }
        }.runTaskLater(plugin, 20L);

        return false;
    }
}
