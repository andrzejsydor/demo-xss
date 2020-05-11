package com.example.xss;

import com.example.xss.model.Post;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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

    @GetMapping({"/"})
    String dom() {
        return "dom";
    }

    @GetMapping("/search")
    String search(@RequestParam(required = false) String searchField, Model model) {
        model.addAttribute("searchField", searchField);
        model.addAttribute("posts", postRepository.findAll());
        return "index";
    }

    @GetMapping("/form")
    String form(Model model) {
        model.addAttribute("post", new Post());
        return "form";
    }

    @PostMapping(value = "/add", consumes = "application/x-www-form-urlencoded;charset=UTF-8")
    String add(@ModelAttribute Post post) {
        postRepository.save(post);
        return "redirect:/index";
    }

    @GetMapping("/delete")
    String delete(@RequestParam(required = false) Long id) {
        postRepository.deleteById(id);
        return "redirect:/index";
    }
}
