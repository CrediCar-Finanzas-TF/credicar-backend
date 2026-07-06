package com.credicar.backend.credit.interfaces.rest;

import com.credicar.backend.credit.domain.exceptions.QuotationNotFoundException;
import com.credicar.backend.credit.domain.model.queries.GetQuotationByIdQuery;
import com.credicar.backend.credit.domain.model.queries.GetQuotationsByClientIdQuery;
import com.credicar.backend.credit.domain.services.QuotationCommandService;
import com.credicar.backend.credit.domain.services.QuotationQueryService;
import com.credicar.backend.credit.interfaces.rest.resources.GenerateQuotationResource;
import com.credicar.backend.credit.interfaces.rest.resources.QuotationResource;
import com.credicar.backend.credit.interfaces.rest.transform.GenerateQuotationCommandFromResourceAssembler;
import com.credicar.backend.credit.interfaces.rest.transform.QuotationResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/quotations")
@Tag(name = "Quotations", description = "Credit quotation simulation and schedule management endpoints")
public class QuotationsController {

    private final QuotationCommandService quotationCommandService;
    private final QuotationQueryService quotationQueryService;

    public QuotationsController(QuotationCommandService quotationCommandService,
                                QuotationQueryService quotationQueryService) {
        this.quotationCommandService = quotationCommandService;
        this.quotationQueryService = quotationQueryService;
    }

    @PostMapping
    public ResponseEntity<QuotationResource> generateQuotation(@RequestBody GenerateQuotationResource resource) {
        var command = GenerateQuotationCommandFromResourceAssembler.toCommandFromResource(resource);
        var quotation = quotationCommandService.handle(command);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(QuotationResourceFromEntityAssembler.toResourceFromEntity(quotation));
    }

    @GetMapping("/{quotationId}")
    public ResponseEntity<QuotationResource> getQuotationById(@PathVariable Long quotationId) {
        return quotationQueryService.handle(new GetQuotationByIdQuery(quotationId))
                .map(QuotationResourceFromEntityAssembler::toResourceFromEntity)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new QuotationNotFoundException(quotationId));
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<QuotationResource>> getQuotationsByClientId(@PathVariable Long clientId) {
        var resources = quotationQueryService.handle(new GetQuotationsByClientIdQuery(clientId))
                .stream()
                .map(QuotationResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }
}
