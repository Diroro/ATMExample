package Denomination;

enum DenominationEnum {
    ONE,
    TWO,
    FIVE,
    TEN,
    TWENTY,
    FIFTY,
    HUNDRED
}

public final class Denomination {
    private int value;

    public final static Denomination ONE = new Denomination(DenominationEnum.ONE);
    public final static Denomination TWO = new Denomination(DenominationEnum.TWO);
    public final static Denomination FIVE = new Denomination(DenominationEnum.FIVE);
    public final static Denomination TEN = new Denomination(DenominationEnum.TEN);
    public final static Denomination TWENTY = new Denomination(DenominationEnum.TWENTY);
    public final static Denomination FIFTY = new Denomination(DenominationEnum.FIFTY);
    public final static Denomination HUNDRED = new Denomination(DenominationEnum.HUNDRED);

    private Denomination() {
    }

    private Denomination(DenominationEnum value) {
        switch (value) {
            case ONE:
                this.value = 1;
                break;
            case TWO:
                this.value = 2;
                break;
            case FIVE:
                this.value = 5;
                break;
            case TEN:
                this.value = 10;
                break;
            case TWENTY:
                this.value = 20;
                break;
            case FIFTY:
                this.value = 50;
                break;
            case HUNDRED:
                this.value = 100;
                break;
        }
    }

    public int getNoteValue() {
        return value;
    }

    public static Denomination[] getValues() {
        return new Denomination[]{HUNDRED, FIFTY, TWENTY, TEN, FIVE, TWO, ONE};
    }
}
