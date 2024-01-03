// package com.example.shoppingcart.Security.useless.data;

// import java.io.Serializable;
// import jakarta.persistence.Column;
// import jakarta.persistence.Embeddable;
// import jakarta.persistence.EmbeddedId;
// import jakarta.persistence.Entity;
// import jakarta.persistence.EnumType;
// import jakarta.persistence.Enumerated;
// import jakarta.persistence.Table;
// import lombok.AllArgsConstructor;
// import lombok.NoArgsConstructor;

// @Entity
// @Table(name = "USER_ROLE")
// public class UserRole {
//   @Embeddable
//   @AllArgsConstructor
//   @NoArgsConstructor
//   public static class Id implements Serializable {
//     // private static final long serialVersionUID = 1322120000551624359L;

//     @Column(name = "APP_USER_ID")
//     protected Long userId;

//     @Enumerated(EnumType.STRING)
//     @Column(name = "ROLE")
//     protected Role role;

//   }

//   @EmbeddedId
//   Id id = new Id();

//   @Enumerated(EnumType.STRING)
//   @Column(name = "ROLE", insertable = false, updatable = false)
//   protected Role role;

//   public Role getRole() {
//     return role;
//   }
// }
