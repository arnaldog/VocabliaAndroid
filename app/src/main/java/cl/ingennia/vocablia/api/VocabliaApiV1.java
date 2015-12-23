package cl.ingennia.vocablia.api;


import java.util.List;

import cl.ingennia.vocablia.model.word.Word;
import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by Arnaldo Gaspar V. on 12/13/15.
 * arnaldog@gmail.com
 */
public interface VocabliaApiV1 {

    @GET("/api/v1/words.json")
    Observable<List<Word>> words();

    @GET("/api/v1/words/{day}.json")
    Observable<Word> getWord(@Path("day") int day);
}
