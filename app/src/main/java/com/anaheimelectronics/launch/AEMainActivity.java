package com.anaheimelectronics.launch;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.anaheimelectronics.R;
import com.anaheimelectronics.common.AERootActivity;
import com.anaheimelectronics.common.AETools;
import com.anaheimelectronics.controller.CustomAlbumActivity;
import com.anaheimelectronics.developing.NormalItemDecoration;
import com.anaheimelectronics.developing.NormalRecyclerViewAdapter;
import com.anaheimelectronics.leakCanary.ActivityPool;
import com.anaheimelectronics.recyclerview.AERecycleViewActivity;

import it.gmariotti.recyclerview.itemanimator.ScaleInOutItemAnimator;
import it.gmariotti.recyclerview.itemanimator.SlideInOutBottomItemAnimator;
import it.gmariotti.recyclerview.itemanimator.SlideInOutLeftItemAnimator;

public class AEMainActivity extends AERootActivity implements View.OnClickListener{

    static final String TAG = "AEMainActivity";

    private CardView mEntryCardRecyclerView;

    @Override
    protected void initData(Context context, AttributeSet attrs) {
        AETools.APPLICATION_CONTEXT = getApplicationContext();
    }

    @Override
    protected View initLayout(LayoutInflater inflater) {
        setContentView(R.layout.ae_main_layout);
        return null;
    }

    @Override
    protected void bindView() {
        mEntryCardRecyclerView = (CardView)findViewById(R.id.entry_card_recycler_view);
    }

    @Override
    protected void initView() {
        mEntryCardRecyclerView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.entry_card_recycler_view:
            {
                Intent intent = new Intent(AEMainActivity.this, AERecycleViewActivity.class);
                startActivity(intent);
            }
                break;
            default:
                break;
        }

    }
}
