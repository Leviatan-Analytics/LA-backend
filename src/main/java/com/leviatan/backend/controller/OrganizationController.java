package com.leviatan.backend.controller;

import com.leviatan.backend.dto.organization.OrganizationAddUserRequest;
import com.leviatan.backend.dto.organization.OrganizationCreateRequest;
import com.leviatan.backend.model.Organization;
import com.leviatan.backend.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/organization")
@CrossOrigin(origins = "*")
public class OrganizationController {

    private final OrganizationService organizationService;

    @Autowired
    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @GetMapping()
    public Organization getOrganization() {
        return organizationService.getOrganization();
    }

    @PostMapping()
    public Organization createOrganization(@RequestBody OrganizationCreateRequest organizationCreateRequest) {
        return organizationService.createOrganization(organizationCreateRequest.getName());
    }

    @PutMapping("/add-user")
    public void updateOrganization(@RequestBody OrganizationAddUserRequest organizationAddUserRequest) {
        organizationService.addUserToOrganization(organizationAddUserRequest.getUserId());
    }
}
