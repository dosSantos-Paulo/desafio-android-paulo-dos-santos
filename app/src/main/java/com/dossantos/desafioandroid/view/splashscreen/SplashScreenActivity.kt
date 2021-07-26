package com.dossantos.desafioandroid.view.splashscreen

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.dossantos.desafioandroid.R

class SplashScreenActivity : AppCompatActivity() {

    val comic1 by lazy {findViewById<ImageView>(R.id.comic_1)}
    val comic2 by lazy {findViewById<ImageView>(R.id.comic_2)}
    val comic3 by lazy {findViewById<ImageView>(R.id.comic_3)}
    val comic4 by lazy {findViewById<ImageView>(R.id.comic_4)}
    val comic5 by lazy {findViewById<ImageView>(R.id.comic_5)}
    val comic6 by lazy {findViewById<ImageView>(R.id.comic_6)}
    val marvelLogo by lazy { findViewById<ImageView>(R.id.marvel_logo) }
    val hqLogo by lazy { findViewById<TextView>(R.id.hq) }
    val progressBar by lazy { findViewById<ProgressBar>(R.id.progress) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        initAnimation()
        Handler(Looper.getMainLooper()).postDelayed({

        }, 5000)
    }


    private fun initAnimation(){
        marvelLogo.alpha = 0f
        comic1.animate().apply {
            alpha(1f)
            duration = 500
        }.withEndAction {
            comic6.animate().apply {
                alpha(1f)
                duration = 500
            }.withEndAction {
                comic5.animate().apply {
                    alpha(1f)
                    duration = 500
                }.withEndAction {
                    comic2.animate().apply {
                        alpha(1f)
                        duration = 500
                    }.withEndAction {
                        comic3.animate().apply {
                            alpha(1f)
                            duration = 500
                        }.withEndAction {
                            comic4.animate().apply {
                                alpha(1f)
                                duration = 500
                            }.withEndAction {
                                marvelLogo.animate().apply {
                                    alpha(1f)
                                    duration = 1000
                                }.withEndAction {
                                    hqLogo.animate().apply {
                                        alpha(1f)
                                        duration = 1000
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        progressBar.animate().apply {
            alpha(1f)
            duration = 4000
        }
    }

}