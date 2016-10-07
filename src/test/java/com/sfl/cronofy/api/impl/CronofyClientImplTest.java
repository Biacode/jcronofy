package com.sfl.cronofy.api.impl;

import com.sfl.cronofy.api.client.CronofyClient;
import com.sfl.cronofy.api.client.exception.UnknownStatusCodeException;
import com.sfl.cronofy.api.client.impl.CronofyClientImpl;
import com.sfl.cronofy.api.model.ScopeModel;
import com.sfl.cronofy.api.model.TokenTypeModel;
import com.sfl.cronofy.api.model.common.CronofyResponse;
import com.sfl.cronofy.api.model.common.ErrorTypeModel;
import com.sfl.cronofy.api.model.request.*;
import com.sfl.cronofy.api.model.response.*;
import com.sfl.cronofy.api.test.AbstractCronofyUniTest;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.reset;
import static org.junit.Assert.*;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 10/6/16
 * Time: 11:10 AM
 */
public class CronofyClientImplTest extends AbstractCronofyUniTest {

    //region Constants
    private static final String BASE_PATH = "https://api.cronofy.com";

    private static final String API_VERSION = "v1";

    private static final String CALENDARS_PATH = "calendars";

    private static final String CHANNELS_PATH = "channels";

    private static final String PROFILES_PATH = "profiles";

    private static final String AUTH_HEADER_KEY = "Authorization";

    private static final String ACCOUNT_PATH = "account";

    private static final String EVENTS = "events";
    //endregion

    //region Test subject and mocks
    @TestSubject
    private final CronofyClient cronofyClient;

    @Mock
    private Client client;

    @Mock
    private WebTarget webTarget;

    @Mock
    private Invocation.Builder builder;
    //endregion

    //region Constructors
    public CronofyClientImplTest() {
        cronofyClient = new CronofyClientImpl(client);
    }
    //endregion

    //region Test callbacks
    @Before
    public void before() {
        reset(client);
        reset(webTarget);
        reset(builder);
    }
    //endregion

    //region Test methods

    //region getAccessToken

    /**
     * With invalid arguments
     */
    @Test
    public void testGetAccessTokenScenario1() {
        resetAll();
        // test data
        // expectations
        replayAll();
        try {
            cronofyClient.getAccessToken(null);
            fail();
        } catch (final IllegalArgumentException ignore) {
        }
        verifyAll();
    }

    /**
     * General case
     */
    @Test
    public void testGetAccessTokenScenario2() {
        resetAll();
        // test data
        final GetAccessTokenRequest request = getHelper().getGetAccessTokenRequest();
        final CronofyResponse<GetAccessTokenResponse> expectedResponse = new CronofyResponse<>(
                new GetAccessTokenResponse(
                        TokenTypeModel.BEARER,
                        UUID.randomUUID().toString(),
                        UUID.randomUUID().toString(),
                        UUID.randomUUID().toString(),
                        3600
                ));
        // expectations
        expect(client.target(BASE_PATH)).andReturn(webTarget);
        expect(webTarget.path("oauth")).andReturn(webTarget);
        expect(webTarget.path("token")).andReturn(webTarget);
        expect(webTarget.request(MediaType.APPLICATION_JSON_TYPE)).andReturn(builder);
        expect(builder.post(Entity.entity(request, MediaType.APPLICATION_JSON_TYPE),
                new GenericType<CronofyResponse<GetAccessTokenResponse>>() {
                })).andReturn(expectedResponse);
        replayAll();
        final CronofyResponse<GetAccessTokenResponse> result = cronofyClient.getAccessToken(request);
        getHelper().assertResultResponse(expectedResponse, result);
        verifyAll();
    }

    /**
     * When bad request exception has been thrown
     */
    @Test
    public void testGetAccessTokenScenario3() {
        resetAll();
        // test data
        final CronofyResponse<GetAccessTokenResponse> expectedResponse = new CronofyResponse<>(ErrorTypeModel.BAD_REQUEST);
        // expectations
        expect(client.target(BASE_PATH)).andThrow(new BadRequestException());
        replayAll();
        final CronofyResponse<GetAccessTokenResponse> result = cronofyClient.getAccessToken(getHelper().getGetAccessTokenRequest());
        getHelper().assertResultResponse(expectedResponse, result);
        verifyAll();
    }
    //endregion

    //region updateAccessToken

    /**
     * With invalid arguments
     */
    @Test
    public void testUpdateAccessTokenScenario1() {
        resetAll();
        // test data
        // expectations
        replayAll();
        try {
            cronofyClient.updateAccessToken(null);
            fail();
        } catch (final IllegalArgumentException ignore) {
        }
        verifyAll();
    }

    /**
     * General case
     */
    @Test
    public void testUpdateAccessTokenScenario2() {
        resetAll();
        // test data
        final UpdateAccessTokenRequest request = getHelper().getUpdateAccessTokenRequestRequest();

        final CronofyResponse<UpdateAccessTokenResponse> expectedResponse = new CronofyResponse<>(
                new UpdateAccessTokenResponse(
                        TokenTypeModel.BEARER, UUID.randomUUID().toString(),
                        UUID.randomUUID().toString(),
                        3600,
                        ScopeModel.CREATE_EVENT
                ));
        // expectations
        expect(client.target(BASE_PATH)).andReturn(webTarget);
        expect(webTarget.path("oauth")).andReturn(webTarget);
        expect(webTarget.path("token")).andReturn(webTarget);
        expect(webTarget.request(MediaType.APPLICATION_JSON_TYPE)).andReturn(builder);
        expect(builder.post(Entity.entity(request, MediaType.APPLICATION_JSON_TYPE),
                new GenericType<CronofyResponse<UpdateAccessTokenResponse>>() {
                })).andReturn(expectedResponse);
        replayAll();
        final CronofyResponse<UpdateAccessTokenResponse> result = cronofyClient.updateAccessToken(request);
        getHelper().assertResultResponse(expectedResponse, result);
        verifyAll();
    }

