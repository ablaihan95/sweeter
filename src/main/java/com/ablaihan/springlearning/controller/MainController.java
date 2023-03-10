package com.ablaihan.springlearning.controller;

import com.ablaihan.springlearning.domain.Message;
import com.ablaihan.springlearning.domain.User;
import com.ablaihan.springlearning.repos.MessagesRepository;
import jakarta.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/")
public class MainController {
    @Autowired
    private MessagesRepository messageRepo;
    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/")
    public String greeting(
            @RequestParam(name = "name", required = false, defaultValue = "World") String name,
            Map<String, Object> model
    ) {
        model.put("name", name);
        return "greeting";
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false) String filter, Model model) {
        Iterable<Message> messages;
        if (filter != null && !filter.isEmpty()) {
            messages = messageRepo.findByTag(filter);
        } else {
            messages = messageRepo.findAll();
        }

        model.addAttribute("messages", messages);
        model.addAttribute("filter", filter);
        model.addAttribute("error", "");
        return "main";
    }

    @PostMapping("/main")
    public String add(@AuthenticationPrincipal User user,
                      @Valid Message message,
                      BindingResult bindingResult,
                      Model model,
                      @RequestParam("file") MultipartFile file) throws IOException {
        message.setAuthor(user);
        if (bindingResult.hasErrors()) {
            Map<String, String> collect = bindingResult.getFieldErrors().stream()
                    .collect(Collectors.toMap(fieldError -> fieldError.getField() + "Error",
                            FieldError::getDefaultMessage));
            model.addAllAttributes(collect);
            model.addAttribute("message", message);
        } else {
            if (file != null && !file.getOriginalFilename().isEmpty()) {
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }

                String uuid = UUID.randomUUID().toString();
                String resultFileName = uuid + "." + file.getOriginalFilename();
                file.transferTo(new File(uploadDir + "/" + resultFileName));
                message.setFilename(resultFileName);
            }
            model.addAttribute("error", "");

            messageRepo.save(message);
        }
        Iterable<Message> messages = messageRepo.findAll();

        model.addAttribute("messages", messages);
        return "main";
    }

}