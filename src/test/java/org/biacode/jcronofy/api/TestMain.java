package org.biacode.jcronofy.api;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.biacode.jcronofy.api.client.CronofyClient;
import org.biacode.jcronofy.api.client.impl.CronofyClientImpl;
import org.biacode.jcronofy.api.model.AvailablePeriodModel;
import org.biacode.jcronofy.api.model.MemberModel;
import org.biacode.jcronofy.api.model.ParticipantModel;
import org.biacode.jcronofy.api.model.RequiredDurationModel;
import org.biacode.jcronofy.api.model.common.CronofyResponse;
import org.biacode.jcronofy.api.model.request.AvailabilityRequest;
import org.biacode.jcronofy.api.model.request.UserInfoRequest;
import org.biacode.jcronofy.api.model.response.AvailabilityResponse;
import org.biacode.jcronofy.api.model.response.UserInfoResponse;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import javax.ws.rs.client.ClientBuilder;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * User: Syuzanna Eprikyan
 * Date: 1/12/18
 * Time: 6:22 PM
 */
public class TestMain {
    public static void main(final String[] args) {
        // Construct cronofy java client
        final CronofyClient cronofyClient = new CronofyClientImpl(ClientBuilder.newBuilder().register(JacksonJsonProvider.class).build());

        // Read events
        final CronofyResponse<UserInfoResponse> result = cronofyClient.userInfo(new UserInfoRequest("-47Yalex5tANkrDbvEBa3QqQuYThC_LE"));
        final List<AvailablePeriodModel> availablePeriod = Collections.singletonList(new AvailablePeriodModel(convertToISO8601(DateTime.now().toDate()), convertToISO8601(DateTime.now().plusHours(3).toDate())));
        final List<MemberModel> members = Collections.singletonList(new MemberModel("acc_57f3925268d15d7629000e48"));
        final List<ParticipantModel> participantModel = Collections.singletonList(new ParticipantModel(members, 1));


        final CronofyResponse<AvailabilityResponse> eventsResult =
                cronofyClient.availability(new AvailabilityRequest("x7yS2h5VdSADECPqaqubIed2Q17Dz9gv", participantModel, new RequiredDurationModel(10), availablePeriod));
        // If an error occur
        if (eventsResult.hasError()) {
            System.out.println(eventsResult.getError().toString());
        } else {
            System.out.println(eventsResult.getResponse().toString());
        }
    }

    private static String convertToISO8601(final Date date) {
        return date == null ? null : new DateTime(date).withZone(DateTimeZone.UTC).toString("YYYY-MM-dd'T'HH:mm:ss'Z'");
    }
}
