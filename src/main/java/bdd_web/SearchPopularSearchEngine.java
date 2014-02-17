package bdd_web;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;
import org.jbehave.core.Embeddable;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.CandidateSteps;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.jbehave.core.steps.SilentStepMonitor;
import org.jbehave.web.selenium.SeleniumConfiguration;
import org.jbehave.web.selenium.SeleniumContext;
import org.jbehave.web.selenium.SeleniumStepMonitor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dragos.serghie
 */
public class SearchPopularSearchEngine extends JUnitStories {
    private final Selenium selenium = new DefaultSelenium("127.0.0.1", 4444, "*googlechrome", "http://www.google.com/");
    private final SeleniumContext seleniumContext = new SeleniumContext();

    @Override
    public Configuration configuration() {
        Class<? extends Embeddable> embeddableClass = this.getClass();
        return new SeleniumConfiguration().useSelenium(selenium).useSeleniumContext(seleniumContext).useStepMonitor(new SeleniumStepMonitor(selenium, seleniumContext, new SilentStepMonitor()))
                .useStoryLoader(new LoadFromClasspath(embeddableClass)).useStoryReporterBuilder(new StoryReporterBuilder().withFormats(Format.CONSOLE, Format.HTML));
    }

    @Override
    public List<CandidateSteps> candidateSteps() {
        return new InstanceStepsFactory(configuration(), new SearchPopularSearchEngineSteps(selenium)).createCandidateSteps();
    }

    @Before
    public void setUp() throws Exception {
        selenium.start();
    }

    @After
    public void tearDown() throws Exception {
        selenium.stop();
    }

    @Override
    @Test
    public void run() throws Throwable {
        super.run();
    }

    @Override
    protected List<String> storyPaths() {
        return new StoryFinder().findPaths(CodeLocations.codeLocationFromClass(this.getClass()).getFile(), Arrays.asList("**/search_popular_search_engine.story"), null);
    }
}
