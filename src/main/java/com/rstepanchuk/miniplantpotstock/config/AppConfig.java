package com.rstepanchuk.miniplantpotstock.config;

import com.rstepanchuk.miniplantpotstock.mapper.CustomerOrderMapper;
import com.rstepanchuk.miniplantpotstock.repository.CustomerOrderRepository;
import com.rstepanchuk.miniplantpotstock.repository.PotRepository;
import com.rstepanchuk.miniplantpotstock.repository.PotSetRepository;
import com.rstepanchuk.miniplantpotstock.repository.ProductionOrderRepository;
import com.rstepanchuk.miniplantpotstock.repository.ProductionSupplyRepository;
import com.rstepanchuk.miniplantpotstock.service.CustomerOrderService;
import com.rstepanchuk.miniplantpotstock.service.PotService;
import com.rstepanchuk.miniplantpotstock.service.PotSetService;
import com.rstepanchuk.miniplantpotstock.service.ProductionOrderService;
import com.rstepanchuk.miniplantpotstock.service.ProductionSupplyService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import static org.modelmapper.config.Configuration.AccessLevel.PRIVATE;

@Configuration
@EnableJpaRepositories
@EnableTransactionManagement
public class AppConfig {

  // Database configuration
  @Bean
  public DataSource dataSource() {

    EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
    return builder.setType(EmbeddedDatabaseType.H2).build();
  }

  @Bean
  public EntityManagerFactory entityManagerFactory() {

    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
    vendorAdapter.setGenerateDdl(true);

    LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
    factory.setJpaVendorAdapter(vendorAdapter);
    factory.setPackagesToScan("com.rstepanchuk.miniplantpotstock.entity");
    factory.setDataSource(dataSource());
    factory.afterPropertiesSet();

    return factory.getObject();
  }

  @Bean
  public PlatformTransactionManager transactionManager() {

    JpaTransactionManager txManager = new JpaTransactionManager();
    txManager.setEntityManagerFactory(entityManagerFactory());
    return txManager;
  }

  @Bean
  public JpaRepositoryFactory jpaRepositoryFactory() {
    EntityManager entityManager = entityManagerFactory().createEntityManager();
    return new JpaRepositoryFactory(entityManager);
  }

  // Model Mapper
  @Bean
  public ModelMapper modelMapper() {
    ModelMapper mapper = new ModelMapper();
    mapper.getConfiguration()
        .setMatchingStrategy(MatchingStrategies.STRICT)
        .setFieldMatchingEnabled(true)
        .setSkipNullEnabled(true)
        .setFieldAccessLevel(PRIVATE);
    return mapper;
  }

  // Layers

  @Bean
  public CustomerOrderMapper customerOrderMapper() {
    CustomerOrderService customerOrderService = customerOrderService();
    ModelMapper modelMapper = modelMapper();
    return new CustomerOrderMapper(modelMapper, customerOrderService);
  }

  @Bean
  public CustomerOrderService customerOrderService(){
    JpaRepositoryFactory jpaRepositoryFactory = jpaRepositoryFactory();
    return new CustomerOrderService(jpaRepositoryFactory.getRepository(CustomerOrderRepository.class));
  }

  @Bean
  public PotService potService(){
    JpaRepositoryFactory jpaRepositoryFactory = jpaRepositoryFactory();
    return new PotService(jpaRepositoryFactory.getRepository(PotRepository.class));
  }

  @Bean
  public PotSetService potSetService(){
    JpaRepositoryFactory jpaRepositoryFactory = jpaRepositoryFactory();
    return new PotSetService(jpaRepositoryFactory.getRepository(PotSetRepository.class));
  }

  @Bean
  public ProductionOrderService productionOrderService(){
    JpaRepositoryFactory jpaRepositoryFactory = jpaRepositoryFactory();
    return new ProductionOrderService(jpaRepositoryFactory.getRepository(ProductionOrderRepository.class));
  }

  @Bean
  public ProductionSupplyService productionSupplyService(){
    JpaRepositoryFactory jpaRepositoryFactory = jpaRepositoryFactory();
    return new ProductionSupplyService(jpaRepositoryFactory.getRepository(ProductionSupplyRepository.class));
  }
}
