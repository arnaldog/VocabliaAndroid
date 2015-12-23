package cl.ingennia.vocablia.ui.word;

import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import cl.ingennia.vocablia.R;
import cl.ingennia.vocablia.VocabliaApplication;
import cl.ingennia.vocablia.model.word.Word;
import cl.ingennia.vocablia.ui.MainModule;

/**
 * A simple {@link Fragment} subclass.
 */
public class WordFragment extends Fragment implements WordView {

    @Bind(R.id.name)
    TextView name;

    @Bind(R.id.description)
    TextView description;

    @Bind(R.id.example)
    TextView example;

    @Bind(R.id.date)
    TextView date;

    @Bind(R.id.no_word)
    TextView noWord;

    @Bind(R.id.progressBar)
    ProgressBar progressBar;

    @Bind(R.id.word_layout)
    LinearLayout wordLayout;

    @Bind(R.id.image)
    ImageView image;

    int dayOfYear;

    @Inject
    WordPresenter wordPresenter;

    public WordFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_word, container, false);
        ButterKnife.bind(this, view);

        DaggerWordComponent.builder()
                .mainModule(new MainModule())
                .wordModule(new WordModule())
                .applicationComponent( ((VocabliaApplication) getActivity().getApplication()).getComponent())
                .build().inject(this);

        date.setText(getDayOfYearFormat());
        date.setShadowLayer(7, 0, 0, Color.BLACK);

        wordPresenter.fetchWord(dayOfYear);

        return view;
    }


    @Override
    public void showNoWord() {
        progressBar.setVisibility(View.GONE);
        noWord.setVisibility(View.VISIBLE);
    }

    @Override
    public void setWord(Word word) {
        name.setText(word.name);
        description.setText(word.description);
        example.setText(String.format("\"%s\"", word.example));

        int orientation = getResources().getConfiguration().orientation;
        image.setVisibility(View.VISIBLE);

//        String uri = String.format("%s%s", host, word.icon);

        if (orientation == Configuration.ORIENTATION_LANDSCAPE
                && Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            //image.setAlpha(.5f);
        }
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
        wordLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        wordLayout.setVisibility(View.GONE);

    }

    public String getDayOfYearFormat() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_YEAR, dayOfYear);

        Locale locale = Locale.getDefault();
        String pattern = "EEEE, d MMMM";
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern, locale);

        String dateString = dateFormat.format(c.getTime());

        return capitalizeAll(dateString);
    }

    public static String capitalizeAll(String string) {
        String[] a = string.split(" ");

        for (int i = 0; i < a.length; i++) {
            a[i] = StringUtils.capitalize(a[i]);
        }

        return arrayToDelimitedString(a, " ");
    }


    public static String arrayToDelimitedString(String[] arr, String delim) {

        if (arr == null || arr.length < 1) return "";
        if (arr.length == 1) return arr[1];

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < arr.length; ++i) {
            if (i > 0) {
                sb.append(delim);
            }

            sb.append(arr[i]);
        }

        return sb.toString();

    }
}
