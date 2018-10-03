package app.repository;

import app.form.UserUpdateForm;
import app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findOneByLogin(String login);
    User findOneByLoginAndPasswordAndRole(String login, String password, String role);


    @Modifying
    @Transactional
    @Query("UPDATE User SET login = :#{#changedUser.login} WHERE id = :id")
    void update(@Param("id") long id, @Param("changedUser") UserUpdateForm changedUser);
}
