package utils;

import annotation.DisplayName;
import enums.Status;
import model.Employee;

import java.lang.reflect.Field;

public class MessageUtility {
    public static String getStatusMessage() {
        StringBuilder messageBuilder = new StringBuilder();
        messageBuilder.append("Lựa chọn trạng thái nhân viên: \n");
        for (int index = 0; index < Status.values().length; index++) {
            messageBuilder.append(index).append(".").append(Status.values()[index]).append("\n");
        }
        return messageBuilder.toString();
    }

    public static void printUpdateMenu() {
        System.out.println("--- CHỌN TRƯỜNG CẦN CẬP NHẬT ---");
        Field[] fields = Employee.class.getDeclaredFields();

        int index = 1;
        for (Field field : fields) {
            if (field.isAnnotationPresent(DisplayName.class)) {
                DisplayName displayName = field.getAnnotation(DisplayName.class);
                System.out.println(index + "." + displayName.value());
                index++;
            }
        }
        System.out.println();
        System.out.println("-1. Hủy cập nhật");
        System.out.println("0. để lưu thông tin và thoát.");
        System.out.println("99. Cập nhật tất cả");
    }

    public static void printMainMenu() {
        System.out.println("========== Employee Management ==========");
        System.out.println("Menu: ");
        System.out.println("1. Thêm mới nhân viên");
        System.out.println("2. Xóa nhân viên");
        System.out.println("3. Cập nhật nhân viên");
        System.out.println("4. Tìm kiếm nhân viên");
        System.out.println("5. Sắp xếp");
        System.out.println("6. Thống Kê");
        System.out.println("7. Lưu danh sách nhân viên");
        System.out.println("8. Hiển thị danh sách nhân viên");
        System.out.println("0. Exit");
    }
}
