package com.rstepanchuk.miniplantpotstock.entity.catalog;

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
import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "variation_attributes")
public class PotVariationAttribute {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;
  @ManyToOne
  @JoinColumn(name = "fk_pot_id")
  private Pot pot;
  @ManyToOne
  @JoinColumn(name = "fk_variation_id")
  private Variation variation;
  @ManyToOne
  @JoinColumn(name = "fk_variation_value_id")
  private VariationValue variationValue;


  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    PotVariationAttribute that = (PotVariationAttribute) obj;
    return pot.getId().equals(that.pot.getId())
        && variation.getId().equals(that.variation.getId())
        && variationValue.getId().equals(that.variationValue.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(pot.getId(), variation.getId(), variationValue.getId());
  }
}
