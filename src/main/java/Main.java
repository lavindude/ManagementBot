import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;

public class Main extends ListenerAdapter {
    public static void main(String[] args) throws LoginException {
        JDABuilder builder = new JDABuilder(AccountType.BOT);
        String token = "Njg4MTAyMzA1NjI2NTg3MTgy.XmvciQ.LwTLgmnkWZGdO1KDWTksRGCBGrI";
        builder.setToken(token);
        builder.addEventListener(new Main());
        builder.buildAsync();
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        System.out.println("We recieved a message from " +
                event.getAuthor().getName() + " \"" +
                event.getMessage().getContentDisplay() + "\"   ------     channel: " + event.getChannel());
        String mes = event.getMessage().getContentRaw();

        if (mes.equals("!test1")) {
            event.getChannel().sendMessage("Welcome to this server!").queue();
        }

        else if (mes.equals("!test21")) {
            event.getChannel().sendMessage("Pong!").queue();
        }


    }
}
