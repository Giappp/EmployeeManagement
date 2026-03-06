package enums;

import constants.Messages;

public enum SortCriteria implements ChoiceMapper {
    BY_SALARY(1, Messages.Prompt.SORT_SALARY),
    BY_NAME(2, Messages.Prompt.SORT_NAME),
    BY_JOIN_DATE(3, Messages.Prompt.SORT_JOIN_DATE),
    ;
    private final Integer choice;
    private final String displayName;

    SortCriteria(int choice, String displayName) {
        this.choice = choice;
        this.displayName = displayName;
    }

    @Override
    public Integer getChoice() {
        return choice;
    }

    @Override
    public String getName() {
        return displayName;
    }
}
