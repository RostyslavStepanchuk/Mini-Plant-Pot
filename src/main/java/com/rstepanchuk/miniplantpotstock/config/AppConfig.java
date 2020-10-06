package com.rstepanchuk.miniplantpotstock.config;

import com.rstepanchuk.miniplantpotstock.mapper.CustomerOrderMapper;
import com.rstepanchuk.miniplantpotstock.mapper.SkuMapper;
import com.rstepanchuk.miniplantpotstock.mapper.ProductionOrderMapper;
import com.rstepanchuk.miniplantpotstock.mapper.ProductionSupplyMapper;
import com.rstepanchuk.miniplantpotstock.repository.CustomerOrderRepository;
import com.rstepanchuk.miniplantpotstock.repository.PotRepository;
import com.rstepanchuk.miniplantpotstock.repository.ProductionOrderRepository;
import com.rstepanchuk.miniplantpotstock.repository.ProductionSupplyRepository;
import com.rstepanchuk.miniplantpotstock.repository.SkuRepository;
import com.rstepanchuk.miniplantpotstock.service.CustomerOrderService;
import com.rstepanchuk.miniplantpotstock.service.EtsyService;
import com.rstepanchuk.miniplantpotstock.service.PotService;
import com.rstepanchuk.miniplantpotstock.service.ProductionOrderService;
import com.rstepanchuk.miniplantpotstock.service.ProductionSupplyService;
import com.rstepanchuk.miniplantpotstock.service.SkuService;
import com.rstepanchuk.miniplantpotstock.service.integration.etsy.EtsyAuthMgr;
import com.rstepanchuk.miniplantpotstock.service.integration.etsy.EtsyClient;
import com.rstepanchuk.miniplantpotstock.service.integration.etsy.EtsyCredentials;
import com.rstepanchuk.miniplantpotstock.util.FormDataParser;
import org.modelmapper.ModelMapper;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@EnableConfigurationProperties(value = EtsyCredentials.class)
public class AppConfig {

  //     <----- Mapper ----->
  @Bean
  public CustomerOrderMapper customerOrderMapper(ModelMapper modelMapper,
                                                 SkuMapper skuMapper,
                                                 CustomerOrderService customerOrderService) {
    return new CustomerOrderMapper(modelMapper, skuMapper, customerOrderService);
  }

  @Bean
  public SkuMapper potMapper(ModelMapper modelMapper, SkuService skuService) {
    return new SkuMapper(modelMapper, skuService);
  }

  @Bean
  public ProductionOrderMapper productionOrderMapper(ModelMapper modelMapper,
                                                     ProductionOrderService productionOrderService) {
    return new ProductionOrderMapper(modelMapper, productionOrderService);
  }

  @Bean
  public ProductionSupplyMapper productionSupplyMapper(ModelMapper modelMapper,
                                                       ProductionSupplyService productionSupplyService) {
    return new ProductionSupplyMapper(modelMapper, productionSupplyService);
  }

  //      <----- Service ----->
  @Bean
  public CustomerOrderService customerOrderService(CustomerOrderRepository customerOrderRepository) {
    return new CustomerOrderService(customerOrderRepository);
  }

  @Bean
  public PotService potService(PotRepository potRepository) {
    return new PotService(potRepository);
  }

  @Bean
  public SkuService skuService(SkuRepository skuRepository, PotService potService) {
    return new SkuService(skuRepository, potService);
  }

  @Bean
  public ProductionOrderService productionOrderService(ProductionOrderRepository productionOrderRepository) {
    return new ProductionOrderService(productionOrderRepository);
  }

  @Bean
  public ProductionSupplyService productionSupplyService(ProductionSupplyRepository productionSupplyRepository) {
    return new ProductionSupplyService(productionSupplyRepository);
  }

  @Bean
  public EtsyService etsyService(EtsyClient etsyClient) {
    return new EtsyService(etsyClient);
  }

  //      <----- Integration Etsy ----->

  @Bean
  public EtsyClient etsyClient(EtsyAuthMgr etsyAuthMgr, WebClient webClient, FormDataParser formDataParser) {
    return new EtsyClient(etsyAuthMgr, webClient, formDataParser);
  }

  @Bean
  public EtsyAuthMgr etsyAuthMgr(EtsyCredentials etsyCredentials) {
    return new EtsyAuthMgr(etsyCredentials);
  }

  @Bean
  EtsyCredentials etsyCredentials() {
    return new EtsyCredentials();
  }

  //      <----- Utils ----->

  @Bean
  public FormDataParser formDataParser() {
    return new FormDataParser();
  }

  @Bean
  public WebClient webClient() {
    return WebClient.create();
  }


}
