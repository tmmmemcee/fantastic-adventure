/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tm.sighting.dao;

import com.tm.sighting.model.Organization;
import java.util.List;

/**
 *
 * @author tmmmemcee
 */
public interface OrganizationDao {
    Organization addOrganization(Organization organization);
    void deleteOrganization(long organizationId);
    void updateOrganization(Organization organization);
    Organization getOrganizationById(long Id);
    List<Organization> getAllOrganizationsFromIdArray(String[] ids);
    List<Organization> getAllOrganizations();
    List<Organization> getAllOrganizationsForSuperHero(long heroId);
}
