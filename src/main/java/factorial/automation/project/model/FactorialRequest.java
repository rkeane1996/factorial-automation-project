package factorial.automation.project.model;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.util.MultiValueMap;

public class FactorialRequest {

    private HttpHeaders headers;
    private MultiValueMap<String, String> map;
    private HttpEntity<MultiValueMap<String, String>> request;
    private String url = "http://qainterview.pythonanywhere.com/factorial";

    public String getUrl() {
        return url;
    }
    public HttpEntity<MultiValueMap<String, String>> getRequest() {
        return request;
    }

    public void setRequest(HttpEntity<MultiValueMap<String, String>> request) {
        this.request = request;
    }

    public HttpHeaders getHeaders() {
        return headers;
    }

    public void setHeaders(HttpHeaders headers) {
        this.headers = headers;
    }

    public MultiValueMap<String, String> getMap() {
        return map;
    }

    public void setMap(MultiValueMap<String, String> map) {
        this.map = map;
    }


}
