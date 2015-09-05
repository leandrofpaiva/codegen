#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.restful;

import java.util.Collections;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class AnuncioTest {

    private final RestTemplate restTemplate = new RestTemplate();
    private final static String URL = "http://localhost/default/api/";

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }

    //@Test
    public void testListAll() {

        HttpEntity<?> requestEntity = getHttpEntity();
        ResponseEntity<String> responseEntity = restTemplate.exchange(URL
                + "Daycare/listAll", HttpMethod.GET, requestEntity,
                String.class);
        
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);

    }
    
    private HttpEntity<?> getHttpEntity() {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setAccept(Collections.singletonList(new MediaType("application", "json")));
        HttpEntity<?> requestEntity = new HttpEntity(requestHeaders);
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        return requestEntity;
    }

    private HttpHeaders getContentType() {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(new MediaType("application", "json"));
        return requestHeaders;

    }

    /*
     * public void testCreate() { HttpEntity<Supplier> requestEntity = new
     * HttpEntity<>(obj, getContentType()); ResponseEntity<String>
     * responseEntity = restTemplate. exchange(URL + "api/supplier/create",
     * HttpMethod.POST, requestEntity, String.class);
     * System.out.println(" THE RESPONSE BODY " + responseEntity.getBody());
     * System.out.println(" THE RESPONSE STATUS CODE " +
     * responseEntity.getStatusCode());
     * System.out.println(" THE RESPONSE IS HEADERS " +
     * responseEntity.getHeaders());
     * Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK); }
     */

}
