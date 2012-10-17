package spikes.mikeyp.atmosphere;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.atmosphere.annotation.Broadcast;
import org.atmosphere.annotation.Suspend;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Configurable;

@Configurable
@Path("/")
public class AtmosphereController {

	@Resource
	public BlockingQueue<JSONObject> updateQueue;

	private static final Logger logger = LoggerFactory.getLogger(AtmosphereController.class);

	@GET
	@Suspend
	public String subscribe() {
		return "";
	}

	@Broadcast(writeEntity = false)
	@POST
	public Map<String, Object> broadcast() {
		Map<String,Object> model = new HashMap<String,Object> () ; 
		model.put("by","mike"); 
		model.put("text", "hi"); 
		model.put("received","just now"); 
		return model; 
	}
	


}
