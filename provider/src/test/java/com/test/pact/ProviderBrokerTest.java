package com.test.pact;

import au.com.dius.pact.provider.junit.PactRunner;
import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.TargetRequestFilter;
import au.com.dius.pact.provider.junit.VerificationReports;
import au.com.dius.pact.provider.junit.loader.PactBroker;
import au.com.dius.pact.provider.junit.loader.PactBrokerAuth;
import au.com.dius.pact.provider.junit.loader.PactFolder;
import au.com.dius.pact.provider.junit.target.HttpTarget;
import au.com.dius.pact.provider.junit.target.Target;
import au.com.dius.pact.provider.junit.target.TestTarget;
import com.test.provider.StudentApplication;
import com.test.provider.dto.Address;
import com.test.provider.dto.Student;
import com.test.provider.service.StudentService;
import org.apache.http.HttpRequest;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;
import java.util.Map;

@RunWith(PactRunner.class)
@Provider("studentProvider")
//https://github.com/DiUS/pact-jvm/tree/master/provider/pact-jvm-provider-junit#download-pacts-from-a-pact-broker
@PactBroker//refer readme
@VerificationReports(value = {"console", "markdown", "json"})
public class ProviderBrokerTest {

    private int PORT = 8099;

    @BeforeClass
    public static void setVersion(){
        //https://github.com/DiUS/pact-jvm/tree/master/provider/pact-jvm-provider-junit#publishing-verification-results-to-a-pact-broker
        System.setProperty("pact.provider.version","1.0.0");
        System.setProperty("pact.verifier.publishResults","true");
    }

    @TestTarget
    public final Target target = new HttpTarget("http", "localhost", PORT);

    private static ConfigurableApplicationContext applicationContext;


    @BeforeClass
    public static void start() {

        applicationContext = SpringApplication.run(StudentApplication.class);

    }

    @AfterClass
    public static void stop() {

        SpringApplication.exit(applicationContext);

    }

    @TargetRequestFilter
    public void printTheRequestHeaders(HttpRequest request) {
        Arrays.asList(request.getAllHeaders())
                .forEach(header -> System.out.println(header.getName() + "->" + header.getValue()));
    }

    @State("SomeState")
    public void withSomeState() {
        System.out.println("do something with state");
        StudentService studentService = applicationContext.getBean(StudentService.class);
        studentService.createStudent(new Student("Z123", "Jerry Van Dam", 27, new Address("line1", "line2", "postCode", "mycity")));
    }

    @State("SomeStateWithMap")
    public void withSomeStateAndMap(Map<String, Object> params) {
        System.out.println("do something with state");
        params.forEach((key, value) -> System.out.println(key + " -> " + value));
        StudentService studentService = applicationContext.getBean(StudentService.class);
        Map<String, Object> values = (Map<String, Object>) params.get("Mykey1");
        Student studentFromParamsInPactState = new Student(
                values.get("rollId").toString(),
                values.get("fullName").toString(),
                Integer.parseInt(values.get("age").toString()),
                new Address("line1", "line2", "postCode", "mycity"));
        studentService.createStudent(studentFromParamsInPactState);

    }

}
