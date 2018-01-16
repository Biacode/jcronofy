package org.biacode.jcronofy.api.helper;

import org.biacode.jcronofy.api.model.*;
import org.biacode.jcronofy.api.model.common.AbstractCronofyResponse;
import org.biacode.jcronofy.api.model.common.CronofyResponse;
import org.biacode.jcronofy.api.model.request.*;

import java.util.*;

import static org.junit.Assert.*;

/**
 * User: Arthur Asatryan
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
                buildOrganizerModel(),
                TransparencyModel.OPAQUE,
                EventStatusModel.CANCELLED,
                new ArrayList<>(Arrays.asList(UUID.randomUUID().toString(), UUID.randomUUID().toString())),
                new ArrayList<>(Arrays.asList(buildAttendeesModel(), buildAttendeesModel())),
                new Date(),
                new Date(),
                false,
                buildOptionModel(),
                buildActionsModel()
        );
    }

    private ActionsModel buildActionsModel() {
        return new ActionsModel("event_id");
    }

    private EventLocationModel buildEventLocationModel() {
        return new EventLocationModel(UUID.randomUUID().toString(), UUID.randomUUID().toString(), UUID.randomUUID().toString());
    }

    private OrganizerModel buildOrganizerModel() {
        return new OrganizerModel(UUID.randomUUID().toString(), UUID.randomUUID().toString());
    }

    private AttendeesModel buildAttendeesModel() {
        return new AttendeesModel();
    }

    private OptionModel buildOptionModel() {
        return new OptionModel(false, true, false);
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
                UUID.randomUUID().toString()
        );
    }

    public UserModel buildUserModel() {
        return new UserModel(UUID.randomUUID().toString(), UUID.randomUUID().toString());
    }

    public ProfileModel buildProfileModel() {
        return new ProfileModel(
                ProviderNameModel.GOOGLE,
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                true,
                UUID.randomUUID().toString(),
                false
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

    public ListCalendarsRequest getListCalendarsRequest() {
        return new ListCalendarsRequest(UUID.randomUUID().toString());
    }

    public CreateCalendarRequest getCreateCalendarRequest() {
        return new CreateCalendarRequest(
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString()
        );
    }

    public ReadEventsRequest getReadEventsRequest() {
        return new ReadEventsRequest(
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString()
        );
    }

    public ReadEventsRequest getReadEventsRequestWithCalendarIds(final List<String> calendarIds) {
        final ReadEventsRequest request = getReadEventsRequest();
        request.setCalendarIds(calendarIds);
        return request;
    }

    public FreeBusyRequest getFreeBusyRequest() {
        return new FreeBusyRequest(UUID.randomUUID().toString(), UUID.randomUUID().toString());
    }

    public FreeBusyRequest getFreeBusyRequestWithCalendarIds(final List<String> calendarIds) {
        final FreeBusyRequest request = getFreeBusyRequest();
        request.setCalendarIds(calendarIds);
        return request;
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
                UUID.randomUUID().toString(),
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

    public BulkDeleteEventsRequest getBulkDeleteEventsRequest() {
        return new BulkDeleteEventsRequest(
                UUID.randomUUID().toString(),
                false
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

    public UserInfoRequest getUserInfoRequest() {
        return new UserInfoRequest(UUID.randomUUID().toString());
    }

    public ProfileInformationRequest getProfileInformationRequest() {
        return new ProfileInformationRequest(UUID.randomUUID().toString());
    }
    //endregion
}

