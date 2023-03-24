package testComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {

	int count = 0;
	final int max = 2;
	
	@Override
	public boolean retry(ITestResult result) {

		if(count < max)
		{
			count++;
			return true;
		}
		
		return false;
	}

	
}
