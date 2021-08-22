package com.javis.iot.mqtt;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class TestMqtt {
	public static void main(String[] args) {
		try {
			MqttClient client = new MqttClient("tcp://localhost:1883", "Nam");
			client.connect();
			MqttMessage message = new MqttMessage();
			message.setPayload("topic test".getBytes());
			client.publish("test1", message);
			client.disconnect();
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}
}
