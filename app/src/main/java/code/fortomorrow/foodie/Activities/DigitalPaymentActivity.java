package code.fortomorrow.foodie.Activities;

import android.Manifest;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.Result;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import code.fortomorrow.foodie.R;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class DigitalPaymentActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    ZXingScannerView zXingScannerView;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_digital_payment);
        zXingScannerView = findViewById(R.id.zxscan);
        textView = findViewById(R.id.text_result);

        Dexter.withActivity(this)
                .withPermission(Manifest.permission.CAMERA)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        zXingScannerView.setResultHandler(DigitalPaymentActivity.this);
                        zXingScannerView.startCamera();

                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                        Toast.makeText(DigitalPaymentActivity.this, "Please accept the request",Toast.LENGTH_LONG);
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {

                    }
                }).check();

    }


    @Override
    public void handleResult(Result rawResult) {
        textView.setText(rawResult.getText());
        zXingScannerView.startCamera();
    }

    @Override
    protected void onResume() {
        super.onResume();
        zXingScannerView.setResultHandler(DigitalPaymentActivity.this);
    }

    @Override
    protected void onDestroy() {
        zXingScannerView.stopCamera();
        super.onDestroy();
    }
}