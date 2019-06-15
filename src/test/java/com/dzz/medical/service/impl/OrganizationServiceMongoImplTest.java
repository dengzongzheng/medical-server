package com.dzz.medical.service.impl;

import com.dzz.medical.common.enums.OrganizationEnum;
import com.dzz.medical.domain.model.Industry;
import com.dzz.medical.domain.model.Organization;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author dzz
 * @version 1.0.0
 * @since 2019年06月14 18:07
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class OrganizationServiceMongoImplTest {


    private OrganizationServiceMongoImpl organizationServiceMongo;

    @Autowired
    public void setOrganizationServiceMongo(OrganizationServiceMongoImpl organizationServiceMongo) {
        this.organizationServiceMongo = organizationServiceMongo;
    }

    @Test
    public void saveOrganization() {

        List<Organization> organizations = new ArrayList<>();

        Organization organization1 = new Organization();
        organization1.setOrganizationCode(OrganizationEnum.PUBLIC.getCode());
        organization1.setOrganizationName(OrganizationEnum.PUBLIC.getName());
        List<Industry> industries = new ArrayList<>();
        industries.add(new Industry(1, "住宿业"));
        industries.add(new Industry(2, "美容店"));
        industries.add(new Industry(3, "理发店"));
        industries.add(new Industry(4, "公共浴室"));
        industries.add(new Industry(5, "商场"));
        organization1.setIndustries(industries);
        organizations.add(organization1);


        Organization organization2 = new Organization();
        organization2.setOrganizationCode(OrganizationEnum.SCHOOL.getCode());
        organization2.setOrganizationName(OrganizationEnum.SCHOOL.getName());
        organization2.setIndustries(new ArrayList<>());
        organizations.add(organization2);


        Organization organization3 = new Organization();
        organization3.setOrganizationCode(OrganizationEnum.WATER_SUPPLY.getCode());
        organization3.setOrganizationName(OrganizationEnum.WATER_SUPPLY.getName());
        organization3.setIndustries(new ArrayList<>());
        organizations.add(organization3);


        Organization organization4 = new Organization();
        organization4.setOrganizationCode(OrganizationEnum.SUPERVISORY.getCode());
        organization4.setOrganizationName(OrganizationEnum.SUPERVISORY.getName());
        organization4.setIndustries(new ArrayList<>());
        organizations.add(organization4);


        Organization organization5 = new Organization();
        organization5.setOrganizationCode(OrganizationEnum.MEDICAL.getCode());
        organization5.setOrganizationName(OrganizationEnum.MEDICAL.getName());
        List<Industry> industries5 = new ArrayList<>();
        industries5.add(new Industry(1, "传染病防控"));
        industries5.add(new Industry(2, "放射卫生"));
        industries5.add(new Industry(3, "依法执业"));
        organization5.setIndustries(industries5);
        organizations.add(organization5);

        for(Organization organization : organizations) {
            organizationServiceMongo.saveOrganization(organization);
        }
    }

    @Test
    public void listOrganization() {

    }
}