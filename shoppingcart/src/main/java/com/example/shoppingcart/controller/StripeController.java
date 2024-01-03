package com.example.shoppingcart.controller;

import java.util.HashMap;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.shoppingcart.controller.impl.StripeControllerImpl;
import com.example.shoppingcart.entity.UserEntity;
import com.example.shoppingcart.infra.JwtUntil;
import com.example.shoppingcart.model.FireBaseUserData;
import com.example.shoppingcart.services.UserService;
import com.example.shoppingcart.services.impl.StripeServiceImpl;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;

/**
 * https://stripe.com/docs/testing
 */
public class StripeController implements StripeControllerImpl {

  private final StripeServiceImpl stripeServiceImpl;
  private final UserService userService;

  @Autowired
  public StripeController(StripeServiceImpl stripeServiceImpl,
      UserService userService) {
    this.stripeServiceImpl = stripeServiceImpl;
    this.userService = userService;
  }

  // @Override
  // public String createPaymentSession(JwtAuthenticationToken jwt) {
  //   FireBaseUserData user = JwtUntil.getFireBaseUser(jwt);
  //   Optional<UserEntity> userEntity =
  //       Optional.ofNullable(userService.getEntityByFireBaseUserData(user));
  //   Long userId = userEntity.get().getUserId();

  //   if (userEntity.isEmpty())
  //     return "";

  //   return "";
  // }

  @Override
    public AjaxResult creditCardPay(@RequestParam(value = "tokenId", required = true) String tokenId){
 
        try {
 
            Stripe.apiKey = "你的秘钥";
 
            logger.info("支付请求token{}",tokenId);
            //======StripeStep1: 根据客户端source获取到customer对象
            Map<String, Object> customerParams = new HashMap<String, Object>();
            customerParams.put("email", "676291554@qq.com");//选填：email
            //customerParams.put("name", "测试");//选填： name 如，顾客的姓名
            //customerParams.put("description", "我是商品描述");//选填： description 如，商品的名称
            customerParams.put("source", tokenId);
 
            
            Customer customer = null;
            try {
                // Customer 对象api https://stripe.com/docs/api/customers/object
                customer = Customer.create(customerParams);
            } catch (StripeException e) {
                e.printStackTrace();
                return AjaxResult.error("支付失败");
            }
 
 
       
            //======StripeStep2: 根据customer对象进行支付
            Map<String, Object> chargeParams = new HashMap<String, Object>();
            chargeParams.put("amount", 100);//必须是整数
            chargeParams.put("currency", "USD");//USD 美元、CNY 人民币
            chargeParams.put("customer", customer.getId());//类似: cus_AFGbOSiITuJVDs
            
 
            // Charge 对象api https://stripe.com/docs/api/charges/object
            Charge charge = null;
            try {
                charge = Charge.create(chargeParams);
            } catch (StripeException e) {
                e.printStackTrace();
                return AjaxResult.error("支付失败");
               
            }
          
 
            if ("succeeded".equalsIgnoreCase(charge.getStatus())) {
 
           
                //成功后做的事情
 
            } 
            } else {
                //支付失败
            }
 
            return AjaxResult.success();
        }catch (Exception e){
            e.printStackTrace();
            return AjaxResult.error("支付失败");
        }
 
    }

}
