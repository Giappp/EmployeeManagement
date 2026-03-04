package dto;

import annotation.DisplayName;
import enums.Status;

public class SearchFilter {
    @DisplayName("Họ và Tên")
    public String name;
    @DisplayName("Phòng Ban")
    public String division;
    @DisplayName("Trạng thái")
    public Status status;
    @DisplayName("Lương nhỏ nhất")
    public Double minSalary;
    @DisplayName("Lương lớn nhất")
    public Double maxSalary;
}
