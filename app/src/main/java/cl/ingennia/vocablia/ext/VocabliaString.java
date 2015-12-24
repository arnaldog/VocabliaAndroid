package cl.ingennia.vocablia.ext;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by arnaldogaspar on 12/24/15.
 */
public class VocabliaString {

    public static String capitalizeAll(String string) {
        String[] a = string.split(" ");

        for (int i = 0; i < a.length; i++) {
            a[i] = StringUtils.capitalize(a[i]);
        }

        return arrayToDelimitedString(a, " ");
    }


    public static String arrayToDelimitedString(String[] arr, String delim) {

        if (ArrayUtils.isEmpty(arr)) {
            return "";
        }

        if (arr.length == 1) {
            return arr[1];
        }

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
