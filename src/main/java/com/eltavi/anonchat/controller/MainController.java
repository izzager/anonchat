package com.eltavi.anonchat.controller;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

@Controller
public class MainController {

    @GetMapping("/")
    public String index(HttpServletRequest request, Model model) {
        String roomCode = (String) request.getSession().getAttribute("roomCode");
        if (roomCode == null || roomCode.isEmpty()) {
            return "redirect:/login";
        }
        model.addAttribute("roomCode", roomCode);
        return "chat";
    }

    @GetMapping( "/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String doLogin(HttpServletRequest request,
                          @RequestParam(defaultValue = "") String roomCode) {
        roomCode = roomCode.trim();
        if (roomCode.isEmpty()) {
            return "login";
        }
        request.getSession().setAttribute("roomCode", roomCode);
        return "redirect:/";
    }

    @RequestMapping( "/logout")
    public String logout(HttpServletRequest request) {
        request.getSession(true).invalidate();
        return "redirect:/login";
    }

    @GetMapping("/newRoom")
    public String createNewRoom(Model model) {
        model.addAttribute("newRoomCode", RandomStringUtils.randomAlphanumeric(5).toUpperCase(Locale.ROOT));
        return "newRoom";
    }

}
