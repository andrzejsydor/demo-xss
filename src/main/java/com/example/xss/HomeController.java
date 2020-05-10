package com.example.xss;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;

@Log4j2
@AllArgsConstructor
@Controller
class HomeController {
    private PostRepository postRepository;

    @GetMapping({"/", "/index"})
    String home(Model model) {
        model.addAttribute("posts", postRepository.findAll());
        return "index";
    }

    @GetMapping("/search")
    String search(@RequestParam(required = false) String searchField, Model model) {
        model.addAttribute("searchField", searchField);
        model.addAttribute("posts", postRepository.findAll());
        return "index";
    }

    @GetMapping("/delete")
    String delete(@RequestParam(required = false) Long id) {
        postRepository.deleteById(id);
        return "redirect:/index";
    }
}