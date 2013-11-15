package dragos;

import examples.ExampleDataProviders;
import org.testng.annotations.*;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

/**
 * Created with IntelliJ IDEA.
 * User: dragos.serghie
 * Date: 11/4/13
 */
public class DataProvidersExampleTest {

    private int i = 0;

    @DataProvider(name = "firstDataProvider")
    public static Object[][] firstData() {
        Object[][] objectArray = {
                {"003", "James", "Manchester"},
                {"005", "Gheorghe", "Cluj-Napoca"}
        };
        return (objectArray);
    }

    @BeforeClass
    private void beforeClass() {
        System.out.println("Logging @BeforeClass..");
        System.out.println("Loading class " + this.getClass().getCanonicalName());
    }

    @BeforeMethod
    private void beforeMethode() {
        System.out.println("Logging @BeforeMethod..");
        i++;
    }

    @AfterMethod
    private void afterMethod() {
        System.out.println("Logging @AfterMethod..");
    }

    @AfterClass
    private void afterClass() {
        System.out.println("Logging @AfterClass..");
        System.out.println("Tests run: " + i);
    }

    @Test(testName = "My First Test",
            dataProviderClass = ExampleDataProviders.class,
            dataProvider = "firstDataProvider")
    public void fistTest(String number, String name, String city) {

        System.out.println("Starting test method..");
        System.out.println("Agent " + number + " is called " + name + " and lives in " + city);

        assertEquals(city, "London", "Could not finish test with success :(");

        System.out.println("Finished test method with success :)");
    }

    @Test(testName = "My Second Test",
            dataProviderClass = ExampleDataProviders.class,
            dataProvider = "secondDataProvider")
    public void secondTest(String name, String city) {

        System.out.println("Starting test method..");
        System.out.println("I'm " + name + " and come from " + city);

        assertNotNull(city, "We have a null city!");

        System.out.println("Finished test method with success :)");
    }

    @Test(testName = "My Third Test",
            dataProvider = "firstDataProvider")
    public void thirdTest(String number, String name, String city) {

        System.out.println("Starting test method..");
        System.out.println("Agent " + number + " is called " + name + " and lives in " + city);

        assertEquals(city, "Manchester", "Could not finish test with success :(");

        System.out.println("Finished test method with success :)");
    }
}
