package enums;

import constants.Messages;

public enum Update implements ChoiceMapper {
    NAME(1, Messages.Prompt.NAME_UPDATE),
    EMAIL(2, Messages.Prompt.EMAIL_UPDATE),
    PHONE(3, Messages.Prompt.PHONE_UPDATE),
    STATUS(4, Messages.Prompt.STATUS_UPDATE),
    SALARY(5, Messages.Prompt.SALARY_UPDATE),
    DIVISION(6, Messages.Prompt.DIVISION),
    JOIN_DATE(7, Messages.Prompt.JOIN_DATE_UPDATE),
    UPDATE(0, Messages.Prompt.PARTIAL_UPDATE),
    CANCEL(-1, Messages.Prompt.CANCEL),
    UPDATE_ALL(99, Messages.Prompt.UPDATE_ALL);

    private final Integer choice;
    private final String displayName;

    Update(Integer choice, String displayName) {
        this.choice = choice;
        this.displayName = displayName;
    }

    @Override
    public Integer getChoice() {
        return this.choice;
    }

    @Override
    public String getName() {
        return this.displayName;
    }
}
