package com.example.scheduleProject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

@SpringBootApplication
public class Application {

    private static final Logger log = LoggerFactory.getLogger("com.example.scheduleProject");

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        log.info("----START----");

        //HashSet
        /*ArrayList<String> list = new ArrayList<>();

        String s = new String("asd1");
        String s1 = new String("asd2");
        String s2 = new String("asd3");
        String s3 = new String("asd4");
        LinkedList<String> linkedList = new LinkedList<>(list);

        linkedList.add(s);
        linkedList.add(s1);
        linkedList.add(s2);
        linkedList.add(s3);

        //ArrayList<String> list1 = new ArrayList<>(list);

        //log.info(list1.toString());
        log.info(String.valueOf(linkedList.indexOf(s)));
        log.info(String.valueOf(linkedList.indexOf(s1)));
        list.clear();
        //list1.clear();*/


    }

}
