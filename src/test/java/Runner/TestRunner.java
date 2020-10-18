package Runner;


import org.junit.runner.RunWith;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features="src/test/resources/features",
		glue= {"StepDefinitions"},
		tags="@Login",		//@ProfileUpdate",//@CaptureTweets",// and @ProfileUpdate",
		dryRun=false,
		//monochrome = true,
		plugin={"pretty","html:target/HtmlReports",
				"json:target/JSONReports/report.json",
		"junit:target/JUnitReports/report.xml"}
		)

public class TestRunner {

}
