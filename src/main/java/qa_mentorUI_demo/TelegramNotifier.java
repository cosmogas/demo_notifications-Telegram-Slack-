package qa_mentorUI_demo;

import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class TelegramNotifier {
    private static final String BOT_TOKEN = "7517405545:AAGJzqlywAA-lp_XWmGlhUTdO4TQBxdHe-g";
    private static final String CHAT_ID = "345599628";
    private static final String REPORT_URL = "http://localhost:63342/qa_mentorUI_demo/build/reports/tests/test/index.html";

    public static void main(String[] args) throws Exception {
        File xmlFile = new File("build/test-results/test/TEST-qa_mentorUI_demo.UITests.xml");
        if (!xmlFile.exists()) {
            System.out.println("❌ Report file not found: " + xmlFile.getAbsolutePath());
            return;
        }

        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(xmlFile);
        doc.getDocumentElement().normalize();

        int total = Integer.parseInt(doc.getDocumentElement().getAttribute("tests"));
        int failures = Integer.parseInt(doc.getDocumentElement().getAttribute("failures"));
        int errors = Integer.parseInt(doc.getDocumentElement().getAttribute("errors"));
        int skipped = Integer.parseInt(doc.getDocumentElement().getAttribute("skipped"));

        int failed = failures + errors;
        int passed = total - failed - skipped;

        String statusEmoji = failed == 0 ? "✅" : "❌";
        String summary = String.format("%s *QA Mentor UI Tests Report*\n\n*Passed:* %d\n*Failed:* %d\n\n📎 [Open Report](%s)",
                statusEmoji, passed, failed, REPORT_URL);

        String encodedMessage = URLEncoder.encode(summary, "UTF-8");
        String urlStr = String.format("https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s&parse_mode=Markdown",
                BOT_TOKEN, CHAT_ID, encodedMessage);

        HttpURLConnection conn = (HttpURLConnection) new URL(urlStr).openConnection();
        conn.setRequestMethod("GET");

        int responseCode = conn.getResponseCode();
        if (responseCode == 200) {
            System.out.println("✅ Telegram notification sent!");
        } else {
            System.out.println("❌ Failed to send Telegram notification, response code: " + responseCode);
        }
    }
}

