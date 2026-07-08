package com.credicar.backend.crm.interfaces.rest;

import com.credicar.backend.crm.domain.model.queries.GetClientByDocumentNumberQuery;
import com.credicar.backend.crm.domain.model.queries.GetClientByIdQuery;
import com.credicar.backend.crm.domain.model.queries.GetClientsBySearchQuery;
import com.credicar.backend.crm.domain.services.ClientCommandService;
import com.credicar.backend.crm.domain.services.ClientQueryService;
import com.credicar.backend.crm.interfaces.rest.resources.ClientResource;
import com.credicar.backend.crm.interfaces.rest.resources.CreateClientResource;
import com.credicar.backend.crm.interfaces.rest.resources.UpdateClientResource;
import com.credicar.backend.crm.interfaces.rest.transform.ClientResourceFromEntityAssembler;
import com.credicar.backend.crm.interfaces.rest.transform.CreateClientCommandFromResourceAssembler;
import com.credicar.backend.crm.interfaces.rest.transform.UpdateClientCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/clients", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Clients", description = "Client management endpoints")
public class ClientsController {

    private final ClientCommandService clientCommandService;
    private final ClientQueryService clientQueryService;

    public ClientsController(ClientCommandService clientCommandService,
                             ClientQueryService clientQueryService) {
        this.clientCommandService = clientCommandService;
        this.clientQueryService = clientQueryService;
    }

    @PostMapping
    @Operation(
            summary = "Register a new client",
            description = "Creates a new client record with personal, contact, address, and employment information.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Client created successfully"),
            @ApiResponse(responseCode = "400", description = "Client already exists or invalid request body"),
    })
    public ResponseEntity<ClientResource> createClient(@Valid @RequestBody CreateClientResource resource) {
        var command = CreateClientCommandFromResourceAssembler.toCommandFromResource(resource);
        return clientCommandService.handle(command)
                .map(client -> ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body(ClientResourceFromEntityAssembler.toResourceFromEntity(client)))
                .orElse(ResponseEntity.badRequest().build());
    }

    @GetMapping(params = "search")
    @Operation(
            summary = "Search clients by name or document number",
            description = "Returns a paginated list of clients whose first name, last name, or document number contains the search term (case-insensitive).")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Results returned (may be empty)"),
    })
    public ResponseEntity<Page<ClientResource>> searchClients(
            @Parameter(description = "Text to search in first name, last name or document number", example = "Juan")
            @RequestParam String search,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        var query = new GetClientsBySearchQuery(search, page, size);
        var results = clientQueryService.handle(query)
                .map(ClientResourceFromEntityAssembler::toResourceFromEntity);
        return ResponseEntity.ok(results);
    }

    @GetMapping(params = "documentNumber")
    @Operation(
            summary = "Get a client by document number",
            description = "Retrieves the full profile of a client identified by their document number. The 'documentNumber' query parameter is required.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Client found"),
            @ApiResponse(responseCode = "400", description = "Missing or blank documentNumber parameter"),
            @ApiResponse(responseCode = "404", description = "No client found with the given document number"),
    })
    public ResponseEntity<ClientResource> getClientByDocumentNumber(
            @Parameter(description = "Document number of the client to retrieve", example = "74839201")
            @RequestParam(required = false) String documentNumber) {
        if (documentNumber == null || documentNumber.isBlank()) {
            return ResponseEntity.badRequest().build();
        }
        var query = new GetClientByDocumentNumberQuery(documentNumber);
        return clientQueryService.handle(query)
                .map(client -> ResponseEntity.ok(ClientResourceFromEntityAssembler.toResourceFromEntity(client)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{clientId}")
    @Operation(
            summary = "Get a client by ID",
            description = "Retrieves the full profile of a client identified by their internal ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Client found"),
            @ApiResponse(responseCode = "404", description = "Client not found"),
    })
    public ResponseEntity<ClientResource> getClientById(@PathVariable Long clientId) {
        var query = new GetClientByIdQuery(clientId);
        return clientQueryService.handle(query)
                .map(client -> ResponseEntity.ok(ClientResourceFromEntityAssembler.toResourceFromEntity(client)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{clientId}")
    @Operation(
            summary = "Update a client",
            description = "Updates the personal, contact, address, and employment information of an existing client.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Client updated successfully"),
            @ApiResponse(responseCode = "404", description = "Client not found"),
    })
    public ResponseEntity<ClientResource> updateClient(@PathVariable Long clientId,
                                                       @Valid @RequestBody UpdateClientResource resource) {
        var command = UpdateClientCommandFromResourceAssembler.toCommandFromResource(clientId, resource);
        return clientCommandService.handle(command)
                .map(client -> ResponseEntity.ok(ClientResourceFromEntityAssembler.toResourceFromEntity(client)))
                .orElse(ResponseEntity.notFound().build());
    }
}
