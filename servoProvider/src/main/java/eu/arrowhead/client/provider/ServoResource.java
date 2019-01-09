/*
 *  Copyright (c) 2018 AITIA International Inc.
 *
 *  This work is part of the Productive 4.0 innovation project, which receives grants from the
 *  European Commissions H2020 research and innovation programme, ECSEL Joint Undertaking
 *  (project no. 737459), the free state of Saxony, the German Federal Ministry of Education and
 *  national funding authorities from involved countries.
 */

package eu.arrowhead.client.provider;


import javax.ws.rs.POST;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.Random;
import javax.validation.Valid;

@Path("/")
//REST service example
public class ServoResource {

  static final String SERVICE_URI = "servo";
  static Random r = new Random();
  static ServoMotor sm = new ServoMotor();

  @POST
  @Path(SERVICE_URI)
  @Consumes(MediaType.APPLICATION_JSON)
  public void postPos(@Valid int pos,@Context SecurityContext context, @QueryParam("token") String token, @QueryParam("signature") String signature) {
    String providerName;
    if (context.isSecure()) {
      RequestVerification.verifyRequester(context, token, signature);
      providerName = "SecureServo";
    } else {
      providerName = "InsecureServo";
    }
    try{
      sm.moveServo(pos);
    }catch(Exception e){
      System.out.println("error");
    }
    /*if (FullProviderMain.customResponsePayload != null) {
      return Response.status(200).entity(FullProviderMain.customResponsePayload).build();
    } else {
      MeasurementEntry entry = new MeasurementEntry("Temperature_IndoorTemperature", 21.0, System.currentTimeMillis());
      TemperatureReadout readout = new TemperatureReadout(providerName, System.currentTimeMillis(), "celsius", 1);
      readout.getE().add(entry);
      return Response.status(200).entity(readout).build();
    }*/
  }

}
