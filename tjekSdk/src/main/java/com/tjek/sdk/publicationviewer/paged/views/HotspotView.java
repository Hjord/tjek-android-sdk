package com.tjek.sdk.publicationviewer.paged.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

import com.tjek.sdk.R;
import com.tjek.sdk.api.models.PublicationHotspotV2;

@SuppressLint("ViewConstructor")
public class HotspotView extends View {

    public static final String TAG = HotspotView.class.getSimpleName();

    private final RectF mBounds;
    private final boolean longPress;

    public HotspotView(Context context, PublicationHotspotV2 hotspot, int[] pages, boolean longPress) {
        super(context);
        this.longPress = longPress;
        mBounds = hotspot.getBoundsForPages(pages);
        setBackgroundResource(R.drawable.tjek_sdk_pagedpub_hotspot_bg);
        // set the 'in' animation
        setAnimation(AnimationUtils.loadAnimation(getContext(), longPress ? R.anim.tjek_sdk_pagedpub_hotspot_in_long_press : R.anim.tjek_sdk_pagedpub_hotspot_in));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        Rect rect = getScaledRect(mBounds, width, height);
        ((ViewGroup.MarginLayoutParams) getLayoutParams()).leftMargin = rect.left;
        ((ViewGroup.MarginLayoutParams) getLayoutParams()).topMargin = rect.top;
        widthMeasureSpec = MeasureSpec.makeMeasureSpec(rect.width(), MeasureSpec.EXACTLY);
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(rect.height(), MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private static Rect getScaledRect(RectF rect, int width, int height) {
        Rect r = new Rect();
        r.left = Math.round(rect.left * (float) width);
        r.top = Math.round(rect.top * (float) height);
        r.right = Math.round(rect.right * (float) width);
        r.bottom = Math.round(rect.bottom * (float) height);
        return r;
    }

    @Override
    protected void onAnimationEnd() {
        super.onAnimationEnd();
        // set the view to gone to avoid flickering
        if (!longPress) {
            setVisibility(View.GONE);
        }
    }

}
