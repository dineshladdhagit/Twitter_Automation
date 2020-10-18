package Runner;


import org.junit.runner.RunWith;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features="src/test/resources/features",
		glue= {"StepDefinitions"},
		tags="@TC02",
		dryRun=false
		//plugin = {"pretty","html:target/HTMLReports"}
		
		)

public class TestRunner {
	
}
