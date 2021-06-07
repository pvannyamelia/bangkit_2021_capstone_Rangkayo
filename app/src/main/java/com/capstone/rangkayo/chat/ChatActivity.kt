package com.capstone.rangkayo.chat

import ai.api.android.AIService
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View.*
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.EditText
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.capstone.rangkayo.R
import com.capstone.rangkayo.databinding.ActivityChatActivtyBinding
import com.google.firebase.database.DatabaseReference


class ChatActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var editText: EditText
    lateinit var addBtn: RelativeLayout
    lateinit var ref: DatabaseReference
    var flagFab = true
    lateinit var  aiService: AIService
    lateinit var binding: ActivityChatActivtyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityChatActivtyBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                val img = BitmapFactory.decodeResource(resources, R.drawable.ic_send_white_24dp)
                val img1 = BitmapFactory.decodeResource(resources, R.drawable.ic_mic_white_24dp)
                if (s.toString().trim { it <= ' ' }.isNotEmpty() && flagFab) {
                    imageViewAnimatedChange(this@ChatActivity, binding.fabImg, img)
                    flagFab = false
                } else if (s.toString().trim { it <= ' ' }.isEmpty()) {
                    imageViewAnimatedChange(this@ChatActivity, binding.fabImg, img1)
                    flagFab = true
                }
            }

            override fun afterTextChanged(s: Editable) {}
        })

    }

    fun imageViewAnimatedChange(c: Context?, v: ImageView, new_image: Bitmap?) {
        val animOut = AnimationUtils.loadAnimation(c, R.anim.zoom_out)
        val animIn = AnimationUtils.loadAnimation(c, R.anim.zoom_in)
        animOut.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {}
            override fun onAnimationRepeat(animation: Animation) {}
            override fun onAnimationEnd(animation: Animation) {
                v.setImageBitmap(new_image)
                animIn.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(animation: Animation) {}
                    override fun onAnimationRepeat(animation: Animation) {}
                    override fun onAnimationEnd(animation: Animation) {}
                })
                v.startAnimation(animIn)
            }
        })
        v.startAnimation(animOut)
    }
}