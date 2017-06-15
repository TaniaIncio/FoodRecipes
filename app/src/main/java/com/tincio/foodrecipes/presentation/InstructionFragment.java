package com.tincio.foodrecipes.presentation;


import android.app.Fragment;
import android.arch.lifecycle.LifecycleFragment;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.squareup.picasso.Picasso;
import com.tincio.foodrecipes.R;
import com.tincio.foodrecipes.data.model.StepRecipe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class InstructionFragment extends LifecycleFragment implements View.OnClickListener{//}, SurfaceHolder.Callback{


    public static String TAG = InstructionFragment.class.getSimpleName();
    /***VIEWS**/
    private TextView txtDescription;
    private Button btnPrevious;
    private Button btnNext;
    LinearLayout linearButtons;
    ImageView imgStep;
    SimpleExoPlayerView exoplayer;
    SimpleExoPlayer player;
    TrackSelector trackSelector;
    /***CONSTANTS**/
    protected static String ID = "ID";
    protected static String URL = "URL";
    protected static String DESCRIPTION = "DESCRIPTION";
    protected static String LIST_STEP = "LIST_STEP";
    protected static String URL_IMAGE = "URL_IMAGE";
    //player
    /**VARIABLES***/
    List<StepRecipe> listStep;
    int idSelected;
    String urlSelected;
    String urlImage;

    public InstructionFragment() {
        // Required empty public constructor
    }

    public static InstructionFragment newInstance(String description, String url, int id, String urlImage,List<StepRecipe> listStep){
        InstructionFragment fragment = new InstructionFragment();
        Bundle bundle = new Bundle();
        bundle.putString(DESCRIPTION, description);
        bundle.putString(URL, url);
        bundle.putString(URL_IMAGE, urlImage);
        bundle.putInt(ID, id);
        bundle.putParcelableArrayList(LIST_STEP,(ArrayList)listStep);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_instruction, container, false);
        txtDescription = (TextView)view.findViewById(R.id.txt_description_instruction);
        btnPrevious = (Button)view.findViewById(R.id.btn_previous);
        btnNext = (Button)view.findViewById(R.id.btn_next);
        this.imgStep = (ImageView)view.findViewById(R.id.img_step);
        this.linearButtons = (LinearLayout)view.findViewById(R.id.linear_buttons);
        this.exoplayer = (SimpleExoPlayerView)view.findViewById(R.id.exoplayer_video);
        this.checkStateVideo();
        return view;
    }

    void setData(){
        this.txtDescription.setText(getArguments().getString(DESCRIPTION));
        this.listStep = getArguments().getParcelableArrayList(LIST_STEP);
        this.idSelected = getArguments().getInt(ID);
        this.urlSelected = getArguments().getString(URL);
        this.urlImage = getArguments().getString(URL_IMAGE);
        this.checkButtons();
        this.checkImageOrVideo();

    }

    void initVideoStreaming(){
        // 1. Create a default TrackSelector
        Handler mainHandler = new Handler();
        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        TrackSelection.Factory videoTrackSelectionFactory =
                new AdaptiveTrackSelection.Factory(bandwidthMeter);
        trackSelector =
                new DefaultTrackSelector(videoTrackSelectionFactory);

// 2. Create the player
         player =
                ExoPlayerFactory.newSimpleInstance(getActivity(), trackSelector);
    //setear
        this.exoplayer.setPlayer(player);
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(getContext(),
                Util.getUserAgent(getContext(), "FoodRecipes"));
        ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
        MediaSource videoSource = new ExtractorMediaSource(Uri.parse(this.urlSelected),
                dataSourceFactory, extractorsFactory, null, null);
        player.prepare(videoSource);
        player.setPlayWhenReady(true);
    }


    @Override
    public void onPause() {
        super.onPause();
        if (Util.SDK_INT <= 23) {
            releasePlayer();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (Util.SDK_INT > 23) {
            releasePlayer();
        }
    }

    private void releasePlayer() {
        if (player != null) {
            player.release();
            player = null;
            trackSelector = null;
        }
    }

    void setImage(String url){
        Picasso.with(getActivity()).load(url).into(this.imgStep);
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

    private void checkImageOrVideo(){
        if(this.urlSelected.isEmpty()){
            this.imgStep.setVisibility(View.VISIBLE);
            this.exoplayer.setVisibility(View.GONE);
            if (this.urlImage.isEmpty() == false)
                this.setImage(this.urlImage);
        }else{
            this.imgStep.setVisibility(View.GONE);
            this.exoplayer.setVisibility(View.VISIBLE);
            this.reproduceVideo();
        }
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
        //    this.checkOrientation();
        }
       // this.initVideoStreaming();
    }



    private void checkOrientation(){
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
        {
            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, (int)getResources().getDimension(R.dimen.height_surface_video));
            this.exoplayer.setLayoutParams(lp);
        } else {
            // Landscape Mode
            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
            this.exoplayer.setLayoutParams(lp);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_next:
                this.checkStateVideo();
                setDataView(this.listStep.get(this.idSelected+1));
                break;
            case R.id.btn_previous:
                this.checkStateVideo();
                setDataView(this.listStep.get(this.idSelected-1));
                break;
        }
    }

    private void checkStateVideo(){
        releasePlayer();
    }
    protected void setDataView(StepRecipe step){
        this.txtDescription.setText(step.getInstruction());
        this.idSelected = step.getId();
        this.urlSelected = step.getUrlPlayer();
        this.urlImage = step.getImage();
        this.checkImageOrVideo();
        this.checkVideo(step.getUrlPlayer());
        //this.reproduceVideo();
        this.checkButtons();

    }

    private void checkVideo(String url){
        if(url.isEmpty())
            this.exoplayer.setVisibility(View.GONE);
        else
            this.exoplayer.setVisibility(View.VISIBLE);
    }

    private void reproduceVideo() {
        this.initVideoStreaming();
       /* if (urlSelected != null && urlSelected.isEmpty() == false) {
            mediaPlayer = new MediaPlayer();
            try {
                mediaPlayer.setDataSource(urlSelected);
                mediaPlayer.prepareAsync();
                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        System.out.println("video ready");
                        mediaPlayer.start();
                    }
                });

            } catch (IOException e) {
                e.printStackTrace();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }*/
    }
}
