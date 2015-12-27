package cl.ingennia.vocablia.model;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

import javax.inject.Singleton;

import cl.ingennia.vocablia.VocabliaApplication;
import cl.ingennia.vocablia.model.word.Word;
import dagger.Module;
import dagger.Provides;

/**
 * Created by arnaldogaspar on 12/27/15.
 */
@Module
public class ModelModule {

    @Provides
    @Singleton
    VocabliaDatabaseHelper providesHelper(VocabliaApplication application) {
        return OpenHelperManager.getHelper(application, VocabliaDatabaseHelper.class);
    }

    @Provides
    @Singleton
    Dao<Word, Long> providesWordDao(VocabliaDatabaseHelper helper){
        try {
            return helper.getWordDao();
        } catch(SQLException e) {
            return null;
        }
    }
}
