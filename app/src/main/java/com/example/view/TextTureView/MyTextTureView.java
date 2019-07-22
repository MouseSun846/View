package com.example.view.TextTureView;

import android.graphics.SurfaceTexture;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.example.view.MainActivity;
import com.example.view.R;

public class MyTextTureView extends AppCompatActivity {
   private final String Tag = MainActivity.class.getSimpleName();
   private MediaPlayer mediaPlayer;
   private ImageView videoImage;
   private SeekBar seekBar;
   private Surface msurface;
   private Handler handler = new Handler();
   private final Runnable mTicker = new Runnable() {
       @Override
       public void run() {
        long now = SystemClock.uptimeMillis();
        long next = now + (1000 - now % 1000);
        handler.postAtTime(mTicker,next);
        //延迟一秒再执行Runnable，与 计时器效果一样
           if (mediaPlayer !=null && mediaPlayer.isPlaying()){
               seekBar.setProgress(mediaPlayer.getCurrentPosition());
           }
       }
   };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_text_ture_view);
        TextureView textureView = findViewById(R.id.textureview);
        textureView.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {
            @Override
            public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
                msurface = new Surface(surface);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            mediaPlayer = new MediaPlayer();
                            Uri uri = Uri.parse("https://vd2.bdstatic.com/mda-jggq05bjke7msj41/sc/mda-jggq05bjke7msj41.mp4?auth_key=1563540876-0-0-61fd7978ef292b4917861512a99a4b50&bcevod_channel=searchbox_feed&pd=bjh&abtest=all");
                            //设置播放源
                            mediaPlayer.setDataSource(MyTextTureView.this,uri);
                            //设置渲染画板
                            mediaPlayer.setSurface(msurface);
                            //设置播放类型
                            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                            //播放完成监听
                            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    //播放完成
                                    videoImage.setVisibility(View.VISIBLE);
                                    seekBar.setProgress(0);
                                    handler.removeCallbacks(mTicker);
                                }
                            });
                            //播放前准备
                            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                                @Override
                                public void onPrepared(MediaPlayer mp) {
                                    //预加载完成
                                    videoImage.setVisibility(View.GONE);
                                    mediaPlayer.start();
                                    //设置播放总进度
                                    seekBar.setMax(mediaPlayer.getDuration());

                                }
                            });
                            mediaPlayer.prepare();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }).start();
                handler.post(mTicker);
            }

            @Override
            public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
                //尺寸改变

            }

            @Override
            public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
                //销毁
                msurface = null;
                mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer = null;
                return true;
            }

            @Override
            public void onSurfaceTextureUpdated(SurfaceTexture surface) {

            }
        });
        videoImage = findViewById(R.id.video_image);
        seekBar = findViewById(R.id.seek_bar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                    //开始拖动seekbar
                Log.i("mouse","onStartTrackingTouch");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (mediaPlayer !=null && mediaPlayer.isPlaying()){
                    mediaPlayer.seekTo(seekBar.getProgress());
                }
            }
        });
    }
}
