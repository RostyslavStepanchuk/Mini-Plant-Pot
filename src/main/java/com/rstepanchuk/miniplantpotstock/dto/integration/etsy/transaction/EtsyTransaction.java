package com.rstepanchuk.miniplantpotstock.dto.integration.etsy.transaction;

import com.rstepanchuk.miniplantpotstock.dto.integration.etsy.listing.EtsyPropertyValue;
import lombok.Data;

import java.util.List;

@Data
public class EtsyTransaction {
  private Long transaction_id;
  private String title;
  private Integer quantity;
  private Long image_listing_id;
  private Long listing_id;
  private List<EtsyPropertyValue> variations;
  private String listing;
  private String url;
}
