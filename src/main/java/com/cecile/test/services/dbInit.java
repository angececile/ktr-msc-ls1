package com.cecile.test.services;

import com.cecile.test.domaine.Profile;
import com.cecile.test.repositories.ProfileRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@Service
public class dbInit implements CommandLineRunner {
    private ProfileRepository profileRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public dbInit(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if(profileRepository.findAll().size() == 0) {
            Profile profile =  new Profile();
            profile.setCompanyName("ISJ");
            profile.setName("Cecile Lotchouang");
            profile.setEmail("aclotchouang@gmail.com");
            profile.setTelephone("00237696534500");
            profile.setPassword(bCryptPasswordEncoder.encode("1234"));

            profileRepository.save(profile);
        }

    }
}
