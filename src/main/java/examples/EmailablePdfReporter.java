package examples;

import com.sun.mail.smtp.SMTPTransport;
import org.apache.commons.mail.EmailException;
import org.testng.Assert;
import org.testng.ISuite;
import org.testng.reporters.EmailableReporter;
import org.testng.xml.XmlSuite;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import static utils.EncryptionUtil.decrypt;
import static utils.PropertiesUtil.readPropertiesFile;


/**
 * EmailablePdfReporter
 *
 * @see <a href="https://github.com/whitejeffreyl/JeffWhite/blob/master/testng/EmailablePdfReporter.java">Jeff White's EmailablePdfReporter class</a>
 */
public class EmailablePdfReporter extends EmailableReporter {

    @Override
    public void generateReport(List<XmlSuite> xml, List<ISuite> suites, String outdir) {
        super.generateReport(xml, suites, outdir);
        try {
            emailResults(readPropertiesFile("src/main/resources/properties/testData.properties", "test.reportName"));
        } catch (EmailException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            Assert.fail("Email sending failed!" + e.getMessage());
        }
    }

    public boolean emailResults(String fileAttachment)
            throws EmailException, MessagingException {

        Properties props = System.getProperties();
        props.put("mail.smtps.host", "smtp.gmail.com");
        props.put("mail.smtps.auth", "true");

        String recipients = readPropertiesFile("src/main/resources/properties/testData.properties", "test.emailTo");
        String sender = readPropertiesFile("src/main/resources/properties//testData.properties", "test.emailSender");
        String accountEmail = readPropertiesFile("src/main/resources/properties/testData.properties", "test.emailUser");
        String acctPwd = decrypt(readPropertiesFile("src/main/resources/properties//testData.properties", "test.emailPassword"));

        Date date = new Date(System.currentTimeMillis());
        Session session = Session.getInstance(props, null);
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(sender));
        msg.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(recipients, false));
        msg.setSubject("Test Report from " + date.toString());

        // create the message part
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        // fill message
        messageBodyPart.setText("Please find the test report in the attachment..");
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        // Part two is attachment
        messageBodyPart = new MimeBodyPart();
        DataSource source = new FileDataSource(fileAttachment);
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName(fileAttachment);
        multipart.addBodyPart(messageBodyPart);
        // Put parts in message
        msg.setContent(multipart);

        msg.setHeader("X-Mailer", "Test Results");
        SMTPTransport t = (SMTPTransport) session.getTransport("smtps");
        t.connect("smtp.gmail.com", accountEmail, acctPwd);
        t.sendMessage(msg, msg.getAllRecipients());
        t.close();
        return true;
    }
}