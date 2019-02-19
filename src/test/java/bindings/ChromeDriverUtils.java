package bindings;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

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

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("headless");
        chromeOptions.addArguments("disable-gpu");
        chromeOptions.addArguments("no-sandbox");
        chromeOptions.addArguments("--window-size=1920,2000");

        try {
            String chromeDriverPath = System.getProperty("user.dir") + getChromeDriverPath();
            setExecutableMode(chromeDriverPath);
            System.out.println("---- Opening chrome browser");
            System.setProperty("webdriver.chrome.driver", chromeDriverPath);
            //driver = new ChromeDriver();
            driver = new ChromeDriver(chromeOptions);
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

