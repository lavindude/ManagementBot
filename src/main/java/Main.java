import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;
import java.util.ArrayList;

public class Main extends ListenerAdapter {
    private ArrayList<Event> events = new ArrayList<Event>();

    public static void main(String[] args) throws LoginException {
        JDABuilder builder = new JDABuilder(AccountType.BOT);
        String token = "Njg4MTAyMzA1NjI2NTg3MTgy.XmvciQ.LwTLgmnkWZGdO1KDWTksRGCBGrI";
        builder.setToken(token);
        builder.addEventListener(new Main());
        builder.buildAsync();
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        System.out.println("We received a message from " +
                event.getAuthor().getName() + " \"" +
                event.getMessage().getContentDisplay() + "\"   ------     channel: " + event.getChannel());

        String mes = event.getMessage().getContentRaw();

        int sub1 = mes.indexOf("!addEvent");
        int sub2 = mes.indexOf("!setMonth");
        int sub3 = mes.indexOf("!setDay");
        int sub4 = mes.indexOf("!setYear");
        int sub5 = mes.indexOf("!addEventManually");
        int sub6 = mes.toLowerCase().indexOf("fuck");
        int sub7 = mes.toLowerCase().indexOf("shit");
        int sub8 = mes.toLowerCase().indexOf("pussy");
        int sub9 = mes.toLowerCase().indexOf("ass");
        int sub10 = mes.toLowerCase().indexOf("gay");
        int sub11 = mes.toLowerCase().indexOf("jew");
        int sub12 = mes.toLowerCase().indexOf("cum");
        int sub13 = mes.toLowerCase().indexOf("penis");
        int sub14 = mes.toLowerCase().indexOf("vagina");

        if (sub1 != -1) {
            Event e = new Event();
            events.add(e);
            Event edit = events.get(events.size() - 1);
            edit.setS(mes.substring(10));
            event.getChannel().sendMessage("Event successfully added!").queue();
        }

        else if (sub2 != -1) {
            Event edit = events.get(events.size() - 1);
            edit.setMonth(mes.substring(10));
            event.getChannel().sendMessage("Month of the event successfully set!").queue();
        }

        else if (sub3 != -1) {
            event.getChannel().sendMessage("What do you want the day of the event's deadline to be?").queue();
            Event edit = events.get(events.size() - 1);
            int m = Integer.parseInt(mes.substring(8));
            edit.setDay(m);
            event.getChannel().sendMessage("Day of the event successfully set!").queue();
        }

        else if (sub4 != -1) {
            Event edit = events.get(events.size() - 1);
            int m = Integer.parseInt(mes.substring(9));
            edit.setYear(m);
            event.getChannel().sendMessage("Year of the event successfully set!").queue();
        }

        else if (sub5 != -1) {
            addEventManually(mes);
        }

        //error with indexes maybe
        else if (sub6 != -1) {
            event.getChannel().sendMessage("No swearing!").queue();
            event.getMessage().delete().queue();
        }

        else if (sub7 != -1) {
            event.getChannel().sendMessage("No swearing!").queue();
            event.getMessage().delete().queue();
        }

        else if (sub8 != -1) {
            event.getChannel().sendMessage("No swearing!").queue();
            event.getMessage().delete().queue();
        }

        else if (sub9 != -1) {
            event.getChannel().sendMessage("No swearing!").queue();
            event.getMessage().delete().queue();
        }

        else if (sub10 != -1) {
            event.getChannel().sendMessage("No swearing!").queue();
            event.getMessage().delete().queue();
        }

        else if (sub11 != -1) {
            event.getChannel().sendMessage("No swearing!").queue();
            event.getMessage().delete().queue();
        }

        else if (sub12 != -1) {
            event.getChannel().sendMessage("No swearing!").queue();
            event.getMessage().delete().queue();
        }

        else if (sub13 != -1) {
            event.getChannel().sendMessage("No swearing!").queue();
            event.getMessage().delete().queue();
        }

        else if (sub14 != -1) {
            event.getChannel().sendMessage("No swearing!").queue();
            event.getMessage().delete().queue();
        }

        else if (mes.equals("!print")) {
            for (Event e : events) {
                String p = e.toString();
                event.getChannel().sendMessage(p).queue();
            }
        }
    }

    public void addEventManually(String mes) {
        //check for logic errors, there is a logic error with the cursor variable, need to fix
        int cursor = 18;
        String rest1 = mes.substring(cursor);
        int space1 = rest1.indexOf(" ");
        String sendMessage = mes.substring(18, space1);
        //code not finished: !addEventmanually eat March 6, 2020 13:49
        cursor = space1 + 1;
        String rest2 = mes.substring(cursor);
        int space2 = rest2.indexOf(" ");
        String sendMonth = rest2.substring(cursor, space2);
        cursor = space2 + 1;
        int comma = rest2.indexOf(",");
        int sendDay = Integer.parseInt(mes.substring(space2, comma));
    }


}
