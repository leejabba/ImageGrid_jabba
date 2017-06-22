package kr.heythisway.imagegrid_jabba;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

/**
 * 이미지 아답터 설정
 * Created by SMARTHINK_MBL13 on 2017. 6. 22..
 */

class ImageAdapter extends BaseAdapter {

    Context context;
    Cursor cursor;

    public ImageAdapter(Context context, Cursor cursor) {
        this.context = context;
        this.cursor = cursor;
    }

    @Override
    public int getCount() {
        return cursor.getCount();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(context);
        } else {
            imageView = (ImageView) convertView;
        }

        cursor.moveToPosition(position);
        Uri uri = Uri.withAppendedPath(MainActivity.THUMBNAILS, cursor.getString(cursor.getColumnIndex(MediaStore.Images.Thumbnails._ID)));

        imageView.setImageURI(uri);
        imageView.setAdjustViewBounds(true);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        return imageView;
    }
}
