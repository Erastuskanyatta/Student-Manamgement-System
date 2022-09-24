package net.javaguides.sms.service;

import net.javaguides.sms.entity.AppUser;
import net.javaguides.sms.entity.Student;
import net.javaguides.sms.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class appUserService {

   @Autowired
   public AppUserRepository appUserRepository;
    public AppUser registerUser(AppUser appUser) {
        return  appUserRepository.save(appUser);
    }
}
