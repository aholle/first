package bindings;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class ChromeDriverUtils {
    private static String getChromeDriverPath() {

        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.contains("win")) {
            return "/resources/drivers/windows/chromedriver.exe";
        }
        if (osName.contains("mac")) {
            return "/resources/drivers/mac/chromedriver";
        }
        return "/resources/drivers/linux/chromedriver";
    }

    private static void setExecutableMode(String path) {
        final File file = new File(path);
        file.setReadable(true, false);
        file.setExecutable(true, false);
        file.setWritable(true, false);
    }

    public static WebDriver openChromeBrowser(String baseURL) {
        WebDriver driver = null;
        try {
            String chromeDriverPath = System.getProperty("user.dir") + getChromeDriverPath();
            setExecutableMode(chromeDriverPath);
            System.out.println("---- Opening chrome browser");
            //DesiredCapabilities capability = new DesiredCapabilities();
            System.setProperty("webdriver.chrome.driver", chromeDriverPath);
            //capability.setJavascriptEnabled(true);
            //capability.setCapability("chrome.switches", "--start-maximized");
            //capability.setCapability(ChromeOptions.CAPABILITY, new ChromeOptions());
            //capability = DesiredCapabilities.chrome();
            //capability.setBrowserName("chrome");
            //driver = new ChromeDriver(capability);
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            driver.manage().window().maximize();
            driver.manage().deleteAllCookies();
            driver.get(baseURL);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
        return driver;
    }
}

