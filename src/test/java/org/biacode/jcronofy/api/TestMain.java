package org.biacode.jcronofy.api;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.biacode.jcronofy.api.client.CronofyClient;
import org.biacode.jcronofy.api.client.impl.CronofyClientImpl;
import org.biacode.jcronofy.api.model.common.CronofyResponse;
import org.biacode.jcronofy.api.model.request.AccountInfoRequest;
import org.biacode.jcronofy.api.model.response.AccountInfoResponse;

import javax.ws.rs.client.ClientBuilder;

/**
 * User: Syuzanna Eprikyan
 * Company: SFL LLC
 * Date: 1/12/18
 * Time: 6:22 PM
 */
public class TestMain {
    public static void main(final String[] args) {
        // Construct cronofy java client
        final CronofyClient cronofyClient = new CronofyClientImpl(ClientBuilder.newBuilder().register(JacksonJsonProvider.class).build());

        // Read events
        final CronofyResponse<AccountInfoResponse> result = cronofyClient.accountInfo(new AccountInfoRequest("LA-AjjpWFWEnjkUJQ9UNfavx04NFiNqo"));
//        final MemberModel memberModel = new MemberModel();
//        memberModel.setSub();
//        final ParticipantModel participantModel = new ParticipantModel();
//        final CronofyResponse<ReadEventsResponse> eventsResult = cronofyClient.availability(new AvailabilityRequest("x7yS2h5VdSADECPqaqubIed2Q17Dz9gv", ));
        // If an error occur
//        if (eventsResult.hasError()) {
//            System.out.println(eventsResult.getError().toString());
//        } else {
//            System.out.println(eventsResult.getResponse().toString());
//        }
    }
}
