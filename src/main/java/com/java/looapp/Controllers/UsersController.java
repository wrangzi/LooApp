package com.java.looapp.Controllers;

import com.java.looapp.Services.MyUserDetailServices;
import com.java.looapp.Services.UserNotFoundException;
import com.java.looapp.Users.User;
import com.java.looapp.Users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Controller
public class UsersController {
    @Autowired
    private MyUserDetailServices service;
    @Autowired
    private UserRepository repo;
    @GetMapping("/register")
    public String showregisterForm(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("pageTitle","Create user account");
        return "signup_form";
    }
    @GetMapping("/users")
    public String listUsers(Model model) {
        List<User> listUsers = service.listAll();
        model.addAttribute("listUsers", listUsers);

        return "users";
    }
    @PostMapping("/users/save")
    public String saveUser(@ModelAttribute(name = "user") User user, BindingResult bindingResult ,
                           RedirectAttributes ra,
                           @RequestParam("emoji") MultipartFile multipartFile) throws IOException {
        try{
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        if( bindingResult.hasErrors())
        {
            user.setEmoji(fileName);
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
            User savedUser = service.save(user);
            String uploadDir = "user-emoji/" + savedUser.getId();
            Path uploadPath = Paths.get(uploadDir);
            if(!Files.exists(uploadPath)){
                Files.createDirectories(uploadPath);
            }
            try(InputStream inputStream = multipartFile.getInputStream()){
//        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
                Path filePath = uploadPath.resolve(fileName);
                Files.copy(inputStream,filePath, StandardCopyOption.REPLACE_EXISTING);
            }catch(IOException e){
                throw new IOException("Could not upload emoji") ;
            }
            ra.addFlashAttribute("message","The user has been updated successfully!");
        }
        }catch(IOException e){
            throw new IOException("ERROR.") ;
        }
        return "redirect:/users";
    }
    @GetMapping("/users/edit/{id}")
    public String showEditForm(@PathVariable("id") String id, Model model, RedirectAttributes ra) {
        try {
            User user = service.get(id);
            model.addAttribute("user", user);
            model.addAttribute("pageTitle","Edit User Account");
            return "edit_form";
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message", "The user has been updated successfully!");
            return "redirect:/users";
        }
    }
    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id") String id, RedirectAttributes ra) {
        try {
            service.delete(id);
            ra.addFlashAttribute("message", "The user has been updated successfully!");
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/users";
    }
}
