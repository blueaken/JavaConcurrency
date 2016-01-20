package com.blueaken.playground.nike;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * Author: blueaken
 * Date: 9/26/14 3:24 下午
 */
public class PostMultiThreadTest {
    public static long timeTasks(int nThreads, final Runnable task1, final Runnable task2)
            throws InterruptedException {
        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(nThreads);

        // one thread 2 task case
        for (int i = 0; i < nThreads; i++) {
            Thread t = new Thread() {
                public void run() {
                    task1.run();
                    task2.run();
//                    try {
//                        startGate.await();
//                        try {
//                            task1.run();
//                            task2.run();
//                        } finally {
//                            endGate.countDown();
//                        }
//                    }
//                    catch (InterruptedException ignored) {
//                    }
                }
            };
            t.start();
        }

        //2 thread same task case
//        Thread t1 = new Thread() {
//            public void run() {
////                try {
//                    task1.run();
////                    startGate.await();
////                    try {
////                        task1.run();
////                    } finally {
////                        endGate.countDown();
////                    }
////                }
////                catch (InterruptedException ignored) {
////                }
//            }
//        };
//        t1.start();
//        System.out.println("Thread 1 State after start:" + t1.getState());

//        Thread t2 = new Thread() {
//            public void run() {
////                try {
//                task2.run();
////                    startGate.await();
////                    try {
////                        task1.run();
////                    } finally {
////                        endGate.countDown();
////                    }
////                }
////                catch (InterruptedException ignored) {
////                }
//            }
//        };
//        t2.start();
//        System.out.println("Thread 2 State after start:" + t2.getState());
//
        long start = System.nanoTime();
        //startGate.countDown();

//        System.out.println("Thread 1 State after countDown:" + t1.getState());
//        System.out.println("Thread 2 State after countDown:" + t2.getState());
//
        //endGate.await();
        long end = System.nanoTime();

//        System.out.println("Thread 1 State after await:" + t1.getState());
//        System.out.println("Thread 2 State after await:" + t2.getState());

        return end-start;
    }

    public static void main(String[] args){
        TaskCampaign taskCampaign = new TaskCampaign();
        TaskTemplate taskTemplate = new TaskTemplate();
        TaskBulkPost taskBulkPost = new TaskBulkPost();

        try {
//            long elapsedTime = timeTasks(1, taskCampaign, taskTemplate);
            long elapsedTime = timeTasks(1, taskTemplate, taskCampaign);
//            long elapsedTime = timeTasks(1, taskBulkPost, taskBulkPost);
            long seconds = elapsedTime / 1000;
            System.out.println("total elapsed seconds :" + seconds);
        } catch (InterruptedException ie){
            System.out.println(ie.getMessage());
        }
    }

