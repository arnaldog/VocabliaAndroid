package cl.ingennia.vocablia.ui.word;

import javax.inject.Singleton;

import cl.ingennia.vocablia.ApplicationComponent;
import cl.ingennia.vocablia.ApplicationModule;
import cl.ingennia.vocablia.PerActivity;
import cl.ingennia.vocablia.ui.MainModule;
import dagger.Component;

/**
 * Created by Arnaldo Gaspar V. on 12/14/15.
 * arnaldog@gmail.com
 */
@Singleton
@Component(dependencies = ApplicationComponent.class, modules = { ApplicationModule.class, WordModule.class})
public interface WordComponent {
    void inject(WordFragment wordFragment);
}
