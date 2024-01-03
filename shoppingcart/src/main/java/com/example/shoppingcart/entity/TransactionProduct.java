package com.example.shoppingcart.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

@Entity
@Getter
@Setter
@Table(name = "transaction_product")
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TransactionProduct implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "tpid")
  Long tpid;

  @ManyToOne
  @JoinColumn(name = "tid")
  @JsonProperty(value = "tid")
  Transaction transaction;

  private Long pid;

  private String name;

  private String description;

  @JsonProperty(value = "image_url")
  private String imageUrl;

  private BigDecimal price;

  private Integer stock;

  private BigDecimal quantity;


}
