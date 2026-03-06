package lib;

import enums.ChoiceMapper;

public class MenuUtility {

    public static <E extends ChoiceMapper> void choiceMenu(Class<E> choices) {
        for (E value : choices.getEnumConstants()) {
            System.out.printf("%d. %s\n", value.getChoice(), value.getName());
        }
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
