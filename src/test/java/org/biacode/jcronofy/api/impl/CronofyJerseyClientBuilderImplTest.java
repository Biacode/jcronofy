package org.biacode.jcronofy.api.impl;

import org.biacode.jcronofy.api.configuration.CronofyJerseyClientBuilder;
import org.biacode.jcronofy.api.configuration.impl.CronofyJerseyClientBuilderImpl;
import org.biacode.jcronofy.api.test.AbstractCronofyUniTest;
import org.easymock.TestSubject;
import org.junit.Test;

import javax.ws.rs.client.Client;

import static org.junit.Assert.assertNotNull;

/**
 * User: Arthur Asatryan
 * Date: 10/6/16
 * Time: 3:14 PM
 */
public class CronofyJerseyClientBuilderImplTest extends AbstractCronofyUniTest {

    //region Test subject and mocks
    @TestSubject
    private final CronofyJerseyClientBuilder cronofyJerseyClientBuilder = new CronofyJerseyClientBuilderImpl();
    //endregion

    //region Constructors
    public CronofyJerseyClientBuilderImplTest() {
    }
    //endregion

    //region Test methods
    @Test
    public void testBuild() {
        resetAll();
        // test data
        // expectations
        replayAll();
        final Client result = cronofyJerseyClientBuilder.build();
        assertNotNull(result);
        verifyAll();
    }
    //endregion
}