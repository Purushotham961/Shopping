package purushotham.resources;

import org.apache.hc.core5.http.impl.io.MonitoringResponseOutOfOrderStrategy;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportsNg 
{
	public static ExtentReports getReport()
	{
		String filePath = System.getProperty("user.dir")+"//Reports//index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(filePath);
		reporter.config().setDocumentTitle("Shopping test");
		reporter.config().setReportName("Web Automation Report");
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Purushotham Vilasagaram");
		return extent;
	}
}
