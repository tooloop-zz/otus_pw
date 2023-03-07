package ru.otus.asamofalov.pw.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class CreditRequestsController {

    @GetMapping("/")
    public String listPage() {
        return "list";
    }

    @GetMapping("/read")
    public String read(@RequestParam("id") Long id, Model model) {
        model.addAttribute("id", id);
        return "read";
    }
}
