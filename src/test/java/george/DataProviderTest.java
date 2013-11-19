package george;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class DataProviderTest {

    @DataProvider(name = "firstDataProvider")
    public static Object[][] firstData() {
        Object[][] objectArray = new Object[][]{
                {"Mos Craciun", "din Maramures", "a fost aici"}
        };
        return (objectArray);
    }

    @DataProvider(name = "secondDataProvider")
    public static Object[][] secondData() {
        Object[][] objectArray = {
                  {"007", "Gheorghe", "Cluj-Napoca"}
        };
        return (objectArray);
    }

    @Test(testName = "Test One",
            dataProvider = "firstDataProvider")
    public void firstTest(String name, String zone, String city) {

        System.out.println("Starting test method..");
        System.out.println("Agent " + name + " a venit " + zone + " si " + city);
        System.out.println("Test One OK");

        assertEquals(name, "Mos Craciun", "Could not finish test with success");

        System.out.println("Finished test method with success :)");
    }
    @Test(testName = "Test Two",
            dataProvider = "secondDataProvider")
    public void secondTest(String number, String zone, String city) {

        System.out.println("Starting test method..");
        System.out.println("Agent " + number + " zis " + zone + " e din " + city);
        System.out.println("Test Two OK");

        assertEquals(number, "007", "Could not finish test with success");

        System.out.println("Finished test method with success :)");
    }
}