    /**
     * When bad request exception has been thrown
     */
    @Test
    public void testUpdateAccessTokenScenario3() {
        resetAll();
        // test data
        final UpdateAccessTokenRequest request = getHelper().getUpdateAccessTokenRequestRequest();
        final CronofyResponse<UpdateAccessTokenResponse> expectedResponse = new CronofyResponse<>(ErrorTypeModel.BAD_REQUEST);
        // expectations
        expect(client.target(BASE_PATH)).andThrow(new BadRequestException());
        replayAll();
        final CronofyResponse<UpdateAccessTokenResponse> result = cronofyClient.updateAccessToken(request);
        getHelper().assertResultResponse(expectedResponse, result);
        verifyAll();
    }
    //endregion

    //region revokeAccessToken

    /**
     * With invalid arguments
     */
    @Test
    public void testRevokeAccessTokenScenario1() {
        resetAll();
        // test data
        // expectations
        replayAll();
        try {
            cronofyClient.revokeAccessToken(null);
            fail();
        } catch (final IllegalArgumentException ignore) {
        }
        verifyAll();
    }

    /**
     * General case
     */
    @Test
    public void testRevokeAccessTokenScenario2() {
        resetAll();
        // test data
        final RevokeAccessTokenRequest request = getHelper().getRevokeAccessTokenRequest();

        final CronofyResponse<RevokeAccessTokenResponse> expectedResponse = new CronofyResponse<>(
                new RevokeAccessTokenResponse()
        );
        final Response expectedResult = Response.status(200).build();
        // expectations
        expect(client.target(BASE_PATH)).andReturn(webTarget);
        expect(webTarget.path("oauth")).andReturn(webTarget);
        expect(webTarget.path("token")).andReturn(webTarget);
        expect(webTarget.path("revoke")).andReturn(webTarget);
        expect(webTarget.request(MediaType.APPLICATION_JSON_TYPE)).andReturn(builder);
        expect(builder.post(Entity.entity(request, MediaType.APPLICATION_JSON_TYPE))).andReturn(expectedResult);
        replayAll();
        final CronofyResponse<RevokeAccessTokenResponse> result = cronofyClient.revokeAccessToken(request);
        assertNotNull(result);
        assertFalse(result.hasError());
        assertTrue(expectedResponse.getClass().isInstance(result));
        verifyAll();
    }

    /**
     * When bad request exception has been thrown
     */
    @Test
    public void testRevokeAccessTokenScenario3() {
        resetAll();
        // test data
        final RevokeAccessTokenRequest request = getHelper().getRevokeAccessTokenRequest();
        final CronofyResponse<RevokeAccessTokenResponse> expectedResponse = new CronofyResponse<>(ErrorTypeModel.BAD_REQUEST);
        final Response expectedResult = Response.status(400).build();
        // expectations
        expect(client.target(BASE_PATH)).andReturn(webTarget);
        expect(webTarget.path("oauth")).andReturn(webTarget);
        expect(webTarget.path("token")).andReturn(webTarget);
        expect(webTarget.path("revoke")).andReturn(webTarget);
        expect(webTarget.request(MediaType.APPLICATION_JSON_TYPE)).andReturn(builder);
        expect(builder.post(Entity.entity(request, MediaType.APPLICATION_JSON_TYPE))).andReturn(expectedResult);
        replayAll();
        final CronofyResponse<RevokeAccessTokenResponse> result = cronofyClient.revokeAccessToken(request);
        getHelper().assertResultResponse(expectedResponse, result);
        verifyAll();
    }
    //endregion

    //region listCalendars

    /**
     * With invalid argument
     */
    @Test
    public void testListCalendarsScenario1() {
        resetAll();
        // test data
        // expectations
        replayAll();
        try {
            cronofyClient.listCalendars(null);
            fail();
        } catch (final IllegalArgumentException ignore) {
        }
        try {
            cronofyClient.listCalendars(new ListCalendarsRequest());
            fail();
        } catch (final IllegalArgumentException ignore) {
        }
        verifyAll();
    }

    /**
     * General case
     */
    @Test
    public void testListCalendarsScenario2() {
        resetAll();
        // test data
        final ListCalendarsRequest request = getHelper().getListCalendarsRequestRequest();

        final CronofyResponse<ListCalendarsResponse> expectedResponse = new CronofyResponse<>(new ListCalendarsResponse(
                new ArrayList<>(Arrays.asList(getHelper().buildCalendarModel(), getHelper().buildCalendarModel()))
        ));
        // expectations
        expect(client.target(BASE_PATH)).andReturn(webTarget);
        expect(webTarget.path(API_VERSION)).andReturn(webTarget);
        expect(webTarget.path(CALENDARS_PATH)).andReturn(webTarget);
        expect(webTarget.request(MediaType.APPLICATION_JSON_TYPE)).andReturn(builder);
        expect(builder.header(AUTH_HEADER_KEY, "Bearer " + request.getAccessToken())).andReturn(builder);
        expect(builder.get(new GenericType<CronofyResponse<ListCalendarsResponse>>() {
        })).andReturn(expectedResponse);
        replayAll();
        final CronofyResponse<ListCalendarsResponse> result = cronofyClient.listCalendars(request);
        getHelper().assertResultResponse(expectedResponse, result);
        verifyAll();
    }

    /**
     * When not authorized exception has been thrown
     */
    @Test
    public void testListCalendarsScenario3() {
        resetAll();
        // test data
        final ListCalendarsRequest request = getHelper().getListCalendarsRequestRequest();

        final CronofyResponse<ListCalendarsResponse> expectedResponse = new CronofyResponse<>(
                ErrorTypeModel.NOT_AUTHORIZED
        );
        // expectations
        expect(client.target(BASE_PATH)).andThrow(new NotAuthorizedException(new CronofyResponse<>()));
        replayAll();
        final CronofyResponse<ListCalendarsResponse> result = cronofyClient.listCalendars(request);
        getHelper().assertResultResponse(expectedResponse, result);
        verifyAll();
    }

