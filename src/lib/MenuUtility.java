package lib;

import enums.Status;

public class MenuUtility {
    public static void statusMenu() {
        StringBuilder messageBuilder = new StringBuilder();
        messageBuilder.append("Lựa chọn trạng thái nhân viên: \n");
        for (int index = 0; index < Status.values().length; index++) {
            messageBuilder.append(index).append(".").append(Status.values()[index]).append("\n");
        }
        System.out.println(messageBuilder.toString());
    }

    public static void updateMenu() {
        System.out.println("--- CHỌN TRƯỜNG CẦN CẬP NHẬT ---");
        System.out.println("1. Cập nhật tên");
        System.out.println("2. Cập nhật email");
        System.out.println("3. Cập nhật số điện thoại");
        System.out.println("4. Cập nhật trạng thái");
        System.out.println("5. Cập nhật lương");
        System.out.println("6. Cập nhật phòng ban");
        System.out.println("7. Cập nhật ngày gia nhập");
        System.out.println("-1. Để hủy cập nhật");
        System.out.println("0. Để lưu thông tin và thoát.");
        System.out.println("99. Cập nhật tất cả");
    }

    public static void mainMenu() {
        System.out.println("\n========== HỆ THỐNG QUẢN LÝ NHÂN VIÊN ==========\n");
        System.out.println("Menu: ");
        System.out.println("1. Add");
        System.out.println("2. Remove");
        System.out.println("3. Update");
        System.out.println("4. Search");
        System.out.println("5. Sort");
        System.out.println("6. Statistics");
        System.out.println("7. Save");
        System.out.println("8. Load");
        System.out.println("0. Exit");
    }

    public static void searchMenu() {
        System.out.println("--- BỘ LỌC TÌM KIẾM ---");
        System.out.println("1. Theo tên");
        System.out.println("2. Phòng ban");
        System.out.println("3. Trạng thái");
        System.out.println("4. Khoảng lương");
        System.out.println("Enter: Bắt đầu tìm kiếm");
        System.out.println("0. Thoát tìm kiếm");
    }

    public static void sortMenu() {
        System.out.println("--- TIÊU CHÍ SẮP XẾP ---");
        System.out.println("1. Theo Lương");
        System.out.println("2. Theo Tên");
        System.out.println("3. Ngày tuyển dụng");
    }

    public static void statisticMenu() {
        System.out.println("--- THỐNG KÊ ---");
        System.out.println("1. Số nhân viên");
        System.out.println("2. Tổng quỹ lương nhân viên");
        System.out.println("3. Lương trung bình");
        System.out.println("4. Lương Cao nhất");
    }
}
