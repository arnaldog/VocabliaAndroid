package cl.ingennia.vocablia.model.word;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import cl.ingennia.vocablia.api.VocabliaApiV1;
import cl.ingennia.vocablia.model.DefaultRepository;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Arnaldo Gaspar V. on 12/14/15.
 * arnaldog@gmail.com
 */
public class WordRepository implements DefaultRepository<Word>{

    VocabliaApiV1 apiV1;
    Dao<Word, Long> dao;

    @Inject
    public WordRepository(VocabliaApiV1 apiV1, Dao<Word, Long> dao){
        this.apiV1 = apiV1;
        this.dao = dao;
    }

    @Override
    public Observable<Word> get(int id) {
        return  getFromDb(id).switchIfEmpty(getFromApi(id).flatMap(word -> writeonDb(word)));

    }

    public Observable<Word> getFromDb(final int id){
        return Observable.create(subscriber -> {
            try {
                subscriber.onNext(dao.queryForId(Long.valueOf(id)));
                subscriber.onCompleted();
            } catch (SQLException e) {
                subscriber.onError(e);
            }
        });
    }

    public Observable<Word> writeonDb(final Word word) {
        return Observable.create(subscriber -> {
            try {
                if (dao.create(word) > 0) {
                    subscriber.onNext(word);
                } else {
                    subscriber.onError(new SQLException());
                }

            } catch (SQLException e) {
                e.printStackTrace();
                subscriber.onError(e);
            }
            subscriber.onCompleted();
        });
    }

    public Observable<Word> getFromApi(final int id) {
       return  apiV1.getWord(id).subscribeOn(Schedulers.newThread());
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
