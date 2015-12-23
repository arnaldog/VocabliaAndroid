package cl.ingennia.vocablia.ui;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Singleton;

import cl.ingennia.vocablia.api.VocabliaApiV1;
import dagger.Module;
import dagger.Provides;
import retrofit.JacksonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * Created by Arnaldo Gaspar V. on 12/14/15.
 * arnaldog@gmail.com
 */
@Module
public class MainModule {

    @Provides
    @Singleton
    VocabliaApiV1 providesVocabliaApi() {

        ObjectMapper objectMapper = new ObjectMapper();
        JacksonConverterFactory factory = JacksonConverterFactory.create(objectMapper);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("")
                .addConverterFactory(factory)
                .addConverterFactory(JacksonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        return retrofit.create(VocabliaApiV1.class);
    }
}
