package com.example.tinker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.Toast
import com.tencent.tinker.lib.tinker.Tinker
import com.tencent.tinker.lib.tinker.TinkerInstaller
import java.io.File

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var path:String = Environment.getExternalStorageDirectory().absolutePath+"/patch_signed_7zip.apk"
        var file: File = File(path)
        if(file.exists()){
            TinkerInstaller.onReceiveUpgradePatch(applicationContext,path)
        }
    }


}
