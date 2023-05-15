package com.example.contestapp.core.service;

import com.example.contestapp.core.dto.UserDTO;
import com.example.contestapp.core.entities.ImageAttachment;
import com.example.contestapp.core.entities.User;
import com.example.contestapp.core.repositories.ImageAttachmentRepository;
import com.example.contestapp.tools.FileTools;
import com.example.contestapp.tools.UserMapper;
import com.example.contestapp.core.repositories.UserRepository;
import com.example.contestapp.tools.FieldValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private final ImageAttachmentRepository imageAttachmentRepository;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper, ImageAttachmentRepository imageAttachmentRepository){
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.imageAttachmentRepository = imageAttachmentRepository;
    }
    @PostConstruct
    public void init(){
        //todo:
    }

    public UserDTO addUser(String surname, String name, String patronymic, String birthday, String email,
                           String phoneNumber){
        User user  = User.builder().surname(surname).name(name).patronymic(patronymic).birthday(birthday).email(email)
                .phoneNumber(phoneNumber).build();
        userRepository.save(user);
        return userMapper.toDTO(user);
    }

    public List<UserDTO> getAll(){
        return userMapper.toDTOAll(userRepository.findAll());
    }

    public UserDTO getById(String id) throws Exception {
        return userMapper.toDTO(userRepository.getById(FieldValidator.validateId(id)));
    }

    public String getSurnameById(String id) throws Exception {
        return userRepository.getById(FieldValidator.validateId(id)).getSurname();
    }

    public String getNameById(String id) throws Exception {
        return userRepository.getById(FieldValidator.validateId(id)).getName();
    }

    public String getPatronymicById(String id) throws Exception {
        return userRepository.getById(FieldValidator.validateId(id)).getPatronymic();
    }

    public UserDTO updateNameById(String id, String name) throws Exception {
        User user = userRepository.getById(FieldValidator.validateId(id));
        user.updateName(name);
        userRepository.save(user);
        return userMapper.toDTO(user);
    }

    public UserDTO updateSurnameById(String id, String surname) throws Exception {
        User user = userRepository.getById(FieldValidator.validateId(id));
        user.updateSurname(surname);
        userRepository.save(user);
        return userMapper.toDTO(user);
    }

    public UserDTO updatePatronymicById(String id, String patronymic) throws Exception {
        User user = userRepository.getById(FieldValidator.validateId(id));
        user.updateSurname(patronymic);
        userRepository.save(user);
        return userMapper.toDTO(user);
    }

    public boolean deleteById(String id) throws Exception {
        userRepository.deleteById(FieldValidator.validateId(id));
        return true;
    }

    public String getBirthdayById(String id) throws Exception {
        return userRepository.getById(FieldValidator.validateId(id)).getBirthday();
    }

    public String getEmailById(String id) throws Exception {
        return userRepository.getById(FieldValidator.validateId(id)).getEmail();
    }

    public String getPhoneNumberById(String id) throws Exception {
        return userRepository.getById(FieldValidator.validateId(id)).getPhoneNumber();
    }

    public UserDTO updateBirthdayById(String id, String birthday) throws Exception {
        User user = userRepository.getById(FieldValidator.validateId(id));
        user.updateBirthday(birthday);
        userRepository.save(user);
        return userMapper.toDTO(user);
    }

    public UserDTO updateEmailById(String id, String email) throws Exception {
        User user = userRepository.getById(FieldValidator.validateId(id));
        user.updateEmail(email);
        userRepository.save(user);
        return userMapper.toDTO(user);
    }

    public UserDTO updatePhoneNumberById(String id, String phoneNumber) throws Exception {
        User user = userRepository.getById(FieldValidator.validateId(id));
        user.updatePhoneNumber(phoneNumber);
        userRepository.save(user);
        return userMapper.toDTO(user);
    }

    public UserDTO addImageAttachment(MultipartFile file, String id) throws Exception {
        File uploadDir = new File("src/main/resources/images");
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
        String curDate = LocalDateTime.now().toString();
        String fileName =
                "attach_" + curDate + "_" + file.getOriginalFilename().toLowerCase().replaceAll(" ", "-").replaceAll(":", "-");
        fileName = fileName.replaceAll(":", "-");
        String filePath = uploadDir + File.separator + fileName;
        Files.copy(file.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
        ImageAttachment imageAttachment = ImageAttachment.builder()
                .attachTitle(fileName)
                .uploadDate(LocalDate.now())
                .extension(FileTools.getFileExtension(file.getOriginalFilename()))
                .downloadLink(filePath)
                .build();
        imageAttachmentRepository.save(imageAttachment);
        User user = userRepository.getById(FieldValidator.validateId(id));
        user.updateImageAttachment(imageAttachment);
        userRepository.save(user);
        return userMapper.toDTO(user);
    }

    public UrlResource getImageByUserId(String id) throws Exception {
        User user = userRepository.getById(FieldValidator.validateId(id));
        String filePath = user.getImageAttachment().getDownloadLink();
        Path path = Paths.get(filePath);
        return new UrlResource(path.toUri());
    }

    public UserDTO updateImageById(MultipartFile file, String id) throws Exception {
        deleteImageById(id);
        return addImageAttachment(file, id);
    }

    public void deleteImageById(String id) throws Exception {
        User user = userRepository.getById(FieldValidator.validateId(id));
        ImageAttachment img = user.getImageAttachment();
        if (img != null){
            imageAttachmentRepository.delete(img);
            String downloadLink = img.getDownloadLink();
            Path path = Paths.get(downloadLink);
            Files.delete(path);
            user.updateImageAttachment(null);
            userRepository.save(user);
        }
    }

}
