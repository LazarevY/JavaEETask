package main;

import annotations.PropertyGetter;
import annotations.ReflectionUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import data.AttributeFilterType;
import data.Filter;
import data.json.AttributeMapper;
import data.property.PropertyManager;
import logic.events.Birthday;
import logic.events.Event;
import logic.expressions.conditions.Condition;
import logic.expressions.interfaces.ConditionChecker;
import logic.expressions.utils.ExpressionsUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main {


    public static void main(String[] args) throws IOException {

        Birthday b =
                new Birthday(
                        LocalDate.of(2020, Month.APRIL, 20),
                        "Desc",
                        "Name",
                        "Deeeep"
                );


        PropertyManager m =
                new PropertyManager("day");

        try {
            System.out.println(m.getValue(b));
        } catch (InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }


    }
}
