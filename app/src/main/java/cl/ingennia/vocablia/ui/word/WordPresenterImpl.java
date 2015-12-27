package cl.ingennia.vocablia.ui.word;

import javax.inject.Inject;

import cl.ingennia.vocablia.model.word.Word;
import cl.ingennia.vocablia.model.word.WordRepository;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by Arnaldo Gaspar V. on 12/13/15.
 * arnaldog@gmail.com
 */
public class WordPresenterImpl implements WordPresenter, Observer<Word> {

    WordRepository repo;
    WordView listener;
    Subscription subscription;

    @Inject
    public WordPresenterImpl(WordRepository repo){
        this.repo = repo;
    }

    @Override
    public void fetchWord(int dayOfYear) {
        subscription = repo.get(dayOfYear).observeOn(AndroidSchedulers.mainThread()).subscribe(this);
        listener.showProgress();
    }

    @Override
    public void onCompleted() {
        listener.hideProgress();
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
    }

    @Override
    public void onNext(Word word) {
        listener.setWord(word);
    }

    @Override
    public void onTakeView(Object view) {
        this.listener = (WordView) view;
    }

    @Override
    public void onDetachView() {
        subscription.unsubscribe();
        listener = null;
    }
}
