package me.moshe.npc;


import me.moshe.npc.ver1_12.NPCCommand1_12;
import me.moshe.npc.ver1_8.NPCCommand1_8;
import me.moshe.npc.util.Utils;
import me.moshe.npc.ver1_9.NPCCommand1_9;
import me.moshe.npc.ver1_10.NPCCommand1_10;
import me.moshe.npc.ver1_11.NPCCommand1_11;
import me.moshe.npc.ver1_13.NPCCommand1_13;
import me.moshe.npc.ver1_14.NPCCommand1_14;
import me.moshe.npc.ver1_15.NPCCommand1_15;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class NPC extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        getConfig().options().copyDefaults(true);
        new Utils(this);
        if (Bukkit.getVersion().contains("1.8")) {
            new NPCCommand1_8(this);
        } else if (Bukkit.getVersion().contains("1.9")) {
            new NPCCommand1_9(this);
        } else if (Bukkit.getVersion().contains("1.10")) {
            new NPCCommand1_10(this);
        }else if(Bukkit.getVersion().contains("1.11")){
            new NPCCommand1_11(this);
        } else if (Bukkit.getVersion().contains("1.12")) {
            new NPCCommand1_12(this);
        }else if(Bukkit.getVersion().contains("1.13")){
            new NPCCommand1_13(this);
        }else if(Bukkit.getVersion().contains("1.14")){
            new NPCCommand1_14(this);
        }else if(Bukkit.getVersion().contains("1.15")){
            new NPCCommand1_15(this);
        }
    }
}
