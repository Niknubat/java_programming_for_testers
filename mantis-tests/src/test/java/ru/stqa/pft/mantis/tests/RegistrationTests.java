package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class RegistrationTests extends TestBase {

    @BeforeMethod // Данная строка(только сама антонация @BeforeMethod) коммитится при получение почты с использованием James (урок 82)
    public void startMailServer() {
        app.mail().start();
    }

    @Test
    public void testRegistration() throws IOException, MessagingException {
        long now = System.currentTimeMillis(); // Фнкция возвращает текущее время в милесекундах, от 1 января 1970 года(так реализовали уникальный идентификатор)
        String user = String.format("user%s", now);
        String password = "password";
        String email = String.format("user%s@localhost.localdomain", now);
        //app.james().createUser(user, password); // Эта строка используется при получение почты с использованием James (раскоммичивается) (урок 82)
        app.registration().start(user, email);
        List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000); // Данная строка коммитится при получение почты с использованием James (урок 82)
        //List<MailMessage> mailMessages = app.james().waitForMail(user, password, 60000);  // Эта строка используется при получение почты с использованием James (раскоммичивается) (урок 82)
        String confirmationLink = findConfirmationLink(mailMessages, email);
        app.registration().finish(confirmationLink, password);
        assertTrue(app.newSession().login(user, password));
    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build(); // Регулярка с помощью либы java-verbal-expressions
        return regex.getText(mailMessage.text); // Возврат куска текста, соответствующего регулярке
    }

    @AfterMethod(alwaysRun = true) // Данная строка(только сама антонация @AfterMethod(alwaysRun = true)) коммитится при получение почты с использованием James (урок 82)
    public void stopMailServer() {
        app.mail().stop();
    }
}