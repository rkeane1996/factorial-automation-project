package factorial.automation.project.tests;

import factorial.automation.project.model.Assertions;
import factorial.automation.project.model.FactorialRequest;
import factorial.automation.project.model.FactorialResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import java.util.Collections;

public class APITests {

    private RestTemplate restTemplate;
    private Assertions assertions;
    private FactorialRequest factorialRequest;

    @Before
    public void setup() {
        this.factorialRequest = new FactorialRequest();
        this.restTemplate = new RestTemplate();
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        this.restTemplate.getMessageConverters().add(mappingJackson2HttpMessageConverter);
        this.assertions = new Assertions();
        this.factorialRequest.setHeaders(new HttpHeaders());
        this.factorialRequest.getHeaders().setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        this.factorialRequest.setMap(new LinkedMultiValueMap<>());
    }

    @Test
    public void getSuccessfulResponseFromAPI(){
        this.factorialRequest.getMap().add("number", "22");
        this.factorialRequest.setRequest(new HttpEntity<>(this.factorialRequest.getMap(), this.factorialRequest.getHeaders()));
        FactorialResponse response = this.restTemplate.postForObject(this.factorialRequest.getUrl(), this.factorialRequest.getRequest() , FactorialResponse.class);
        Assert.assertEquals(assertions.ResultForNumber22, response.getAnswer());
    }

    @Test
    public void retrieveStatusCode500ForNonIntValue(){
        this.factorialRequest.getMap().add("number", "2.2");
        this.factorialRequest.setRequest(new HttpEntity<>(this.factorialRequest.getMap(), this.factorialRequest.getHeaders()));
        try {
            this.restTemplate.postForEntity(this.factorialRequest.getUrl(), this.factorialRequest.getRequest() , FactorialResponse.class);
        }catch(HttpServerErrorException errorResponse){
            Assert.assertEquals(500, errorResponse.getRawStatusCode());
        }
    }

    @Test
    public void retrieveStatusCode404ForIncorrectEndpoint(){
        this.factorialRequest.getMap().add("number", "22");
        this.factorialRequest.setRequest(new HttpEntity<>(this.factorialRequest.getMap(), this.factorialRequest.getHeaders()));
        try {
            this.restTemplate.postForEntity("http://qainterview.pythonanywhere.com/factor", this.factorialRequest.getRequest() , FactorialResponse.class);
        }catch(HttpClientErrorException errorResponse){
            Assert.assertEquals(404, errorResponse.getRawStatusCode());
        }
    }
}
