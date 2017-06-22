package kr.heythisway.imagegrid_jabba;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

public class MainActivity extends AppCompatActivity {
    GridView imageGrid;
    // 자주 사용하는 Uri 값을 스태틱 상수로 설정
    final static Uri THUMBNAILS = MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageGrid = (GridView) findViewById(R.id.imageGrid);
        final Cursor cursor = getImageCursor();

        // 커스텀 어댑터 연결
        ImageAdapter adapter = new ImageAdapter(this, cursor);
        imageGrid.setAdapter(adapter);

        // 그리드뷰에서 아이템을 클릭할 때 실행되는 리스너
        imageGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 어댑터 포지션 값을 가지는 곳을 커서 위치를 옮김
                cursor.moveToPosition(position);
                // 해당 경로를 문자열 타입으로 저장
                String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA));
                // 경로값을 담아 인텐트를 보냄
                Intent intent = new Intent(getBaseContext(), ImageGridFull.class);
                intent.putExtra("path", path);
                startActivity(intent);
            }
        });
    }

    /**
     * 이미지 데이터를 가지는 Cursor 구하는 메서드
     * @return
     */
    private Cursor getImageCursor() {
        ContentResolver cr = getContentResolver();
        Cursor cursor = cr.query(THUMBNAILS, null, null, null, null);
        return cursor;
    }
}
