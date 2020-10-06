package com.rstepanchuk.miniplantpotstock.dto.order;

import com.rstepanchuk.miniplantpotstock.dto.catalog.SkuDtoOut;
import lombok.Data;

import java.util.Date;

@Data
public class CustomerOrderDtoOut {

  private Long id;
  private SkuDtoOut orderedSku;
  private String etsyOrderId;
  private Date submitted;
  private Date deadlineToSend;
  private Date sentDate;
  private Boolean isClosed;
}
