package spikes.mikeyp.atmosphere;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.atmosphere.annotation.Suspend;
import org.atmosphere.cpr.Broadcaster;
import org.atmosphere.cpr.BroadcasterFactory;
import org.atmosphere.jersey.Broadcastable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.jersey.spi.resource.Singleton;

@Path("/")
@Singleton
@Produces("application/json")
public class AtmosphereController {

	private static final Broadcaster topic = BroadcasterFactory.getDefault().get("atmosphere"); 

	

	private static final Logger logger = LoggerFactory.getLogger(AtmosphereController.class);

	@GET
	@Suspend(contentType = "application/json")
	public Broadcastable subscribe() {
		logger.info("a new browser subscribes"); 
		return new Broadcastable(topic); 
	}

	public static Broadcaster getTopic() {
		return topic;
	}


}
