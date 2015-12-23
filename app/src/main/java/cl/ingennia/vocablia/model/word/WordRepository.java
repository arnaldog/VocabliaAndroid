package cl.ingennia.vocablia.model.word;

import java.util.List;

import javax.inject.Inject;

import cl.ingennia.vocablia.api.VocabliaApiV1;
import cl.ingennia.vocablia.model.DefaultRepository;
import rx.Observable;

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
        return apiV1.getWord(id);
    }

    @Override
    public Observable<List<Word>> all() {
        return apiV1.words();
    }

    @Override
    public Observable delete() {
        return null;
    }

    @Override
    public Observable update() {
        return null;
    }

    @Override
    public Observable<Word> create(Word object) {
        return null;
    }
}
