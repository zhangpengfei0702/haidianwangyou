package jni.text.zhzl.com.netizensservices.mvp;

import android.app.Activity;
import android.content.res.Resources;
import android.support.v4.view.ViewPager;
import android.util.SparseArray;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.TextView;


import java.lang.ref.WeakReference;

public class ViewFinder {
    private SparseArray<WeakReference<View>> mViews;

    private interface FindWrapper {

        View findViewById(int id);

        Resources getResources();

    }

    private static class WindowWrapper implements FindWrapper {

        private final Window window;

        WindowWrapper(final Window window) {
            this.window = window;
        }

        @Override
        public View findViewById(final int id) {
            return window.findViewById(id);
        }

        @Override
        public Resources getResources() {

            return window.getContext().getResources();
        }

    }

    private static class ViewWrapper implements FindWrapper {

        private final View view;

        ViewWrapper(final View view) {
            this.view = view;
        }

        @Override
        public View findViewById(final int id) {
            return view.findViewById(id);
        }

        @Override
        public Resources getResources() {
            return view.getResources();
        }

    }

    private final FindWrapper wrapper;

    /**
     * @param view
     */
    public ViewFinder(View view) {
        wrapper = new ViewWrapper(view);
        init();
    }

    /**
     * @param window
     */
    public ViewFinder(Window window) {
        wrapper = new WindowWrapper(window);
        init();
    }

    /**
     * @param activity
     */
    public ViewFinder(Activity activity) {
        this(activity.getWindow());
    }

    private void init() {
        mViews = new SparseArray<>();
    }

    public <V extends View> V find(int id) {
        View v;

        if (mViews.get(id) != null)
            v = mViews.get(id).get();
        else {
            v = wrapper.findViewById(id);
            if (v != null)
                mViews.put(id, new WeakReference<View>(v));
        }

        return (V) v;
    }

    /**
     * @param id
     * @return image view
     */
    public ImageView imageView(final int id) {
        return find(id);
    }

    /**
     * @param id
     * @return text view
     */
    public TextView textView(final int id) {
        return find(id);
    }


    public RatingBar ratingbar(final int id) {
        return find(id);
    }


    public RadioButton radiobutton(final int id) {
        return find(id);
    }

    public EditText editText(final int id) {
        return find(id);
    }


    public Button button(final int id) {
        return find(id);
    }

    public ListView listView(final int id) {
        return find(id);
    }


    public ViewPager viewPager(int id) {
        return find(id);
    }


    /**
     * @param id
     * @param drawable
     * @return image view
     */
    public ImageView setDrawable(final int id, final int drawable) {
        ImageView image = imageView(id);
        image.setImageDrawable(image.getResources().getDrawable(drawable));
        return image;
    }
}


