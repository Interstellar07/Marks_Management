package com.zaidisam.to_do;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.document.FirebaseVisionDocumentTextRecognizer;
import com.google.firebase.ml.vision.text.FirebaseVisionText;
import com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer;
import com.zaidisam.to_do.R;

import java.util.List;

public class Scan_activity extends AppCompatActivity {
    private Button capture,convert;
    ImageView photo;
    TextView scannedtext;
    Bitmap imageBitmap;
    static final int REQUEST_IMAGE_CAPTURE =1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        capture = findViewById(R.id.captureimage);
        convert = findViewById(R.id.showtext);
        photo = findViewById(R.id.imageview);
        scannedtext = findViewById(R.id.scannedtxt);

        capture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();

            }
        });
        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detecttextfromImage();
            }
        });

    }



    private void dispatchTakePictureIntent()
    {
        Intent takepicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(takepicture.resolveActivity(getPackageManager())!=null){
            startActivityForResult(takepicture,REQUEST_IMAGE_CAPTURE);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            photo.setImageBitmap(imageBitmap);
        }
    }
    private void detecttextfromImage() {
        FirebaseVisionImage firebaseVisionImage = FirebaseVisionImage.fromBitmap(imageBitmap);
        FirebaseVisionTextRecognizer firebaseVisionTextRecognizer = FirebaseVision.getInstance().getOnDeviceTextRecognizer();
        firebaseVisionTextRecognizer.processImage(firebaseVisionImage).addOnSuccessListener(new OnSuccessListener<FirebaseVisionText>() {
            @Override
            public void onSuccess(FirebaseVisionText firebaseVisionText) {
                List<FirebaseVisionText.TextBlock> blockList = firebaseVisionText.getTextBlocks();
                for(FirebaseVisionText.TextBlock block: firebaseVisionText.getTextBlocks())
                {
                 scannedtext.setText("Scanned Answers Codes: "+block.getText());
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                System.out.println("Error"+ e.getMessage());
                Toast.makeText(Scan_activity.this,"Error"+ e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });


    }
}