    /**
     * When client error exception has been thrown
     */
    @Test
    public void testListCalendarsScenario5() {
        resetAll();
        // test data
        final ListCalendarsRequest request = getHelper().getListCalendarsRequestRequest();
        final CronofyResponse<ListCalendarsResponse> expectedResponse = new CronofyResponse<>(
                ErrorTypeModel.UNPROCESSABLE
        );
        // expectations
        expect(client.target(BASE_PATH)).andThrow(new ClientErrorException(Response.Status.CONFLICT));
        replayAll();
        final CronofyResponse<ListCalendarsResponse> result = cronofyClient.listCalendars(request);
        getHelper().assertResultResponse(expectedResponse, result);
        verifyAll();
    }
    //endregion

    //region readEvents

    /**
     * With invalid arguments
     */
    @Test
    public void testReadEventsScenario1() {
        resetAll();
        // test data
        // expectations
        replayAll();
        try {
            cronofyClient.readEvents(null);
            fail();
        } catch (final IllegalArgumentException ignore) {
        }
        try {
            cronofyClient.readEvents(new ReadEventsRequest());
            fail();
        } catch (final IllegalArgumentException ignore) {
        }
        verifyAll();
    }

    /**
     * General case
     */
    @Test
    public void testReadEventsScenario2() {
        resetAll();
        // test data
        final ReadEventsRequest request = getHelper().getReadEventsRequest();

        final CronofyResponse<ReadEventsResponse> expectedResponse = new CronofyResponse<>(new ReadEventsResponse(
                getHelper().buildEventsPagesModel(),
                new ArrayList<>(Arrays.asList(getHelper().buildEventModel(), getHelper().buildEventModel()))
        ));
        // expectations
        expect(client.target(BASE_PATH)).andReturn(webTarget);
        expect(webTarget.path(API_VERSION)).andReturn(webTarget);
        expect(webTarget.path(EVENTS)).andReturn(webTarget);
        expect(webTarget.queryParam("from", request.getFrom())).andReturn(webTarget);
        expect(webTarget.queryParam("to", request.getTo())).andReturn(webTarget);
        expect(webTarget.queryParam("tzid", request.getTzId())).andReturn(webTarget);
        expect(webTarget.queryParam("include_deleted", request.isIncludeDeleted())).andReturn(webTarget);
        expect(webTarget.queryParam("include_moved", request.isIncludeMoved())).andReturn(webTarget);
        expect(webTarget.queryParam("last_modified", request.getLastModified())).andReturn(webTarget);
        expect(webTarget.queryParam("include_managed", request.isIncludeManaged())).andReturn(webTarget);
        expect(webTarget.queryParam("only_managed", request.isOnlyManaged())).andReturn(webTarget);
        expect(webTarget.queryParam("calendar_ids", request.getCalendarIds())).andReturn(webTarget);
        expect(webTarget.queryParam("localized_times", request.isLocalizedTimes())).andReturn(webTarget);
        expect(webTarget.request(MediaType.APPLICATION_JSON_TYPE)).andReturn(builder);
        expect(builder.header(AUTH_HEADER_KEY, "Bearer " + request.getAccessToken())).andReturn(builder);
        expect(builder.get(new GenericType<CronofyResponse<ReadEventsResponse>>() {
        })).andReturn(expectedResponse);
        replayAll();
        final CronofyResponse<ReadEventsResponse> result = cronofyClient.readEvents(request);
        getHelper().assertResultResponse(expectedResponse, result);
        verifyAll();
    }

    /**
     * When not authorized exception has been thrown
     */
    @Test
    public void testReadEventsScenario3() {
        resetAll();
        // test data
        final ReadEventsRequest request = getHelper().getReadEventsRequest();
        final CronofyResponse<ReadEventsResponse> expectedResponse = new CronofyResponse<>(
                ErrorTypeModel.NOT_AUTHORIZED
        );
        // expectations
        expect(client.target(BASE_PATH)).andThrow(new NotAuthorizedException(new CronofyResponse<>()));
        replayAll();
        final CronofyResponse<ReadEventsResponse> result = cronofyClient.readEvents(request);
        getHelper().assertResultResponse(expectedResponse, result);
        verifyAll();
    }

    /**
     * When forbidden exception has been thrown
     */
    @Test
    public void testReadEventsScenario4() {
        resetAll();
        // test data
        final ReadEventsRequest request = getHelper().getReadEventsRequest();
        final CronofyResponse<ReadEventsResponse> expectedResponse = new CronofyResponse<>(
                ErrorTypeModel.FORBIDDEN
        );
        // expectations
        expect(client.target(BASE_PATH)).andThrow(new ForbiddenException());
        replayAll();
        final CronofyResponse<ReadEventsResponse> result = cronofyClient.readEvents(request);
        getHelper().assertResultResponse(expectedResponse, result);
        verifyAll();
    }

    /**
     * When client exception has been thrown
     */
    @Test
    public void testReadEventsScenario6() {
        resetAll();
        // test data
        final ReadEventsRequest request = getHelper().getReadEventsRequest();
        final CronofyResponse<ReadEventsResponse> expectedResponse = new CronofyResponse<>(
                ErrorTypeModel.UNPROCESSABLE
        );
        // expectations
        expect(client.target(BASE_PATH)).andThrow(new ClientErrorException(Response.Status.CONFLICT));
        replayAll();
        final CronofyResponse<ReadEventsResponse> result = cronofyClient.readEvents(request);
        getHelper().assertResultResponse(expectedResponse, result);
        verifyAll();
    }
    //endregion

    //region freeBusy

    /**
     * With invalid arguments
     */
    @Test
    public void testFreeBusyScenario1() {
        resetAll();
        // test data
        // expectations
        replayAll();
        try {
            cronofyClient.freeBusy(null);
            fail();
        } catch (final IllegalArgumentException ignore) {
        }
        try {
            cronofyClient.freeBusy(new FreeBusyRequest());
            fail();
        } catch (final IllegalArgumentException ignore) {
        }
        verifyAll();
    }

