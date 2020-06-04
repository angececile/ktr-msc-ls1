package com.cecile.test.controllers;

import com.cecile.test.domaine.BusinessCard;
import com.cecile.test.domaine.Profile;
import com.cecile.test.repositories.BusinessCardRepository;
import com.cecile.test.repositories.ProfileRepository;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class AppController {

    private final ProfileRepository profileRepository;
    private final BusinessCardRepository businessCardRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();


    public AppController(ProfileRepository profileRepository, BusinessCardRepository businessCardRepository) {
        this.profileRepository = profileRepository;
        this.businessCardRepository = businessCardRepository;
    }

    //afin de transformer les chaines de caractères vides en null
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/")
    public String showLibrary(Model model, Authentication authentication){
        Profile profile = profileRepository.findByName(authentication.getName());
        List<BusinessCard> businessCards = businessCardRepository.findAllByProfile(profile);
        model.addAttribute("businessCards",businessCards);
        return "library";
    }

    @GetMapping("/register")
    public String register(Model model){
        Profile profile = new Profile();
        model.addAttribute("profile",profile);
        return "register";
    }

    @PostMapping("/save")
    public String save(@Valid Profile profile, BindingResult result){
        if(result.hasErrors()){
            return "register";
        }

        profile.setPassword(bCryptPasswordEncoder.encode(profile.getPassword()));
        profileRepository.save(profile);
        return "redirect:/login";
    }

    @GetMapping("/add-business-card")
    public String add(Model model){
        BusinessCard businessCard = new BusinessCard();
        model.addAttribute("businessCard",businessCard);
        return "add-business-card";
    }

    @PostMapping("/saveBusinessCard")
    public String saveB(@Valid BusinessCard businessCard,
                        BindingResult result,Authentication authentication){
        if(result.hasErrors()){
            return "add-business-card";
        }
        Profile profile = profileRepository.findByName(authentication.getName());
        businessCard.setProfile(profile);
        businessCardRepository.save(businessCard);

        return "redirect:/";

    }
}
