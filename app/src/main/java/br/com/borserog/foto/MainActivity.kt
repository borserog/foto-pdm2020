package br.com.borserog.foto

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat.startActivity

class MainActivity : AppCompatActivity() {
    private lateinit var imgView: ImageView
    private val REQUEST_IMAGE_CAPTURE = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        Ao tocar no ImageView (OnClickListener), seu aplicativo deverá chamar a câmera esperando uma
//        resposta (forResult), que é a foto capturada.
        this.imgView = findViewById(R.id.imgViewer);
        this.imgView.setOnClickListener { capturePhoto(it) }

    }

    private fun capturePhoto(view: View) {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        if (intent.resolveActivity(packageManager) != null) {
            startActivityForResult(intent, this.REQUEST_IMAGE_CAPTURE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            val thumbnail: Bitmap? = data?.getParcelableExtra("data")
            this.imgView.setImageBitmap(thumbnail);
        }
    }
}