package com.devvault.service;

import com.devvault.dao.NotificationDAO;
import com.devvault.model.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationDAO notificationDAO;

    public void notify(int userId, String message) {
        Notification n = new Notification();
        n.setUserId(userId);
        n.setMessage(message);
        notificationDAO.save(n);
    }

    public List<Notification> getNotificationsForUser(int userId) {
        return notificationDAO.findByUserId(userId);
    }

    public int getUnreadCount(int userId) { return notificationDAO.countUnread(userId); }
    public void markAsRead(int id) { notificationDAO.markAsRead(id); }
    public void markAllAsRead(int userId) { notificationDAO.markAllAsRead(userId); }
}
