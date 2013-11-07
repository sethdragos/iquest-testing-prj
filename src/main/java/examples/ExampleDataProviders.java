package examples;

import org.testng.annotations.DataProvider;

/**
 * Created with IntelliJ IDEA.
 * User: dragos.serghie
 * Date: 11/7/13
 */
public class ExampleDataProviders {

    @DataProvider(name = "firstDataProvider")
    public static Object[][] firstData() {
        Object[][] objectArray = {
                {"001", "John", "London"},
                {"002", "George", "Cluj"}
        };
        return (objectArray);
    }

    @DataProvider(name = "secondDataProvider")
    public static Object[][] secondData() {
        Object[][] objectArray = {
                {"Jacques", "Toulon"},
                {"Shivam", "Agra"},
                {"Ghita", "Turda"}
        };
        return (objectArray);
    }
}
