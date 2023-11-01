package com.example.StockWise.Services;

import com.example.StockWise.Model.User;
import com.example.StockWise.Model.dto.Credential;
import com.example.StockWise.Repository.UserRepo;
import com.example.StockWise.Services.Utility.PasswordEncrypter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;

    public String registerUser(User user) throws NoSuchAlgorithmException {

        if (userRepo.existsByuserEmail(user.getUserEmail())){
            return "Already Register";
        }
        String hashPass = PasswordEncrypter.hashPasswordWithStaticSecret(user.getUserPassword());
        user.setUserPassword(hashPass);
        userRepo.save(user);
        return "Register Successfully";
    }

    public String loginUser(Credential credential) throws NoSuchAlgorithmException {
        if (!userRepo.existsByuserEmail(credential.getEmail())){
            return "Please Create a Account";
        }
        String hashPass = PasswordEncrypter.hashPasswordWithStaticSecret(credential.getPassword());

        User user = userRepo.findByUserEmail(credential.getEmail());


        if (hashPass.equals(user.getUserPassword())) {
            user.setStatus("logged in");
            userRepo.save(user);
            return "login successfully";
        } else {
            return "Credential MisMatch";
        }
    }

    public String signoutUser(String email) {
        User user = userRepo.findByUserEmail(email);

        if (user.getStatus().equals("logged in")){
            user.setStatus("log out");
            userRepo.save(user);
            return "logout successfully";
        }
        else {
            return "plz login first then click on logout ";
        }

    }

    public String addFund(String email, double amount) {
       User user= userRepo.findByUserEmail(email);
       user.setFund(amount);
       userRepo.save(user);
       return "Success0";
    }
}
