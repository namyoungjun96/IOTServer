package com.javis.iot.mqtt;

import java.util.*;
import java.util.function.Consumer;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;

public class MyMqttClient implements MqttCallback{

    private MqttClient client;
    private MqttConnectOptions option;
    private Consumer<HashMap<Object, Object>> FNC = null;  //메시지 도착 후 응답하는 함수
    private Consumer<HashMap<Object, Object>> FNC2 = null; //커넥션이 끊긴 후 응답하는 함수
    private Consumer<HashMap<Object, Object>> FNC3 = null; //전송이 완료된 이후 응답하는 함수.

	
    /**
     * 기본 생성자로 Predicate를 받습니다. 해당 Predicate는 메시지가 도착한 행위에 대한 콜백함수입니다.<br>
     * 해당 함수를 구현하지 않으면 클래스를 생성 할 수 없습니다.
     * **/
    public MyMqttClient (Consumer<HashMap<Object, Object>> fnc){
        this.FNC = fnc;
    }
	
    /**
     * 설정파일을 등록합니다.<br>
     * 파라미터는 총 4개가 필요합니다.<br>
     * 사용자이름, 비밀번호, 주소, 접속후에 사용할 아이디값 입니다.
     * */
    public MyMqttClient init(String userName, String password, String serverURI, String clientId){
        option = new MqttConnectOptions();
        option.setCleanSession(true);
        option.setKeepAliveInterval(30);
        option.setUserName(userName);
        option.setPassword(password.toCharArray());
        try {
            client = new MqttClient(serverURI, clientId);
            client.setCallback(this);
            client.connect(option);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }
	
    /***
     * 전송 메소드입니다.
     *
    **/
    public boolean sender(String topic, String msg) throws MqttPersistenceException, MqttException{
        MqttMessage message = new MqttMessage();
        message.setPayload(msg.getBytes());
        client.publish(topic, message);
        return true;
    }
	
    /***
     * 구독 대상을 전달합니다.
     *
     * **/
    public boolean subscribe(String... topics){
        try {
            if(topics != null){
                for(String topic : topics){
                    client.subscribe(topic,0);
                }
            }			
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
	
    /**
     * 커넥션이 끊어진 이후의 콜백행위를 등록합니다.<br>
     * 해쉬맵 형태의 결과에 키는 result, 값은 Throwable 객체를 반환 합니다. 
     * **/
    public void initConnectionLost (Consumer<HashMap<Object, Object>> fnc){
        FNC2 = fnc;
    }
	

    /**
     * 커넥션이 끊어진 이후의 콜백행위를 등록합니다.<br>
     * 해쉬맵 형태의 결과에 키는 result, 값은 IMqttDeliveryToken 객체를 반환 합니다. 
     * **/
    public void initDeliveryComplete (Consumer<HashMap<Object, Object>> fnc){
        FNC3 = fnc;
    }
	
    /**
     * 종료메소드입니다.<br>
     * 클라이언트를 종료 합니다.
     * */
    public void close(){
        if(client != null){
            try {
                client.disconnect();
                client.close();
            } catch (MqttException e) {
                e.printStackTrace();
            }
        }
    }
	
    @Override
    public void connectionLost(Throwable arg0) {
        if(FNC2 != null){
            HashMap<Object, Object> result = new HashMap<Object, Object>();
            result.put("result", arg0);
            FNC2.accept(result);
            arg0.printStackTrace();
        }
    }	
	
    @Override
    public void deliveryComplete(IMqttDeliveryToken arg0) {
        if(FNC3 != null){
            HashMap<Object, Object> result = new HashMap<Object, Object>();
            try {
                result.put("result", arg0);
            } catch (Exception e) {
                e.printStackTrace();
                result.put("result", "ERROR");
                result.put("error", e.getMessage());
            }
            FNC3.accept(result);
        }
    }

    //메시지 도착
    @Override
    public void messageArrived(String arg0, MqttMessage arg1) throws Exception {
        if(FNC != null){
            HashMap<Object, Object> result = new HashMap<Object, Object>();
            result.put("topic", arg0);
            result.put("message", new String(arg1.getPayload(),"UTF-8"));
            FNC.accept(result);  //콜백행위 실행
        }
    }
}