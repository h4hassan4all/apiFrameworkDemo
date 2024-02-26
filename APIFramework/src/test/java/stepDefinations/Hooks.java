package stepDefinations;

import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.cucumber.java.Before;
import org.testng.annotations.BeforeTest;

public class Hooks {


	ExtentSparkReporter htmlReporter;

	ExtentReports extent;
	//helps to generate the logs in the test report.
	ExtentTest test;

	@BeforeTest
	public void startReport() {
		// initialize the HtmlReporter
		htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir") +"/test-output/testReport.html");

		//initialize ExtentReports and attach the HtmlReporter
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);


		//configuration items to change the look and feel
		//add content, manage tests etc
//		htmlReporter.config().
		htmlReporter.config().setDocumentTitle("Simple Automation Report");
		htmlReporter.config().setReportName("Test Report");
//		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.STANDARD);
		htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
		System.out.println("Hello Extent");
	}

	@Before("@DeletePlace")
	public void beforeScenario() throws IOException
	{		//execute this code only when place id is null
		//write a code that will give you place id
		
		StepDefination m =new StepDefination();
		if(StepDefination.place_id==null)
		{
		
		m.add_Place_Payload_with("Shetty", "French", "Asia");
		m.user_calls_with_http_request("AddPlaceAPI", "POST");
		m.verify_place_Id_created_maps_to_using("Shetty", "getPlaceAPI");
		}
		
		

	}
}
