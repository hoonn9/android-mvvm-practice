package com.example.oslotest.viewimpl;

import android.content.Context;
import android.widget.Toast;

import com.example.oslotest.view.ToastView;

/**
 * @author firefly2.kim
 * @since 19. 8. 25
 */
public class ToastViewImpl implements ToastView {

    @Override
    public void render(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
