package com.example.shoppingcart.controller.impl;

import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = "http://localhost:5173", maxAge = 3600)
public interface StripeControllerImpl {
  // @PostMapping("checkout/sessions")
  // @ResponseStatus(HttpStatus.CREATED)
  // public String createPaymentSession(JwtAuthenticationToken jwt);
 /**
     * 获取用户卡片列表
     *
     * @return
     */
    @PostMapping("/getCardList")
    public Response getCardList(@RequestBody @Valid StripePayRequestVO stripePayRequestVO, BindingResult result) {
      
        return stripePayService.getCardList(stripePayRequestVO);
    }

 
    /**
     * 添加用户卡片
     * @return
     */
    @RequestMapping(value = "/addCard", method = RequestMethod.POST)
    public Response addCard(@RequestBody @Valid StripePayRequestVO stripePayRequestVO, BindingResult result) {
        logger.debug("购买套餐请求参数 {} = ", JsonUtil.INSTANCE.toJson(stripePayRequestVO));

        return stripePayService.addCard(stripePayRequestVO);
    }


    /**
     * 发起支付
     * @return
     */
    @RequestMapping(value = "/charge", method = RequestMethod.POST)
    public Response aliPay(@RequestBody @Valid StripePayRequestVO stripePayRequestVO, BindingResult result) {
        return stripePayService.charge(stripePayRequestVO);
    }

}