    @Test
    public void testFreeBusyScenario2() {
        resetAll();
        // test data
        final FreeBusyRequest request = getHelper().getFreeBusyRequest();

        final CronofyResponse<FreeBusyResponse> expectedResponse = new CronofyResponse<>(new FreeBusyResponse(
                getHelper().buildEventsPagesModel(),
                new ArrayList<>(Arrays.asList(getHelper().buildFreeBusyModel(), getHelper().buildFreeBusyModel()))
        ));
        // expectations
        expect(client.target(BASE_PATH)).andReturn(webTarget);
        expect(webTarget.path(API_VERSION)).andReturn(webTarget);
        expect(webTarget.path("free_busy")).andReturn(webTarget);
        expect(webTarget.queryParam("from", request.getFrom())).andReturn(webTarget);
        expect(webTarget.queryParam("to", request.getTo())).andReturn(webTarget);
        expect(webTarget.queryParam("tzid", request.getTzId())).andReturn(webTarget);
        expect(webTarget.queryParam("include_managed", request.getIncludeManaged())).andReturn(webTarget);
        expect(webTarget.queryParam("calendar_ids", request.getCalendarIds())).andReturn(webTarget);
        expect(webTarget.queryParam("localized_times", request.getLocalizedTimes())).andReturn(webTarget);
        expect(webTarget.request(MediaType.APPLICATION_JSON_TYPE)).andReturn(builder);
        expect(builder.header(AUTH_HEADER_KEY, "Bearer " + request.getAccessToken())).andReturn(builder);
        expect(builder.get(new GenericType<CronofyResponse<FreeBusyResponse>>() {
        })).andReturn(expectedResponse);
        replayAll();
        final CronofyResponse<FreeBusyResponse> result = cronofyClient.freeBusy(request);
        getHelper().assertResultResponse(expectedResponse, result);
        verifyAll();
    }

    /**
     * When not authorized exception has been thrown
     */
    @Test
    public void testFreeBusyScenario3() {
        resetAll();
        // test data
        final FreeBusyRequest request = getHelper().getFreeBusyRequest();
        final CronofyResponse<FreeBusyResponse> expectedResponse = new CronofyResponse<>(
                ErrorTypeModel.NOT_AUTHORIZED
        );
        // expectations
        expect(client.target(BASE_PATH)).andThrow(new NotAuthorizedException(new CronofyResponse<>()));
        replayAll();
        final CronofyResponse<FreeBusyResponse> result = cronofyClient.freeBusy(request);
        getHelper().assertResultResponse(expectedResponse, result);
        verifyAll();
    }

    /**
     * When forbidden exception has been thrown
     */
    @Test
    public void testFreeBusyScenario4() {
        resetAll();
        // test data
        final FreeBusyRequest request = getHelper().getFreeBusyRequest();
        final CronofyResponse<FreeBusyResponse> expectedResponse = new CronofyResponse<>(
                ErrorTypeModel.FORBIDDEN
        );
        // expectations
        expect(client.target(BASE_PATH)).andThrow(new ForbiddenException());
        replayAll();
        final CronofyResponse<FreeBusyResponse> result = cronofyClient.freeBusy(request);
        getHelper().assertResultResponse(expectedResponse, result);
        verifyAll();
    }
    //endregion

    //region createOrUpdateEvent

    /**
     * With invalid arguments
     */
    @Test
    public void testCreateOrUpdateEventScenario1() {
        resetAll();
        // test data
        // expectations
        replayAll();
        try {
            cronofyClient.createOrUpdateEvent(null);
            fail();
        } catch (final IllegalArgumentException ignore) {
        }
        try {
            cronofyClient.createOrUpdateEvent(new CreateOrUpdateEventRequest());
            fail();
        } catch (final IllegalArgumentException ignore) {
        }
        verifyAll();
    }

    /**
     * General case
     */
    @Test
    public void testCreateOrUpdateEventScenario2() {
        resetAll();
        // test data
        final CreateOrUpdateEventRequest request = getHelper().getCreateOrUpdateEventRequest();

        final CronofyResponse<CreateOrUpdateEventResponse> expectedResponse = new CronofyResponse<>(
                new CreateOrUpdateEventResponse()
        );
        final Response expectedResult = Response.status(202).build();
        // expectations
        expect(client.target(BASE_PATH)).andReturn(webTarget);
        expect(webTarget.path(API_VERSION)).andReturn(webTarget);
        expect(webTarget.path(CALENDARS_PATH)).andReturn(webTarget);
        expect(webTarget.path(request.getCalendarId())).andReturn(webTarget);
        expect(webTarget.path(EVENTS)).andReturn(webTarget);
        expect(webTarget.request(MediaType.APPLICATION_JSON_TYPE)).andReturn(builder);
        expect(builder.header(AUTH_HEADER_KEY, "Bearer " + request.getAccessToken())).andReturn(builder);
        expect(builder.post(Entity.entity(request, MediaType.APPLICATION_JSON_TYPE))).andReturn(expectedResult);
        replayAll();
        final CronofyResponse<CreateOrUpdateEventResponse> result = cronofyClient.createOrUpdateEvent(request);
        assertNotNull(result);
        assertFalse(result.hasError());
        assertTrue(expectedResponse.getClass().isInstance(result));
        verifyAll();
    }

    /**
     * When not authorized
     */
    @Test
    public void testCreateOrUpdateEventScenario3() {
        resetAll();
        // test data
        final CreateOrUpdateEventRequest request = getHelper().getCreateOrUpdateEventRequest();
        final CronofyResponse<CreateOrUpdateEventResponse> expectedResponse = new CronofyResponse<>(
                ErrorTypeModel.NOT_AUTHORIZED
        );
        final Response expectedResult = Response.status(401).build();
        // expectations
        expect(client.target(BASE_PATH)).andReturn(webTarget);
        expect(webTarget.path(API_VERSION)).andReturn(webTarget);
        expect(webTarget.path(CALENDARS_PATH)).andReturn(webTarget);
        expect(webTarget.path(request.getCalendarId())).andReturn(webTarget);
        expect(webTarget.path(EVENTS)).andReturn(webTarget);
        expect(webTarget.request(MediaType.APPLICATION_JSON_TYPE)).andReturn(builder);
        expect(builder.header(AUTH_HEADER_KEY, "Bearer " + request.getAccessToken())).andReturn(builder);
        expect(builder.post(Entity.entity(request, MediaType.APPLICATION_JSON_TYPE))).andReturn(expectedResult);
        replayAll();
        final CronofyResponse<CreateOrUpdateEventResponse> result = cronofyClient.createOrUpdateEvent(request);
        getHelper().assertResultResponse(expectedResponse, result);
        verifyAll();
    }

