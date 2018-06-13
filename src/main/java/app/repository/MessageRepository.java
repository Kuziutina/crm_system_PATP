package app.repository;

import app.model.Message;
import app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    @Modifying
    @Transactional
    @Query("SELECT m from Message m WHERE (m.sender = :sender and m.recipient = :recipient) or (m.recipient = :sender and m.sender = :recipient)")
    List<Message> getAllBySenderAndRecipientOrderByDate(@Param("sender") User sender,@Param("recipient") User recipient);

    @Modifying
    @Transactional
    @Query("SELECT DISTINCT recipient from Message WHERE sender_id = :sender_id or recipient_id = :sender_id")
    List<User> getAllRecipientBySender(@Param("sender_id") Long sender_id);

    @Modifying
    @Transactional
    @Query("SELECT DISTINCT sender from Message WHERE sender_id = :sender_id or recipient_id = :sender_id")
    List<User> getAllSenderBySender(@Param("sender_id") Long sender_id);
}
