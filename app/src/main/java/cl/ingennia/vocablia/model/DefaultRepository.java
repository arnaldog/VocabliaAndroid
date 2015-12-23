package cl.ingennia.vocablia.model;

import java.util.List;

import rx.Observable;

/**
 * Created by Arnaldo Gaspar V. on 12/20/15.
 * arnaldog@gmail.com
 */
public interface DefaultRepository<T> {

    Observable<T> get(int id);

    Observable<List<T>> all();

    Observable delete();

    Observable update();

    Observable<T> create(T object);
}
