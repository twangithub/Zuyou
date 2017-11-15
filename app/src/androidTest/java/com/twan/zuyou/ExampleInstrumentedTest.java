package com.twan.zuyou;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.twan.zuyou.api.Api;import com.twan.zuyou.api.Result;import com.twan.zuyou.util.LogUtil;import org.junit.Test;
import org.junit.runner.RunWith;

import retrofit2.Call;import retrofit2.Callback;import retrofit2.Response;import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.twan.zuyou", appContext.getPackageName());

        Api.getApiService().getTest().enqueue(new Callback<Result<String>>() {
            @Override
            public void onResponse(Call<Result<String>> call, Response<Result<String>> response) {
                Result<String> result = response.body();
                LogUtil.d(result.toString());
            }

            @Override
            public void onFailure(Call<Result<String>> call, Throwable t) {
                LogUtil.e(t.toString( ));
            }
        });
    }
}
