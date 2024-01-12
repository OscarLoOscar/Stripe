package com.example.shoppingcart.stripePayment.config;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UsePublicKeyCondition implements Condition {

  @Override
  public boolean matches(ConditionContext context,
      AnnotatedTypeMetadata metadata) {
        boolean useSecretKey = context.getEnvironment().getProperty("use.secret.key", Boolean.class, false);
        log.info("Use Secret Key: {}", useSecretKey);
        return useSecretKey;
        }

}
