package com.test.pact;

import au.com.dius.pact.provider.junit.PactRunner;
import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.TargetRequestFilter;
import au.com.dius.pact.provider.junit.VerificationReports;
import au.com.dius.pact.provider.junit.loader.PactFolder;
import au.com.dius.pact.provider.junit.target.HttpTarget;
import au.com.dius.pact.provider.junit.target.Target;
import au.com.dius.pact.provider.junit.target.TestTarget;
import com.test.consumer.StudentConsumerApplication;
import one.xingyi.pactstubber.ConfigBasedStubber;
import one.xingyi.pactstubber.ServerSpec;
import org.apache.http.HttpRequest;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RunWith(PactRunner.class)
@Provider("myconsumer")
@PactFolder("../generated-consumer-pacts")
@VerificationReports(value = {"console", "markdown", "json"})
public class PactStubberTest {

    private int PORT = 8097;

    @TestTarget
    public final Target target = new HttpTarget("http", "localhost", PORT);

    private static ConfigurableApplicationContext applicationContext;

    private static ConfigBasedStubber stubber;
    private static ExecutorService executors;



    @BeforeClass
    public static void start() {

        executors= Executors.newCachedThreadPool();
        //start a stub server utilising pacts ,instead of the actual provider
        stubber = ConfigBasedStubber.apply(ServerSpec.forHttpValidation("stub-provider", 8099, "../generated-pacts", false)
                , ResourceBundle.getBundle("messages")
                , executors);


        applicationContext = SpringApplication.run(StudentConsumerApplication.class);

    }

    @AfterClass
    public static void tearDown() {
        executors.shutdownNow();
        stubber.shutdown();
    }


    @TargetRequestFilter
    public void printTheRequestHeaders(HttpRequest request) {
        Arrays.asList(request.getAllHeaders())
                .forEach(header -> System.out.println(header.getName() + "->" + header.getValue()));
    }


}

