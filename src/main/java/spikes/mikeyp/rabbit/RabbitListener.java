package spikes.mikeyp.rabbit;

import java.util.logging.Logger;

import org.codehaus.jettison.json.JSONObject;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import spikes.mikeyp.atmosphere.AtmosphereController;

@Component("atmosphereListener")
public class RabbitListener implements MessageListener{

	
	private final static Logger logger = Logger.getLogger(RabbitListener.class.getName());
	
	@Autowired
	private RabbitMessageJsonAdapter messageConverter;
	
	@Override
	public void onMessage(Message message) {
		
		try {
			JSONObject o =  messageConverter.fromMessage(message);
			AtmosphereController.getTopic().broadcast(o.toString()); 
			//TODO: look for a better way of cross the classloader boundary ..
			
		}  catch (Exception e) {
			logger.severe("unable to parse message "+e.getMessage()); 
		}
		
	}
	
}
