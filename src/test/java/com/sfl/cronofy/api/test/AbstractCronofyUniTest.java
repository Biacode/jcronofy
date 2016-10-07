package com.sfl.cronofy.api.test;

import com.sfl.cronofy.api.helper.CronofyUnitTestHelper;
import org.easymock.EasyMockRunner;
import org.easymock.EasyMockSupport;
import org.junit.runner.RunWith;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 10/6/16
 * Time: 12:12 PM
 */
@RunWith(EasyMockRunner.class)
public abstract class AbstractCronofyUniTest extends EasyMockSupport {

    //region Dependencies
    private final CronofyUnitTestHelper helper;
    //endregion

    //region Constructors
    protected AbstractCronofyUniTest() {
        helper = new CronofyUnitTestHelper();
    }
    //endregion

    //region Public methods
    protected CronofyUnitTestHelper getHelper() {
        return helper;
    }
    //endregion

}
