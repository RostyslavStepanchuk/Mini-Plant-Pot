package com.rstepanchuk.miniplantpotstock.dto.order;

import com.rstepanchuk.miniplantpotstock.entity.catalog.Pot;
import lombok.Data;

import javax.persistence.Column;
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
