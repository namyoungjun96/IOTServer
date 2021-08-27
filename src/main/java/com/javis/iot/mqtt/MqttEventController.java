package com.javis.iot.mqtt;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
public class MqttEventController {
	
	@GetMapping("/")
	public String OnClickButton(){
		try {
			MqttClient client = new MqttClient("tcp://localhost:1883", "Nam");
			client.connect();
			MqttMessage message = new MqttMessage();
			message.setPayload("topic test".getBytes());
			client.publish("test1", message);
			client.disconnect();
			client.close();
		} catch (MqttException e) {
			e.printStackTrace();
		}
		
		return "home";
	}
}
