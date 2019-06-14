package com.dzz.medical.service.impl;

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

    }

    @Test
    public void listOrganization() {

    }
}