    /**
     * When forbidden
     */
    @Test
    public void testCreateOrUpdateEventScenario4() {
        resetAll();
        // test data
        final CreateOrUpdateEventRequest request = getHelper().getCreateOrUpdateEventRequest();
        final CronofyResponse<CreateOrUpdateEventResponse> expectedResponse = new CronofyResponse<>(
                ErrorTypeModel.FORBIDDEN
        );
        final Response expectedResult = Response.status(403).build();
        // expectations
        expect(client.target(BASE_PATH)).andReturn(webTarget);
        expect(webTarget.path(API_VERSION)).andReturn(webTarget);
        expect(webTarget.path(CALENDARS_PATH)).andReturn(webTarget);
        expect(webTarget.path(request.getCalendarId())).andReturn(webTarget);
        expect(webTarget.path(EVENTS)).andReturn(webTarget);
        expect(webTarget.request(MediaType.APPLICATION_JSON_TYPE)).andReturn(builder);
        expect(builder.header(AUTH_HEADER_KEY, "Bearer " + request.getAccessToken())).andReturn(builder);
        expect(builder.post(Entity.entity(request, MediaType.APPLICATION_JSON_TYPE))).andReturn(expectedResult);
        replayAll();
        final CronofyResponse<CreateOrUpdateEventResponse> result = cronofyClient.createOrUpdateEvent(request);
        getHelper().assertResultResponse(expectedResponse, result);
        verifyAll();
    }

    /**
     * When client exception exception has been thrown
     */
    @Test
    public void testCreateOrUpdateEventScenario6() {
        resetAll();
        // test data
        final CreateOrUpdateEventRequest request = getHelper().getCreateOrUpdateEventRequest();
        final CronofyResponse<CreateOrUpdateEventResponse> expectedResponse = new CronofyResponse<>(
                ErrorTypeModel.UNPROCESSABLE
        );
        final Response expectedResult = Response.status(422).build();
        // expectations
        expect(client.target(BASE_PATH)).andReturn(webTarget);
        expect(webTarget.path(API_VERSION)).andReturn(webTarget);
        expect(webTarget.path(CALENDARS_PATH)).andReturn(webTarget);
        expect(webTarget.path(request.getCalendarId())).andReturn(webTarget);
        expect(webTarget.path(EVENTS)).andReturn(webTarget);
        expect(webTarget.request(MediaType.APPLICATION_JSON_TYPE)).andReturn(builder);
        expect(builder.header(AUTH_HEADER_KEY, "Bearer " + request.getAccessToken())).andReturn(builder);
        expect(builder.post(Entity.entity(request, MediaType.APPLICATION_JSON_TYPE))).andReturn(expectedResult);
        replayAll();
        final CronofyResponse<CreateOrUpdateEventResponse> result = cronofyClient.createOrUpdateEvent(request);
        getHelper().assertResultResponse(expectedResponse, result);
        verifyAll();
    }
    //endregion

    //region deleteEvent

    /**
     * With invalid arguments
     */
    @Test
    public void testDeleteEventScenario1() {
        resetAll();
        // test data
        // expectations
        replayAll();
        try {
            cronofyClient.deleteEvent(null);
            fail();
        } catch (final IllegalArgumentException ignore) {
        }
        try {
            cronofyClient.deleteEvent(new DeleteEventRequest());
            fail();
        } catch (final IllegalArgumentException ignore) {
        }
        verifyAll();
    }

    /**
     * General case
     */
    @Test
    public void testDeleteEventScenario2() {
        resetAll();
        // test data
        final DeleteEventRequest request = getHelper().getDeleteEventRequest();

        final CronofyResponse<DeleteEventResponse> expectedResponse = new CronofyResponse<>(new DeleteEventResponse());
        // expectations
        expect(client.target(BASE_PATH)).andReturn(webTarget);
        expect(webTarget.path(API_VERSION)).andReturn(webTarget);
        expect(webTarget.path(CALENDARS_PATH)).andReturn(webTarget);
        expect(webTarget.path(request.getCalendarId())).andReturn(webTarget);
        expect(webTarget.path(EVENTS)).andReturn(webTarget);
        expect(webTarget.queryParam("event_id", request.getEventId())).andReturn(webTarget);
        expect(webTarget.request(MediaType.APPLICATION_JSON_TYPE)).andReturn(builder);
        expect(builder.header(AUTH_HEADER_KEY, "Bearer " + request.getAccessToken())).andReturn(builder);
        expect(builder.delete()).andReturn(Response.accepted().build());
        replayAll();
        final CronofyResponse<DeleteEventResponse> result = cronofyClient.deleteEvent(request);
        assertNotNull(result);
        assertFalse(result.hasError());
        assertTrue(expectedResponse.getClass().isInstance(result));
        verifyAll();
    }

