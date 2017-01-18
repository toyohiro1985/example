package com.nablarch.example.form;

import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;

import nablarch.core.util.StringUtil;

/**
 * 日付を表すバリューオブジェクト
 *
 * @author Nabu Rakutaro
 */
public class DateValue {

    /**
     * 日付
     */
    private final LocalDate value;

    /**
     * 文字列表現の日付からバリューオブジェクトを構築する。
     *
     * @param value 文字列表現の日付
     */
    public DateValue(String value) {
        if (StringUtil.isNullOrEmpty(value)) {
            this.value = null;
        } else {
            this.value = LocalDate.parse(String.join("", StringUtil.split(value, "/")),
                    DateTimeFormatter.BASIC_ISO_DATE);
        }
    }

    /**
     * {@link Date}に変換した値を返す。
     *
     * @return {@link Date}
     */
    public Date toDate() {
        return value == null ? null : Date.from(Instant.from(value));
    }

    /**
     * 値を返す。
     * @return 値
     */
    public Optional<LocalDate> getValue() {
        return Optional.ofNullable(value);
    }

    /**
     * yyyyMMddフォーマットの文字列表現を返す。
     * 
     * @return 文字列表現
     */
    public String toStringFormat() {
        return value == null ? null : value.format(DateTimeFormatter.BASIC_ISO_DATE);
    }
}

