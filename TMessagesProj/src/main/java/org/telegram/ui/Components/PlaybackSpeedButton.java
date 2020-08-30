package org.telegram.ui.Components;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.TextView;

import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaController;
import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.Theme;

import java.util.Locale;

public class PlaybackSpeedButton extends TextView {
    private final boolean isMusic;

    @SuppressLint("RtlHardcoded")
    PlaybackSpeedButton(Context context, boolean isMusic) {
        super(context);
        this.isMusic = isMusic;
        setContentDescription(LocaleController.getString("AccDescrPlayerSpeed", R.string.AccDescrPlayerSpeed));
        setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15);
        //setTypeface(Typeface.DEFAULT_BOLD);
        setGravity(Gravity.CENTER_VERTICAL | Gravity.RIGHT);

        setOnClickListener(v -> {
            float currentPlaybackSpeed = MediaController.getInstance().getPlaybackSpeed(isMusic);
            if (currentPlaybackSpeed > 2.0f) {
                MediaController.getInstance().setPlaybackSpeed(isMusic, 1.0f);
            } else if (currentPlaybackSpeed > 1.5) {
                MediaController.getInstance().setPlaybackSpeed(isMusic, 2.5f);
            } else if (currentPlaybackSpeed > 1) {
                MediaController.getInstance().setPlaybackSpeed(isMusic, 1.8f);
            } else {
                MediaController.getInstance().setPlaybackSpeed(isMusic, 1.4f);
            }
            update();
        });
        update();
    }

    void update() {
        float currentPlaybackSpeed = MediaController.getInstance().getPlaybackSpeed(isMusic);
        setText(String.format(Locale.getDefault(), "%.1fX", currentPlaybackSpeed));
        String key;
        if (currentPlaybackSpeed > 1) {
            key = Theme.key_inappPlayerPlayPause;
        } else {
            key = Theme.key_inappPlayerClose;
        }
        setTextColor(Theme.getColor(key));
    }
}