    /**
     * When not authorized
     */
    @Test
    public void testDeleteEventScenario3() {
        resetAll();
        // test data
        final DeleteEventRequest request = getHelper().getDeleteEventRequest();
        final CronofyResponse<DeleteEventResponse> expectedResponse = new CronofyResponse<>(
                ErrorTypeModel.NOT_AUTHORIZED
        );
        final Response expectedResult = Response.status(401).build();
        // expectations
        expect(client.target(BASE_PATH)).andReturn(webTarget);
        expect(webTarget.path(API_VERSION)).andReturn(webTarget);
        expect(webTarget.path(CALENDARS_PATH)).andReturn(webTarget);
        expect(webTarget.path(request.getCalendarId())).andReturn(webTarget);
        expect(webTarget.path(EVENTS)).andReturn(webTarget);
        expect(webTarget.queryParam("event_id", request.getEventId())).andReturn(webTarget);
        expect(webTarget.request(MediaType.APPLICATION_JSON_TYPE)).andReturn(builder);
        expect(builder.header(AUTH_HEADER_KEY, "Bearer " + request.getAccessToken())).andReturn(builder);
        expect(builder.delete()).andReturn(expectedResult);
        replayAll();
        final CronofyResponse<DeleteEventResponse> result = cronofyClient.deleteEvent(request);
        getHelper().assertResultResponse(expectedResponse, result);
        verifyAll();
    }

    /**
     * When forbidden
     */
    @Test
    public void testDeleteEventScenario4() {
        resetAll();
        // test data
        final DeleteEventRequest request = getHelper().getDeleteEventRequest();
        final CronofyResponse<DeleteEventResponse> expectedResponse = new CronofyResponse<>(
                ErrorTypeModel.FORBIDDEN
        );
        final Response expectedResult = Response.status(403).build();
        // expectations
        expect(client.target(BASE_PATH)).andReturn(webTarget);
        expect(webTarget.path(API_VERSION)).andReturn(webTarget);
        expect(webTarget.path(CALENDARS_PATH)).andReturn(webTarget);
        expect(webTarget.path(request.getCalendarId())).andReturn(webTarget);
        expect(webTarget.path(EVENTS)).andReturn(webTarget);
        expect(webTarget.queryParam("event_id", request.getEventId())).andReturn(webTarget);
        expect(webTarget.request(MediaType.APPLICATION_JSON_TYPE)).andReturn(builder);
        expect(builder.header(AUTH_HEADER_KEY, "Bearer " + request.getAccessToken())).andReturn(builder);
        expect(builder.delete()).andReturn(expectedResult);
        replayAll();
        final CronofyResponse<DeleteEventResponse> result = cronofyClient.deleteEvent(request);
        getHelper().assertResultResponse(expectedResponse, result);
        verifyAll();
    }

    /**
     * When un processable
     */
    @Test
    public void testDeleteEventScenario6() {
        resetAll();
        // test data
        final DeleteEventRequest request = getHelper().getDeleteEventRequest();
        final CronofyResponse<DeleteEventResponse> expectedResponse = new CronofyResponse<>(
                ErrorTypeModel.UNPROCESSABLE
        );
        final Response expectedResult = Response.status(422).build();
        // expectations
        expect(client.target(BASE_PATH)).andReturn(webTarget);
        expect(webTarget.path(API_VERSION)).andReturn(webTarget);
        expect(webTarget.path(CALENDARS_PATH)).andReturn(webTarget);
        expect(webTarget.path(request.getCalendarId())).andReturn(webTarget);
        expect(webTarget.path(EVENTS)).andReturn(webTarget);
        expect(webTarget.queryParam("event_id", request.getEventId())).andReturn(webTarget);
        expect(webTarget.request(MediaType.APPLICATION_JSON_TYPE)).andReturn(builder);
        expect(builder.header(AUTH_HEADER_KEY, "Bearer " + request.getAccessToken())).andReturn(builder);
        expect(builder.delete()).andReturn(expectedResult);
        replayAll();
        final CronofyResponse<DeleteEventResponse> result = cronofyClient.deleteEvent(request);
        getHelper().assertResultResponse(expectedResponse, result);
        verifyAll();
    }

    /**
     * When got unknown status code
     */
    @Test
    public void testDeleteEventScenario7() {
        resetAll();
        // test data
        final DeleteEventRequest request = getHelper().getDeleteEventRequest();
        final Response expectedResult = Response.status(1024).build();
        // expectations
        expect(client.target(BASE_PATH)).andReturn(webTarget);
        expect(webTarget.path(API_VERSION)).andReturn(webTarget);
        expect(webTarget.path(CALENDARS_PATH)).andReturn(webTarget);
        expect(webTarget.path(request.getCalendarId())).andReturn(webTarget);
        expect(webTarget.path(EVENTS)).andReturn(webTarget);
        expect(webTarget.queryParam("event_id", request.getEventId())).andReturn(webTarget);
        expect(webTarget.request(MediaType.APPLICATION_JSON_TYPE)).andReturn(builder);
        expect(builder.header(AUTH_HEADER_KEY, "Bearer " + request.getAccessToken())).andReturn(builder);
        expect(builder.delete()).andReturn(expectedResult);
        replayAll();
        try {
            cronofyClient.deleteEvent(request);
            fail("Exception should be thrown");
        } catch (final UnknownStatusCodeException ignore) {
            // Expected
            assertNotNull(ignore);
            assertEquals(request, ignore.getRequest());
        }
        verifyAll();
    }
    //endregion

    //region createNotificationChannel

    /**
     * With invalid arguments
     */
    @Test
    public void testCreateNotificationChannelScenario1() {
        resetAll();
        // test data
        // expectations
        replayAll();
        try {
            cronofyClient.createNotificationChannel(null);
            fail();
        } catch (final IllegalArgumentException ignore) {
        }
        try {
            cronofyClient.createNotificationChannel(new CreateNotificationChannelRequest());
            fail();
        } catch (final IllegalArgumentException ignore) {
        }
        verifyAll();
    }

    /**
     * General case
     */
    @Test
    public void testCreateNotificationChannelScenario2() {
        resetAll();
        // test data
        final CreateNotificationChannelRequest request = getHelper().getCreateNotificationChannelRequest();

        final CronofyResponse<CreateNotificationChannelResponse> expectedResponse = new CronofyResponse<>(
                new CreateNotificationChannelResponse(
                        getHelper().buildChannelModel()
                ));
        // expectations
        expect(client.target(BASE_PATH)).andReturn(webTarget);
        expect(webTarget.path(API_VERSION)).andReturn(webTarget);
        expect(webTarget.path(CHANNELS_PATH)).andReturn(webTarget);
        expect(webTarget.request(MediaType.APPLICATION_JSON_TYPE)).andReturn(builder);
        expect(builder.header(AUTH_HEADER_KEY, "Bearer " + request.getAccessToken())).andReturn(builder);
        expect(builder.post(Entity.entity(request, MediaType.APPLICATION_JSON_TYPE),
                new GenericType<CronofyResponse<CreateNotificationChannelResponse>>() {
                })).andReturn(expectedResponse);
        replayAll();
        final CronofyResponse<CreateNotificationChannelResponse> result = cronofyClient.createNotificationChannel(request);
        getHelper().assertResultResponse(expectedResponse, result);
        verifyAll();
    }

