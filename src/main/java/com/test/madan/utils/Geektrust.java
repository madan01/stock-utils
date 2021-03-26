package com.test.madan.utils;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.test.madan.utils.guice.ConfigModule;
import com.test.madan.utils.utils.StockUtils;
import com.test.madan.utils.utils.OutputUtils;
import org.apache.commons.cli.*;

/**
 * Created by madan on 26/03/21.
 **/

public class Geektrust {
    public static void main(String[] args) throws ParseException {
        Injector injector = Guice.createInjector(new ConfigModule());
        StockUtils stockUtils = injector.getInstance(StockUtils.class);
        OutputUtils.printStockOutput(stockUtils.readData(args[0]));
    }
}
