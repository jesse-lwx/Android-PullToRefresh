/*******************************************************************************
 * Copyright 2011, 2012 Chris Banes.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.handmark.pulltorefresh.extras.recyclerview;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.handmark.pulltorefresh.library.PullToRefreshBase;

import java.util.ArrayList;

/**
 * 版权所有：XXX有限公司</br>
 *
 * PullToRefreshRecyclerView </br>
 *
 * @author zhou.wenkai<zwenkai@foxmail.com> ,Created on 2015-11-19 10:52:02</br>
 * @description Major Function：对PullToRefreshRecyclerView的扩展,增加支持添加头部 </br>
 *
 * 注:如果您修改了本类请填写以下内容作为记录，如非本人操作劳烦通知，谢谢！！！</br>
 * @author mender，Modified Date Modify Content:
 */
public class PullToRefreshHeaderRecyclerView extends PullToRefreshRecyclerView {

	private View mHeaderView;

	public PullToRefreshHeaderRecyclerView(Context context) {
		super(context);
	}

	public PullToRefreshHeaderRecyclerView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public PullToRefreshHeaderRecyclerView(Context context, Mode mode) {
		super(context, mode);
	}

	public PullToRefreshHeaderRecyclerView(Context context, Mode mode, AnimationStyle style) {
		super(context, mode, style);
	}

	public void addHeaderView(View headerView){
		if(null == headerView) return;
		this.mHeaderView = headerView;
		FrameLayout refreshableViewWrapper = getRefreshableViewWrapper();
		refreshableViewWrapper.addView(headerView);
	}

	/**
	 * 验证 RecyclerView 是否能够添加头部
	 *
	 * @rerurn void
	 * @date 2015-11-19 11:17:22
	 */
	private void validateRecycler() {
		RecyclerView.LayoutManager layoutManager = mRefreshableView.getLayoutManager();
		if (layoutManager == null) {
			throw new IllegalStateException("Be sure to call HeaderRecyclerView constructor after setting your RecyclerView's LayoutManager.");
		} else if (layoutManager.getClass() != LinearLayoutManager.class    //not using instanceof on purpose
				&& layoutManager.getClass() != GridLayoutManager.class
				&& !(layoutManager instanceof StaggeredGridLayoutManager)) {
			throw new IllegalArgumentException("Currently HeaderRecyclerView supports only LinearLayoutManager, GridLayoutManager and StaggeredGridLayoutManager.");
		}

		if (layoutManager instanceof LinearLayoutManager) {
			if (((LinearLayoutManager) layoutManager).getOrientation() != LinearLayoutManager.VERTICAL) {
				throw new IllegalArgumentException("Currently HeaderRecyclerView supports only VERTICAL orientation LayoutManagers.");
			}
		} else if (layoutManager instanceof StaggeredGridLayoutManager) {
			if (((StaggeredGridLayoutManager) layoutManager).getOrientation() != StaggeredGridLayoutManager.VERTICAL) {
				throw new IllegalArgumentException("Currently HeaderRecyclerView supports only VERTICAL orientation StaggeredGridLayoutManagers.");
			}
		}
	}

}
