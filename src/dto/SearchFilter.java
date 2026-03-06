package dto;

import enums.Status;

public class SearchFilter {
    public String name;
    public String division;
    public Status status;
    public Double minSalary;
    public Double maxSalary;

    @Override
    public String toString() {
        return String.format("[ Tên: %s | Phòng: %s | Trạng thái: %s | Lương: %s ]",
                name != null ? name : "Tất cả",
                division != null ? division : "Tất cả",
                status != null ? status : "Tất cả",
                (minSalary != null ? minSalary + " - " + maxSalary : "Tất cả"));
    }
}
