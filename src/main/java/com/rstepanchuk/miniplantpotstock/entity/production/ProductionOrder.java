package com.rstepanchuk.miniplantpotstock.entity.production;

import com.rstepanchuk.miniplantpotstock.entity.catalog.Pot;
import lombok.Data;

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
@Entity
@Table(name = "production_orders")
public class ProductionOrder {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;
  @ManyToOne
  @JoinColumn(name = "fk_pot_id")
  private Pot pot;
  @Column(name = "submit_date")
  private Date submitDate;
  @Column(name = "initial_deadline")
  private Date initialDeadline;
  @Column(name = "current_deadline")
  private Date currentDeadline;
  @Column(name = "deadline_changes")
  private Integer deadlineChanges;
  @Column(name = "ordered_quantity")
  private Integer orderedQuantity;
  @Column(name = "fulfilled_quantity")
  private Integer fulfilledQuantity;
  @Column(name = "delivered")
  private Date delivered;

}