    /**
     * When not authorized exception has been thrown
     */
    @Test
    public void testCreateNotificationChannelScenario3() {
        resetAll();
        // test data
        final CreateNotificationChannelRequest request = getHelper().getCreateNotificationChannelRequest();
        final CronofyResponse<CreateNotificationChannelResponse> expectedResponse = new CronofyResponse<>(
                ErrorTypeModel.NOT_AUTHORIZED
        );
        // expectations
        expect(client.target(BASE_PATH)).andThrow(new NotAuthorizedException(new CronofyResponse<>()));
        replayAll();
        final CronofyResponse<CreateNotificationChannelResponse> result = cronofyClient.createNotificationChannel(request);
        getHelper().assertResultResponse(expectedResponse, result);
        verifyAll();
    }
    //endregion

    //region listNotificationChannels

    /**
     * With invalid arguments
     */
    @Test
    public void testListNotificationChannelsScenario1() {
        resetAll();
        // test data
        // expectations
        replayAll();
        try {
            cronofyClient.listNotificationChannels(null);
            fail();
        } catch (final IllegalArgumentException ignore) {
        }
        try {
            cronofyClient.listNotificationChannels(new ListNotificationChannelsRequest());
            fail();
        } catch (final IllegalArgumentException ignore) {
        }
        verifyAll();
    }

    @Test
    public void testListNotificationChannelsScenario2() {
        resetAll();
        // test data
        final ListNotificationChannelsRequest request = getHelper().getListNotificationChannelsRequest();

        final CronofyResponse<ListNotificationChannelsResponse> expectedResponse = new CronofyResponse<>(
                new ListNotificationChannelsResponse(
                        new ArrayList<>(Arrays.asList(getHelper().buildChannelModel(), getHelper().buildChannelModel()))
                )
        );
        // expectations
        expect(client.target(BASE_PATH)).andReturn(webTarget);
        expect(webTarget.path(API_VERSION)).andReturn(webTarget);
        expect(webTarget.path(CHANNELS_PATH)).andReturn(webTarget);
        expect(webTarget.request(MediaType.APPLICATION_JSON_TYPE)).andReturn(builder);
        expect(builder.header(AUTH_HEADER_KEY, "Bearer " + request.getAccessToken())).andReturn(builder);
        expect(builder.get(new GenericType<CronofyResponse<ListNotificationChannelsResponse>>() {
        })).andReturn(expectedResponse);
        replayAll();
        final CronofyResponse<ListNotificationChannelsResponse> result = cronofyClient.listNotificationChannels(request);
        getHelper().assertResultResponse(expectedResponse, result);
        verifyAll();
    }

    /**
     * When not authorized exception has been thrown
     */
    @Test
    public void testListNotificationChannelsScenario3() {
        resetAll();
        // test data
        final ListNotificationChannelsRequest request = getHelper().getListNotificationChannelsRequest();
        final CronofyResponse<ListNotificationChannelsResponse> expectedResponse = new CronofyResponse<>(
                ErrorTypeModel.NOT_AUTHORIZED
        );
        // expectations
        expect(client.target(BASE_PATH)).andThrow(new NotAuthorizedException(new CronofyResponse<>()));
        replayAll();
        final CronofyResponse<ListNotificationChannelsResponse> result = cronofyClient.listNotificationChannels(request);
        getHelper().assertResultResponse(expectedResponse, result);
        verifyAll();
    }
    //endregion

    //region closeNotificationChannel

    /**
     * With invalid arguments
     */
    @Test
    public void testCloseNotificationChannelScenario1() {
        resetAll();
        // test data
        // expectations
        replayAll();
        try {
            cronofyClient.closeNotificationChannel(null);
            fail();
        } catch (final IllegalArgumentException ignore) {
        }
        try {
            cronofyClient.closeNotificationChannel(new CloseNotificationChannelRequest());
            fail();
        } catch (final IllegalArgumentException ignore) {
        }
        verifyAll();
    }

    /**
     * General case
     */
    @Test
    public void testCloseNotificationChannelScenario2() {
        resetAll();
        // test data
        final CloseNotificationChannelRequest request = getHelper().getCloseNotificationChannelRequest();

        final CronofyResponse<CloseNotificationChannelResponse> expectedResponse = new CronofyResponse<>(
                new CloseNotificationChannelResponse()
        );
        final Response expectedResult = Response.status(202).build();
        // expectations
        expect(client.target(BASE_PATH)).andReturn(webTarget);
        expect(webTarget.path(API_VERSION)).andReturn(webTarget);
        expect(webTarget.path(CHANNELS_PATH)).andReturn(webTarget);
        expect(webTarget.path(request.getChannelId())).andReturn(webTarget);
        expect(webTarget.request(MediaType.APPLICATION_JSON_TYPE)).andReturn(builder);
        expect(builder.header(AUTH_HEADER_KEY, "Bearer " + request.getAccessToken())).andReturn(builder);
        expect(builder.delete()).andReturn(expectedResult);
        replayAll();
        final CronofyResponse<CloseNotificationChannelResponse> result = cronofyClient.closeNotificationChannel(request);
        assertNotNull(result);
        assertFalse(result.hasError());
        assertTrue(expectedResponse.getClass().isInstance(result));
        verifyAll();
    }

