package carDealer.model.enums;

/**
 * Created by George-Lenovo on 03/08/2018.
 */
public enum TableEnum {
    CAR,
    CUSTOMER,
    PART,
    SALE,
    SUPPLIER;

    public static String getSimpleName(TableEnum tableEnum) {
        return Character.toUpperCase(tableEnum.toString().charAt(0)) + tableEnum.toString().substring(1).toLowerCase();
    }
}