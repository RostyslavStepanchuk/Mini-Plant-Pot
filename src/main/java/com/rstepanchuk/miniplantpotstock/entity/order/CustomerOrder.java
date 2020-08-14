package com.rstepanchuk.miniplantpotstock.entity.order;

import com.rstepanchuk.miniplantpotstock.entity.catalog.Pot;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "customer_orders")
public class CustomerOrder {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @ManyToMany
  @JoinTable(name = "customer_orders_has_pots",
      joinColumns = @JoinColumn(name = "fk_customer_order_id"),
      inverseJoinColumns = @JoinColumn(name = "fk_pot_id"))
  private List<Pot> pots;
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
