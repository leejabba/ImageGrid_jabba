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
    final static Uri THUMBNAILS = MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageGrid = (GridView) findViewById(R.id.imageGrid);
        final Cursor cursor = getImageCursor();

        ImageAdapter adapter = new ImageAdapter(this, cursor);
        imageGrid.setAdapter(adapter);

        imageGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cursor.moveToPosition(position);
                String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA));
                Intent intent = new Intent(getBaseContext(), ImageGridFull.class);
                intent.putExtra("path", path);
                startActivity(intent);
            }
        });
    }

    // 이미지 주소 가져오기
    private Cursor getImageCursor() {
        ContentResolver cr = getContentResolver();
        Cursor cursor = cr.query(THUMBNAILS, null, null, null, null);
        return cursor;
    }
}
