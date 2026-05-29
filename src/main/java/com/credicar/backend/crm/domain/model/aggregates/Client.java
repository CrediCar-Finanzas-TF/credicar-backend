package com.credicar.backend.crm.domain.model.aggregates;

import com.credicar.backend.crm.domain.model.commands.CreateClientCommand;
import com.credicar.backend.crm.domain.model.commands.UpdateClientCommand;
import com.credicar.backend.crm.domain.model.valueobjects.Address;
import com.credicar.backend.crm.domain.model.valueobjects.ContactInfo;
import com.credicar.backend.crm.domain.model.valueobjects.DocumentId;
import com.credicar.backend.crm.domain.model.valueobjects.EmploymentProfile;
import com.credicar.backend.crm.domain.model.valueobjects.PersonName;
import com.credicar.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Client extends AuditableAbstractAggregateRoot<Client> {

    @Embedded
    private DocumentId documentId;

    @Embedded
    private PersonName personName;

    @Embedded
    private ContactInfo contactInfo;

    @Embedded
    private Address address;

    @Embedded
    private EmploymentProfile employmentProfile;

    public Client(CreateClientCommand command) {
        this.documentId = new DocumentId(command.documentType(), command.documentNumber());
        this.personName = new PersonName(command.firstName(), command.lastName());
        this.contactInfo = new ContactInfo(command.phone(), command.email());
        this.address = new Address(command.streetAddress());
        this.employmentProfile = new EmploymentProfile(command.company(), command.monthlyIncome(), command.seniorityYears());
    }

    public void updateInformation(UpdateClientCommand command) {
        this.personName = new PersonName(command.firstName(), command.lastName());
        this.contactInfo = new ContactInfo(command.phone(), command.email());
        this.address = new Address(command.streetAddress());
        this.employmentProfile = new EmploymentProfile(command.company(), command.monthlyIncome(), command.seniorityYears());
    }
}
