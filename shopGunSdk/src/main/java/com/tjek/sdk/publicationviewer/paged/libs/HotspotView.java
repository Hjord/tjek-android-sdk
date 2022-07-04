package com.tjek.sdk.publicationviewer.paged.libs;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

import com.shopgun.android.sdk.R;
import com.tjek.sdk.api.models.PublicationHotspotV2;

@SuppressLint("ViewConstructor")
public class HotspotView extends View {

    public static final String TAG = HotspotView.class.getSimpleName();

    PublicationHotspotV2 mHotspot;
    int[] mPages;
    RectF mBounds;
    private boolean outPlayed = false;

    public HotspotView(Context context, PublicationHotspotV2 hotspot, int[] pages) {
        super(context);
        mHotspot = hotspot;
        mPages = pages;
        mBounds = mHotspot.getBoundsForPages(mPages);
        setBackgroundResource(R.drawable.sgn_pagedpubkit_hotspot_bg);
        // set the 'in' animation
        setAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.sgn_pagedpubkit_hotspot_in));
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
        if (!outPlayed) {
            // if the 'in' animation ended, start the 'out' animation automatically
            outPlayed = true;
            startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.sgn_pagedpubkit_hotspot_out));
        }
        else {
            // after the 'out' animation has ended, post to the parent to remove the view
            final ViewGroup parent = (ViewGroup) getParent();
            if (parent != null) {
                final View view = this;
                parent.post(() -> parent.removeView(view));
            }
        }
    }

}
