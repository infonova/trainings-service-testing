package com.bearingpoint.training.demo.servicetests.util;

import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class TemplateUtils {

    public static String readFromTemplate(final String path, Map<String, String> substitutionMap) {
        Objects.requireNonNull(path, "Path for template file is missing");
        final String template = TestFileUtils.getFileAsString(path);
        return resolveTemplate(template, substitutionMap);
    }

    public static String resolveTemplate(final String template, Map<String, String> substitutionMap) {

        String result = template;

        for (Map.Entry<String, String> entry : substitutionMap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            result = StringUtils.replace(result, key, value);
        }

        result = StringUtils.replace(result, "${randomUuid}", UUID.randomUUID().toString());
        return result;
    }
}
