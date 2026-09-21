package com.devvault.service;

import com.devvault.dao.UserDAO;
import com.devvault.model.User;
import com.devvault.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    public int registerUser(User user) {
        if (user.getName() == null || user.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name is required");
        }
        if (user.getPassword() == null || user.getPassword().length() < 6) {
            throw new IllegalArgumentException("Password must be at least 6 characters");
        }
        if (userDAO.findByEmail(user.getEmail()) != null) {
            throw new IllegalArgumentException("An account with this email already exists");
        }
        user.setPassword(PasswordUtil.hash(user.getPassword()));
        return userDAO.save(user);
    }

    public User login(String email, String password) {
        User user = userDAO.findByEmail(email);
        if (user == null) return null;
        if (!PasswordUtil.matches(password, user.getPassword())) return null;
        return user;
    }

    public User getUserById(int id) { return userDAO.findById(id); }
    public List<User> getAllUsers() { return userDAO.findAll(); }
    public int countUsers() { return userDAO.countAll(); }
    public void changeRole(int userId, int roleId) { userDAO.updateRole(userId, roleId); }
    public void deleteUser(int id) { userDAO.delete(id); }
}
