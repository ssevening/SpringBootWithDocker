package hello.utils;


public class MainUtils {

    public static boolean sendmail(String[] toEmails, String subject, String body) {
        Mail mail = new Mail();
        mail.set_to(toEmails);
        mail.set_subject(subject);
        mail.set_body(body);
        try {
            return mail.send();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
