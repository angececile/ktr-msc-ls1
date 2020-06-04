package com.cecile.test.services;

import com.cecile.test.domaine.Profile;
import com.cecile.test.repositories.ProfileRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UtilisateurDetailService implements UserDetailsService {

    private ProfileRepository profileRepository;

    public UtilisateurDetailService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Profile profile = profileRepository.findByName(s);
        if(profile !=null) {
            return new UtilisateurDetail(profile);
        }else {
            return null;
        }
    }
}
