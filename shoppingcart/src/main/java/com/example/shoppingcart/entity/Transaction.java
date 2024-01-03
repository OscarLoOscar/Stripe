package com.example.shoppingcart.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.example.shoppingcart.infra.enums.TranStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Builder
@Entity
@Getter
@Setter
@Table(name = "transaction")
@NoArgsConstructor
@AllArgsConstructor
public class Transaction implements Serializable {
  @Id
  @Column(name = "tid")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long tid;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "uid", nullable = false)
  private UserEntity user;

  @Column(nullable = false)
  private LocalDateTime datetime;

  @Column(name = "status", nullable = false)
  @Enumerated(EnumType.STRING)
  private TranStatus status;

  @Column(name = "total_price", nullable = false)
  private BigDecimal totalPrice;
}
