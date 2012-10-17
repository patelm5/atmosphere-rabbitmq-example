package spikes.mikeyp.rabbit;

import java.util.concurrent.BlockingQueue;
import java.util.logging.Logger;

import javax.annotation.Resource;

import org.codehaus.jettison.json.JSONObject;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("atmosphereListener")
public class RabbitListener implements MessageListener{

	
	private final static Logger logger = Logger.getLogger(RabbitListener.class.getName());
	@Autowired
	private RabbitMessageJsonAdapter messageConverter;

	@Resource 
	private BlockingQueue<JSONObject> updateQueue ; 
	
	@Override
	public void onMessage(Message message) {
		
		try {
			JSONObject o =  messageConverter.fromMessage(message);
			updateQueue.add(o); 
			
		}  catch (Exception e) {
			logger.severe("unable to parse message "+e.getMessage()); 
		}
		
	}
	
}
