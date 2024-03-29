package commands;

import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IMessage;
import util.Config;
import util.QueueHandler;

import java.util.Map;

public class MapConfig implements ICommand
{
    /*
     * this is in a different command since my permissions setup is jank
     */
    @Override
    public String getName()
    {
        return "map";
    }

    @Override
    public void run(IDiscordClient client, String args, IMessage msg, Config cfg, Map<String, ICommand> cmdMap, QueueHandler queue, int permLevel )
    {
        String[] commandVar;

        try
        {
            commandVar = args.split("\\s+", 2);
        }
        catch ( NullPointerException e )
        {
            msg.reply("No args");

            return;
        }

        try
        {
            switch( commandVar[0] )
            {
                case "add":
                    queue.addMap( msg, commandVar[1] );
                    break;
                case "remove":
                    queue.removeMap( msg, commandVar[1] );
                    break;
                case "reset":
                    queue.resetMaps();

                    msg.reply("maps reset");
                    break;
                case "toggle":
                    queue.toggleMode( msg );
                    break;
                default:
                    msg.reply("invalid sub command");
                    break;

            }
        }
        catch ( NullPointerException e )
        {
            msg.reply("your subcommand requires an argument");

            e.printStackTrace();
        }

    }

    @Override
    public int getRank() {
        return admin;
    }

    @Override
    public String help(int permLevel)
    {
        return "map: \n" +
                "       Joins current queue\n" +
                "   Sub Commands:\n" +
                "       add (map)\n" +
                "           adds a map to the random map pool\n" +
                "       remove (map)\n" +
                "           removes map from random map pool\n" +
                "       reset (map)\n" +
                "           resets map pool to full maps\n" +
                "       toggle\n" +
                "           toggles between veto and random map";
    }
}

