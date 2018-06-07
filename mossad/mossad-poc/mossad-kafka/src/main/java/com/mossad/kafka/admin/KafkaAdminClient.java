package com.mossad.kafka.admin;

import com.mossad.kafka.KafkaPropertiesFactory;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.DescribeClusterResult;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.KafkaFuture;
import org.apache.kafka.common.Node;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;


public final class KafkaAdminClient {

    public static void main(String ... args){
        Properties properties = KafkaPropertiesFactory.getProperties();
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
        node.whenComplete((nodeRed, throwable) -> {
            String idString = nodeRed.idString();

         });


    }
}