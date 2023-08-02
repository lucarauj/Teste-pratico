package com.desafio.controller;

import com.desafio.model.HtmlTag;
import com.desafio.service.HtmlTagService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/html-tag")
public class HtmlTagController {

    private final HtmlTagService service;

    public HtmlTagController(HtmlTagService service) {
        this.service = service;
    }

    @GetMapping("/formulario")
    public String carregaFormulario(Model model) {
        return "formulario";
    }

    @PostMapping
    public String processar(@RequestParam("url") String url, Model model) {
        service.processarURL(url);
        List<HtmlTag> tags = service.filtrarUrl(url);
        model.addAttribute("tags", tags);
        model.addAttribute("url", url);
        return "listagem";
    }
}
