package com.rstepanchuk.miniplantpotstock.dto.integration.etsy.transaction;

import lombok.Data;

import java.util.List;

@Data
public class EtsyTransactionsCollection {
  private Integer count;
  private List<EtsyTransaction> results;
}
