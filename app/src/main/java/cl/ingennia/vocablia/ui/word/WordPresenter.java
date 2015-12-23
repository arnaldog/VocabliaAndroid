package cl.ingennia.vocablia.ui.word;

import cl.ingennia.vocablia.ui.MainPresenter;

/**
 * Created by Arnaldo Gaspar V. on 12/13/15.
 * arnaldog@gmail.com
 */
public interface WordPresenter extends MainPresenter{

    void fetchWord(int dayOfYear);
}
