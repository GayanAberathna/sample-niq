package lk.sample.application.util;

public enum DateTimeFormat {
    DATE_WITH_TIME("yyyy-MM-dd HH:mm:ss"),
    DATE_WITHOUT_TIME("yyyy-MM-dd"),
    ONLY_TIME("HH:mm:ss");

    private final String customDateTimeFormat;

    DateTimeFormat(String customDateTimeFormat) {
        this.customDateTimeFormat = customDateTimeFormat;
    }

    public String getCustomDateTimeFormat(){
        return customDateTimeFormat;
    }
}
