package ru.itis.services;

import ru.itis.models.Subscription;
import ru.itis.models.User;

import java.util.List;
import java.util.Optional;

public interface SubscriptionService {
    List<Subscription> getFollowingsByUser(User user);
    List<Subscription> getFollowersByUser(User user);
    void addSubscription(Subscription subscription);
    void deleteSubscription(Subscription subscription);
    Optional<Subscription> getSubscriptionById(Integer id);
}
