package com.example.shoppingcart.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.shoppingcart.payment.entity.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    @Modifying
    @Query(value = "UPDATE payment SET status = :payment_status, description = :payment_description WHERE payment_intent_id = :payment_intent_id",
            nativeQuery = true)
    void updateStatusAndDescriptionInPayment(@Param("payment_intent_id") String paymentIntentId,
                                             @Param("payment_status") String paymentStatus,
                                             @Param("payment_description") String paymentDescription);

}
