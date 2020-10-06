package com.rstepanchuk.miniplantpotstock.dto.integration.etsy.listing;

import lombok.Data;

@Data
public class EtsyPropertyValue {
  private Long property_id;
  private String formatted_name;
  private String formatted_value;
}
