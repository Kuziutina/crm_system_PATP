package app.service;

import app.dto.UserDTO;
import app.dto.UserRegisterDTO;
import app.form.UserImageForm;
import app.form.UserUpdateForm;
import app.model.User;
import app.model.UserData;
import app.model.UserImage;
import app.repository.UserDataRepository;
import app.repository.UserImageRepository;
import app.repository.UserRepository;
import app.service.interfaces.UserServiceInt;
import lombok.SneakyThrows;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserService implements UserServiceInt {

    @Value("${storage.path.user.image}")
    private String imageStoragePath;

    private UserDataRepository userDataRepository;
    private UserImageRepository userImageRepository;
    private UserRepository userRepository;

    public UserService(UserRepository userRepository, UserDataRepository userDataRepository, UserImageRepository userImageRepository) {
        this.userRepository = userRepository;
        this.userDataRepository = userDataRepository;
        this.userImageRepository = userImageRepository;
    }

    @Override
    public void addNewUser(String login, String password) {
        User user = User.builder()
                        .login(login)
                        .password(password)
                        .userData(new UserData())
                        .build();
        userRepository.save(user);
    }

    @Override
    public void addUser(UserDTO userDTO) {
        userRepository.save(createUserFromUserDTO(userDTO));
    }

    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User find(long id) {
        return userRepository.getOne(id);
    }

    @Override
    public User find(User user) {
        return userRepository.findOneByLoginAndPasswordAndRole(user.getLogin(), user.getPassword(), user.getRole());
    }

    @Override
    public void delete(long id) {
        User user = find(id);
        userRepository.delete(user);
    }

    @Override
    public void updateUserData(User user, UserUpdateForm userUpdateForm) {
        UserData userData = user.getUserData();
        userData.setLastName(userUpdateForm.getLastName());
        userData.setName(userUpdateForm.getName());
        userData.setFatherName(userUpdateForm.getFatherName());
        userData.setEmail(userUpdateForm.getEmail());
        userDataRepository.save(userData);
    }

    @Override
    public void updateUserImage(User user, UserImageForm userImageForm) {
        System.out.println("i in update image");
        createNewImage(user, userImageForm.getImageUser());
    }

    @Override
    @SneakyThrows
    public void loadUserImage(long id, HttpServletResponse response) {
        UserImage userImage = userImageRepository.getOne(id);
        response.setContentType(userImage.getType());
        InputStream inputStream = new FileInputStream(new File(userImage.getPath()));
        IOUtils.copy(inputStream, response.getOutputStream());
        response.flushBuffer();
    }

    @Override
    public List<UserDTO> getAllUserDTO() {
        List<UserDTO> userDTOList = new ArrayList<>();
        List<User> users = getAllUsers();
        for(User u: users) {
            userDTOList.add(UserDTO.getByUser(u));
        }
        return userDTOList;
    }

    @Override
    public User createUserFromUserDTO(UserDTO userDTO) {
        User user = User.builder()
                        .role(userDTO.getRole())
                        .password(userDTO.getPassword())
                        .login(userDTO.getLogin())
                        .build();
        return user;
    }

    private void createNewImage(User user, MultipartFile file) {
        if (file != null) {
            System.out.println("i start create image");
            UserImage userImage = new UserImage();
            if (user.getUserImage() != null) {
                userImage = user.getUserImage();
            }
            userImage.setUser(user);
            userImage.setFileName(generateStorageName() + "." + FilenameUtils.getExtension(file.getOriginalFilename()));
            userImage.setPath(imageStoragePath + "/" + userImage.getFileName());
            userImage.setType(file.getContentType());
            userImageRepository.save(userImage);
            user.setUserImage(userImage);
            saveNewsImageFileToStorage(file, userImage);
        }
        System.out.println("file null??");
    }

    private String generateStorageName() {
        return UUID.randomUUID().toString();
    }

    public void saveNewsImageFileToStorage(MultipartFile file, UserImage userImage) {
        try {
            Files.copy(file.getInputStream(), Paths.get(imageStoragePath, userImage.getFileName()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public User registerNewUserAccount(UserRegisterDTO accountDto) {
        User user = User.builder().login(accountDto.getLogin())
                .password(accountDto.getPassword())
                .role("USER_ROLE")
                .build();
        userRepository.save(user);
        UserData userData = new UserData();
        userData.setUser(user);
        userDataRepository.save(userData);
        return user;
    }

    public User getUserByLogin(String username) {
        return userRepository.findOneByLogin(username);
    }
}
