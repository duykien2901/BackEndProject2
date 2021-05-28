package com.example.project2.Device.testFile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Optional;

@RestController
@CrossOrigin(value = "*")
@RequestMapping("/user")
public class UserController {
    private static final Path CURRENT_FOLDER = Paths.get(System.getProperty("user.dir"));
    @Autowired
    private UserRepositoryTest userRepository;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestParam String email,
                       @RequestParam String password,
                       @RequestParam MultipartFile image) throws IOException {
        Path staticPath = Paths.get("static");
        Path imagePath = Paths.get("images");
        if (!Files.exists(CURRENT_FOLDER.resolve(staticPath).resolve(imagePath))) {
            Files.createDirectories(CURRENT_FOLDER.resolve(staticPath).resolve(imagePath));
        }
        Path file = CURRENT_FOLDER.resolve(staticPath)
                .resolve(imagePath).resolve(image.getOriginalFilename());
        try (OutputStream os = Files.newOutputStream(file)) {
            os.write(image.getBytes());
        }
        User user = new User();
        user.setId(1L);
        user.setEmail(email);
        user.setPassword(password);
        user.setPhoto(imagePath.resolve(image.getOriginalFilename()).toString());
        userRepository.save(user);
        File f = new File(CURRENT_FOLDER.resolve(staticPath).resolve(imagePath).resolve(image.getOriginalFilename()).toString());
        System.out.println(f.exists());
        return userRepository.save(user);
    }

    @GetMapping
    public User get() throws IOException {
        Path staticPath = Paths.get("static");
        Optional<User> user =  userRepository.findById(1L);
        User user1 = user.get();
        String s = "sds";
        File f = new File(String.valueOf(CURRENT_FOLDER.resolve(staticPath).resolve(user1.getPhoto())));
        user1.setPhoto(Base64.getEncoder().encodeToString(Files.readAllBytes(CURRENT_FOLDER.resolve(staticPath).resolve(user1.getPhoto()))));
        return user1;
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<?> download(@PathVariable Long id) throws IOException {
        Path staticPath = Paths.get("static");
        Optional<User> user =  userRepository.findById(id);
        User user1 = user.get();
        File file = new File(String.valueOf(CURRENT_FOLDER.resolve(staticPath).resolve(user1.getPhoto())));
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition",
                String.format("attachment; filename=\"%s\"", file.getName()));
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        ResponseEntity<Object> responseEntity = ResponseEntity.ok().headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM).body(resource);
        return responseEntity;
    }

    @GetMapping("/redirect")
    public String  redirect(@RequestParam("id") Long id) {
        String url = "http://localhost:8000/user/download/" + id;
        return url;
    }
}

// resolve noi path
//