package org.biacode.jcronofy.api.impl;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.biacode.jcronofy.api.client.CronofyClient;
import org.biacode.jcronofy.api.client.exception.UnknownStatusCodeException;
import org.biacode.jcronofy.api.client.impl.CronofyClientImpl;
import org.biacode.jcronofy.api.model.EventsPagesModel;
import org.biacode.jcronofy.api.model.TokenTypeModel;
import org.biacode.jcronofy.api.model.common.CronofyResponse;
import org.biacode.jcronofy.api.model.common.ErrorTypeModel;
import org.biacode.jcronofy.api.model.request.*;
import org.biacode.jcronofy.api.model.response.*;
import org.biacode.jcronofy.api.test.AbstractCronofyUniTest;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.client.*;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;

import static org.easymock.EasyMock.*;
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

    //region calendarIdParameterEncoding
    @Test
    public void testCalendarIdParameterEncoding() {
        // test data
        final Client client = ClientBuilder.newBuilder().register(JacksonJsonProvider.class).build();
        final WebTarget eventsTarget = client.target("https://api.cronofy.com/")
                .path("v1")
                .path("events");
        // expectations
        final WebTarget noCalendars = eventsTarget.queryParam("calendar_ids[]", null);
        final WebTarget emptyCalendars = eventsTarget.queryParam("calendar_ids[]", new String[]{});
        final WebTarget twoCalendars1 = eventsTarget.queryParam("calendar_ids[]", new String[]{"a", "b"});
        final WebTarget twoCalendars2 = eventsTarget.queryParam("calendar_ids[]", "a", "b");
        // test scenario
        assertEquals("https://api.cronofy.com/v1/events", noCalendars.getUri().toString());
        assertEquals("https://api.cronofy.com/v1/events", emptyCalendars.getUri().toString());
        assertEquals("https://api.cronofy.com/v1/events?calendar_ids%5B%5D=a&calendar_ids%5B%5D=b", twoCalendars1.getUri().toString());
        assertEquals("https://api.cronofy.com/v1/events?calendar_ids%5B%5D=a&calendar_ids%5B%5D=b", twoCalendars2.getUri().toString());
    }
    //endregion

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
                        UUID.randomUUID().toString()
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

    /**
     * When unknown status code
     */
    @Test
    public void testRevokeAccessTokenScenario4() {
        resetAll();
        // test data
        final RevokeAccessTokenRequest request = getHelper().getRevokeAccessTokenRequest();
        final Response expectedResult = Response.status(1024).build();
        // expectations
        expect(client.target(BASE_PATH)).andReturn(webTarget);
        expect(webTarget.path("oauth")).andReturn(webTarget);
        expect(webTarget.path("token")).andReturn(webTarget);
        expect(webTarget.path("revoke")).andReturn(webTarget);
        expect(webTarget.request(MediaType.APPLICATION_JSON_TYPE)).andReturn(builder);
        expect(builder.post(Entity.entity(request, MediaType.APPLICATION_JSON_TYPE))).andReturn(expectedResult);
        replayAll();
        try {
            cronofyClient.revokeAccessToken(request);
            fail("Exception should be thrown");
        } catch (final UnknownStatusCodeException ignore) {
            // Expected
            assertNotNull(ignore);
            assertEquals(request, ignore.getRequest());
        }
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
        final ListCalendarsRequest request = getHelper().getListCalendarsRequest();

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
        final ListCalendarsRequest request = getHelper().getListCalendarsRequest();

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
        final ListCalendarsRequest request = getHelper().getListCalendarsRequest();
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

    //region createCalendar

    /**
     * With invalid arguments
     */
    @Test
    public void testCreateCalendarScenario1() {
        resetAll();
        // test data
        // expectations
        replayAll();
        try {
            cronofyClient.createCalendar(null);
            fail();
        } catch (final IllegalArgumentException ignore) {
        }
        try {
            cronofyClient.createCalendar(new CreateCalendarRequest());
            fail();
        } catch (final IllegalArgumentException ignore) {
        }
        verifyAll();
    }

    /**
     * General case
     */
    @Test
    public void testCreateCalendarScenario() {
        resetAll();
        // test data
        final CreateCalendarRequest request = getHelper().getCreateCalendarRequest();
        final CronofyResponse<CreateCalendarResponse> expectedResponse = new CronofyResponse<>(
                new CreateCalendarResponse(getHelper().buildCalendarModel())
        );
        final Response expectedResult = Response.status(202).build();
        // expectations
        expect(client.target(BASE_PATH)).andReturn(webTarget);
        expect(webTarget.path(API_VERSION)).andReturn(webTarget);
        expect(webTarget.path(CALENDARS_PATH)).andReturn(webTarget);
        expect(webTarget.request(MediaType.APPLICATION_JSON_TYPE)).andReturn(builder);
        expect(builder.header(AUTH_HEADER_KEY, "Bearer " + request.getAccessToken())).andReturn(builder);
        expect(builder.post(Entity.entity(request, MediaType.APPLICATION_JSON_TYPE))).andReturn(expectedResult);
        replayAll();
        final CronofyResponse<CreateCalendarResponse> result = cronofyClient.createCalendar(request);
        getHelper().assertResultResponse(expectedResponse, result);
        verifyAll();
    }

    /**
     * When not authorized exception has been thrown
     */
    @Test
    public void testCreateCalendarScenario3() {
        resetAll();
        // test data
        final CreateCalendarRequest request = getHelper().getCreateCalendarRequest();

        final CronofyResponse<CreateCalendarResponse> expectedResponse = new CronofyResponse<>(
                ErrorTypeModel.NOT_AUTHORIZED
        );
        final Response expectedResult = Response.status(401).build();
        // expectations
        expect(client.target(BASE_PATH)).andReturn(webTarget);
        expect(webTarget.path(API_VERSION)).andReturn(webTarget);
        expect(webTarget.path(CALENDARS_PATH)).andReturn(webTarget);
        expect(webTarget.request(MediaType.APPLICATION_JSON_TYPE)).andReturn(builder);
        expect(builder.header(AUTH_HEADER_KEY, "Bearer " + request.getAccessToken())).andReturn(builder);
        expect(builder.post(Entity.entity(request, MediaType.APPLICATION_JSON_TYPE))).andReturn(expectedResult);
        replayAll();
        final CronofyResponse<CreateCalendarResponse> result = cronofyClient.createCalendar(request);
        getHelper().assertResultResponse(expectedResponse, result);
        verifyAll();
    }

    /**
     * When unprocessable exception has been thrown
     */
    @Test
    public void testCreateCalendarScenario4() {
        resetAll();
        // test data
        final CreateCalendarRequest request = getHelper().getCreateCalendarRequest();

        final CronofyResponse<CreateCalendarResponse> expectedResponse = new CronofyResponse<>(
                ErrorTypeModel.UNPROCESSABLE
        );
        final Response expectedResult = Response.status(422).build();
        // expectations
        expect(client.target(BASE_PATH)).andReturn(webTarget);
        expect(webTarget.path(API_VERSION)).andReturn(webTarget);
        expect(webTarget.path(CALENDARS_PATH)).andReturn(webTarget);
        expect(webTarget.request(MediaType.APPLICATION_JSON_TYPE)).andReturn(builder);
        expect(builder.header(AUTH_HEADER_KEY, "Bearer " + request.getAccessToken())).andReturn(builder);
        expect(builder.post(Entity.entity(request, MediaType.APPLICATION_JSON_TYPE))).andReturn(expectedResult);
        replayAll();
        final CronofyResponse<CreateCalendarResponse> result = cronofyClient.createCalendar(request);
        getHelper().assertResultResponse(expectedResponse, result);
        verifyAll();
    }

    /**
     * When forbidden exception has been thrown
     */
    @Test
    public void testCreateCalendarScenario5() {
        resetAll();
        // test data
        final CreateCalendarRequest request = getHelper().getCreateCalendarRequest();

        final CronofyResponse<CreateCalendarResponse> expectedResponse = new CronofyResponse<>(
                ErrorTypeModel.FORBIDDEN
        );
        final Response expectedResult = Response.status(403).build();
        // expectations
        expect(client.target(BASE_PATH)).andReturn(webTarget);
        expect(webTarget.path(API_VERSION)).andReturn(webTarget);
        expect(webTarget.path(CALENDARS_PATH)).andReturn(webTarget);
        expect(webTarget.request(MediaType.APPLICATION_JSON_TYPE)).andReturn(builder);
        expect(builder.header(AUTH_HEADER_KEY, "Bearer " + request.getAccessToken())).andReturn(builder);
        expect(builder.post(Entity.entity(request, MediaType.APPLICATION_JSON_TYPE))).andReturn(expectedResult);
        replayAll();
        final CronofyResponse<CreateCalendarResponse> result = cronofyClient.createCalendar(request);
        getHelper().assertResultResponse(expectedResponse, result);
        verifyAll();
    }

    /**
     * When locked exception has been thrown
     */
    @Test
    public void testCreateCalendarScenario6() {
        resetAll();
        // test data
        final CreateCalendarRequest request = getHelper().getCreateCalendarRequest();

        final CronofyResponse<CreateCalendarResponse> expectedResponse = new CronofyResponse<>(
                ErrorTypeModel.LOCKED
        );
        final Response expectedResult = Response.status(423).build();
        // expectations
        expect(client.target(BASE_PATH)).andReturn(webTarget);
        expect(webTarget.path(API_VERSION)).andReturn(webTarget);
        expect(webTarget.path(CALENDARS_PATH)).andReturn(webTarget);
        expect(webTarget.request(MediaType.APPLICATION_JSON_TYPE)).andReturn(builder);
        expect(builder.header(AUTH_HEADER_KEY, "Bearer " + request.getAccessToken())).andReturn(builder);
        expect(builder.post(Entity.entity(request, MediaType.APPLICATION_JSON_TYPE))).andReturn(expectedResult);
        replayAll();
        final CronofyResponse<CreateCalendarResponse> result = cronofyClient.createCalendar(request);
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
     * When does not have pages
     */
    @Test
    public void testReadEventsScenario2() {
        resetAll();
        // test data
        final String calendarId = UUID.randomUUID().toString();
        final ReadEventsRequest request = getHelper().getReadEventsRequestWithCalendarIds(Collections.singletonList(calendarId));
        final EventsPagesModel eventsPagesModel = getHelper().buildEventsPagesModel();
        eventsPagesModel.setNextPage(null);

        final CronofyResponse<ReadEventsResponse> expectedResponse = new CronofyResponse<>(new ReadEventsResponse(
                eventsPagesModel, new ArrayList<>(Arrays.asList(getHelper().buildEventModel(), getHelper().buildEventModel()))
        ));
        // expectations
        expect(client.target(BASE_PATH)).andReturn(webTarget);
        expect(webTarget.path(API_VERSION)).andReturn(webTarget);
        expect(webTarget.path(EVENTS)).andReturn(webTarget);
        expect(webTarget.queryParam(eq("from"), eq(null))).andReturn(webTarget);
        expect(webTarget.queryParam(eq("to"), eq(null))).andReturn(webTarget);
        expect(webTarget.queryParam(eq("last_modified"), eq(null))).andReturn(webTarget);
        expect(webTarget.queryParam("tzid", request.getTzId())).andReturn(webTarget);
        expect(webTarget.queryParam("include_deleted", request.isIncludeDeleted())).andReturn(webTarget);
        expect(webTarget.queryParam("include_moved", request.isIncludeMoved())).andReturn(webTarget);
        expect(webTarget.queryParam("include_managed", request.isIncludeManaged())).andReturn(webTarget);
        expect(webTarget.queryParam("only_managed", request.isOnlyManaged())).andReturn(webTarget);
        expect(webTarget.queryParam("calendar_ids[]", calendarId)).andReturn(webTarget);
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
     * When has pages
     */
    @Test
    public void testReadEventsScenario3() {
        resetAll();
        // test data
        final String calendarId = UUID.randomUUID().toString();
        final ReadEventsRequest request = getHelper().getReadEventsRequestWithCalendarIds(Collections.singletonList(calendarId));
        final EventsPagesModel eventsPagesModel1 = getHelper().buildEventsPagesModel();
        final EventsPagesModel eventsPagesModel2 = getHelper().buildEventsPagesModel();
        eventsPagesModel2.setNextPage(null);

        final CronofyResponse<ReadEventsResponse> expectedResponse = new CronofyResponse<>(new ReadEventsResponse(
                eventsPagesModel1,
                new ArrayList<>(Arrays.asList(getHelper().buildEventModel(), getHelper().buildEventModel()))
        ));

        final CronofyResponse<ReadEventsResponse> pageResult1 = new CronofyResponse<>(new ReadEventsResponse(
                eventsPagesModel2,
                new ArrayList<>(Arrays.asList(getHelper().buildEventModel(), getHelper().buildEventModel()))
        ));

        final CronofyResponse<ReadEventsResponse> finalResponse = new CronofyResponse<>(new ReadEventsResponse(
                eventsPagesModel2,
                new ArrayList<>()
        ));
        finalResponse.getResponse().getEvents().addAll(expectedResponse.getResponse().getEvents());
        finalResponse.getResponse().getEvents().addAll(pageResult1.getResponse().getEvents());
        // expectations
        expect(client.target(BASE_PATH)).andReturn(webTarget);
        expect(webTarget.path(API_VERSION)).andReturn(webTarget);
        expect(webTarget.path(EVENTS)).andReturn(webTarget);
        expect(webTarget.queryParam(eq("from"), eq(null))).andReturn(webTarget);
        expect(webTarget.queryParam(eq("to"), eq(null))).andReturn(webTarget);
        expect(webTarget.queryParam(eq("last_modified"), eq(null))).andReturn(webTarget);
        expect(webTarget.queryParam("tzid", request.getTzId())).andReturn(webTarget);
        expect(webTarget.queryParam("include_deleted", request.isIncludeDeleted())).andReturn(webTarget);
        expect(webTarget.queryParam("include_moved", request.isIncludeMoved())).andReturn(webTarget);
        expect(webTarget.queryParam("include_managed", request.isIncludeManaged())).andReturn(webTarget);
        expect(webTarget.queryParam("only_managed", request.isOnlyManaged())).andReturn(webTarget);
        expect(webTarget.queryParam("calendar_ids[]", calendarId)).andReturn(webTarget);
        expect(webTarget.queryParam("localized_times", request.isLocalizedTimes())).andReturn(webTarget);
        expect(webTarget.request(MediaType.APPLICATION_JSON_TYPE)).andReturn(builder);
        expect(builder.header(AUTH_HEADER_KEY, "Bearer " + request.getAccessToken())).andReturn(builder);
        expect(builder.get(new GenericType<CronofyResponse<ReadEventsResponse>>() {
        })).andReturn(expectedResponse);
        // first iteration
        expect(client.target(eventsPagesModel1.getNextPage())).andReturn(webTarget);
        expect(webTarget.request(MediaType.APPLICATION_JSON_TYPE)).andReturn(builder);
        expect(builder.header(AUTH_HEADER_KEY, "Bearer " + request.getAccessToken())).andReturn(builder);
        expect(builder.get(new GenericType<CronofyResponse<ReadEventsResponse>>() {
        })).andReturn(pageResult1);
        replayAll();
        final CronofyResponse<ReadEventsResponse> result = cronofyClient.readEvents(request);
        getHelper().assertResultResponse(finalResponse, result);
        verifyAll();
    }

    /**
     * When not authorized exception has been thrown
     */
    @Test
    public void testReadEventsScenario4() {
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
    public void testReadEventsScenario5() {
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

    /**
     * When there is multiple calendars
     */
    @Test
    public void testReadEventsScenario7() {
        resetAll();
        // test data
        final String calendarId1 = "cal_123_456";
        final String calendarId2 = "cal_456_789";
        final ReadEventsRequest request = getHelper().getReadEventsRequestWithCalendarIds(Arrays.asList(calendarId1, calendarId2));
        final EventsPagesModel eventsPagesModel = getHelper().buildEventsPagesModel();
        eventsPagesModel.setNextPage(null);

        final CronofyResponse<ReadEventsResponse> expectedResponse = new CronofyResponse<>(new ReadEventsResponse(
                eventsPagesModel, new ArrayList<>(Arrays.asList(getHelper().buildEventModel(), getHelper().buildEventModel()))
        ));
        // expectations
        expect(client.target(BASE_PATH)).andReturn(webTarget);
        expect(webTarget.path(API_VERSION)).andReturn(webTarget);
        expect(webTarget.path(EVENTS)).andReturn(webTarget);
        expect(webTarget.queryParam(eq("from"), eq(null))).andReturn(webTarget);
        expect(webTarget.queryParam(eq("to"), eq(null))).andReturn(webTarget);
        expect(webTarget.queryParam(eq("last_modified"), eq(null))).andReturn(webTarget);
        expect(webTarget.queryParam("tzid", request.getTzId())).andReturn(webTarget);
        expect(webTarget.queryParam("include_deleted", request.isIncludeDeleted())).andReturn(webTarget);
        expect(webTarget.queryParam("include_moved", request.isIncludeMoved())).andReturn(webTarget);
        expect(webTarget.queryParam("include_managed", request.isIncludeManaged())).andReturn(webTarget);
        expect(webTarget.queryParam("only_managed", request.isOnlyManaged())).andReturn(webTarget);
        expect(webTarget.queryParam("calendar_ids[]", new String[]{calendarId1, calendarId2})).andReturn(webTarget);
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

    /**
     * General case
     */
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
        expect(webTarget.queryParam(eq("from"), eq(null))).andReturn(webTarget);
        expect(webTarget.queryParam(eq("to"), eq(null))).andReturn(webTarget);
        expect(webTarget.queryParam("tzid", request.getTzId())).andReturn(webTarget);
        expect(webTarget.queryParam("include_managed", request.getIncludeManaged())).andReturn(webTarget);
        expect(webTarget.queryParam("calendar_ids[]", new String[0])).andReturn(webTarget);
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

    /**
     * When client error exception has been thrown
     */
    @Test
    public void testFreeBusyScenario5() {
        resetAll();
        // test data
        final FreeBusyRequest request = getHelper().getFreeBusyRequest();
        final CronofyResponse<FreeBusyResponse> expectedResponse = new CronofyResponse<>(
                ErrorTypeModel.UNPROCESSABLE
        );
        // expectations
        expect(client.target(BASE_PATH)).andThrow(new ClientErrorException(Response.Status.CONFLICT));
        replayAll();
        final CronofyResponse<FreeBusyResponse> result = cronofyClient.freeBusy(request);
        getHelper().assertResultResponse(expectedResponse, result);
        verifyAll();
    }

    /**
     * Calendar there is multiple calendars (with varargs)
     */
    @Test
    public void testFreeBusyScenario6() {
        resetAll();
        // test data
        final String calendarId1 = "cal_123_456";
        final String calendarId2 = "cal_456_789";
        final FreeBusyRequest request = getHelper().getFreeBusyRequestWithCalendarIds(Arrays.asList(calendarId1, calendarId2));

        final CronofyResponse<FreeBusyResponse> expectedResponse = new CronofyResponse<>(new FreeBusyResponse(
                getHelper().buildEventsPagesModel(),
                new ArrayList<>(Arrays.asList(getHelper().buildFreeBusyModel(), getHelper().buildFreeBusyModel()))
        ));
        // expectations
        expect(client.target(BASE_PATH)).andReturn(webTarget);
        expect(webTarget.path(API_VERSION)).andReturn(webTarget);
        expect(webTarget.path("free_busy")).andReturn(webTarget);
        expect(webTarget.queryParam(eq("from"), eq(null))).andReturn(webTarget);
        expect(webTarget.queryParam(eq("to"), eq(null))).andReturn(webTarget);
        expect(webTarget.queryParam("tzid", request.getTzId())).andReturn(webTarget);
        expect(webTarget.queryParam("include_managed", request.getIncludeManaged())).andReturn(webTarget);
        expect(webTarget.queryParam("calendar_ids[]", calendarId1, calendarId2)).andReturn(webTarget);
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

    /**
     * When unknown status code
     */
    @Test
    public void testCreateOrUpdateEventScenario7() {
        resetAll();
        // test data
        final CreateOrUpdateEventRequest request = getHelper().getCreateOrUpdateEventRequest();
        final Response expectedResult = Response.status(1024).build();
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
        try {
            cronofyClient.createOrUpdateEvent(request);
            fail("Exception should be thrown");
        } catch (final UnknownStatusCodeException ignore) {
            // Expecetd
            assertNotNull(ignore);
            assertEquals(request, ignore.getRequest());
        }
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

    /**
     * When client error exception has been thrown
     */
    @Test
    public void testCreateNotificationChannelScenario4() {
        resetAll();
        // test data
        final CreateNotificationChannelRequest request = getHelper().getCreateNotificationChannelRequest();
        final CronofyResponse<CreateNotificationChannelResponse> expectedResponse = new CronofyResponse<>(
                ErrorTypeModel.UNPROCESSABLE
        );
        // expectations
        expect(client.target(BASE_PATH)).andThrow(new ClientErrorException(Response.Status.CONFLICT));
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

    /**
     * When unknown status code
     */
    @Test
    public void testCloseNotificationChannelScenario4() {
        resetAll();
        // test data
        final CloseNotificationChannelRequest request = getHelper().getCloseNotificationChannelRequest();
        final Response expectedResult = Response.status(1024).build();
        // expectations
        expect(client.target(BASE_PATH)).andReturn(webTarget);
        expect(webTarget.path(API_VERSION)).andReturn(webTarget);
        expect(webTarget.path(CHANNELS_PATH)).andReturn(webTarget);
        expect(webTarget.path(request.getChannelId())).andReturn(webTarget);
        expect(webTarget.request(MediaType.APPLICATION_JSON_TYPE)).andReturn(builder);
        expect(builder.header(AUTH_HEADER_KEY, "Bearer " + request.getAccessToken())).andReturn(builder);
        expect(builder.delete()).andReturn(expectedResult);
        replayAll();
        try {
            cronofyClient.closeNotificationChannel(request);
            fail("Exception should be thrown");
        } catch (final UnknownStatusCodeException ignore) {
            // Expected
        }
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