package org.example.ui;

public enum Command {
    EXIT,
    CMD1,
    CMD2,
    CMD3,
    CMD4,
    CMD5,
    SQL_ONE_TABLE,
    SQL_MANY_TABLES,
    SQL_UPDATE,
    SQL_INSERT,
    SQL_DEL,
    SQL_PROC,
    JSON_READ,
    JSON_UPDATE,
    JSON_INSERT ,
    JSON_DEL ,
    ADD1,
    UNKNOWN_CMD;

    public static Command fromInt(int x) {
        return switch (x) {
            case 0 -> EXIT;
            case 1 -> CMD1;
            case 2 -> CMD2;
            case 3 -> CMD3;
            case 4 -> CMD4;
            case 5 -> CMD5;
            case 6 -> SQL_ONE_TABLE;
            case 7 -> SQL_MANY_TABLES;
            case 8 -> SQL_UPDATE;
            case 9 -> SQL_INSERT;
            case 10 -> SQL_DEL;
            case 11 -> SQL_PROC;
            case 12 -> JSON_READ;
            case 13 -> JSON_UPDATE;
            case 14 -> JSON_INSERT;
            case 15 -> JSON_DEL;
            case 16 -> ADD1;
            default -> UNKNOWN_CMD;
        };
    }
}
