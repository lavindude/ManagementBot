/* Tasks:
    implement time limit
    certain 
    push to github
 */

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Invite;
import net.dv8tion.jda.core.events.guild.GuildJoinEvent;
import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.exceptions.ErrorResponseException;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;
import java.util.ArrayList;
import java.util.List;

//listernerAdapter class
// https://ci.dv8tion.net/job/JDA/javadoc/net/dv8tion/jda/api/hooks/ListenerAdapter.html
public class Main extends ListenerAdapter {
    private ArrayList<Event> events = new ArrayList<Event>();
    private ArrayList<String> spammer = new ArrayList<String>();
    private ArrayList<Long> mesIDs = new ArrayList<Long>();
    private ArrayList<Long> times = new ArrayList<Long>();

    private int timesSworn = 0;
    private long start = 0;
    public static void main(String[] args) throws LoginException {
        JDABuilder builder = new JDABuilder(AccountType.BOT);
        String token = "Njg4MTAyMzA1NjI2NTg3MTgy.XpJDtg.BeSmnfiCQO3CbN5nmBZMP3uBfl8";
        builder.setToken(token);
        builder.addEventListener(new Main());
        builder.buildAsync();

    }

    //greet user
    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
        EmbedBuilder join = new EmbedBuilder();
        join.setDescription("Welcome to the IHS Server! Please look over the rules and then send a dm to an admin with your name so we know who you are. Once you are verified, you will be able to send messages. Go to the roles tab and add roles as you wish.");
        event.getGuild().getDefaultChannel().sendMessage(join.build()).queue();
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        //ignore bots (including this one)
        if (event.getAuthor().isBot()) {
            return;
        }

        boolean allowSpam = false;
        System.out.println("We received a message from " +
                event.getAuthor().getName() + " \"" +
                event.getMessage().getContentDisplay() + "\"   ------     channel: " + event.getChannel());



        String mes = event.getMessage().getContentRaw();

        String channel = "" + event.getMessage().getCategory().getTextChannels();
        String user = "" + event.getAuthor().getName();
        Long id = event.getChannel().getLatestMessageIdLong();


        //spammer algorithm

        times.add(System.currentTimeMillis());

        if (channel.contains("😀") || channel.contains("🧠") || channel.contains("🤖")) {
            allowSpam = true;
        }

        if (!allowSpam) {
            if (times.size() >= 2) {
                if (times.get(times.size() - 1) - times.get(times.size() - 2) > 5000) {
                    allowSpam = true;
                    spammer.clear();
                    times.clear();
                    mesIDs.clear();
                }

            }

            if (!allowSpam) {
                int i = spammer.size() - 3;

                spammer.add(user);
                mesIDs.add(id);

                if (spammer.size() >= 4) {
                    if (spammer.get(i).equals(spammer.get(i + 1)) && spammer.get(i).equals(spammer.get(i + 2)) && spammer.get(i).equals(spammer.get(i + 3))) {
                        event.getChannel().sendMessage("Stop spamming!").queue();
                        int k = i + 3;
                        for (i = spammer.size() - 4; i < k; i++) {
                            event.getChannel().deleteMessageById(mesIDs.get(i)).complete();
                        }

                        spammer.clear();
                        mesIDs.clear();
                    }
                }
            }
        }




        //swearing algorithm
        Swears check = new Swears();
        String swears = check.swear(mes);

        if (swears.equals("No Swearing!")) {
            event.getMessage().delete().queue();
            event.getChannel().sendMessage("No swearing!").queue();
            timesSworn++;
        }

        else if (swears.equals("Use the word 'homosexual' instead.")) {
            event.getMessage().delete().queue();
            event.getChannel().sendMessage("Use the word 'homosexual' instead.").queue();
            timesSworn++;
        }

        if (timesSworn % 5 == 0 && timesSworn != 0) {
            event.getChannel().sendMessage("What do you not get about not swearing?").queue();
            timesSworn++;
        }

        //events
        int sub1 = mes.indexOf("!addEvent");
        int sub2 = mes.indexOf("!setMonth");
        int sub3 = mes.indexOf("!setDay");
        int sub4 = mes.indexOf("!setYear");
        int sub5 = mes.indexOf("!addEventManually");

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



        else if (mes.equals("!printE")) {
            setEventsE();
            for (Event e : events) {
                String p = e.toString();
                event.getChannel().sendMessage(p).queue();
            }
        }

        else if (mes.equals("!p")) {

        }

        else if (mes.equals("!test")) {
            event.getChannel().sendMessage("" + event.getChannel()).queue();
        }

    }

    //manually enter events in code:
    public void setEventsE() {
        /*Event meeting = new Event("AP U1 Review Session", "March", 25, 2020, "AP European History", "3:45 - 5:00");
        events.add(meeting);
        meeting = new Event("AP U2 Review Session", "March", 26, 2020, "AP European History", "3:45 - 5:00");
        events.add(meeting);*/
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
