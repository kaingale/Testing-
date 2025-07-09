package com.tests;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

public class WebDriverOptionsSetup {
	//Chrome browser
    public ChromeOptions initChromeOptions() {
    	ChromeOptions options = new ChromeOptions();

//      Disable Chrome's "Save password" prompt and notifications
        Map<String, Object> prefs = new HashMap<String, Object>();
//        prefs.put("credentials_enable_service", false);
//        prefs.put("profile.password_manager_enabled", false);
//        prefs.put("profile.default_content_setting_values.notifications", 2);

//        options.setExperimentalOption("prefs", prefs);
//        options.addArguments("--disable-notifications");
//        options.addArguments("--disable-popup-blocking");
        options.addArguments("--incognito");
//        options.addArguments("--disable-extensions");
//        options.addArguments("user-data-dir=/tmp/temporary-profile");
        if (System.getenv("CI") != null) {
            options.addArguments("--headless=new");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
        }
        try {
            String tempDir = java.nio.file.Files.createTempDirectory("chrome-user-data").toString();
            options.addArguments("--user-data-dir=" + tempDir);
        } catch (IOException e) {
            System.out.println("Failed to create temp user data dir: " + e.getMessage());
        }
        
        return options;
    }
    
    // Edge browser
    public EdgeOptions initEdgeOptions() {
        EdgeOptions options = new EdgeOptions();

        Map<String, Object> prefs = new HashMap<>();
//        prefs.put("credentials_enable_service", false);
//        prefs.put("profile.password_manager_enabled", false);
//        prefs.put("profile.default_content_setting_values.notifications", 2);

//        options.setExperimentalOption("prefs", prefs);
//        options.addArguments("--disable-notifications");
//        options.addArguments("--disable-popup-blocking");
        options.addArguments("--inprivate"); // Edge's incognito mode
//        options.addArguments("--disable-extensions");
//        options.addArguments("user-data-dir=/tmp/temporary-profile-edge");

        return options;
    }

    // Firefox - browser
    public FirefoxOptions initFirefoxOptions() {
        FirefoxOptions options = new FirefoxOptions();

        // Disable notifications and password manager via profile preferences
//        options.addPreference("signon.rememberSignons", false);
//        options.addPreference("permissions.default.desktop-notification", 2);
//        options.addPreference("dom.webnotifications.enabled", false);
//        options.addPreference("dom.webnotifications.serviceworker.enabled", false);

        // Incognito mode (private browsing)
        options.addArguments("-private");

        // Disable extensions (by using a temporary profile)
//        options.addArguments("-profile");
//        options.addArguments("/tmp/temporary-profile-firefox");

        return options;
    }
    
}

