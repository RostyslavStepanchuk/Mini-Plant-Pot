package com.rstepanchuk.miniplantpotstock.dto.order;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CustomerOrderDtoOut {

  private Long id;
  private List<Long> potsIds;
  private String etsyOrderId;
  private Date submitted;
  private Date deadlineToSend;
  private Date sentDate;
  private Boolean isClosed;
}
