package com.example.tt_logging;

<<<<<<< HEAD
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

=======
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
>>>>>>> origin/master
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
<<<<<<< HEAD
import com.squareup.picasso.Picasso;

public class Add_Tratamiento extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1;
    private Button mButtonElegirImagen;
    private Button mButtonSubir;
    private TextView mTextViewMostrarSubidas;
    private EditText mEditTextFechaImagen;
    private ImageView mImageView;
    private ProgressBar mProgressBar;
    private Uri mImageUri;
    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;

=======
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

import id.zelory.compressor.Compressor;

public class Add_Tratamiento extends AppCompatActivity {
    Button subir,seleccionar;
    ImageView foto;
    DatabaseReference imgref;
    StorageReference storageReference;
    ProgressDialog cargando;

    Bitmap thumb_bitmap = null;
>>>>>>> origin/master
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__tratamiento);
<<<<<<< HEAD

        mButtonElegirImagen=findViewById(R.id.button_elegirArchivo);
        mButtonSubir=findViewById(R.id.subir_foto);
        mTextViewMostrarSubidas=findViewById(R.id.text_view_mostrar_recetas);
        mEditTextFechaImagen=findViewById(R.id.editar_nombre_foto);
        mImageView=findViewById(R.id.image_view);
        mProgressBar=findViewById(R.id.progress_bar);
        mStorageRef = FirebaseStorage.getInstance().getReference("uploads");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("uploads");


        //Configuracion del boton de elegir imagen
        mButtonElegirImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });
        //Configuracion del boton para subir la imagen
        mButtonSubir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadFile();
            }
        });

        //Boton mostrar recetas
        mTextViewMostrarSubidas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }//Fin del oncreate

    private void openFileChooser()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
        && data != null && data .getData() != null)
        {
            mImageUri = data.getData();
            Picasso.with(this).load(mImageUri).into(mImageView);
            System.out.println("ya quedo");

        }
    }
    private String getFileExtension(Uri uri)
    {
        ContentResolver cR= getContentResolver();
        MimeTypeMap mime
    }
    private void uploadFile()
    {

=======
        foto=findViewById(R.id.img_foto);
        seleccionar=findViewById(R.id.btn_selefoto);
        subir=findViewById(R.id.btn_subirfoto);
        imgref= FirebaseDatabase.getInstance().getReference().child("Fotos_Subidas");
        storageReference = FirebaseStorage.getInstance().getReference().child("img_comprimidas");
        cargando = new ProgressDialog(this);

        seleccionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CropImage.startPickImageActivity(Add_Tratamiento.this);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE && resultCode == Activity.RESULT_OK)
        {
            Uri imageuri =CropImage.getPickImageResultUri(this,data);

            //Recortar Imagen
            CropImage.activity(imageuri).setGuidelines(CropImageView.Guidelines.ON).setRequestedSize(640,480)
                    .setAspectRatio(2,1).start(Add_Tratamiento.this);
        }
        if(requestCode==CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE)
        {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode==RESULT_OK)
            {
                Uri resultUri = result.getUri();
                File url = new File(resultUri.getPath());
                Picasso.with(this).load(url).into(foto);
                //Comprimiendo Imagen

                try{
                    thumb_bitmap = new Compressor(this)
                            .setMaxWidth(640).setMaxHeight(480)
                            .setQuality(90).compressToBitmap(url);
                }catch (IOException e)
                {
                    e.printStackTrace();
                }

                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                thumb_bitmap.compress(Bitmap.CompressFormat.JPEG,90,byteArrayOutputStream);
                final byte [] thumb_byte = byteArrayOutputStream.toByteArray();

                //fin del compresor...

                int p=(int) (Math.random() * 8+1);
                int s=(int) (Math.random() * 8+1);
                int  c=(int) (Math.random() * 8+1);
                int numero1=(int) (Math.random() * 1012+2111);
                int numero2=(int) (Math.random() * 1012+2111);

                String[] elementos = {"a","b","c","d","e","f","g","h","i"};

                final String aleatorio = elementos[p]+elementos[s]+numero1+elementos[c]+numero2+"comprimido.jpg";

                subir.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        cargando.setTitle("Subiendo foto...");
                        cargando.setMessage("Espere por favor..");
                        cargando.show();

                        final StorageReference ref = storageReference.child(aleatorio);
                        UploadTask uploadTask = ref.putBytes(thumb_byte);

                        //Subir imagen en Storage
                        Task<Uri> uriTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                            @Override
                            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                                if(!task.isSuccessful()){
                                    throw Objects.requireNonNull(task.getException());
                                }
                                return ref.getDownloadUrl();
                            }
                        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                            @Override
                            public void onComplete(@NonNull Task<Uri> task) {
                                Uri downloaduri=task.getResult();
                                imgref.push().child("urlfoto").setValue(downloaduri.toString());
                                cargando.dismiss();

                                Toast.makeText(Add_Tratamiento.this,"Imagen cargada con exito",Toast.LENGTH_SHORT).show();

                            }
                        });

                    }
                });


            }
        }
>>>>>>> origin/master
    }
}