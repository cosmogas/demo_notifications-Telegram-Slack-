QA Mentor UI Demo - Telegram Notifications

This project demonstrates how to run UI tests using Selenide and JUnit 5, generate test reports, and send a summary notification to Telegram automatically after the tests complete.

Features
UI tests for QA Mentor website pages (Home, About Us, Contact, Sitemap)

Test reporting with JUnit XML and Allure reports

Automated Telegram notification with test results summary (passed/failed tests count)

Telegram message includes a clickable link to the Allure report

How It Works
Run UI tests (UITests.java) which navigate through QA Mentor site and validate content.

Tests generate XML reports in build/test-results/test.

After tests finish, the TelegramNotifier.java reads the XML report, parses the test results, and sends a formatted message to your Telegram bot using Telegram Bot API.

The Telegram notification includes the number of passed and failed tests and a link to the Allure report.

Setup
Configure Telegram Bot

Create a Telegram bot via BotFather.

Obtain the bot token and your chat ID.

Update TelegramNotifier.java with your BOT_TOKEN and CHAT_ID.

Build and Run Tests

Use Gradle or your IDE to run the tests in UITests.java.

Send Telegram Notification

Run TelegramNotifier.java after tests complete to send the notification.
