package me.moshe.npc.ver1_15;

import me.moshe.npc.NPC;
import net.minecraft.server.v1_15_R1.EntityPose;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import static me.moshe.npc.util.Utils.*;

public class NPCCommand1_15 implements CommandExecutor {
    private NPC plugin;

    public NPCCommand1_15(NPC plugin) {
        this.plugin = plugin;
        plugin.getCommand("npc").setExecutor(this);
    }

    public NPCManager1_15 npcManager13;

    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args){
        if(!playerCheck(sender))return true;
        Player p = (Player) sender;
        if(!permCheck(p, "npc.npc"))return true;
        if(!argsCheck(p, 0, args))return true;
        Location location = new Location(p.getWorld(), cd("npcLocation.x"), cd("npcLocation.y"), cd("npcLocation.z"));
        npcManager13 = new NPCManager1_15("Bob", location);
        npcManager13.spawn();
        new BukkitRunnable(){
            public void run(){
                npcManager13.teleport(location.add(0, 1.2, 0));
                Bukkit.getScheduler().runTaskLater(plugin, () -> npcManager13.teleport(location.add(0, -1.2, 0)), 5L);
                new BukkitRunnable(){
                    public void run(){
                        npcManager13.teleport(location.add(0, 1.2, 0));
                        Bukkit.getScheduler().runTaskLater(plugin, () -> npcManager13.teleport(location.add(0, -1.2, 0)), 5L);
                    }
                }.runTaskLater(plugin, 20L);
                new BukkitRunnable(){
                    public void run(){
                        npcManager13.entityMetadata(EntityPose.CROUCHING);
                        Bukkit.getScheduler().runTaskLater(plugin, () -> npcManager13.entityMetadata(EntityPose.STANDING), 20L);
                    }
                }.runTaskLater(plugin, 40L);
                new BukkitRunnable(){
                    public void run(){
                        npcManager13.entityMetadata(EntityPose.CROUCHING);
                        Bukkit.getScheduler().runTaskLater(plugin, () -> npcManager13.animation(0), 20L);
                        Bukkit.getScheduler().runTaskLater(plugin, () -> npcManager13.entityMetadata(EntityPose.STANDING), 40L);
                    }
                }.runTaskLater(plugin, 80L);
            }
        }.runTaskLater(plugin, 20L);

        return false;
    }
}