    static class TaskBulkPost implements Runnable{
        //        private int counter = 0;
        public void run() {
//            counter++;
//            System.out.println("Task1 : Counter : " + counter);
            System.out.println("Task bulk post started ...");
            printCurrentThreadName();
            String endpoint = "http://plusv3.test.nikecloud.com/plus/v3/notifications/requests/bulk";
            String payload = "{\n" +
                    "\t\"notification_type\": \"CHALLENGE.ACCEPT.WARNING\",\n" +
                    "\t\"data\": [{\n" +
                    "\t\t\"recipient_user_id\": \"12908758052\",\n" +
                    "\t\t\"recipient_app_id\": \"nikeplusgps\",\n" +
                    "\t\t\"locale_language\": \"es\",\n" +
                    "\t\t\"locale_country\": \"AR\",\n" +
                    "\t\t\"content\": {\n" +
                    "\t\t\t\"challengeId\": \"23f3bbee-440a-11e4-a183-164230d1df67\",\n" +
                    "\t\t\t\"challenger_first_name\": \"Joe\"\n" +
                    "\t\t}\n" +
                    "\t}, {\n" +
                    "\t\t\"recipient_user_id\": \"12908783054\",\n" +
                    "\t\t\"recipient_app_id\": \"nikeplusgps\",\n" +
                    "\t\t\"locale_language\": \"es\",\n" +
                    "\t\t\"locale_country\": \"LA\",\n" +
                    "\t\t\"content\": {\n" +
                    "\t\t\t\"challengeId\": \"23f3bbee-440a-11e4-a183-164230d1df67\",\n" +
                    "\t\t\t\"challenger_first_name\": \"Joe\"\n" +
                    "\t\t}\n" +
                    "\t}, {\n" +
                    "\t\t\"recipient_user_id\": \"12908758051\",\n" +
                    "\t\t\"recipient_app_id\": \"nikeplusgps\",\n" +
                    "\t\t\"locale_country\": \"US\",\n" +
                    "\t\t\"content\": {\n" +
                    "\t\t\t\"challengeId\": \"23f3bbee-440a-11e4-a183-164230d1df67\",\n" +
                    "\t\t\t\"challenger_first_name\": \"Joe\"\n" +
                    "\t\t}\n" +
                    "\t}, {\n" +
                    "\t\t\"recipient_user_id\": \"12908749036\",\n" +
                    "\t\t\"recipient_app_id\": \"nikeplusgps\",\n" +
                    "\t\t\"locale_country\": \"MX\",\n" +
                    "\t\t\"content\": {\n" +
                    "\t\t\t\"challengeId\": \"23f3bbee-440a-11e4-a183-164230d1df67\",\n" +
                    "\t\t\t\"challenger_first_name\": \"Joe\"\n" +
                    "\t\t}\n" +
                    "\t}, {\n" +
                    "\t\t\"recipient_user_id\": \"12908749035\",\n" +
                    "\t\t\"recipient_app_id\": \"nikeplusgps\",\n" +
                    "\t\t\"locale_language\": \"en\",\n" +
                    "\t\t\"content\": {\n" +
                    "\t\t\t\"challengeId\": \"23f3bbee-440a-11e4-a183-164230d1df67\",\n" +
                    "\t\t\t\"challenger_first_name\": \"Joe\"\n" +
                    "\t\t}\n" +
                    "\t}, {\n" +
                    "\t\t\"recipient_user_id\": \"12908749034\",\n" +
                    "\t\t\"recipient_app_id\": \"nikeplusgps\",\n" +
                    "\t\t\"content\": {\n" +
                    "\t\t\t\"challengeId\": \"23f3bbee-440a-11e4-a183-164230d1df67\",\n" +
                    "\t\t\t\"challenger_first_name\": \"Joe\"\n" +
                    "\t\t}\n" +
                    "\t}]\n" +
                    "}";
            postRest(endpoint, payload, false);
        }
    }

    static class TaskCampaign implements Runnable{
//        private int counter = 0;
        public void run() {
//            counter++;
//            System.out.println("Task1 : Counter : " + counter);
            System.out.println("Task campaign post started ...");
            String endpoint = "http://plusv3.test.nikecloud.com/plus/v3/notifications/requests/campaign";
            String payload = "\n" +
                    "{\n" +
                    "  \"notification_type\": \"ucp:campaign:pushstored\",\n" +
                    "  \"template_name\": \"ucp:campaign:pushstored_25\",\n" +
                    "  \"locale_language\": \"en\",\n" +
                    "  \"locale_country\": \"US\",\n" +
                    "  \"recipient_users\": [\n" +
                    "    \"12908779041\"\n" +
                    "  ],\n" +
                    "  \"content\": {\n" +
                    "    \"key\": \"value\"\n" +
                    "  }\n" +
                    "}";
            postRest(endpoint, payload, false);
        }
    }

