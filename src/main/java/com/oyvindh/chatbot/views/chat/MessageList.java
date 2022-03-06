package com.oyvindh.chatbot.views.chat;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import org.vaadin.artur.Avataaar;

public class MessageList extends Div {
  public MessageList() {
    setClassName(getClass().getSimpleName());
    // Here it sets the name to the name of the main class
  }
  public void addMessage(String from, Avataaar avatar, String text, boolean isCurrentUser) {
    // Here it creates the variables from, avatar, text and isCurrentUser
    Span fromContainer = new Span(new Text(from));
    fromContainer.addClassName(getClass().getSimpleName()+"-name");
    // here it creates a new Span object and places a text object within it. It has the name fromContainer and adds the classname + the string "-name"
    // The "-name" is added so we can customize it in CSS later
    Div textContainer = new Div(new Text(text));
    textContainer.addClassName(getClass().getSimpleName()+"-bubble");
    //here it creates a new Div object and places a new text object within it, and it also adds the classname + the string "-bubble"
    // The "-bubble" is added so we can customize it in CSS later
    Div avatarContainer = new Div(avatar, fromContainer);
    avatarContainer.addClassName(getClass().getSimpleName()+"-avatar");
    // Here it creates a new Div object and places both the Avatar variable and the fromContainer Span within it. It also adds the string "-avatar" to the classname.
    // The "-avatar" is added so we can customize it in CSS later
    Div line = new Div(avatarContainer, textContainer);
    line.addClassName(getClass().getSimpleName()+"-row");
    //Here it creates a new Div (Line) and places both the avatarContainer and the textContainer within it and adds "-row" to the classname.
    // The "-row" is added so we can customize it in CSS later
    add(line);
    // Here it adds "line" to the screen.
    if (isCurrentUser) {
      line.addClassName(getClass().getSimpleName()+"-row-currentUser");
      // Here it adds "-row-currentUser" to the line object if isCurrentUser is true
      textContainer.addClassName(getClass().getSimpleName()+"-bubble-currentUser");
      // Here it adds "-bubble-currentUser" to the textContainer object if isCurrentUser is true.
    }
    line.getElement().callJsFunction("scrollIntoView");
    // Here it calls the Js function with the name "scrollIntoView"
  }
}
