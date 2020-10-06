package com.rstepanchuk.miniplantpotstock.entity.production;

import com.rstepanchuk.miniplantpotstock.entity.catalog.Pot;
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
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "production_supplies")
public class ProductionSupply {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;
  @ManyToOne
  @JoinColumn(name = "fk_pot_id")
  private Pot pot;
  @Column(name = "supply_date")
  private Date supplyDate;
  @Column(name = "supplied_quantity")
  private Integer suppliedQuantity;
  @Column(name = "was_ordered")
  private Boolean wasOrdered;
}
