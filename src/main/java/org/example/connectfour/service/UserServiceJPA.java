package org.example.connectfour.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.example.connectfour.entity.User;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


@Transactional
public class UserServiceJPA implements UserService {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void register(String userName, String password) {
        if (isExist(userName)) {
            throw new RuntimeException("User with email " + userName + " already exists");
        }
        User user = new User();
        user.setUsername(userName);
        user.setPassword(hashPassword(password));
        entityManager.persist(user);
    }

    @Override
    public User login(String username, String password) {
        try {
            User user = entityManager.createNamedQuery("User.findUserByName", User.class).setParameter("username", username).getSingleResult();

            if(user.getPassword().equals(hashPassword(password))){
                return user;
            } else {
                throw new UserException.InvalidPasswordException("Incorrect password");
            }

        } catch(NoResultException e) {
            throw new UserException.UsernameNotFoundException("User with username " + username + " not found");
        }
    }

    @Override
    public boolean isExist(String username) {
        Long count = entityManager
                .createNamedQuery("User.isExists", Long.class)
                .setParameter("username", username)
                .getSingleResult();
        return count > 0;
    }

    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encoded = digest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : encoded) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error caching password", e);
        }   
    }
}
