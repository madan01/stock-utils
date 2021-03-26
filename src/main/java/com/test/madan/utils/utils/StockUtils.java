package com.test.madan.utils.utils;

import com.test.madan.utils.models.Order;
import com.test.madan.utils.models.Output;
import com.test.madan.utils.models.enums.Operation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Stream;

/**
 * Created by madan on 26/03/21.
 **/

public interface StockUtils {

    List<Output> readData(String filePath);
    List<Output> processOrder(Order order);
    void resetQueue();
}
