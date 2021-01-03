package org.biacode.jcronofy.api.client.impl;

import org.apache.commons.lang3.StringUtils;
import org.biacode.jcronofy.api.client.AbstractCronofyClient;
import org.biacode.jcronofy.api.client.CronofyClient;
import org.biacode.jcronofy.api.client.exception.UnknownStatusCodeException;
import org.biacode.jcronofy.api.model.common.*;
import org.biacode.jcronofy.api.model.request.*;
import org.biacode.jcronofy.api.model.response.*;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.List;

/**
 * User: Arthur Asatryan
 * Date: 10/5/16
 * Time: 11:44 AM
 */
@SuppressWarnings({"squid:S1075"})
public class CronofyClientImpl extends AbstractCronofyClient implements CronofyClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(CronofyClientImpl.class);

    //region Constants
    private static final String DEFAULT_BASE_PATH = "https://api.cronofy.com";

    private static final String AUTH_HEADER_KEY = "Authorization";

    private static final String CALENDARS_PATH = "calendars";

    private static final String CHANNELS_PATH = "channels";

    private static final String USER_PATH = "userinfo";

    private static final String PROFILES_PATH = "profiles";

    private static final String ACCOUNT_PATH = "account";

    private static final String EVENTS_PATH = "events";

    private static final String ELEMENT_TOKENS_PATH = "element_tokens";

    private static final String AVAILABILITY_PATH = "availability";

    private static final String OAUTH_PATH = "oauth";

    private static final String TOKEN_PAH = "token";

    private static final String API_VERSION = "v1";

    private static final String REVOKE = "revoke";

    private static final String QUERY_PARAM_DATE_FORMAT = "YYYY-MM-dd";

    private static final String QUERY_PARAM_ISO8601_FORMAT = "YYYY-MM-dd'T'HH:mm:ss'Z'";

    private static final int START_DATE_DAY_OFFSET = 0;

    private static final int END_DATE_DAY_OFFSET = 1;

    private final String basePath;

    //region Exception messages
    private static final String UNKNOWN_STATUS_CODE_EXCEPTION_MSG = "Got an unknown status code - {} while processing request - {}";

    private static final String NOT_AUTHORIZED_EXCEPTION_MSG = "Not authorized exception - {} occur while processing request - {}";

    private static final String CLIENT_ERROR_EXCEPTION_MSG = "Client error exception - {} occur while processing request - {}";

    private static final String BAD_REQUEST_EXCEPTION_MSG = "Bad request exception - {} occur while processing request - {}";

    private static final String FORBIDDEN_EXCEPTION_MSG = "Forbidden exception - {} occur while processing request - {}";

    private static final String NOT_FOUND_EXCEPTION_MSG = "Not found exception - {} occur while processing request - {}";
    //endregion

    //endregion

    //region Constructors
    public CronofyClientImpl(final Client client) {
        this(client, DEFAULT_BASE_PATH);
    }

    public CronofyClientImpl(final Client client, final String basePath) {
        super(client);
        this.basePath = basePath;
        LOGGER.debug("Initializing cronofy client");

    }
    //endregion

    //region Public methods
    @Override
    public CronofyResponse<GetAccessTokenResponse> getAccessToken(final GetAccessTokenRequest request) {
        assertCronofyRequest(request);
        try {
            return getClient()
                    .target(basePath)
                    .path(OAUTH_PATH)
                    .path(TOKEN_PAH)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(request, MediaType.APPLICATION_JSON_TYPE),
                            new GenericType<CronofyResponse<GetAccessTokenResponse>>() {
                            }
                    );
        } catch (final BadRequestException ignore) {
            LOGGER.warn(BAD_REQUEST_EXCEPTION_MSG, ignore, request);
            return new CronofyResponse<>(ErrorTypeModel.BAD_REQUEST);
        }
    }

    @Override
    public CronofyResponse<UpdateAccessTokenResponse> updateAccessToken(final UpdateAccessTokenRequest request) {
        assertCronofyRequest(request);
        try {
            return getClient()
                    .target(basePath)
                    .path(OAUTH_PATH)
                    .path(TOKEN_PAH)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(request, MediaType.APPLICATION_JSON_TYPE),
                            new GenericType<CronofyResponse<UpdateAccessTokenResponse>>() {
                            }
                    );
        } catch (final BadRequestException ex) {
            LOGGER.warn(BAD_REQUEST_EXCEPTION_MSG, ex, request);
            return new CronofyResponse<>(ErrorTypeModel.BAD_REQUEST);
        }
    }

    @SuppressWarnings({"squid:MethodCyclomaticComplexity", "squid:S1192"})
    @Override
    public CronofyResponse<RevokeAccessTokenResponse> revokeAccessToken(final RevokeAccessTokenRequest request) {
        assertCronofyRequest(request);
        final CronofyResponse<RevokeAccessTokenResponse> response = new CronofyResponse<>();
        final Response result = getClient()
                .target(basePath)
                .path(OAUTH_PATH)
                .path(TOKEN_PAH)
                .path(REVOKE)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.entity(request, MediaType.APPLICATION_JSON_TYPE));
        final int statusCode = result.getStatus();
        switch (statusCode) {
            case 200:
                response.setResponse(new RevokeAccessTokenResponse());
                break;
            case 400:
                response.setError(ErrorTypeModel.BAD_REQUEST);
                break;
            default:
                LOGGER.error(UNKNOWN_STATUS_CODE_EXCEPTION_MSG, statusCode, request);
                throw new UnknownStatusCodeException("Got an unknown status code - " + statusCode + " while processing request.", request);
        }
        return response;
    }

    @Override
    public CronofyResponse<ListCalendarsResponse> listCalendars(final ListCalendarsRequest request) {
        assertCronofyRequest(request);
        try {
            return getClient()
                    .target(basePath)
                    .path(API_VERSION)
                    .path(CALENDARS_PATH)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .header(AUTH_HEADER_KEY, getAccessTokenFromRequest(request))
                    .get(new GenericType<CronofyResponse<ListCalendarsResponse>>() {
                    });
        } catch (final NotAuthorizedException ex) {
            LOGGER.warn(NOT_AUTHORIZED_EXCEPTION_MSG, ex, request);
            return new CronofyResponse<>(ErrorTypeModel.NOT_AUTHORIZED);
        } catch (final ClientErrorException ex) {
            LOGGER.warn(CLIENT_ERROR_EXCEPTION_MSG, ex, request);
            return new CronofyResponse<>(ErrorTypeModel.UNPROCESSABLE);
        }
    }

    @Override
    public CronofyResponse<CreateCalendarResponse> createCalendar(final CreateCalendarRequest request) {
        assertCronofyRequest(request);
        final CronofyResponse<CreateCalendarResponse> response = new CronofyResponse<>();
        final Response result = getClient()
                .target(basePath)
                .path(API_VERSION)
                .path(CALENDARS_PATH)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .header(AUTH_HEADER_KEY, getAccessTokenFromRequest(request))
                .post(Entity.entity(request, MediaType.APPLICATION_JSON_TYPE)
                );
        processStatusCode(request, response, result.getStatus());
        return response;
    }

    @Override
    public CronofyResponse<ReadEventsResponse> readEvents(final ReadEventsRequest request) {
        assertCronofyRequest(request);
        try {
            final CronofyResponse<ReadEventsResponse> result = getClient()
                    .target(basePath)
                    .path(API_VERSION)
                    .path(EVENTS_PATH)
                    .queryParam("from", getQueryParamFromDate(request.getFrom(), START_DATE_DAY_OFFSET))
                    .queryParam("to", getQueryParamFromDate(request.getTo(), END_DATE_DAY_OFFSET))
                    .queryParam("last_modified", convertToISO8601(request.getLastModified()))
                    .queryParam("tzid", request.getTzId())
                    .queryParam("include_deleted", request.isIncludeDeleted())
                    .queryParam("include_moved", request.isIncludeMoved())
                    .queryParam("include_managed", request.isIncludeManaged())
                    .queryParam("only_managed", request.isOnlyManaged())
                    .queryParam("calendar_ids[]", getCalendarIdsArray(request.getCalendarIds()))
                    .queryParam("localized_times", request.isLocalizedTimes())
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .header(AUTH_HEADER_KEY, getAccessTokenFromRequest(request))
                    .get(new GenericType<CronofyResponse<ReadEventsResponse>>() {
                    });
            final CronofyResponse<ReadEventsResponse> response = new CronofyResponse<>(new ReadEventsResponse(
                    result.getResponse().getPages(),
                    result.getResponse().getEvents()
            ));
            String nextPage = result.getResponse().getPages().getNextPage();
            while (!StringUtils.isBlank(nextPage)) {
                final CronofyResponse<ReadEventsResponse> pageResult = getClient()
                        .target(nextPage)
                        .request(MediaType.APPLICATION_JSON_TYPE)
                        .header(AUTH_HEADER_KEY, getAccessTokenFromRequest(request))
                        .get(new GenericType<CronofyResponse<ReadEventsResponse>>() {
                        });
                response.getResponse().getEvents().addAll(pageResult.getResponse().getEvents());
                response.getResponse().setPages(pageResult.getResponse().getPages());
                nextPage = pageResult.getResponse().getPages().getNextPage();
            }
            return response;
        } catch (final NotAuthorizedException ex) {
            LOGGER.warn(NOT_AUTHORIZED_EXCEPTION_MSG, ex, request);
            return new CronofyResponse<>(ErrorTypeModel.NOT_AUTHORIZED);
        } catch (final ForbiddenException ex) {
            LOGGER.warn(FORBIDDEN_EXCEPTION_MSG, ex, request);
            return new CronofyResponse<>(ErrorTypeModel.FORBIDDEN);
        } catch (final ClientErrorException ex) {
            LOGGER.warn(CLIENT_ERROR_EXCEPTION_MSG, ex, request);
            return new CronofyResponse<>(ErrorTypeModel.UNPROCESSABLE);
        }
    }

    @Override
    public CronofyResponse<FreeBusyResponse> freeBusy(final FreeBusyRequest request) {
        assertCronofyRequest(request);
        try {
            return getClient()
                    .target(basePath)
                    .path(API_VERSION)
                    .path("free_busy")
                    .queryParam("from", getQueryParamFromDate(request.getFrom(), START_DATE_DAY_OFFSET))
                    .queryParam("to", getQueryParamFromDate(request.getTo(), END_DATE_DAY_OFFSET))
                    .queryParam("tzid", request.getTzId())
                    .queryParam("include_managed", request.getIncludeManaged())
                    .queryParam("calendar_ids[]", getCalendarIdsArray(request.getCalendarIds()))
                    .queryParam("localized_times", request.getLocalizedTimes())
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .header(AUTH_HEADER_KEY, getAccessTokenFromRequest(request))
                    .get(new GenericType<CronofyResponse<FreeBusyResponse>>() {
                    });
        } catch (final NotAuthorizedException ex) {
            LOGGER.warn(NOT_AUTHORIZED_EXCEPTION_MSG, ex, request);
            return new CronofyResponse<>(ErrorTypeModel.NOT_AUTHORIZED);
        } catch (final ForbiddenException ex) {
            LOGGER.warn(FORBIDDEN_EXCEPTION_MSG, ex, request);
            return new CronofyResponse<>(ErrorTypeModel.FORBIDDEN);
        } catch (final ClientErrorException ex) {
            LOGGER.warn(CLIENT_ERROR_EXCEPTION_MSG, ex, request);
            return new CronofyResponse<>(ErrorTypeModel.UNPROCESSABLE);
        }
    }

    @SuppressWarnings({"squid:MethodCyclomaticComplexity", "squid:S1192"})
    @Override
    public CronofyResponse<CreateOrUpdateEventResponse> createOrUpdateEvent(final CreateOrUpdateEventRequest request) {
        assertCronofyRequest(request);
        try {
        return getClient()
                .target(basePath)
                .path(API_VERSION)
                .path(CALENDARS_PATH)
                .path(request.getCalendarId())
                .path(EVENTS_PATH)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .header(AUTH_HEADER_KEY, getAccessTokenFromRequest(request))
                .post(Entity.entity(request, MediaType.APPLICATION_JSON_TYPE),
                        new GenericType<CronofyResponse<CreateOrUpdateEventResponse>>(){}
                );
        } catch (final NotAuthorizedException ex) {
            LOGGER.warn(NOT_AUTHORIZED_EXCEPTION_MSG, ex, request);
            return new CronofyResponse<>(ErrorTypeModel.NOT_AUTHORIZED);
        } catch (final ForbiddenException ex) {
            LOGGER.warn(FORBIDDEN_EXCEPTION_MSG, ex, request);
            return new CronofyResponse<>(ErrorTypeModel.FORBIDDEN);
        }  catch (final NotFoundException ex) {
            LOGGER.warn(NOT_FOUND_EXCEPTION_MSG, ex, request);
            return new CronofyResponse<>(ErrorTypeModel.NOT_FOUND);
        }catch (final ClientErrorException ex) {
            LOGGER.warn(CLIENT_ERROR_EXCEPTION_MSG, ex, request);
            return new CronofyResponse<>(ErrorTypeModel.UNPROCESSABLE);
        }
        // TODO: 1/3/2021 add Notfound exception when
    }

    @SuppressWarnings({"squid:MethodCyclomaticComplexity", "squid:S1192"})
    @Override
    public CronofyResponse<DeleteEventResponse> deleteEvent(final DeleteEventRequest request) {
        assertCronofyRequest(request);
        final CronofyResponse<DeleteEventResponse> response = new CronofyResponse<>();
        final Response result = getClient()
                .target(basePath)
                .path(API_VERSION)
                .path(CALENDARS_PATH)
                .path(request.getCalendarId())
                .path(EVENTS_PATH)
                .queryParam("event_id", request.getEventId())
                .request(MediaType.APPLICATION_JSON_TYPE)
                .header(AUTH_HEADER_KEY, getAccessTokenFromRequest(request))
                .delete();
        processStatusCode(request, response, result.getStatus());
        return response;
    }

    @Override
    public CronofyResponse<BulkDeleteEventsResponse> bulkDeleteEvents(final BulkDeleteEventsRequest request) {
        assertCronofyRequest(request);
        final CronofyResponse<BulkDeleteEventsResponse> response = new CronofyResponse<>();
        final Response result = getClient()
                .target(basePath)
                .path(API_VERSION)
                .path(EVENTS_PATH)
                .queryParam("delete_all", request.getDeleteAll())
                .queryParam("calendar_ids[]", getCalendarIdsArray(request.getCalendarIds()))
                .request(MediaType.APPLICATION_JSON_TYPE)
                .header(AUTH_HEADER_KEY, getAccessTokenFromRequest(request))
                .delete();
        processStatusCode(request, response, result.getStatus());
        return response;
    }

    @Override
    public CronofyResponse<CreateNotificationChannelResponse> createNotificationChannel(final CreateNotificationChannelRequest request) {
        assertCronofyRequest(request);
        try {
            return getClient()
                    .target(basePath)
                    .path(API_VERSION)
                    .path(CHANNELS_PATH)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .header(AUTH_HEADER_KEY, getAccessTokenFromRequest(request))
                    .post(Entity.entity(request, MediaType.APPLICATION_JSON_TYPE),
                            new GenericType<CronofyResponse<CreateNotificationChannelResponse>>() {
                            }
                    );
        } catch (final NotAuthorizedException ex) {
            LOGGER.warn(NOT_AUTHORIZED_EXCEPTION_MSG, ex, request);
            return new CronofyResponse<>(ErrorTypeModel.NOT_AUTHORIZED);
        } catch (final ClientErrorException ex) {
            LOGGER.warn(CLIENT_ERROR_EXCEPTION_MSG, ex, request);
            return new CronofyResponse<>(ErrorTypeModel.UNPROCESSABLE);
        }
    }

    @Override
    public CronofyResponse<ListNotificationChannelsResponse> listNotificationChannels(final ListNotificationChannelsRequest request) {
        assertCronofyRequest(request);
        try {
            return getClient()
                    .target(basePath)
                    .path(API_VERSION)
                    .path(CHANNELS_PATH)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .header(AUTH_HEADER_KEY, getAccessTokenFromRequest(request))
                    .get(new GenericType<CronofyResponse<ListNotificationChannelsResponse>>() {
                    });
        } catch (final NotAuthorizedException ex) {
            LOGGER.warn(NOT_AUTHORIZED_EXCEPTION_MSG, ex, request);
            return new CronofyResponse<>(ErrorTypeModel.NOT_AUTHORIZED);
        }
    }

    @SuppressWarnings({"squid:MethodCyclomaticComplexity", "squid:S1192"})
    @Override
    public CronofyResponse<CloseNotificationChannelResponse> closeNotificationChannel(final CloseNotificationChannelRequest request) {
        assertCronofyRequest(request);
        final CronofyResponse<CloseNotificationChannelResponse> response = new CronofyResponse<>();
        final Response result = getClient()
                .target(basePath)
                .path(API_VERSION)
                .path(CHANNELS_PATH)
                .path(request.getChannelId())
                .request(MediaType.APPLICATION_JSON_TYPE)
                .header(AUTH_HEADER_KEY, getAccessTokenFromRequest(request))
                .delete();
        final int statusCode = result.getStatus();
        switch (statusCode) {
            case 202:
                response.setResponse(new CloseNotificationChannelResponse());
                break;
            case 401:
                response.setError(ErrorTypeModel.NOT_AUTHORIZED);
                break;
            default:
                LOGGER.error(UNKNOWN_STATUS_CODE_EXCEPTION_MSG, statusCode, request);
                throw new UnknownStatusCodeException("Got an unknown status code - " + statusCode + " while processing request.", request);
        }
        return response;
    }

    @Override
    public CronofyResponse<UserInfoResponse> userInfo(final UserInfoRequest request) {
        assertCronofyRequest(request);
        try {
            return getClient()
                    .target(basePath)
                    .path(API_VERSION)
                    .path(USER_PATH)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .header(AUTH_HEADER_KEY, getAccessTokenFromRequest(request))
                    .get(new GenericType<CronofyResponse<UserInfoResponse>>() {
                    });
        } catch (final NotAuthorizedException ex) {
            LOGGER.warn(NOT_AUTHORIZED_EXCEPTION_MSG, ex, request);
            return new CronofyResponse<>(ErrorTypeModel.NOT_AUTHORIZED);
        }
    }

    @Override
    public CronofyResponse<AccountInfoResponse> accountInfo(final AccountInfoRequest request) {
        assertCronofyRequest(request);
        try {
            return getClient()
                    .target(basePath)
                    .path(API_VERSION)
                    .path(ACCOUNT_PATH)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .header(AUTH_HEADER_KEY, getAccessTokenFromRequest(request))
                    .get(new GenericType<CronofyResponse<AccountInfoResponse>>() {
                    });
        } catch (final NotAuthorizedException ex) {
            LOGGER.warn(NOT_AUTHORIZED_EXCEPTION_MSG, ex, request);
            return new CronofyResponse<>(ErrorTypeModel.NOT_AUTHORIZED);
        }
    }

    @Override
    public CronofyResponse<ProfileInformationResponse> profileInfo(final ProfileInformationRequest request) {
        assertCronofyRequest(request);
        try {
            return getClient()
                    .target(basePath)
                    .path(API_VERSION)
                    .path(PROFILES_PATH)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .header(AUTH_HEADER_KEY, getAccessTokenFromRequest(request))
                    .get(new GenericType<CronofyResponse<ProfileInformationResponse>>() {
                    });
        } catch (final NotAuthorizedException ex) {
            LOGGER.warn(NOT_AUTHORIZED_EXCEPTION_MSG, ex, request);
            return new CronofyResponse<>(ErrorTypeModel.NOT_AUTHORIZED);
        }
    }

    @Override
    public CronofyResponse<AvailabilityResponse> availability(final AvailabilityRequest request) {
        assertCronofyRequest(request);
        try {
            return getClient()
                    .target(basePath)
                    .path(API_VERSION)
                    .path(AVAILABILITY_PATH)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .header(AUTH_HEADER_KEY, getAccessTokenFromRequest(request))
                    .post(Entity.entity(request, MediaType.APPLICATION_JSON_TYPE),
                            new GenericType<CronofyResponse<AvailabilityResponse>>() {
                            });
        } catch (final NotAuthorizedException ex) {
            LOGGER.warn(NOT_AUTHORIZED_EXCEPTION_MSG, ex, request);
            return new CronofyResponse<>(ErrorTypeModel.NOT_AUTHORIZED);
        } catch (final ClientErrorException ex) {
            LOGGER.warn(CLIENT_ERROR_EXCEPTION_MSG, ex, request);
            return new CronofyResponse<>(ErrorTypeModel.UNPROCESSABLE);
        }
    }

    @Override
    public CronofyResponse<ElementTokenResponse> getElementToken(final ElementTokenRequest request) {
        assertCronofyRequest(request);
        try {
            return getClient()
                    .target(basePath)
                    .path(API_VERSION)
                    .path(ELEMENT_TOKENS_PATH)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .header(AUTH_HEADER_KEY, getAccessTokenFromRequest(request))
                    .post(Entity.entity(request, MediaType.APPLICATION_JSON_TYPE),
                            new GenericType<CronofyResponse<ElementTokenResponse>>() {
                            });
        } catch (final NotAuthorizedException ex) {
            LOGGER.warn(NOT_AUTHORIZED_EXCEPTION_MSG, ex, request);
            return new CronofyResponse<>(ErrorTypeModel.NOT_AUTHORIZED);
        } catch (final ClientErrorException ex) {
            LOGGER.warn(CLIENT_ERROR_EXCEPTION_MSG, ex, request);
            return new CronofyResponse<>(ErrorTypeModel.UNPROCESSABLE);
        }
    }

    //endregion

    //region Utility methods
    private <T extends AbstractAccessTokenAwareCronofyRequest> String getAccessTokenFromRequest(final T request) {
        return "Bearer " + request.getAccessToken();
    }

    private void assertCronofyRequest(final AbstractCronofyRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("The cronofy request should not be null");
        }
        if (request instanceof AbstractAccessTokenAwareCronofyRequest
                && StringUtils.isBlank(((AbstractAccessTokenAwareCronofyRequest) request).getAccessToken())) {
            throw new IllegalArgumentException("The access token should not be blank");
        }
    }

    private String getQueryParamFromDate(final Date date, final int daysToAdd) {
        return date == null ? null : new DateTime(date).plusDays(daysToAdd).withZone(DateTimeZone.UTC).toString(QUERY_PARAM_DATE_FORMAT);
    }

    private String convertToISO8601(final Date date) {
        return date == null ? null : new DateTime(date).withZone(DateTimeZone.UTC).toString(QUERY_PARAM_ISO8601_FORMAT);
    }

    private void processStatusCode(final AbstractCronofyRequest request,
                                   final CronofyResponse<? extends AbstractCronofyResponse> response,
                                   final int statusCode) {
        switch (statusCode) {
            case 202:
                break;
            case 401:
                response.setError(ErrorTypeModel.NOT_AUTHORIZED);
                break;
            case 403:
                response.setError(ErrorTypeModel.FORBIDDEN);
                break;
            case 422:
                response.setError(ErrorTypeModel.UNPROCESSABLE);
                break;
            case 423:
                response.setError(ErrorTypeModel.LOCKED);
                break;
            default:
                LOGGER.error(UNKNOWN_STATUS_CODE_EXCEPTION_MSG, statusCode, request);
                throw new UnknownStatusCodeException("Got an unknown status code - " + statusCode + " while processing request.", request);
        }
    }

    private Object[] getCalendarIdsArray(final List<String> calendarIds) {
        if (calendarIds == null) {
            return new String[0];
        }
        return calendarIds.toArray(new String[0]);
    }
    //endregion
}
