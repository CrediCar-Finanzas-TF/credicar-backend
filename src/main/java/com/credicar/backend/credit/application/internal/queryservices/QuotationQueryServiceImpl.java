package com.credicar.backend.credit.application.internal.queryservices;

import com.credicar.backend.credit.domain.model.aggregates.Quotation;
import com.credicar.backend.credit.domain.model.queries.GetAllQuotationsQuery;
import com.credicar.backend.credit.domain.model.queries.GetQuotationByIdQuery;
import com.credicar.backend.credit.domain.model.queries.GetQuotationsByClientIdQuery;
import com.credicar.backend.credit.domain.services.QuotationQueryService;
import com.credicar.backend.credit.infrastructure.persistence.jpa.repositories.QuotationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class QuotationQueryServiceImpl implements QuotationQueryService {

    private final QuotationRepository quotationRepository;

    public QuotationQueryServiceImpl(QuotationRepository quotationRepository) {
        this.quotationRepository = quotationRepository;
    }

    @Override
    public Optional<Quotation> handle(GetQuotationByIdQuery query) {
        return quotationRepository.findById(query.quotationId());
    }

    @Override
    public List<Quotation> handle(GetQuotationsByClientIdQuery query) {
        return quotationRepository.findAllByClientId(query.clientId());
    }

    @Override
    public Page<Quotation> handle(GetAllQuotationsQuery query) {
        return quotationRepository.findAll(PageRequest.of(query.page(), query.size()));
    }
}
