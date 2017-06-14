package com.tincio.foodrecipes.presentation;


import android.app.Fragment;
import android.arch.lifecycle.LifecycleFragment;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tincio.foodrecipes.R;
import com.tincio.foodrecipes.data.model.StepRecipe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class InstructionFragment extends LifecycleFragment implements View.OnClickListener{//}, SurfaceHolder.Callback{

    //protected VideoView videoView;

    public static String TAG = InstructionFragment.class.getSimpleName();
    /***VIEWS**/
    private TextView txtDescription;
    private Button btnPrevious;
    private Button btnNext;
    SurfaceView surfaceView;
    LinearLayout linearButtons;
    /***CONSTANTS**/
    protected static String ID = "ID";
    protected static String URL = "URL";
    protected static String DESCRIPTION = "DESCRIPTION";
    protected static String LIST_STEP = "LIST_STEP";
    //player
    /**VARIABLES***/
    List<StepRecipe> listStep;
    int idSelected;
    private MediaPlayer mediaPlayer;
    String urlSelected;

    public InstructionFragment() {
        // Required empty public constructor
    }

    public static InstructionFragment newInstance(String description, String url, int id, List<StepRecipe> listStep){
        InstructionFragment fragment = new InstructionFragment();
        Bundle bundle = new Bundle();
        bundle.putString(DESCRIPTION, description);
        bundle.putString(URL, url);
        bundle.putInt(ID, id);
        bundle.putParcelableArrayList(LIST_STEP,(ArrayList)listStep);
        fragment.setArguments(bundle);
        return fragment;
    }

    void setData(){
        this.txtDescription.setText(getArguments().getString(DESCRIPTION));
        this.listStep = getArguments().getParcelableArrayList(LIST_STEP);
        this.idSelected = getArguments().getInt(ID);
        this.urlSelected = getArguments().getString(URL);
        this.checkButtons();
        this.reproduceVideo(this.urlSelected);
    }

    void checkButtons(){
        this.btnPrevious.setVisibility(View.VISIBLE);
        this.btnNext.setVisibility(View.VISIBLE);
        if(this.idSelected> 0){
            if(this.idSelected == this.listStep.size() - 1)
                this.btnNext.setVisibility(View.INVISIBLE);
        }else
            this.btnPrevious.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.btnNext.setOnClickListener(this);
        this.btnPrevious.setOnClickListener(this);
        this.initControls();
        setData();
    }

    private void initControls(){
        if(getResources().getBoolean(R.bool.isTablet)) {
            this.linearButtons.setVisibility(View.GONE);
        }else {
            this.linearButtons.setVisibility(View.VISIBLE);
            this.checkOrientation();
        }
    }

    private void checkOrientation(){
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
        {
            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, (int)getResources().getDimension(R.dimen.height_surface_video));
            this.surfaceView.setLayoutParams(lp);
        } else {
            // Landscape Mode
            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
            this.surfaceView.setLayoutParams(lp);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_instruction, container, false);
        txtDescription = (TextView)view.findViewById(R.id.txt_description_instruction);
        btnPrevious = (Button)view.findViewById(R.id.btn_previous);
        btnNext = (Button)view.findViewById(R.id.btn_next);
        this.surfaceView = (SurfaceView)view.findViewById(R.id.player_view);
        this.linearButtons = (LinearLayout)view.findViewById(R.id.linear_buttons);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_next:
                setDataView(this.listStep.get(this.idSelected+1));
                break;
            case R.id.btn_previous:
                setDataView(this.listStep.get(this.idSelected-1));
                break;
        }
    }

    protected void setDataView(StepRecipe step){
        this.txtDescription.setText(step.getInstruction());
        this.idSelected = step.getId();
        this.reproduceVideo(step.getUrlPlayer());
        this.checkButtons();
        this.checkVideo(step.getUrlPlayer());
    }

    private void checkVideo(String url){
        if(url.isEmpty())
            this.surfaceView.setVisibility(View.GONE);
        else
            this.surfaceView.setVisibility(View.VISIBLE);
    }

    private void reproduceVideo(String urlVideo){
        //mediaPlayer.setDisplay(holder);
        try {
            System.out.println("url a setear "+urlVideo);
            if(mediaPlayer!=null){
                mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer = null;
            }
            this.urlSelected = urlVideo;
            surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
                @Override
                public void surfaceCreated(SurfaceHolder surfaceHolder) {
                    if (urlSelected != null) {
                        mediaPlayer = new MediaPlayer();
                        try {
                            mediaPlayer.setDataSource(urlSelected);
                            mediaPlayer.setDisplay(surfaceHolder);
                            mediaPlayer.prepare();
                            mediaPlayer.start();
                        } catch (IOException e) {
                            e.printStackTrace();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                }

                @Override
                public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {

                }

                @Override
                public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                    Log.d(TAG, "First surface destroyed!");
                }
            });
        }  catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
