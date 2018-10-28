package com.nanotrick.ordershipper.Common;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.location.Location;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.database.FirebaseDatabase;
import com.nanotrick.ordershipper.Model.Request;
import com.nanotrick.ordershipper.Model.Shipper;
import com.nanotrick.ordershipper.Model.ShippingInformation;
import com.nanotrick.ordershipper.Remote.IGeoCoordinates;
import com.nanotrick.ordershipper.Remote.RetrofitClient;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


public class Common {
    public static final String SHIPPER_TABLE = "Shippers";
    public static final String ORDER_NEED_SHIP_TABLE = "OrdersNeedShip";
    public static final String SHIPPER_INFO_TABLE = "ShippingOrders";

    public static  Request currentRequest;
    public static  String currentKey;

    public static Shipper currentShipper;

    public static final int LOCATION_REQUEST_CODE = 1000;
    private static final String BASE_URL = "https://maps.googleapis.com";


    public static String convertCodeToStatus(String code) {
        if (code.equals("0"))
            return "placed";
        else if (code.equals("1"))
            return "on my way";
        else if (code.equals("2"))
            return "Shipping";
        else
            return "Shipped";

    }


    public static String getDate(long time) {
        Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
        calendar.setTimeInMillis(time);
        StringBuilder data = new StringBuilder(
                android.text.format.DateFormat.format("dd-MM-yyyy HH:mm",
                        calendar)
                        .toString());


        return data.toString();
    }

    public static void createShippingOrder(String key, String phone, Location mLastLocation) {

        ShippingInformation shippingInformation = new ShippingInformation();
        shippingInformation.setOrderId(key);
        shippingInformation.setShipperPhone(phone);
        shippingInformation.setLat(mLastLocation.getLatitude());
        shippingInformation.setLng(mLastLocation.getLongitude());


        //Create new item on ShipperInformation table
        FirebaseDatabase.getInstance()
                .getReference(SHIPPER_INFO_TABLE)
                .child(key)
                .setValue(shippingInformation)
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("ERROR",e.getMessage());
                    }
                });



    }

    public static void updateShippingInformation(String currentKey, Location mLastLocation) {
        Map<String , Object> update_location = new HashMap<>();
        update_location.put("lat",mLastLocation.getLatitude());
        update_location.put("lng",mLastLocation.getLongitude());

        FirebaseDatabase.getInstance()
                .getReference(SHIPPER_INFO_TABLE)
                .child(currentKey)
                .updateChildren(update_location)
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("ERROR",e.getMessage());
                    }
                });
    }

    public static IGeoCoordinates getGeoCodeService() {
        return RetrofitClient.getClient(BASE_URL).create(IGeoCoordinates.class);
    }

    public static Bitmap scaleBitmap(Bitmap bitmap, int newWidth, int newHeight) {
        Bitmap scaledBitmap = Bitmap.createBitmap(newWidth, newHeight, Bitmap.Config.ARGB_8888);

        float scaleX = newWidth / (float) bitmap.getWidth();
        float scaleY = newHeight / (float) bitmap.getHeight();
        float pivotX = 0, pivotY = 0;

        Matrix scaleMatrix = new Matrix();
        scaleMatrix.setScale(scaleX, scaleY, pivotX, pivotY);

        Canvas canvas = new Canvas(scaledBitmap);
        canvas.setMatrix(scaleMatrix);
        canvas.drawBitmap(bitmap, 0, 0, new Paint(Paint.FILTER_BITMAP_FLAG));

        return scaledBitmap;
    }
}
