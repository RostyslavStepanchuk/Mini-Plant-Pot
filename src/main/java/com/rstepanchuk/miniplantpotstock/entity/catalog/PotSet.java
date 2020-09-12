package com.rstepanchuk.miniplantpotstock.entity.catalog;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Comparator;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sets")
public class PotSet implements Sku {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;
  @Column(name = "name")
  private String name;

  private Integer availableQuantity;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "sets_have_pots",
      joinColumns = @JoinColumn(name = "fk_set_id"),
      inverseJoinColumns = @JoinColumn(name = "fk_pot_id"))
  private List<Pot> pots;

  public Integer getAvailableQuantity() {
    //TODO: refactor to support duplicates in set
    return pots.stream()
        .map(Pot::getAvailableQuantity)
        .min(Comparator.comparingInt(o -> o))
        .orElse(0);
  }
}
