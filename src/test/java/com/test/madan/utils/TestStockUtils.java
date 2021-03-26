package com.test.madan.utils;

import com.test.madan.utils.models.Output;
import com.test.madan.utils.utils.StockUtils;
import com.test.madan.utils.utils.impl.StockUtilsImpl;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by madan on 26/03/21.
 **/

public class TestStockUtils {
    StockUtils stockUtils = new StockUtilsImpl();

    @Test
    public void testBaseCase(){
        List<Output> expectedOutput=new ArrayList<>();
        expectedOutput.add(new Output("#3",237.45,90l,"#2"));
        expectedOutput.add(new Output("#3",236.0,20l,"#6"));
        expectedOutput.add(new Output("#4",236.0,10l,"#6"));
        expectedOutput.add(new Output("#5",236.0,20l,"#6"));

        List<Output> actualOutput=stockUtils.readData("test-files/test1.txt");
        Assert.assertEquals(expectedOutput,actualOutput);
        stockUtils.resetQueue();
    }

    @Test
    public void testInvalidRecord(){
        List<Output> expectedOutput=new ArrayList<>();
        expectedOutput.add(new Output("#3",237.45,90l,"#2"));
        expectedOutput.add(new Output("#3",236.0,20l,"#6"));
        expectedOutput.add(new Output("#4",236.0,10l,"#6"));
        expectedOutput.add(new Output("#5",236.0,20l,"#6"));
        List<Output> actualOutput=stockUtils.readData("test-files/test2.txt");
        Assert.assertEquals(expectedOutput,actualOutput);
        stockUtils.resetQueue();
    }
    @Test
    public void testInvalidDataFormat(){
        List<Output> expectedOutput=new ArrayList<>();
        expectedOutput.add(new Output("#3",237.45,90l,"#2"));
        expectedOutput.add(new Output("#3",236.0,20l,"#6"));
        expectedOutput.add(new Output("#4",236.0,10l,"#6"));
        expectedOutput.add(new Output("#5",236.0,20l,"#6"));
        List<Output> actualOutput=stockUtils.readData("test-files/test3.txt");
        Assert.assertEquals(expectedOutput,actualOutput);
        stockUtils.resetQueue();
    }
}
