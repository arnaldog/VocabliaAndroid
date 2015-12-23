package cl.ingennia.vocablia;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import javax.inject.Named;
import javax.inject.Singleton;

import cl.ingennia.vocablia.api.VocabliaApiV1;
import dagger.Module;
import dagger.Provides;
import retrofit.JacksonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by Arnaldo Gaspar V. on 12/14/15.
 * arnaldog@gmail.com
 */
@Module
public class ApplicationModule {

    VocabliaApplication application;

    public ApplicationModule(VocabliaApplication application){
        this.application = application;
    }

    @Provides
    @Singleton
    SharedPreferences provideSharedPrefs() {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }

    @Provides
    @Singleton
    VocabliaApplication providesApplication() {
        return application;
    }

    @Provides
    VocabliaApiV1 providesVocabliaApiV1(@Named("api_url") String  apiUrl) {

        OkHttpClient client = new OkHttpClient();
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        client.interceptors().add(logging);

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .addConverterFactory(JacksonConverterFactory.create())
                .baseUrl(apiUrl)
                .build();

        return retrofit.create(VocabliaApiV1.class);
    }

    @Provides
    @Singleton
    @Named("api_url")
    String providesVocabliaApiUrl(VocabliaApplication application) {
        return application.getResources().getString(R.string.vocablia_api_url_dev);
    }

}
