package cl.ingennia.vocablia.ui.word;

import javax.inject.Singleton;

import cl.ingennia.vocablia.ApplicationComponent;
import cl.ingennia.vocablia.PerActivity;
import cl.ingennia.vocablia.ui.MainModule;
import dagger.Component;

/**
 * Created by Arnaldo Gaspar V. on 12/14/15.
 * arnaldog@gmail.com
 */
@Component(dependencies = ApplicationComponent.class, modules = {MainModule.class, WordModule.class})
public interface WordComponent {
    void inject(WordFragment wordFragment);
}
