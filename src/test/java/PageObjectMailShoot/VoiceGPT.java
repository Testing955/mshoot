package PageObjectMailShoot;

import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.Authenticator;

public class VoiceGPT extends BaseClass {
    private WebDriver driver;

    public VoiceGPT(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void VoiceChatGPT() {
        String expectedTitle = "hgjk"; // Intentionally incorrect title for testing
        String actualTitle = driver.getTitle();
        System.out.println("Actual title fetched from the webpage: " + actualTitle);
        System.out.println("Expected title: " + expectedTitle);

        try {
            Assert.assertEquals(actualTitle, expectedTitle);
            System.out.println("The page title matched: " + actualTitle);
        } catch (AssertionError e) {
            System.out.println("Title mismatch detected. Expected: " + expectedTitle + ", but found: " + actualTitle);
            sendEmail(actualTitle, expectedTitle);
            throw e; // Rethrow the exception to ensure the test fails
        }
    }

    private void sendEmail(String actualTitle, String expectedTitle) {
        String to = "hestabitt@gmail.com";
        String cc = "prince.gaurav@hestabit.in";
        String from = "princehestabit7@gmail.com";

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        final String username = "princehestabit7@gmail.com";
        final String password = "iqjz thdc nddq ilrh";

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.addRecipient(Message.RecipientType.CC, new InternetAddress(cc));
            message.setSubject("Testing of Voice GPT");
            message.setText("Expected title was '" + expectedTitle + "', but found '" + actualTitle + "'.");

            Transport.send(message);
            System.out.println("Email sent successfully!");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        // Define the task to be executed
        Runnable task = new Runnable() {
            public void run() {
                // Create an instance of VoiceGPT
                WebDriver driver = null; // Initialize your WebDriver instance here
                VoiceGPT voiceGPT = new VoiceGPT(driver);
                // Call the VoiceChatGPT method
                voiceGPT.VoiceChatGPT();
            }
        };

        // Schedule the task to run every day at 10 am
        scheduler.scheduleAtFixedRate(task, calculateInitialDelay(), 24, TimeUnit.HOURS);
    }

    // Calculate the initial delay to start the task at 10 am
    private static long calculateInitialDelay() {
        long currentTime = System.currentTimeMillis();
        long targetTime = getTargetTime(10, 0); // 10 am
        long initialDelay = targetTime - currentTime;
        if (initialDelay < 0) {
            initialDelay += TimeUnit.DAYS.toMillis(1); // If the target time has already passed, schedule it for the next day
        }
        return initialDelay;
    }

    // Helper method to calculate the target time in milliseconds
    private static long getTargetTime(int hour, int minute) {
        long now = System.currentTimeMillis();
        long today = now - (now % TimeUnit.DAYS.toMillis(1)); // Today's midnight
        long targetTime = today + TimeUnit.HOURS.toMillis(hour) + TimeUnit.MINUTES.toMillis(minute);
        return targetTime;
    }
}
