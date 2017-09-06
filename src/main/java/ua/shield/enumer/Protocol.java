package ua.shield.enumer;

/**
 * Created by sa on 01.09.17.
 */
public enum Protocol {
    SMTPS("smtps"), POP3("pop3"), SMTP("smtp"), HTTP("http"), HTTPS("https");

    private final String string;

    Protocol(String name) {
        string = name;
    }

    @Override
    public String toString() {
        return string;
    }
}
