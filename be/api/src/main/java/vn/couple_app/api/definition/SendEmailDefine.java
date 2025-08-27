package vn.couple_app.api.definition;

import lombok.Getter;

public interface SendEmailDefine {
    @Getter
    enum Template {
        WELCOME_USER("/email/welcome");
        Template(String value) {
            this.value = value;
        }
        final String value;

        @Override
        public String toString() {
            return value;
        }
    }
    @Getter
    enum Title {
        WELCOME_USER("WELCOME NEW USER");
        Title(String value) {
            this.value = value;
        }
        final String value;

        @Override
        public String toString() {
            return value;
        }
    }
    interface Attribute {
        @Getter
        enum WELCOME_USER {
            NAME("name")
            ;
            WELCOME_USER(String value) {
                this.value = value;
            }
            final String value;
        }
    }
}
