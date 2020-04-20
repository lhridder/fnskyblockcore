package me.tysluukie.skyblock;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;

import com.connorlinfoot.titleapi.TitleAPI;
import com.nametagedit.plugin.NametagEdit;

import net.milkbowl.vault.economy.Economy;

public class Main extends JavaPlugin implements Listener {
	
	public void onEnable() {
		if (!setupEconomy() ) {
            getLogger().severe(String.format("Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
		this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
		
	}
    
	public static Economy econ = null;
	
	private boolean setupEconomy() {
    if (getServer().getPluginManager().getPlugin("Vault") == null) {
        return false;
    }
    RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
    if (rsp == null) {
        return false;
    }
    econ = rsp.getProvider();
    return econ != null;
}
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		getConfig().addDefault(e.getPlayer().getName() + ".kills","0");
		getConfig().addDefault(e.getPlayer().getName() + ".deaths","0");
		getConfig().addDefault(e.getPlayer().getName() + ".rank","Speler");
    	getConfig().options().copyDefaults(true);
    	saveConfig();
		new BukkitRunnable() {
	            @Override
	            public void run() {
	                if (e.getPlayer() == null || !e.getPlayer().isOnline()) {
	                    cancel();
	                    return;
	                }
	                final org.bukkit.scoreboard.Scoreboard s = Bukkit.getScoreboardManager().getNewScoreboard();
	                final Objective o = s.registerNewObjective("sidebar", "dummy");
	            	o.setDisplaySlot(DisplaySlot.SIDEBAR);
	            	o.setDisplayName(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Skyblock");
	            	Score spacer = o.getScore(ChatColor.AQUA + "");
	            	spacer.setScore(7);
	            	
	            	Score players = o.getScore(ChatColor.AQUA + "" + ChatColor.BOLD + "Welkom, " + ChatColor.GREEN + "" + ChatColor.BOLD + e.getPlayer().getName());
	            	players.setScore(6);
	            	
	            	Score spacer1 = o.getScore(ChatColor.BLACK + "");
	            	spacer1.setScore(5);
	            	
	            	@SuppressWarnings("deprecation")
					double coinscounter = econ.getBalance(e.getPlayer().getName());
	            	Score coins = o.getScore(ChatColor.AQUA + "" + ChatColor.BOLD + "Coins: " + ChatColor.GREEN + coinscounter);
	            	coins.setScore(4);
	            	
	            	int killscounter = getConfig().getInt(e.getPlayer().getName() + ".kills");
	            	Score kills = o.getScore(ChatColor.AQUA + "" + ChatColor.BOLD + "Kills: " + ChatColor.GREEN + killscounter);
	            	kills.setScore(3);
	            	
	            	int deathscounter = getConfig().getInt(e.getPlayer().getName() + ".deaths");
	            	Score deaths = o.getScore(ChatColor.AQUA + "" + ChatColor.BOLD + "Deaths: " + ChatColor.GREEN + deathscounter);
	            	deaths.setScore(2);
	            	
	            	Score spacer2 = o.getScore(ChatColor.BLUE + "");
	            	spacer2.setScore(1);
	            	
	            	Score ip = o.getScore(ChatColor.DARK_RED + "" + ChatColor.BOLD + "FlameNetwork.g-s.nu ");
	            	ip.setScore(0);
	            	
	            	e.getPlayer().setScoreboard(s);
	            }
	        }.runTaskTimer(this, 10, 100);
		e.getPlayer().performCommand("spawn");
    	TitleAPI.sendTitle(e.getPlayer(), 30, 5 * 20, 10, ChatColor.RED + "Welcome!", "at the Flamenetwork Skyblock!");
    	Player target = e.getPlayer();
    	if(getConfig().get(e.getPlayer().getName() + ".rank").equals("Owner")) {
    		String prefix = "&7[&4Owner&7]&6";
			NametagEdit.getApi().setPrefix(target, prefix);
		}
    	if(getConfig().get(e.getPlayer().getName() + ".rank").equals("Moderator")) {
    		String prefix = "&7[&2Mod&7]&6";
			NametagEdit.getApi().setPrefix(target, prefix);
		}
		if(getConfig().get(e.getPlayer().getName() + ".rank").equals("Helper")) {
			String prefix = "&7[&3Helper&7]&6";
			NametagEdit.getApi().setPrefix(target, prefix);
		}
		if(getConfig().get(e.getPlayer().getName() + ".rank").equals("Admin")) {
			String prefix = "&7[&1Admin&7]&6";
			NametagEdit.getApi().setPrefix(target, prefix);
		}
		if(getConfig().get(e.getPlayer().getName() + ".rank").equals("Speler")) {
			String prefix = "&7[&3Speler&7]&6";
			NametagEdit.getApi().setPrefix(target, prefix);
		}
			e.getPlayer().sendMessage("");
			e.getPlayer().sendMessage("");
			e.getPlayer().sendMessage("");
			e.getPlayer().sendMessage("");
			e.getPlayer().sendMessage("");
			e.getPlayer().sendMessage("");
			e.getPlayer().sendMessage("");
			e.getPlayer().sendMessage("");
			e.getPlayer().sendMessage("");
			e.getPlayer().sendMessage("");
			e.getPlayer().sendMessage("");
			e.getPlayer().sendMessage("");
			e.getPlayer().sendMessage("");
			e.getPlayer().sendMessage("");
			e.getPlayer().sendMessage("");
			e.getPlayer().sendMessage("");
			e.getPlayer().sendMessage(ChatColor.GRAY + "_____________________________________________");
			e.getPlayer().sendMessage("");
			e.getPlayer().sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "                  <FlameNetwork>");
			e.getPlayer().sendMessage("");
			e.getPlayer().sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "      Welcome to Skyblock, " + e.getPlayer().getName() + "!");
			e.getPlayer().sendMessage("");
			e.getPlayer().sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "      Do " + ChatColor.GREEN + "" + ChatColor.BOLD + "/is" + ChatColor.GOLD + "" + ChatColor.BOLD + " to create an island!");
			e.getPlayer().sendMessage("");
			e.getPlayer().sendMessage(ChatColor.GRAY + "_____________________________________________");
			e.setJoinMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "+" + ChatColor.GRAY + "] " + ChatColor.GRAY + e.getPlayer().getName());
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		e.setQuitMessage(ChatColor.GRAY + "[" + ChatColor.RED + "-" + ChatColor.GRAY + "] " + ChatColor.GRAY + e.getPlayer().getName());
	}
	
	@EventHandler
	public void onDie(PlayerDeathEvent e) {
		Entity entity = e.getEntity();
		if (entity instanceof Player) {
		Player player = (Player)entity;
		EntityDamageEvent ede = entity.getLastDamageCause();
	    DamageCause dc = ede.getCause();
		int beforedeaths = getConfig().getInt(player.getName() + ".deaths");
		int deaths = beforedeaths + 1;
		getConfig().set(player.getName() + ".deaths", deaths);
		Player killer = ((Player) entity).getKiller();
		if(killer.equals(null)) {
			if (dc == DamageCause.BLOCK_EXPLOSION | dc == DamageCause.SUFFOCATION | dc == DamageCause.FALL | dc == DamageCause.DROWNING | dc == DamageCause.FIRE | dc == DamageCause.POISON) {
				e.setDeathMessage(ChatColor.GOLD + player.getName() + ChatColor.RED + " died!");
			}
		} else {
			e.setDeathMessage(ChatColor.GOLD + player.getName() + ChatColor.RED + " got killed by " + ChatColor.GOLD + killer.getName() + ChatColor.RED + "!");
			int beforekills = getConfig().getInt(killer.getName() + ".kills");
			int kills = beforekills + 1;
			getConfig().set(killer.getName() + ".kills", kills);
		}
		} else {
			//do nothing
		}
		saveConfig();
	}

	@EventHandler
	public void onCommand(PlayerCommandPreprocessEvent e) {
		if(getConfig().get(e.getPlayer().getName() + ".rank").equals("Speler")) {
		if(e.getMessage().contains("plugins")) {
			e.getPlayer().sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Je mag dit commando niet gebruiken!");
			e.setCancelled(true);
		}
		if(e.getMessage().contains("pl")) {
			e.getPlayer().sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Je mag dit commando niet gebruiken!");
			e.setCancelled(true);
		}
		if(e.getMessage().contains("?")) {
			e.getPlayer().sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Je mag dit commando niet gebruiken!");
			e.setCancelled(true);
		}
		if(e.getMessage().contains("bukkit:help")) {
			e.getPlayer().sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Je mag dit commando niet gebruiken!");
			e.setCancelled(true);
		}
		if(e.getMessage().contains("version")) {
			e.getPlayer().sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Je mag dit commando niet gebruiken!");
			e.setCancelled(true);
		}
		if(e.getMessage().contains("about")) {
			e.getPlayer().sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Je mag dit commando niet gebruiken!");
			e.setCancelled(true);
		}
		} else {
			return;
		}
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(cmd.getName().equalsIgnoreCase("setrank")) {
			if(sender.hasPermission("FlameSkyblock.admin")) {
			if(args.length == 0) {
				sender.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "[FlameNetwork] " + ChatColor.GREEN + "/setrank (player) (rank)");
				return true;
			}
			if(args.length == 1) {
				sender.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "[FlameNetwork] " + ChatColor.GREEN + "/setrank (player) (rank)");
				return true;
			}
			if(args.length == 2) {
				Player target = Bukkit.getServer().getPlayer(args[0]);
				if (target == null) {
					sender.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "[FlameNetwork] " + ChatColor.GREEN + "Could not find player " + args[0] + ChatColor.RED + " !");
					return true;
				}
				getConfig().set(target.getName() + ".rank", args[1]);
				target.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "[FlameNetwork] " + ChatColor.GREEN + "Rank updated to: " + getConfig().getString(target.getName() + ".rank") + ChatColor.RED + " !");
				saveConfig();
				if(args[1].equalsIgnoreCase("owner")) {
					String prefix = "&7[&4Owner&7]&6";
					NametagEdit.getApi().setPrefix(target, prefix);
				}
				if(args[1].equalsIgnoreCase("admin")) {
					String prefix = "&7[&1Admin&7]&6";
					NametagEdit.getApi().setPrefix(target, prefix);
				}
				if(args[1].equalsIgnoreCase("moderator")) {
					String prefix = "&7[&2Mod&7]&6";
					NametagEdit.getApi().setPrefix(target, prefix);
				}
				if(args[1].equalsIgnoreCase("builder")) {
					String prefix = "&7[&eBuilder&7]&6";
					NametagEdit.getApi().setPrefix(target, prefix);
				}
				if(args[1].equalsIgnoreCase("speler")) {
					NametagEdit.getApi().clearNametag(target);
				}
				sender.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "[FlameNetwork] " + ChatColor.GREEN + "Speler " + args[0] + " heeft nu rank: " + args[1] + " !");
				}
		} else { 
			sender.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "[FlameNetwork] " + ChatColor.RED + "Je mag dit commando niet uitvoeren!");
		}
		}
		if(cmd.getName().equalsIgnoreCase("hub")) {
			sender.sendMessage(ChatColor.GOLD + "Sending you back to the hub...");
			Player p = (Player)sender;
			ByteArrayOutputStream b = new ByteArrayOutputStream();
			DataOutputStream out = new DataOutputStream(b);
			try {
				out.writeUTF("Connect");
				out.writeUTF("hub");
				} catch (IOException e) {
					e.printStackTrace();
				}
				p.sendPluginMessage(this, "BungeeCord", b.toByteArray());
		}
		if(cmd.getName().equalsIgnoreCase("kitpvp")) {
			sender.sendMessage(ChatColor.GOLD + "Sending you to kitpvp...");
			Player p = (Player)sender;
			ByteArrayOutputStream b = new ByteArrayOutputStream();
			DataOutputStream out = new DataOutputStream(b);
			try {
				out.writeUTF("Connect");
				out.writeUTF("kitpvp");
				} catch (IOException e) {
					e.printStackTrace();
				}
				p.sendPluginMessage(this, "BungeeCord", b.toByteArray());
		}
		return true;
		}
}
