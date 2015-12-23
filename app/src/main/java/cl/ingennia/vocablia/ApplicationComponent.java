package cl.ingennia.vocablia;

import javax.inject.Singleton;

import cl.ingennia.vocablia.ui.MainActivity;
import dagger.Component;

/**
 * Created by familia on 12/23/15.
 */
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    void inject(MainActivity activity);
}