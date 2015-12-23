package cl.ingennia.vocablia.ui.word;

import cl.ingennia.vocablia.model.word.Word;

/**
 * Created by Arnaldo Gaspar V. on 12/13/15.
 * arnaldog@gmail.com
 */
public interface WordView {

    void showNoWord();
    void setWord(Word word);
    void hideProgress();
    void showProgress();

}
