package ru.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.models.Subscription;

import java.util.List;

public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {
    List<Subscription> findAllBySubscriptor_Id(Integer id);
    List<Subscription> findAllBySubscriber_Id(Integer id);
}
