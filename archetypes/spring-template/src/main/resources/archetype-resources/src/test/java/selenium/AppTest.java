#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.selenium;

import static org.testng.Assert.assertEquals;

import com.thoughtworks.selenium.DefaultSelenium;


/**
 * Unit test for simple App.
 */
public class AppTest
{
    private DefaultSelenium selenium;

    //@BeforeClass
    public void setUp()
    {
        selenium = new DefaultSelenium("localhost",4444,"*firefox","http://www.google.com/");
        selenium.start();
    }


    //@Test
    public void testApp()
    {
        selenium.open("/");
        selenium.type("q","Selenium");
        selenium.click("btnG");
        assertEquals(selenium.getTitle(), "Google");
    }

    //@AfterClass
    public void tearDown()
    {
        selenium.stop();
    }
}