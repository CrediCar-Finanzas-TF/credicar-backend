package com.credicar.backend.credit.domain.services;

import com.credicar.backend.credit.domain.model.aggregates.Quotation;
import com.credicar.backend.credit.domain.model.queries.GetAllQuotationsQuery;
import com.credicar.backend.credit.domain.model.queries.GetQuotationByIdQuery;
import com.credicar.backend.credit.domain.model.queries.GetQuotationsByClientIdQuery;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface QuotationQueryService {
    Optional<Quotation> handle(GetQuotationByIdQuery query);
    List<Quotation> handle(GetQuotationsByClientIdQuery query);
    Page<Quotation> handle(GetAllQuotationsQuery query);
}
