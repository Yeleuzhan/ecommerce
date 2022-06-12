package kz.ecommerce.repository;

import kz.ecommerce.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findOrderByEmail(String email);

    List<Order> findAllByOrderByIdAsc();

}
