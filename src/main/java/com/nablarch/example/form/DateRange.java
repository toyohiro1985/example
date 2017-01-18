package com.nablarch.example.form;

import java.util.Optional;

/**
 * 日付の期間を表すクラス。
 *
 * @author Nabu Rakutaro
 */
public class DateRange {

    /** 開始 */
    private final DateValue start;

    /** 終了 */
    private final DateValue end;

    /**
     * 開始と終了を元に日付の期間を生成する。
     *
     * @param start 開始日付
     * @param end 終了日付
     */
    public DateRange(final String start, final String end) {
        this.start = new DateValue(start);
        this.end = new DateValue(end);
    }

    /**
     * 期間が有効かどうか判定する。
     * <p>
     * 開始、終了ともに値が設定されていて、
     * 開始 < 終了の関係であれば有効な期間と判断する。
     *
     * @return 有効な期間の場合は{@code true}
     */
    @SuppressWarnings("WeakerAccess")
    public boolean isValid() {
        return start.getValue()
                    .flatMap(date -> end.getValue()
                                        .flatMap(e -> Optional.of(date.isBefore(e))))
                    .orElse(true);
    }
}
