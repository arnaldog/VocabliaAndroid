package cl.ingennia.vocablia.model.word;

import java.util.List;

import javax.inject.Inject;

import cl.ingennia.vocablia.api.VocabliaApiV1;
import cl.ingennia.vocablia.model.DefaultRepository;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Arnaldo Gaspar V. on 12/14/15.
 * arnaldog@gmail.com
 */
public class WordRepository implements DefaultRepository<Word>{

    VocabliaApiV1 apiV1;

    @Inject
    public WordRepository(VocabliaApiV1 apiV1){
        this.apiV1 = apiV1;
    }

    @Override
    public Observable<Word> get(int id) {
        return apiV1
                .getWord(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<List<Word>> all() {
        return apiV1.words();
    }

    @Override
    public Observable delete() {
        return Observable.empty();
    }

    @Override
    public Observable update() {
        return Observable.empty();
    }

    @Override
    public Observable<Word> create(Word object) {
        return Observable.empty();
    }
}
