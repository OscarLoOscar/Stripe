package com.example.shoppingcart.entity;

import java.io.Serializable;
import java.util.List;
import com.example.shoppingcart.model.FireBaseUserData;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "user")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserEntity implements Serializable {
  @Id
  @Column(name = "uid")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long userId;

  // String userName;

  // String phone;

  @Column(name = "fireBase_uid", nullable = false, unique = true) // unique = true -> column 入面value唔重覆
  String fireBaseUid;

  @Column(name = "email", nullable = false, unique = true) // unique = true -> column 入面value唔重覆
  String email;

  @OneToMany(mappedBy = "user", cascade = CascadeType.MERGE,
      fetch = FetchType.LAZY)
  private List<Transaction> transactions;

  public UserEntity(FireBaseUserData fireBaseUserData) {
    this.fireBaseUid = fireBaseUserData.getFirebaseUid();
    this.email = fireBaseUserData.getEmail();
  }

}
  // (cascade = CascadeType.ALL , fetch = FetchType.EAGER) check meaning =>
  // @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  // @JoinColumn(name = "shippingAddressId")
  // ShippingAddress shippingAddress;

  // cascade = CascadeType.ALL: 這個屬性指定了關聯操作的級聯行為。CascadeType.ALL 表示當對主實體（在這種情況下是主要的一方，例如Person）進行持久化操作時，
  // 這個操作也將級聯到相關的實體（在這種情況下是被擁有的一方，例如Address）。
  // 這意味著當您保存Person時，關聯的Address也將被保存。
  // fetch = FetchType.EAGER: 這個屬性指定了如何從數據庫中檢索關聯的實體。
  // FetchType.EAGER 表示在查詢主實體（例如，Person）時，也立即檢索相關的Address。這是一種立即加載策略，意味著所有相關的數據都在同一次數據庫查詢中檢索，這可能會導致性能問題，特別是當關聯實體的數據量很大時。
  // 請注意，EAGER 立即加載可能會導致性能問題，因為它會將所有相關數據一次性檢索出來。
  // 在某些情況下，您可能希望使用 FetchType.LAZY 來實現延遲加載，只有在需要訪問相關數據時才進行檢索。這取決於您的業務需求和性能考慮。
  // @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  // @JoinColumn(name = "billingAddressId")
  // BillingAddress billingAddress;

