package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.models.Subscription;
import ru.itis.models.User;
import ru.itis.repositories.SubscriptionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SubscriptionServiceImpl implements SubscriptionService{

    @Autowired
    SubscriptionRepository subscriptionRepository;

    public List<Subscription> getFollowingsByUser(User user) {
        return subscriptionRepository.findAllBySubscriber_Id(user.getId());
    }

    public List<Subscription> getFollowersByUser(User user) {
        return subscriptionRepository.findAllBySubscriptor_Id(user.getId());
    }

    public void addSubscription(Subscription subscription) {
        subscriptionRepository.save(subscription);
    }
    public void deleteSubscription(Subscription subscription) {
        subscriptionRepository.delete(subscription);
    }

    public Optional<Subscription> getSubscriptionById(Integer id) {
        return Optional.ofNullable(subscriptionRepository.findOne(id));
    }

}
