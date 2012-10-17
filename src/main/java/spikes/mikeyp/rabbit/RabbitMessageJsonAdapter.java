package spikes.mikeyp.rabbit;

import java.io.UnsupportedEncodingException;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.stereotype.Component;

@Component
public class RabbitMessageJsonAdapter {

	public static final String DEFAULT_CHARSET = "utf-8";

	public JSONObject fromMessage(Message message) throws MessageConversionException {
		try {
			String content = new String(message.getBody(), DEFAULT_CHARSET).trim();

			return new JSONObject(stripBadChars(content));
		} catch (JSONException e) {
			throw new MessageConversionException("Failed to convert json-based Message content, badly formed json ?", e);
		} catch (UnsupportedEncodingException e) {
			throw new MessageConversionException("Failed to convert json-based Message content, bad encoding ?", e);
		}
	}

	private String stripBadChars(String content) {
		if (content != null && !content.isEmpty() && content.indexOf("{") != 0 && content.contains("{")) {
			// strip weird control chars
			return content.substring(content.indexOf("{"));
		} else {
			return content;
		}
	}
}
