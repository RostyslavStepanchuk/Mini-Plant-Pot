package com.rstepanchuk.miniplantpotstock.entity.catalog;


import com.rstepanchuk.miniplantpotstock.exception.SkuHasNoPotsException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Comparator;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "skus")
@Builder
public class Sku {
  @Id
  @Column(name = "id")
  private String id;

  @Column(name = "is_set")
  private boolean isSet;

  @ManyToMany
  @JoinTable(name = "skus_have_pots",
      joinColumns = @JoinColumn(name = "fk_sku_id"),
      inverseJoinColumns = @JoinColumn(name = "fk_pot_id"))
  private List<Pot> pots;

  public Integer getQuantity() {
    return pots.stream()
        .min(Comparator.comparingInt(Pot::getAvailableQuantity))
        .orElseThrow(() -> new SkuHasNoPotsException(
            String.format("SKU %s is empty while it must have at least one pot", id)))
        .getAvailableQuantity();

  }
}
