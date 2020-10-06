package com.rstepanchuk.miniplantpotstock.entity.order;

import com.rstepanchuk.miniplantpotstock.entity.catalog.Sku;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customer_orders")
@Builder
public class CustomerOrder {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @ManyToOne
  @JoinColumn(name = "fk_sku_id")
  private Sku sku;
  @Column(name = "etsy_order_id")
  private String etsyOrderId;
  @Column(name = "submitted")
  private Date submitted;
  @Column(name = "deadline_to_send")
  private Date deadlineToSend;
  @Column(name = "sent_date")
  private Date sentDate;
  @Column(name = "is_closed")
  private Boolean isClosed;

}
