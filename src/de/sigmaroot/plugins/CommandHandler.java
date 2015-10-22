package de.sigmaroot.plugins;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class CommandHandler {

	private FlatMe plugin;
	private List<Command> commandList;

	public List<Command> getCommandList() {
		return commandList;
	}

	public void setCommandList(List<Command> commandList) {
		this.commandList = commandList;
	}

	public CommandHandler(FlatMe plugin) {
		super();
		this.plugin = plugin;
		commandList = new ArrayList<Command>();
		this.commandList.add(new Command("help", "flatme.player", "/flatme help %page", 0));
	}

	public void handleCommand(CommandSender sender, String[] args) {
		sender.sendMessage(plugin.configurator.resolveLocaledString("&4Hey :-)", null));
	}

	private void showTable(CommandSender sender, String title, int page, List<String> entrys) {
		int show_entrys_per_page = 5;
		int perPage = show_entrys_per_page;
		int pages = (int) Math.ceil(((double) entrys.size()) / ((double) perPage));
		if (pages < 1) {
			pages = 1;
		}
		if (page > pages) {
			page = pages;
		}
		int min = (page - 1) * perPage;
		int max = min + (perPage - 1);
		if (entrys.size() == 0) {
			entrys.add(ChatColor.DARK_RED + "Keine Eintr�ge gefunden!");
		}
		if ((max + 1) > entrys.size()) {
			max = (entrys.size() - 1);
		}
		sender.sendMessage(ChatColor.GOLD + title);
		sender.sendMessage("=============== [Seite " + page + " von " + pages + "] ===============");
		for (int i = min; i <= max; i++) {
			sender.sendMessage(entrys.get(i));
		}
	}

	private void executeConsole(String cmd) {
		Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), cmd);
	}

	private static int myRandom(int low, int high) {
		return (int) (Math.random() * (high - low) + low);
	}

	private int parsePageNumber(String test) {
		int value;
		try {
			value = Integer.parseInt(test);
		} catch (Exception e) {
			return -1;
		}
		return value;
	}

}
