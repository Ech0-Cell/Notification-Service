package com.echocell.notificationservice;

public class NotificationRecord {
    private String msisdn;
    private int packageId;
    private int notificationType;

    public NotificationRecord(String msisdn, int packageId, int notificationType) {
        this.msisdn = msisdn;
        this.packageId = packageId;
        this.notificationType = notificationType;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public int getPackageId() {
        return packageId;
    }

    public int getNotificationType() {
        return notificationType;
    }
}
