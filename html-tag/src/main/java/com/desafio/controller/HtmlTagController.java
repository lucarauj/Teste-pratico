package com.desafio.controller;

import com.desafio.model.HtmlTag;
import com.desafio.service.HtmlTagService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/html-tag")
public class HtmlTagController {

    private final HtmlTagService service;

    public HtmlTagController(HtmlTagService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<HtmlTag> tags = service.getAllTags();
        model.addAttribute("tags", tags);

        return "index";
    }

    @PostMapping("/processar")
    public String processar(@RequestParam("url") String url) {
        service.processarURL(url);
        return "redirect:/";
    }
}
