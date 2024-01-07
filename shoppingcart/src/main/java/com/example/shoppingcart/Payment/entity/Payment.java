package com.example.shoppingcart.Payment.entity;

import java.math.BigDecimal;
import java.util.UUID;
import com.example.shoppingcart.Payment.enums.PaymentStatus;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Payment {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "payment_id")
  private Long paymentId;

  @Column(name = "payment_intent_id", nullable = false, unique = true)
  private String paymentIntentId;

  @Column(name = "payment_cart_id", nullable = false, unique = true)
  private UUID shoppingCartId;

  @Column(name = "item_total_price", nullable = false)
  private BigDecimal itemsTotalPrice;

  @Column(nullable = true)
  private PaymentStatus status;

  @Column(nullable = true)
  private String description;

  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof Payment payment)) {
      return false;
    }
    EqualsBuilder eb = new EqualsBuilder();
    eb.append(paymentIntentId, payment.paymentId);
    return eb.isEquals();
  }

  public int hashCode() {
    HashCodeBuilder hcb = new HashCodeBuilder();
    hcb.append(paymentIntentId);
    return hcb.toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this).append("paymentIntentId", paymentIntentId)
        .toString();
  }
}
