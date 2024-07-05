package com.josue.account.application.request;

import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;

@Data
public class AccountReportRequest {

    private OffsetDateTime dateFrom;
    private OffsetDateTime dateTo;
    private List<String> clientIds;

}
