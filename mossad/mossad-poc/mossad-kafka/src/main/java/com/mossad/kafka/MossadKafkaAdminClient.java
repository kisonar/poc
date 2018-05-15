package com.mossad.kafka;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.DescribeClusterResult;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.KafkaFuture;
import org.apache.kafka.common.Node;


public final class MossadKafkaAdminClient {

    public static void main(String ... args){
        Properties properties = PropertiesFactory.getProperties();
        AdminClient adminClient = AdminClient.create(properties);

        NewTopic topic = new NewTopic("myTopic",1, Short.parseShort("1"));

        Set<NewTopic> topicSet = new HashSet<>();
        topicSet.add(topic);
        adminClient.createTopics(topicSet);

        ListTopicsResult listTopicsResult= adminClient.listTopics();
        KafkaFuture<Set<String>> names =  listTopicsResult.names();
        names.whenComplete((strings, throwable) -> {
            strings.forEach(s -> {
                System.out.print(s);
            });
        });

         DescribeClusterResult describeClusterResult = adminClient.describeCluster();
         KafkaFuture<Node> node=  describeClusterResult.controller();
         node.whenComplete((node1, throwable) -> {
             String idString = node1.idString();
         });


    }
}
