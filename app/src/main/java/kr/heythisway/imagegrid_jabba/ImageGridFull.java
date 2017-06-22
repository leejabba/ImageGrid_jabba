package kr.heythisway.imagegrid_jabba;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import java.io.File;

public class ImageGridFull extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_grid_full);

        ImageView fullImage = (ImageView) findViewById(R.id.fullImage);

        // intent에서 값 가져와 path 문자열 타입으로 담아두기
        Intent intent = getIntent();
        if (intent != null) {
            String path = intent.getExtras().getString("path");
            // 경로를 통해 비트맵 이미지 이미지뷰에 뿌려주기
            File file = new File(path);
            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
            fullImage.setImageBitmap(bitmap);
        } else {
            Log.e("e", "============ 인텐트를 가져오지 못했습니다.");
        }
    }
}
