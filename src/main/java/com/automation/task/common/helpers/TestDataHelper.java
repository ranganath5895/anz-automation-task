package com.automation.task.common.helpers;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.automation.task.common.CommonException;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class TestDataHelper {

	public static <T> List<T> getObjectsFromString(String jsonBody, Class<T> classname) throws CommonException {

        Type listType = TypeToken.getParameterized(ArrayList.class, classname).getType();

        try {
            return new GsonBuilder().create().fromJson(jsonBody, listType);

        } catch (Exception e) {
            throw new CommonException("Exception while parsing Json Data", e);
        }
    }
}
