package com.java.looapp.Services;

import com.java.looapp.Users.MyUserDetails;
import com.java.looapp.Users.User;
import com.java.looapp.Users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MyUserDetailServices implements UserDetailsService{

    @Autowired
    private UserRepository userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new MyUserDetails(user);
    }

    public List<User>listAll(){
        return (List<User>) userRepo.findAll();
    }

    public User save(User user){
        try {
            userRepo.save(user);
            return user;
        }catch(DataIntegrityViolationException e){
            throw new UsernameNotFoundException("User not found");
        }
    }

    public User get(String id) throws UserNotFoundException{
        Optional<User> result = userRepo.findById(id);
            if(result.isPresent()){
                return result.get();
            }
            throw new UserNotFoundException("User not found."+id);
    }
    public void delete(String id) throws UserNotFoundException{
        Optional<User> user = userRepo.findById(id);
        if (user == null) {
            throw new UserNotFoundException("User not found");
        }
        userRepo.deleteById(id);
    }

}
