package Yipykso.repository;

import Yipykso.entity.Revenue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RevenueRepository extends JpaRepository<Revenue, Integer>, JpaSpecificationExecutor<Revenue> {
    // Tambahkan disini jika ingin mencari ke database. Interface ini bersifat reusable
    Optional<Revenue> findById(Integer id);
}
