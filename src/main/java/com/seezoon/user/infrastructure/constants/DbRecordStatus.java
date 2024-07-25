package com.seezoon.user.infrastructure.constants;

import com.seezoon.user.infrastructure.exception.Assertion;

/**
 * @author dfenghuang
 * @date 2023/9/23 08:56
 */
public class DbRecordStatus {

    /**
     * 有效
     */
    public static final byte VALID = 1;
    /**
     * 无效
     */
    public static final byte INVALID = 2;

    public static void check(Byte status) {
        Assertion.notNull(status);
        Assertion.isTrue(status == VALID
                || status == INVALID, "status incorrect");
    }

    public static boolean valid(Byte status) {
        return VALID == status;
    }
}
