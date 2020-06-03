package org.biacode.jcronofy.api.client;

import org.biacode.jcronofy.api.model.common.CronofyResponse;
import org.biacode.jcronofy.api.model.request.*;
import org.biacode.jcronofy.api.model.response.*;

/**
 * User: Arthur Asatryan
 * Date: 10/4/16
 * Time: 3:01 PM
 */
public interface CronofyClient {
    /**
     * Gets access token.
     *
     * @param request the request
     * @return the access token
     */
    CronofyResponse<GetAccessTokenResponse> getAccessToken(final GetAccessTokenRequest request);

    /**
     * Update access token.
     *
     * @param request the request
     * @return the cronofy response
     */
    CronofyResponse<UpdateAccessTokenResponse> updateAccessToken(final UpdateAccessTokenRequest request);

    /**
     * Revoke access token.
     *
     * @param request the request
     * @return the cronofy response
     */
    CronofyResponse<RevokeAccessTokenResponse> revokeAccessToken(final RevokeAccessTokenRequest request);

    /**
     * List calendars.
     *
     * @param request the request
     * @return the cronofy response
     */
    CronofyResponse<ListCalendarsResponse> listCalendars(final ListCalendarsRequest request);

    /**
     * Create calendar
     *
     * @param request the request
     * @return the cronofy response
     */
    CronofyResponse<CreateCalendarResponse> createCalendar(final CreateCalendarRequest request);

    /**
     * Read events.
     *
     * @param request the request
     * @return the cronofy response
     */
    CronofyResponse<ReadEventsResponse> readEvents(final ReadEventsRequest request);

    /**
     * Free busy.
     *
     * @param request the request
     * @return the cronofy response
     */
    CronofyResponse<FreeBusyResponse> freeBusy(final FreeBusyRequest request);

    /**
     * Create or update event.
     *
     * @param request the request
     * @return the cronofy response
     */
    CronofyResponse<CreateOrUpdateEventResponse> createOrUpdateEvent(final CreateOrUpdateEventRequest request);

    /**
     * Delete event.
     *
     * @param request the request
     * @return the cronofy response
     */
    CronofyResponse<DeleteEventResponse> deleteEvent(final DeleteEventRequest request);

    /**
     * Delete events that you are managing from the user's calendars in bulk.
     *
     * @param request the request
     * @return the cronofy response
     */
    CronofyResponse<BulkDeleteEventsResponse> bulkDeleteEvents(final BulkDeleteEventsRequest request);

    /**
     * Create notification channel.
     *
     * @param request the request
     * @return the cronofy response
     */
    CronofyResponse<CreateNotificationChannelResponse> createNotificationChannel(final CreateNotificationChannelRequest request);

    /**
     * List notification channels.
     *
     * @param request the request
     * @return the cronofy response
     */
    CronofyResponse<ListNotificationChannelsResponse> listNotificationChannels(final ListNotificationChannelsRequest request);

    /**
     * Close notification channel.
     *
     * @param request the request
     * @return the cronofy response
     */
    CronofyResponse<CloseNotificationChannelResponse> closeNotificationChannel(final CloseNotificationChannelRequest request);

    /**
     * @param request the request
     * @return the cronofy response
     */
    CronofyResponse<UserInfoResponse> userInfo(final UserInfoRequest request);

    /**
     * Account info.
     *
     * @param request the request
     * @return the cronofy response
     */
    CronofyResponse<AccountInfoResponse> accountInfo(final AccountInfoRequest request);

    /**
     * Profile info.
     *
     * @param request the request
     * @return the cronofy response
     */
    CronofyResponse<ProfileInformationResponse> profileInfo(final ProfileInformationRequest request);

    /**
     * @param request the request
     * @return the cronofy response
     */
    CronofyResponse<AvailabilityResponse> availability(final AvailabilityRequest request);

    /**
     * Element Token
     * 
     * @param request the request
     * @return the cronofy response
     */
    CronofyResponse<ElementTokenResponse> getElementToken(final ElementTokenRequest request);
}