    /**
     * When not authorized
     */
    @Test
    public void testCloseNotificationChannelScenario3() {
        resetAll();
        // test data
        final CloseNotificationChannelRequest request = getHelper().getCloseNotificationChannelRequest();
        final CronofyResponse<CloseNotificationChannelResponse> expectedResponse = new CronofyResponse<>(
                ErrorTypeModel.NOT_AUTHORIZED
        );
        final Response expectedResult = Response.status(401).build();
        // expectations
        expect(client.target(BASE_PATH)).andReturn(webTarget);
        expect(webTarget.path(API_VERSION)).andReturn(webTarget);
        expect(webTarget.path(CHANNELS_PATH)).andReturn(webTarget);
        expect(webTarget.path(request.getChannelId())).andReturn(webTarget);
        expect(webTarget.request(MediaType.APPLICATION_JSON_TYPE)).andReturn(builder);
        expect(builder.header(AUTH_HEADER_KEY, "Bearer " + request.getAccessToken())).andReturn(builder);
        expect(builder.delete()).andReturn(expectedResult);
        replayAll();
        final CronofyResponse<CloseNotificationChannelResponse> result = cronofyClient.closeNotificationChannel(request);
        getHelper().assertResultResponse(expectedResponse, result);
        verifyAll();
    }
    //endregion

    //region accountInfo

    /**
     * With invalid arguments
     */
    @Test
    public void testAccountInfoScenario1() {
        resetAll();
        // test data
        // expectations
        replayAll();
        try {
            cronofyClient.accountInfo(null);
            fail();
        } catch (final IllegalArgumentException ignore) {
        }
        try {
            cronofyClient.accountInfo(new AccountInfoRequest());
            fail();
        } catch (final IllegalArgumentException ignore) {
        }
        verifyAll();
    }

    /**
     * General case
     */
    @Test
    public void testAccountInfoScenario2() {
        resetAll();
        // test data
        final AccountInfoRequest request = getHelper().getAccountInfoRequest();
        final CronofyResponse<AccountInfoResponse> expectedResponse = new CronofyResponse<>(
                new AccountInfoResponse(getHelper().buildAccountModel())
        );
        // expectations
        expect(client.target(BASE_PATH)).andReturn(webTarget);
        expect(webTarget.path(API_VERSION)).andReturn(webTarget);
        expect(webTarget.path(ACCOUNT_PATH)).andReturn(webTarget);
        expect(webTarget.request(MediaType.APPLICATION_JSON_TYPE)).andReturn(builder);
        expect(builder.header(AUTH_HEADER_KEY, "Bearer " + request.getAccessToken())).andReturn(builder);
        expect(builder.get(new GenericType<CronofyResponse<AccountInfoResponse>>() {
        })).andReturn(expectedResponse);
        replayAll();
        final CronofyResponse<AccountInfoResponse> result = cronofyClient.accountInfo(request);
        getHelper().assertResultResponse(expectedResponse, result);
        verifyAll();
    }

    /**
     * When not authorized exception has been thrown
     */
    @Test
    public void testAccountInfoScenario3() {
        resetAll();
        // test data
        final AccountInfoRequest request = getHelper().getAccountInfoRequest();
        final CronofyResponse<AccountInfoResponse> expectedResponse = new CronofyResponse<>(
                ErrorTypeModel.NOT_AUTHORIZED
        );
        // expectations
        expect(client.target(BASE_PATH)).andThrow(new NotAuthorizedException(new CronofyResponse<>()));
        replayAll();
        final CronofyResponse<AccountInfoResponse> result = cronofyClient.accountInfo(request);
        getHelper().assertResultResponse(expectedResponse, result);
        verifyAll();
    }
    //endregion

    //region profileInfo

    /**
     * With invalid arguments
     */
    @Test
    public void testProfileInfoScenario1() {
        resetAll();
        // test data
        // expectations
        replayAll();
        try {
            cronofyClient.profileInfo(null);
            fail();
        } catch (final IllegalArgumentException ignore) {
        }
        try {
            cronofyClient.profileInfo(new ProfileInformationRequest());
            fail();
        } catch (final IllegalArgumentException ignore) {
        }
        verifyAll();
    }

    /**
     * General case
     */
    @Test
    public void testProfileInfoScenario2() {
        resetAll();
        // test data
        final ProfileInformationRequest request = getHelper().getProfileInformationRequest();

        final CronofyResponse<ProfileInformationResponse> expectedResponse = new CronofyResponse<>(
                new ProfileInformationResponse(
                        new ArrayList<>(Arrays.asList(getHelper().buildProfileModel(), getHelper().buildProfileModel()))
                )
        );
        // expectations
        expect(client.target(BASE_PATH)).andReturn(webTarget);
        expect(webTarget.path(API_VERSION)).andReturn(webTarget);
        expect(webTarget.path(PROFILES_PATH)).andReturn(webTarget);
        expect(webTarget.request(MediaType.APPLICATION_JSON_TYPE)).andReturn(builder);
        expect(builder.header(AUTH_HEADER_KEY, "Bearer " + request.getAccessToken())).andReturn(builder);
        expect(builder.get(new GenericType<CronofyResponse<ProfileInformationResponse>>() {
        })).andReturn(expectedResponse);
        replayAll();
        final CronofyResponse<ProfileInformationResponse> result = cronofyClient.profileInfo(request);
        getHelper().assertResultResponse(expectedResponse, result);
        verifyAll();
    }

    /**
     * When not authorized exception has been thrown
     */
    @Test
    public void testProfileInfoScenario3() {
        resetAll();
        // test data
        final ProfileInformationRequest request = getHelper().getProfileInformationRequest();
        final CronofyResponse<ProfileInformationResponse> expectedResponse = new CronofyResponse<>(
                ErrorTypeModel.NOT_AUTHORIZED
        );
        // expectations
        expect(client.target(BASE_PATH)).andThrow(new NotAuthorizedException(new CronofyResponse<>()));
        replayAll();
        final CronofyResponse<ProfileInformationResponse> result = cronofyClient.profileInfo(request);
        getHelper().assertResultResponse(expectedResponse, result);
        verifyAll();
    }
    //endregion

    //endregion

}