package com.alibaba.fastjson2.date;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.TestUtils;
import com.alibaba.fastjson2.annotation.JSONField;
import com.alibaba.fastjson2.reader.ObjectReader;
import com.alibaba.fastjson2.reader.ObjectReaderCreator;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DateFormatTest_Local {
    @Test
    void localeDateTest() {
        final String date = "{\"today\": \"2022 March 10\"}";
        assertAll(() -> JSON.parseObject(date, Today.class));
    }

    @Test
    void localeDateTest_CN() {
        final String date = "{\"today\": \"2022 五月 10\"}";

        Today today;
        try (JSONReader reader = JSONReader.of(date)) {
            reader.getContext().setLocale(Locale.CHINESE);
            today = reader.read(Today.class);
        }
    }

    @Test
    void localeDateTest_CN_creators() {
        final String date = "{\"today\": \"2022 五月 10\"}";

        for (ObjectReaderCreator creator : TestUtils.readerCreators2()) {
            ObjectReader<TodayCN> objectReader = creator.createObjectReader(TodayCN.class);
            TodayCN todayCN = objectReader.readObject(JSONReader.of(date));
            assertNotNull(todayCN.today);
        }
    }

    private static class Today {
        @JSONField(format = "yyyy MMMM dd")
        private Date today;

        public Date getToday() {
            return today;
        }

        public void setToday(Date today) {
            this.today = today;
        }

        @Override
        public String toString() {
            return "Today{" +
                    "today=" + today +
                    '}';
        }
    }

    @Test
    void zhLocaleDateTest() {
        final String date = "{\"today\": \"2022 五月 10\"}";
        assertAll(() -> JSON.parseObject(date, TodayCN.class));
    }

    private static class TodayCN {
        @JSONField(format = "yyyy MMMM dd", locale = "zh_CN")
        private Date today;

        public Date getToday() {
            return today;
        }

        public void setToday(Date today) {
            this.today = today;
        }

        @Override
        public String toString() {
            return "Today{" +
                    "today=" + today +
                    '}';
        }
    }
}
