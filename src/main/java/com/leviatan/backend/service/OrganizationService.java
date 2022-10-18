package com.leviatan.backend.service;

import com.leviatan.backend.exception.NotFoundException;
import com.leviatan.backend.model.Organization;
import com.leviatan.backend.model.User;
import com.leviatan.backend.repository.OrganizationRepository;
import com.leviatan.backend.repository.UserRepository;
import com.leviatan.backend.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizationService {

    private OrganizationRepository organizationRepository;

    private SessionUtils sessionUtils;

    private UserRepository userRepository;

    @Autowired
    public OrganizationService(OrganizationRepository organizationRepository, SessionUtils sessionUtils, UserRepository userRepository) {
        this.organizationRepository = organizationRepository;
        this.sessionUtils = sessionUtils;
        this.userRepository = userRepository;
    }

    public Organization getOrganization() {
        User user =  sessionUtils.getLoggedUserInfo();
        return user.getOrganization();
    }

    public Organization createOrganization(String name) {
        User user = sessionUtils.getLoggedUserInfo();
        Organization organization = new Organization();
        organization.setOwner(user);
        organization.setName(name);
        organization = organizationRepository.save(organization);
        user.setOrganization(organization);
        userRepository.save(user);
        return organization;
    }

    public void addUserToOrganization(String userId) {
        User user = sessionUtils.getLoggedUserInfo();
        User userToAdd = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));

        userToAdd.setOrganization(user.getOrganization());
        userRepository.save(userToAdd);
    }
}
