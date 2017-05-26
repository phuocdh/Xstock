package com.xstock.tab;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * To be used with ViewPager to provide a tab indicator component which give
 * constant feedback as to the user's scroll progress.
 * <p>
 * To use the component, simply add it to your view hierarchy. Then in your
 * {@link android.app.Activity} or {@link android.support.v4.app.Fragment} call
 * {@link #setViewPager(ViewPager)} providing it the ViewPager this layout is
 * being used for.
 * <p>
 * The colors can be customized in two ways. The first and simplest is to
 * provide an array of colors via {@link #setSelectedIndicatorColors(int...)}.
 * The alternative is via the {@link TabColorizer} interface which provides you
 * complete control over which color is used for any individual position.
 * <p>
 * The views used as tabs can be customized by calling
 * {@link #setCustomTabView(int, int)}, providing the layout ID of your custom
 * layout.
 */
public class SlidingTabLayout extends HorizontalScrollView {
	/**
	 * Allows complete control over the colors drawn in the tab layout. Set with
	 * {@link #setCustomTabColorizer(TabColorizer)}.
	 */
	public interface TabColorizer {
		/**
		 * @return return the color of the indicator used when {@code position}
		 *         is selected.
		 */
		int getIndicatorColor(int position);
	}

	private static final int TITLE_OFFSET_DIPS = 24;
	private static final int TAB_VIEW_PADDING_DIPS = 16;
	private static int TAB_VIEW_TEXT_SIZE_SP = 20;
	private int iTitleOffset;
	private int iTabViewLayoutId;
	private int iTabViewTextViewId;
	private boolean isDistributeEvenly;
	private ViewPager bvViewPager;
	private SparseArray<String> saContentDescriptions = new SparseArray<String>();
	private ViewPager.OnPageChangeListener mViewPagerPageChangeListener;
	private final SlidingTabStrip stsTabStrip;

	public SlidingTabLayout(Context context) {

		this(context, null);
	}

	public SlidingTabLayout(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public SlidingTabLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// Disable the Scroll Bar
		setHorizontalScrollBarEnabled(false);
		// Make sure that the Tab Strips fills this View
		setFillViewport(true);
		iTitleOffset = (int) (TITLE_OFFSET_DIPS * getResources()
				.getDisplayMetrics().density);
		stsTabStrip = new SlidingTabStrip(context);
//		if ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE) {
//			TAB_VIEW_TEXT_SIZE_SP = Constant.TABLET;
//		} else {
//			TAB_VIEW_TEXT_SIZE_SP = Constant.MOBILE;
//		}
		addView(stsTabStrip, LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
	}

	/**
	 * Set the custom {@link TabColorizer} to be used.
	 * 
	 * If you only require simple custmisation then you can use
	 * {@link #setSelectedIndicatorColors(int...)} to achieve similar effects.
	 */
	public void setCustomTabColorizer(TabColorizer tabColorizer) {
		stsTabStrip.setCustomTabColorizer(tabColorizer);
	}

	public void setDistributeEvenly(boolean distributeEvenly) {
		isDistributeEvenly = distributeEvenly;
	}

	/**
	 * Sets the colors to be used for indicating the selected tab. These colors
	 * are treated as a circular array. Providing one color will mean that all
	 * tabs are indicated with the same color.
	 */
	public void setSelectedIndicatorColors(int... colors) {
		stsTabStrip.setSelectedIndicatorColors(colors);
	}

	/**
	 * Set the {@link ViewPager.OnPageChangeListener}. When using
	 * {@link SlidingTabLayout} you are required to set any
	 * {@link ViewPager.OnPageChangeListener} through this method. This is so
	 * that the layout can update it's scroll position correctly.
	 * 
	 * @see ViewPager#setOnPageChangeListener(ViewPager.OnPageChangeListener)
	 */
	public void setOnPageChangeListener(ViewPager.OnPageChangeListener listener) {
		mViewPagerPageChangeListener = listener;
	}

	/**
	 * Set the custom layout to be inflated for the tab views.
	 * 
	 * @param layoutResId
	 *            Layout id to be inflated
	 * @param textViewId
	 *            id of the {@link TextView} in the inflated view
	 */
	public void setCustomTabView(int layoutResId, int textViewId) {
		iTabViewLayoutId = layoutResId;
		iTabViewTextViewId = textViewId;
	}

	/**
	 * Sets the associated view pager. Note that the assumption here is that the
	 * pager content (number of tabs and tab titles) does not change after this
	 * call has been made.
	 */
	@SuppressWarnings("deprecation")
	public void setViewPager(ViewPager viewPager, int position) {
		stsTabStrip.removeAllViews();
		bvViewPager = viewPager;
		if (viewPager != null) {
			viewPager.setOnPageChangeListener(new InternalViewPagerListener());
			populateTabStrip(position);
		}
	}

	/**
	 * Create a default view to be used for tabs. This is called if a custom tab
	 * view is not set via {@link #setCustomTabView(int, int)}.
	 */
	@SuppressLint("InlinedApi")
	protected TextView createDefaultTabView(Context context) {
		TextView textView = new TextView(context);
		textView.setGravity(Gravity.CENTER);
		textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, TAB_VIEW_TEXT_SIZE_SP);
		textView.setTypeface(Typeface.DEFAULT_BOLD);
		textView.setLayoutParams(new LinearLayout.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.WRAP_CONTENT));
		TypedValue outValue = new TypedValue();
		getContext().getTheme().resolveAttribute(
				android.R.attr.selectableItemBackground, outValue, true);
		textView.setBackgroundResource(outValue.resourceId);
		textView.setAllCaps(true);
		int padding = (int) (TAB_VIEW_PADDING_DIPS * getResources()
				.getDisplayMetrics().density);
		textView.setPadding(padding, padding, padding, padding);
		return textView;
	}

	private void populateTabStrip(int pos) {
		final PagerAdapter adapter = bvViewPager.getAdapter();
		final View.OnClickListener tabClickListener = new TabClickListener();
		for (int i = 0; i < adapter.getCount(); i++) {
			View tabView = null;
			TextView tabTitleView = null;
			if (iTabViewLayoutId != 0) {
				// If there is a custom tab view layout id set, try and inflate
				// it
				tabView = LayoutInflater.from(getContext()).inflate(
						iTabViewLayoutId, stsTabStrip, false);
				tabTitleView = (TextView) tabView
						.findViewById(iTabViewTextViewId);
			}
			if (tabView == null) {
				tabView = createDefaultTabView(getContext());
			}
			if (tabTitleView == null && TextView.class.isInstance(tabView)) {
				tabTitleView = (TextView) tabView;
			}
			LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) tabView
					.getLayoutParams();
			if (isDistributeEvenly) {
				lp.width = 0;
			}
			lp.weight = 1;
			tabTitleView.setText(adapter.getPageTitle(i));
			if (i == pos) {
				tabTitleView.setTextColor(Color.WHITE);
			}
			tabView.setOnClickListener(tabClickListener);
			String desc = saContentDescriptions.get(i, null);
			if (desc != null) {
				tabView.setContentDescription(desc);
			}

			stsTabStrip.addView(tabView);
			if (i == bvViewPager.getCurrentItem()) {
				tabView.setSelected(true);
			}
		}
	}

	public void setContentDescription(int i, String desc) {
		saContentDescriptions.put(i, desc);
	}

	@Override
	protected void onAttachedToWindow() {
		super.onAttachedToWindow();
		if (bvViewPager != null) {
			scrollToTab(bvViewPager.getCurrentItem(), 0);
		}
	}

	private void scrollToTab(int tabIndex, int positionOffset) {
		final int tabStripChildCount = stsTabStrip.getChildCount();
		if (tabStripChildCount == 0 || tabIndex < 0
				|| tabIndex >= tabStripChildCount) {
			return;
		}
		View selectedChild = stsTabStrip.getChildAt(tabIndex);
		if (selectedChild != null) {
			int targetScrollX = selectedChild.getLeft() + positionOffset;
			if (tabIndex > 0 || positionOffset > 0) {
				// If we're not at the first child and are mid-scroll, make sure
				// we obey the offset
				targetScrollX -= iTitleOffset;
			}
			scrollTo(targetScrollX, 0);
		}
	}

	private class InternalViewPagerListener implements
			ViewPager.OnPageChangeListener {
		private int mScrollState;

		@Override
		public void onPageScrolled(int position, float positionOffset,
				int positionOffsetPixels) {
			int tabStripChildCount = stsTabStrip.getChildCount();
			if ((tabStripChildCount == 0) || (position < 0)
					|| (position >= tabStripChildCount)) {
				return;
			}
			stsTabStrip.onViewPagerPageChanged(position, positionOffset);
			View selectedTitle = stsTabStrip.getChildAt(position);
			int extraOffset = (selectedTitle != null) ? (int) (positionOffset * selectedTitle
					.getWidth()) : 0;
			scrollToTab(position, extraOffset);
			if (mViewPagerPageChangeListener != null) {
				mViewPagerPageChangeListener.onPageScrolled(position,
						positionOffset, positionOffsetPixels);

			}
		}

		@Override
		public void onPageScrollStateChanged(int state) {
			mScrollState = state;
			if (mViewPagerPageChangeListener != null) {
				mViewPagerPageChangeListener.onPageScrollStateChanged(state);
			}

		}

		@Override
		public void onPageSelected(int position) {
			if (mScrollState == ViewPager.SCROLL_STATE_IDLE) {
				stsTabStrip.onViewPagerPageChanged(position, 0f);
				scrollToTab(position, 0);
			}
			for (int i = 0; i < stsTabStrip.getChildCount(); i++) {
				stsTabStrip.getChildAt(i).setSelected(position == i);
			}
			if (mViewPagerPageChangeListener != null) {
				mViewPagerPageChangeListener.onPageSelected(position);
			}
			stsTabStrip.removeAllViews();
			populateTabStrip(position);
		}
	}

	private class TabClickListener implements View.OnClickListener {
		@Override
		public void onClick(View v) {
			for (int i = 0; i < stsTabStrip.getChildCount(); i++) {
				if (v == stsTabStrip.getChildAt(i)) {
					bvViewPager.setCurrentItem(i);
					return;
				}
			}
		}
	}

	public void setFocusTab(int pos) {
		bvViewPager.setCurrentItem(pos);
	}
}