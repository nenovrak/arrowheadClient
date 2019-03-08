/*
 *  Copyright (c) 2018 AITIA International Inc.
 *
 *  This work is part of the Productive 4.0 innovation project, which receives grants from the
 *  European Commissions H2020 research and innovation programme, ECSEL Joint Undertaking
 *  (project no. 737459), the free state of Saxony, the German Federal Ministry of Education and
 *  national funding authorities from involved countries.
 */

package eu.arrowhead.client.provider;

import eu.arrowhead.client.common.model.MeasurementEntry;
import eu.arrowhead.client.common.model.TemperatureReadout;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.Random;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
//REST service example
public class TemperatureResource {
    
    static final String SERVICE_URI = "temperature";
    static TemperatureReader tempReader = new TemperatureReader();
    
    @GET
    @Path("temperature/{sensorId}")
    public Response getIt(@Context SecurityContext context, @QueryParam("token") String token, @QueryParam("signature") String signature, @PathParam("sensorId") String sensorId) {
          
        String providerName;
          
        if(context.isSecure()){
            RequestVerification.verifyRequester(context, token, signature);
            providerName = "TemperatureSensors_SecureTemperatureSensor";
        }
        else{
            providerName = "TemperatureSensors_InsecureTemperatureSensor";
        }
          
        double temp = tempReader.readTemperature(sensorId); //Reads the temperature from the sensor
          
        MeasurementEntry entry = new MeasurementEntry("Temperature_IndoorTemperature", temp, System.currentTimeMillis());
          
        TemperatureReadout readout = new TemperatureReadout(providerName, System.currentTimeMillis(), "celsius", 1);
          
        readout.getE().add(entry); //Adds the 'entry' to a list consisting of MeasurementEntry
          
        return Response.status(200).entity(readout).build();
    }

}
