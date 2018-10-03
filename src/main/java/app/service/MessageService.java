package app.service;

import app.dto.MessageDTO;
import app.form.AddMessageForm;
import app.model.Message;
import app.model.User;
import app.repository.MessageRepository;
import app.service.interfaces.MessageServiceInt;
import app.service.interfaces.UserServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class MessageService implements MessageServiceInt {
    private MessageRepository messageRepository;
    private UserServiceInt userService;

    @Autowired
    public MessageService(MessageRepository messageRepository, UserServiceInt userService) {
        this.messageRepository = messageRepository;
        this.userService = userService;
    }


    @Override
    public List<MessageDTO> getAllMessage(User sender, User recipient) {
        List<Message> messages = messageRepository.getAllBySenderAndRecipientOrderByDate(sender, recipient);
        return createMessageDTO(messages, sender);
    }

    @Override
    public List<User> getRecipients(long sender_id) {
        User user = userService.find(sender_id);
        List<User> users = getRecipients(user);
//        if (user.getRole().equals("MANAGER_ROLE")) {
//            for (User u: users) {
//                if (u.getRole().equals("EMPLOYEE_ROLE") || u.getRole().equals("USER_ROLE")) {
//                    u.setLogin("Аноним #" + UUID.randomUUID().hashCode()%10000);
//                }
//            }
//        }
//        else if (user.getRole().equals("EMPLOYEE_ROLE")) {
//
//            for (User u: users) {
//                if (u.getRole().equals("USER_ROLE")) {
//                    u.setLogin("Аноним #" + UUID.randomUUID().hashCode()%10000);
//                }
//            }
//        }
        return users;
    }

    @Override
    public List<User> getRecipients(User sender) {
//        return null;
        List<User> users =  messageRepository.getAllRecipientBySender(sender.getId());
        users.addAll(messageRepository.getAllSenderBySender(sender.getId()));
        List<User> result = new ArrayList<>();
        System.out.println("i have recepients " + users.size());
        if (sender.getRole().equals("MANAGER_ROLE")) {
            for (User u: users) {
                if (u.getRole().equals("EMPLOYEE_ROLE") || u.getRole().equals("USER_ROLE")) {
                    u.setLogin("Аноним #" + hashForAnonim(u.getLogin(), u.getRole(), u.getId()));
                }
            }
        }
        else if (sender.getRole().equals("EMPLOYEE_ROLE")) {

            for (User u: users) {
                if (u.getRole().equals("USER_ROLE")) {
                    u.setLogin("Аноним #" + hashForAnonim(u.getLogin(), u.getRole(), u.getId()));
                }
            }
        }
        for (User u: users) {
            if (!result.contains(u)) result.add(u);
        }
        result.remove(sender);
        return result;
    }

    private String hashForAnonim(String login, String role, long id) {
        long hash = miniHash(login, 1);
        hash = miniHash(role, hash);
        hash = hash*31%10000;
        return Long.toString(hash);
    }

    private long miniHash(String string, long hash) {
        for (int i = 0; i < string.length(); i++) {
            hash = (hash*31 + string.charAt(i))%10000;
        }
        return hash;
    }
    @Override
    public void addMessage(AddMessageForm addMessageForm) {
        User sender = userService.find(addMessageForm.getSender_id());
        User recipient = userService.find(addMessageForm.getRecipient_id());
        Message message = Message.builder()
                                .recipient(recipient)
                                .sender(sender)
                                .body(addMessageForm.getBody())
                                .date(addMessageForm.getDate())
                                .build();
        messageRepository.save(message);

    }

    private List<MessageDTO> createMessageDTO(List<Message> messages, User user) {
        List<MessageDTO> messageDTOs = new ArrayList<>();
        User u = null;
        for (Message message: messages) {
            u = message.getSender();

            if (user.getRole().equals("MANAGER_ROLE")) {
                if (u.getRole().equals("EMPLOYEE_ROLE") || u.getRole().equals("USER_ROLE")) {
                       u.setLogin("Аноним #" + hashForAnonim(u.getLogin(), u.getRole(), u.getId()));
                }
            }
            else if (user.getRole().equals("EMPLOYEE_ROLE")) {
                if (u.getRole().equals("USER_ROLE")) {
                    u.setLogin("Аноним #" + hashForAnonim(u.getLogin(), u.getRole(), u.getId()));
                }
            }
            messageDTOs.add(new MessageDTO(message));
        }
        return messageDTOs;
    }
}
