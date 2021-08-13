package me.pulsi_.advancedcheatcontrol.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class MainCommandTab implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender s, Command command, String alias, String[] args) {

        List<String> args1 = new ArrayList<>();
        if (s.hasPermission("advancedcheatcontrol.reload")) args1.add("reload");
        if (s.hasPermission("advancedcheatcontrol.setwarp")) args1.add("setwarp");
        if (s.hasPermission("advancedcheatcontrol.setexit")) args1.add("setexit");
        if (s.hasPermission("advancedcheatcontrol.help")) args1.add("help");

        List<String> args2 = new ArrayList<>();
        if (s.hasPermission("advancedcheatcontrol.setwarp") || s.hasPermission("advancedcheatcontrol.setexit")) args2.add("player");
        if (s.hasPermission("advancedcheatcontrol.setwarp") || s.hasPermission("advancedcheatcontrol.setexit")) args2.add("admin");

        List<String> resultArgs1 = new ArrayList<>();
        if (args.length == 1) {
            for (String a : args1) {
                if (a.toLowerCase().startsWith(args[0].toLowerCase()))
                    resultArgs1.add(a);
            }
            return resultArgs1;
        }

        List<String> resultArgs2 = new ArrayList<>();
        if (args.length == 2) {
            for (String a : args2) {
                if (a.toLowerCase().startsWith(args[1].toLowerCase()))
                    resultArgs2.add(a);
            }
            return resultArgs2;
        }
        return null;
    }
}