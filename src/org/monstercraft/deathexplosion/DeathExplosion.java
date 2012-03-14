package org.monstercraft.deathexplosion;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.monstercraft.deathexplosion.listeners.DeathExplosionListener;
import org.monstercraft.deathexplosion.managers.CommandManager;
import org.monstercraft.deathexplosion.managers.HookManager;
import org.monstercraft.deathexplosion.managers.SettingsManager;

public class DeathExplosion extends JavaPlugin {

	private DeathExplosionListener listener;
	private static Logger logger = Logger.getLogger("Minecraft");
	private CommandManager commandManager;
	private static SettingsManager settings;
	private static HookManager hooks;

	public void onEnable() {
		settings = new SettingsManager(this);
		hooks = new HookManager(this);
		this.commandManager = new CommandManager();
		listener = new DeathExplosionListener(this);
		getServer().getPluginManager().registerEvents(listener, this);
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
				"lwc admin purge -remove BoomyExplody");
		log("DeathExplosion has been enabled!");
	}

	public void onDisable() {
		log("DeathExplosion has been disabled.");
	}

	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		return commandManager.onGameCommand(sender, command, label, args);
	}

	/**
	 * Logs a message to the console.
	 * 
	 * @param msg
	 *            The message to print.
	 */
	public static void log(String msg) {
		logger.log(Level.INFO, "[Death Explosions] " + msg);
	}

	/**
	 * Logs debugging messages to the console.
	 * 
	 * @param error
	 *            The message to print.
	 */
	protected static void debug(Exception error) {
		logger.log(Level.WARNING,
				"[Death Explosions - Critical error detected!]");
		error.printStackTrace();
	}

	public SettingsManager getSettingsManager() {
		return settings;
	}

	public HookManager getHookManager() {
		return hooks;
	}
}