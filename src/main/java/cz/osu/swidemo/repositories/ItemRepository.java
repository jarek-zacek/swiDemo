package cz.osu.swidemo.repositories;

import cz.osu.swidemo.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, String> {
    Optional<Item> findByItemName(String itemName);
    boolean existsByItemName(String itemName);
}

