package me.javoris767.votesql.utils;

import java.util.logging.Logger;

import me.javoris767.votesql.VoteSQL;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VoteSQLChat
{
	private static VoteSQL plugin;

	private static String pluginName;
	private static String logName;
	private static String prefix;

	private static final Logger log = Logger.getLogger("Minecraft");

	public VoteSQLChat(VoteSQL voteSQL)
	{
		plugin = voteSQL;
		pluginName = "VoteSQL";
		logName = "[" + pluginName + "]";
		prefix = "[" + ChatColor.DARK_AQUA + pluginName
				+ ChatColor.WHITE + "] ";
	}

	public static void logInfo(String message)
	{
		log.info(logName + message);
	}

	public static void logSevere(String message)
	{
		log.severe(logName + message);
	}

	public static void logWarning(String message)
	{
		log.warning(logName + message);
	}

	public static void enableMessage()
	{
		logInfo(" v" + VoteSQL.v + " enabled.");
	}

	public static void disableMessage()
	{
		logInfo(" v" + VoteSQL.v + " disabled.");
	}

	public static void debugMessage()
	{
		logInfo(" DEBUG: We got here!");
	}

	public static void sendMessage(CommandSender sender, String message)
	{
		sender.sendMessage(prefix + message);
	}

	public static void broadcast(String message)
	{
		Bukkit.getServer().broadcastMessage(prefix + message);
	}

	public static void broadcastVoteMessage(String playerName,
			String siteVotedOn)
	{
		String rawMessage = plugin.getConfig().getString("VoteSQL.onVote.Message").toString();
		rawMessage = rawMessage.replace("%P", playerName.toLowerCase());
		rawMessage = rawMessage.replace("%S", siteVotedOn.toLowerCase());
		String finalMessage = Functions.colorize(rawMessage);
		Bukkit.getServer().broadcastMessage(prefix + finalMessage);
	}
	public static void sendCurrencyReveivedMessage(Player player, String playerName,
			int money)
	{
		String rawMessage = plugin.getConfig().getString("VoteSQL.currency.Message").toString();
		rawMessage = rawMessage.replace("%P", playerName.toLowerCase());
		rawMessage = rawMessage.replace("%M" , "" + money);
		String finalMessage = Functions.colorize(rawMessage);
		player.sendMessage(prefix + finalMessage);
	}
	public static void dontHavePermission(CommandSender sender)
	{
		sender.sendMessage(ChatColor.DARK_RED + "You do not have permission!");
	}
	public static void errorMessage(Exception e)
	{
		logWarning(" Error checking for updates " + e);
	}
}
