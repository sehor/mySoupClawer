package clawer.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SingleBean {
   //@Bean
   public WebDriver myChromeDriver() {
	   System.setProperty("webdriver.chrome.driver","E:\\chromeDriver\\chromedriver.exe"); // 第二步：初始化驱动 
	   WebDriver driver = new ChromeDriver();
	   return driver;
   }
}
