package enums;

import constants.Messages;

public enum Search implements ChoiceMapper {
    NAME_FILTER(1, Messages.Prompt.SEARCH_NAME),
    DIVISION_FILTER(2, Messages.Prompt.SEARCH_DIVISION),
    STATUS_FILTER(3, Messages.Prompt.SEARCH_STATUS),
    SALARY_FILTER(4, Messages.Prompt.SEARCH_SALARY_RANGE),
    EXECUTE_SEARCH(0, Messages.Prompt.SEARCH_EXECUTE),
    CANCEL_SEARCH(-1, Messages.Prompt.SEARCH_CANCEL);

    private final Integer choice;
    private final String displayName;

    Search(Integer choice, String displayName) {
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
