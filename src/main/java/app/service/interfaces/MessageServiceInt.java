package app.service.interfaces;

import app.dto.MessageDTO;
import app.form.AddMessageForm;
import app.model.Message;
import app.model.User;

import java.util.List;

public interface MessageServiceInt {
    List<MessageDTO> getAllMessage(User sender, User recipient);
    List<User> getRecipients(long sender_id);
    List<User> getRecipients(User sender);
    void addMessage(AddMessageForm addMessageForm);
}
