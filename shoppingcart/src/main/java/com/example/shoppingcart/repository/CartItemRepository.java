package com.example.shoppingcart.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.example.shoppingcart.entity.CartItem;
import com.example.shoppingcart.entity.Product;
import com.example.shoppingcart.entity.UserEntity;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
        @Query(value = "SELECT * FROM cart_item c WHERE c.uid=:uid ",
                        nativeQuery = true)
        Optional<List<CartItem>> findAllByUserUid(@Param("uid") Long uid);

        Optional<List<CartItem>> findByUserAndProduct(UserEntity user,
                        Product product);

        // @Query(value = "SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END FROM cart_item c "
        // + "WHERE c.uid = :uid AND c.pid = :pid",nativeQuery = true)
        // boolean existsByuidAndpid(@Param("uid") Long uid,
        // @Param("pid") Long pid);

        // @Query(value = "SELECT * FROM cart_item c WHERE c.uid = :uid AND c.pid = :pid",
        // nativeQuery = true)
        @Query(value = "SELECT * FROM cart_item WHERE uid = ?1 AND pid = ?2",
                        nativeQuery = true)
        Optional<CartItem> findByUser_UidAndProduct_Pid(@Param("uid") Long uid,
                        @Param("pid") Long pid);
}


// org.hibernate.query.sqm.ParsingException:
// mismatched input 'THEN' expecting {<EOF>, '+', '-', '*', '/', '%', '||', AND, BY, DAY, EPOCH, GROUP, HOUR, MINUTE, MONTH, NANOSECOND, OR, ORDER, QUARTER, SECOND, WEEK, YEAR}
