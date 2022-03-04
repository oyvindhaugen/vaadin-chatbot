package com.oyvindh.chatbot.views.chat;

import com.oyvindh.chatbot.views.main.MainView;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.alicebot.ab.Bot;
import org.alicebot.ab.Chat;
import org.vaadin.artur.Avataaar;
@PageTitle("Chat")
@Route(value = "chat", layout = MainView.class)
@RouteAlias(value = "", layout = MainView.class)
public class ChatView extends VerticalLayout {
    private final UI ui;
    // Here we introduce the UI method as a private final which means it can never be changed and can only be accessed inside this class.
    private final MessageList messageList = new MessageList();
    // here we introduce MessageList as a new object so we can use the method inside this class.
    private final TextField message = new TextField();
    // here we do the same
    private final Chat chatSession;
    // here we introduce the Chat method so it can be used later, but not changed
    private final ScheduledExecutorService executorService;
    // here we introduce the delay method that I mentioned ealier in MessageList.java


    public ChatView(Bot alice, ScheduledExecutorService executorService) {
        // here we construct these things
        this.executorService = executorService;
        chatSession = new Chat(alice);
        ui = UI.getCurrent().getUI().get();
        message.setPlaceholder("Enter a message...");
        message.setSizeFull();
        // Here we're configuring how the messagebox will look
        Button send = new Button(VaadinIcon.ENTER.create(), event -> sendMessage());
        send.addClickShortcut(Key.ENTER);
        // this is the sendmessage button, but we also add the shortcut for using enter

        HorizontalLayout inputLayout = new HorizontalLayout(message, send);
        inputLayout.addClassName("inputLayout");
        // Here we set the message layout to a horizontal one
        add(messageList, inputLayout);
        // Here we add it to the layout
        expand(messageList);
        // we make it somewhat bigger here
        setSizeFull();
        // Here we set the size
    }

    private void sendMessage() {
        String text = message.getValue();
        messageList.addMessage("You", new Avataaar("Name"), text, true);
        // here we add the title "You" for the person sending the messages and it also gives an avatar. Afterwards it sends the String variable "text".
        message.clear();

        executorService.schedule(() -> {
            //here is the delay in action
            String answer = chatSession.multisentenceRespond(text);
            ui.access(() -> messageList.addMessage(
                "Alice", new Avataaar("Alice2"), answer, false));
        }, new Random().ints(1000, 3000).findFirst().getAsInt(), TimeUnit.MILLISECONDS);
    } // here we set it to wait 1-3 seconds just so it doesnt answer immediately like a robot would


}
