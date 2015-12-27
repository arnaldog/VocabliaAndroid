package cl.ingennia.vocablia.ui.word;

import javax.inject.Singleton;

import cl.ingennia.vocablia.PerActivity;
import cl.ingennia.vocablia.api.VocabliaApiV1;
import cl.ingennia.vocablia.model.word.WordRepository;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Arnaldo Gaspar V. on 12/13/15.
 * arnaldog@gmail.com
 */
@Module
 public class WordModule {

    @Provides
    @Singleton
    WordPresenter providesWordPresenter(WordRepository repo) {
        return new WordPresenterImpl(repo);
    }

    @Provides
    @Singleton
    WordRepository providesWordRepository(VocabliaApiV1 apiV1){
        return new WordRepository(apiV1);
    }

        
}
