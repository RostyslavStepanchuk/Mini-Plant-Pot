package com.rstepanchuk.miniplantpotstock.entity.catalog;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "variation_values")
public class VariationValue {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;
  @Column(name = "value")
  private String value;
  @ManyToOne
  @JoinColumn(name = "fk_variation_id")
  private Variation variation;

}
