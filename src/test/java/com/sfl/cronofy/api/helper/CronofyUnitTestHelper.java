package com.sfl.cronofy.api.helper;

import com.sfl.cronofy.api.model.*;
import com.sfl.cronofy.api.model.common.AbstractCronofyResponse;
import com.sfl.cronofy.api.model.common.CronofyResponse;
import com.sfl.cronofy.api.model.request.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

import static org.junit.Assert.*;

/**
 * User: Arthur Asatryan
 * Company: SFL LLC
 * Date: 10/6/16
 * Time: 12:12 PM
 */
public final class CronofyUnitTestHelper {

    //region Constructors
    public CronofyUnitTestHelper() {
    }
    //endregion

    //region Common
    public <T extends AbstractCronofyResponse> void assertResultResponse(final CronofyResponse<T> expectedResponse,
                                                                         final CronofyResponse<T> result) {
        assertNotNull(result);
        assertNotNull(expectedResponse);
        if (result.getResponse() != null) {
            assertEquals(expectedResponse, result);
        }
        if (result.hasError() && result.getError() == null) {
            fail("Can not find error body");
        }
        if (result.hasError()) {
            assertEquals(expectedResponse.getError(), result.getError());
        }
    }
    //endregion

    //region Public methods
    public CalendarModel buildCalendarModel() {
        return new CalendarModel(
                ProviderNameModel.GOOGLE,
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                false,
                false,
                false,
                UUID.randomUUID().toString()
        );
    }

    public EventsPagesModel buildEventsPagesModel() {
        return new EventsPagesModel(
                1, 5, UUID.randomUUID().toString()
        );
    }

    public EventModel buildEventModel() {
        return new EventModel(
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                new Date(),
                new Date(),
                false,
                buildEventLocationModel(),
                UUID.randomUUID().toString(),
                ParticipationStatusModel.ACCEPTED,
                TransparencyModel.OPAQUE,
                EventStatusModel.CANCELLED,
                new ArrayList<>(Arrays.asList(UUID.randomUUID().toString(), UUID.randomUUID().toString())),
                new ArrayList<>(Arrays.asList(buildAttendeesModel(), buildAttendeesModel())),
                new Date(),
                new Date(),
                false,
                buildOptionModel()
        );
    }

    private EventLocationModel buildEventLocationModel() {
        return new EventLocationModel(UUID.randomUUID().toString());
    }

    private AttendeesModel buildAttendeesModel() {
        return new AttendeesModel();
    }

    private OptionModel buildOptionModel() {
        return new OptionModel(false, true);
    }

    public FreeBusyModel buildFreeBusyModel() {
        return new FreeBusyModel(
                UUID.randomUUID().toString(),
                new Date(),
                new Date(),
                FreeBusyStatusModel.BUSY
        );
    }

    public ChannelModel buildChannelModel() {
        return new ChannelModel(
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                buildNotificationFilterModel()
        );
    }

    private NotificationFilterModel buildNotificationFilterModel() {
        return new NotificationFilterModel(
                new ArrayList<>(Arrays.asList(UUID.randomUUID().toString(), UUID.randomUUID().toString())),
                false
        );
    }

    public AccountModel buildAccountModel() {
        return new AccountModel(
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                AccountTypeModel.ACCOUNT,
                ScopeModel.CREATE_EVENT
        );
    }

    public ProfileModel buildProfileModel() {
        return new ProfileModel(
                ProviderNameModel.GOOGLE,
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                true,
                UUID.randomUUID().toString()
        );
    }

    public GetAccessTokenRequest getGetAccessTokenRequest() {
        return new GetAccessTokenRequest(
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString()
        );
    }

    public UpdateAccessTokenRequest getUpdateAccessTokenRequestRequest() {
        return new UpdateAccessTokenRequest(
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString()
        );
    }

    public RevokeAccessTokenRequest getRevokeAccessTokenRequest() {
        return new RevokeAccessTokenRequest(
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString()
        );
    }

    public ListCalendarsRequest getListCalendarsRequestRequest() {
        return new ListCalendarsRequest(UUID.randomUUID().toString());
    }

    public ReadEventsRequest getReadEventsRequest() {
        return new ReadEventsRequest(
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString()
        );
    }

    public FreeBusyRequest getFreeBusyRequest() {
        return new FreeBusyRequest(UUID.randomUUID().toString(), UUID.randomUUID().toString());
    }

    public CreateOrUpdateEventRequest getCreateOrUpdateEventRequest() {
        return new CreateOrUpdateEventRequest(
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                new Date(),
                new Date(),
                buildEventLocationModel()
        );
    }

    public DeleteEventRequest getDeleteEventRequest() {
        return new DeleteEventRequest(
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString()
        );
    }

    public CreateNotificationChannelRequest getCreateNotificationChannelRequest() {
        return new CreateNotificationChannelRequest(
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString()
        );
    }

    public ListNotificationChannelsRequest getListNotificationChannelsRequest() {
        return new ListNotificationChannelsRequest(
                UUID.randomUUID().toString()
        );
    }

    public CloseNotificationChannelRequest getCloseNotificationChannelRequest() {
        return new CloseNotificationChannelRequest(
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString()
        );
    }

    public AccountInfoRequest getAccountInfoRequest() {
        return new AccountInfoRequest(UUID.randomUUID().toString());
    }

    public ProfileInformationRequest getProfileInformationRequest() {
        return new ProfileInformationRequest(UUID.randomUUID().toString());
    }
    //endregion
}

