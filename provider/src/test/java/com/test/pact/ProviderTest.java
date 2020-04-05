package com.test.pact;

import au.com.dius.pact.provider.junit.PactRunner;
import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.TargetRequestFilter;
import au.com.dius.pact.provider.junit.VerificationReports;
import au.com.dius.pact.provider.junit.loader.PactBroker;
import au.com.dius.pact.provider.junit.loader.PactFolder;
import au.com.dius.pact.provider.junit.loader.PactFolderLoader;
import au.com.dius.pact.provider.junit.loader.PactSource;
import au.com.dius.pact.provider.junit.target.HttpTarget;
import au.com.dius.pact.provider.junit.target.Target;
import au.com.dius.pact.provider.junit.target.TestTarget;
import com.test.provider.StudentApplication;
import org.apache.http.HttpRequest;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;

import java.util.Arrays;

@RunWith(PactRunner.class)
@Provider("studentProvider")
@PactFolder("../generated-pacts")
@VerificationReports(value = { "console", "markdown", "json" })
public class ProviderTest {

    private int PORT = 8099;

    @TestTarget
    public final Target target = new HttpTarget("http", "localhost", PORT);

    @BeforeClass
    public static void start() {

        SpringApplication.run(StudentApplication.class);

    }

    @TargetRequestFilter
    public void printTheRequestHeaders(HttpRequest request) {
        Arrays.asList(request.getAllHeaders())
                .forEach(header -> System.out.println(header.getName() + "->" + header.getValue()));
    }

}
