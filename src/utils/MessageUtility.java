package utils;

import model.enums.Status;

public class MessageUtility {
    public static String getStatusMessage() {
        StringBuilder messageBuilder = new StringBuilder();
        messageBuilder.append("Lựa chọn trạng thái nhân viên: \n");
        for (int index = 0; index < Status.values().length; index++) {
            messageBuilder.append(index).append(".").append(Status.values()[index]).append("\n");
        }
        return messageBuilder.toString();
    }
}
