package com.iotrack.mqttbroker;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@SpringBootApplication
public class MqttbrokerApplication {

    public static void main(String[] args) throws ParseException, InterruptedException {


        List<PublishData> publishDataList1 = new ArrayList<>();
        List<PublishData> publishDataList2 = new ArrayList<>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSS");

        Date date_time_list1 = sdf.parse("2017-10-01 14:50:00.000");
        Date date_time_list2 = sdf.parse("2017-10-01 14:50:01.000");

        int xCoord1 = 150;
        int ycoord1 = 150;

        int xCoord2 = 250;
        int ycoord2 = 300;


        PublishData publishData1 = new PublishData();
        publishData1.setTagName("Fatih");
        publishData1.setTime(date_time_list1);
        publishData1.setZoneId(1);
        publishData1.setTimeDiff(0);
        publishData1.setxCoord(xCoord1);
        publishData1.setyCoord(ycoord1);
        publishDataList1.add(publishData1);

        PublishData publishData2 = new PublishData();
        publishData2.setTagName("Yunus");
        publishData2.setTime(date_time_list1);
        publishData2.setZoneId(1);
        publishData2.setTimeDiff(0);
        publishData2.setxCoord(xCoord1+50);
        publishData2.setyCoord(ycoord1-50);
        publishDataList1.add(publishData2);

        PublishData publishData3 = new PublishData();
        publishData3.setTagName("Ali");
        publishData3.setTime(date_time_list1);
        publishData3.setZoneId(1);
        publishData3.setTimeDiff(0);
        publishData3.setxCoord(xCoord1+80);
        publishData3.setyCoord(ycoord1-80);
        publishDataList1.add(publishData3);

        PublishData publishData4 = new PublishData();
        publishData4.setTagName("Ayşe");
        publishData4.setTime(date_time_list2);
        publishData4.setZoneId(1);
        publishData4.setTimeDiff(0);
        publishData4.setxCoord(xCoord2);
        publishData4.setyCoord(ycoord2);
        publishDataList2.add(publishData4);

        PublishData publishData5 = new PublishData();
        publishData5.setTagName("Ahmet");
        publishData5.setTime(date_time_list2);
        publishData5.setZoneId(1);
        publishData5.setTimeDiff(0);
        publishData5.setxCoord(xCoord2+50);
        publishData5.setyCoord(ycoord2-50);
        publishDataList2.add(publishData5);

        PublishData publishData6 = new PublishData();
        publishData6.setTagName("Veli");
        publishData6.setTime(date_time_list2);
        publishData6.setZoneId(1);
        publishData6.setTimeDiff(0);
        publishData6.setxCoord(xCoord2+80);
        publishData6.setyCoord(ycoord2-80);
        publishDataList2.add(publishData6);

        List<PublishData> lastPublishDataList2 = new ArrayList<>();
        List<PublishData> lastPublishDataList1 = new ArrayList<>();


        lastPublishDataList1.addAll(publishDataList1);
        lastPublishDataList2.addAll(publishDataList2);

        System.out.println(date_time_list1);
        System.out.println(date_time_list2);

        long miliseconds1 = date_time_list1.getTime();
        long miliseconds2 = date_time_list2.getTime();


        for (int i = 0; i < 20; i++) {

            Date newDate1 = new Date();
            miliseconds1 += 2000;
            newDate1.setTime(miliseconds1);

            Date newDate2 = new Date();
            miliseconds2 += 2000;
            newDate2.setTime(miliseconds2);


            xCoord1 += (i+1)*2;
            ycoord1 += (i+1)*3;
            xCoord2 += (i+1)*4;
            ycoord2 += (i+1)*5;


            for (int j = 0; j < publishDataList1.size(); j++) {



                PublishData newPublishData1 = new PublishData();

                newPublishData1.setTagName(publishDataList1.get(j).getTagName());
                newPublishData1.setZoneId(publishDataList1.get(j).getZoneId());
                newPublishData1.setSensorInfo("sampale sensor info");
                newPublishData1.setTime(newDate1);//date_time_list1
                newPublishData1.setTimeDiff(2000);
                newPublishData1.setxCoord(xCoord1);
                newPublishData1.setyCoord(ycoord1);

                lastPublishDataList1.add(newPublishData1);


                PublishData newPublishData2 = new PublishData();

                newPublishData2.setTagName(publishDataList2.get(j).getTagName());
                newPublishData2.setZoneId(publishDataList2.get(j).getZoneId());
                newPublishData2.setSensorInfo("sampale sensor info");
                newPublishData2.setTime(newDate2);//date_time_list1
                newPublishData2.setTimeDiff(2000);
                newPublishData2.setxCoord(xCoord2);
                newPublishData2.setyCoord(ycoord2);

                lastPublishDataList2.add(newPublishData2);


            }


        }

        System.out.println(lastPublishDataList1);
        System.out.println(lastPublishDataList2);





        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

        for (int i = 0; i < lastPublishDataList1.size(); i += 3) {

            try {

                String jsonData1 = ow.writeValueAsString(lastPublishDataList1.get(i));
                String jsonData2 = ow.writeValueAsString(lastPublishDataList1.get(i+1));
                String jsonData3 = ow.writeValueAsString(lastPublishDataList1.get(i+2));

                String jsonData4 = ow.writeValueAsString(lastPublishDataList2.get(i));
                String jsonData5 = ow.writeValueAsString(lastPublishDataList2.get(i+1));
                String jsonData6 = ow.writeValueAsString(lastPublishDataList2.get(i+2));

                try {

                    MqttClient client = new MqttClient("tcp://127.0.0.1:1883", MqttClient.generateClientId());
                    client.connect();


                    MqttMessage message1 = new MqttMessage();
                    MqttMessage message2 = new MqttMessage();
                    MqttMessage message3 = new MqttMessage();

                    MqttMessage message4 = new MqttMessage();
                    MqttMessage message5 = new MqttMessage();
                    MqttMessage message6 = new MqttMessage();

                    message1.setPayload(jsonData1.getBytes());
                    message3.setPayload(jsonData3.getBytes());
                    message2.setPayload(jsonData2.getBytes());

                    client.publish("iot_data", message1);
                    client.publish("iot_data", message2);
                    client.publish("iot_data", message3);

                    Thread.sleep(1000);

                    message4.setPayload(jsonData4.getBytes());
                    message5.setPayload(jsonData5.getBytes());
                    message6.setPayload(jsonData6.getBytes());

                    client.publish("iot_data", message4);
                    client.publish("iot_data", message5);
                    client.publish("iot_data", message6);

                    Thread.sleep(1000);

                    client.disconnect();



                } catch (MqttException e) {
                    e.printStackTrace();
                }

            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }






        SpringApplication.run(MqttbrokerApplication.class, args);
    }
}
