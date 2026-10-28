import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ProductSearchTest {

    public static void main(String[] args) {

        WebDriver driver = null;

        try {

            // Setup ChromeDriver
            WebDriverManager.chromedriver().setup();

            // Launch Chrome
            driver = new ChromeDriver();

            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            // Navigate to Amazon
            driver.get("https://www.amazon.in");

            // Search for Laptop
            WebElement searchInput = driver.findElement(By.id("twotabsearchtextbox"));
            searchInput.sendKeys("Laptop");
            searchInput.sendKeys(Keys.ENTER);

            // Wait for search results
            Thread.sleep(3000);

            // Verify search results
            List<WebElement> searchResults = driver.findElements(
                    By.cssSelector("div[data-component-type='s-search-result']")
            );
            System.out.println("---------------------------------------------------------------");

            if (searchResults.size() > 0) {
                System.out.println("Search results displayed.");
                System.out.println("Products Found: " + searchResults.size());
            } else {
                System.out.println("Defect: Search results not displayed.");
            }

        } catch (Exception e) {

            System.out.println("Error: " + e.getMessage());

        } finally {

            if (driver != null) {
                driver.quit();
            }

            System.out.println("Browser Closed.");
            System.out.println("Test Completed.");

            System.exit(0);
        }
    }
}