    static class TaskTemplate implements Runnable{
//        private int counter = 0;
        public void run() {
//            counter++;
//            System.out.println("Task2 : Counter : " + counter);
            System.out.println("Task template post started ...");
            printCurrentThreadName();
            String endpoint = "http://plusv3-ext.test.nikecloud.com/plus/v3/notifications/template";
            String payload = "{\n" +
                    "\t\"template_name\": \"ucp:campaign:pushstored_25\",\n" +
                    "\t\"ttl\": 90,\n" +
                    "\t\"template_default_content\": \"You just earned a reward.\",\n" +
                    "\t\"translations\": [{\n" +
                    "\t\t\"language\": \"en\",\n" +
                    "\t\t\"language_default_content\": \"You just earned a reward.\",\n" +
                    "\t\t\"countries\": [\n" +
                    "\t\t\t\"US\",\n" +
                    "\t\t\t\"GB\"\n" +
                    "\t\t],\n" +
                    "\t\t\"variants\": [],\n" +
                    "\t\t\"template_content\": \"You just earned a reward.\"\n" +
                    "\t}]\n" +
                    "}";
            postRest(endpoint, payload, true);
        }
    }

    private static void postRest(String endpoint, String payload, boolean isViaRouter){
        try {

            URL url = new URL(endpoint);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            if (!isViaRouter){
                conn.setRequestProperty("X-Test", "testskipapigee");
            }else{
                conn.setRequestProperty("X-Nike-Authorization", "Bearer eyJhbGciOiJSUzI1NiJ9.eyJzY3AiOlsicmVhZCIsIndyaXRlIl0sImV4cCI6MTQ1MjYxODMzOCwic3ViIjoiZXNiIiwiYXVkIjpbIm5vdGlmaWNhdGlvbnJvdXRlciJdLCJpc3MiOiJlc2IifQ.XhrG_Nb_qsZO0KdVbctlsz8Lw8SMYn_sthhgWDeQotleE19StaBI1xJJUKPbOQV_oBqDKbZyvzYiDLgtY2BQ787tsVSrM4CLHQ7Jniia-wYugABSW1kjOqidxxbVEC5mJzUWQzT4pbv-pdnL3wB-EUGL06L5EYaZ_Z7U3aHELLEHxLHkr4aW-KHScIYNSIV6hl6pNM4X6da9po7ezxZRAZmG0ngfvAy5kCwSQaapj3lboa97jlDDvwIagVzHqHuE3ZfC38vnKVbLmBo48-840JuFH0sT3vKXsEchPhFvZYeH1dXxaenJWANMPrSRFZ6BPejYV9WshPzaHMeMfZw0tg");
                        conn.setRequestProperty("X-Nike-AppId", "esb");
            }

            OutputStream os = conn.getOutputStream();
            os.write(payload.getBytes());
            os.flush();

            StringBuilder builder = new StringBuilder();
            builder.append(conn.getResponseCode())
                    .append(" ")
                    .append(conn.getResponseMessage())
                    .append("\n");

            Map<String, List<String>> map = conn.getHeaderFields();
            for (Map.Entry<String, List<String>> entry : map.entrySet())
            {
                if (entry.getKey() == null)
                    continue;
                builder.append( entry.getKey())
                        .append(": ");

                List<String> headerValues = entry.getValue();
                Iterator<String> it = headerValues.iterator();
                if (it.hasNext()) {
                    builder.append(it.next());

                    while (it.hasNext()) {
                        builder.append(", ")
                                .append(it.next());
                    }
                }

                builder.append("\n");
            }
            System.out.println(builder);

            conn.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void printCurrentThreadName() {
        Thread t = Thread.currentThread();
        String name = t.getName();
        System.out.println("Current thread name=" + name);
    }

    private static void displayStateAndIsAlive(Thread thread) {
        // java.lang.Thread.State can be NEW, RUNNABLE, BLOCKED, WAITING, TIMED_WAITING, TERMINATED
        System.out.println("State:" + thread.getState());
        System.out.println("Is alive?:" + thread.isAlive());
    }

}

