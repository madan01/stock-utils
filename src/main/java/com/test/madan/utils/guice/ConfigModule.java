package com.test.madan.utils.guice;
import com.google.inject.AbstractModule;
import com.test.madan.utils.utils.StockUtils;
import com.test.madan.utils.utils.impl.StockUtilsImpl;

/**
 * Created by madan on 26/03/21.
 **/

public class ConfigModule extends AbstractModule{

    @Override
    protected void configure() {
        bind(StockUtils.class).to(StockUtilsImpl.class);
    }
}
